package dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// 通过这个类来封装针对 file_meta 表的操作
public class FileDao {
    // 1、初始化数据库（自动建表）
    public void initDB() {
        // 1）  先能够读取到 db.sql 中的 SQL 语句
        // 2）  根据 SQL 语句调用 jdbc 执行操作
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DButil.getConnection();
            statement = connection.createStatement();
            String[] sqls = getInitSQL();
            for (String sql : sqls) {
                System.out.println("[initDB] sql" + sql);
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.close(connection, statement, null);
        }
    }

    // 从 db.sql 中读取文件内容
    // 虽然当前 db.sql 中只有一个 SQL 语句，但是原则上写了多个也是要应该支持的
    // 返回值就设定成一个 String[] 每个元素都是一个独立的 SQL 语句了
    private String[] getInitSQL() {
        // 用这个 StringBuilder 来存储最终结果
        StringBuilder stringBuilder = new StringBuilder();
        // 此处需要能够动态的获取到 db.sql 文件的路径，而不要直接写死一个绝对路径
        try(InputStream inputStream = FileDao.class.getClassLoader().getResourceAsStream("db.sql")) {
            // 将字节流转换为字符流 因为db.sql是一个文本文件
            try(InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf8")) {
                while (true) {
                    // 注意，此处的 ch 返回值，虽然是 int ，但实际上是一个 char
                    // 只不过为了表示 -1 这个特殊的结束标记， 使用 int 类型作为返回值
                    // 当文件读取完毕后，read就会读取到-1
                    int ch = inputStreamReader.read();
                    if (ch == -1) {
                        // 文件读取完毕
                        break;
                    }
                    stringBuilder.append((char)ch);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        // 返回字符串数组，按 分号 分割
        // 使用 ; 来进行切分，把整个文件内容切分成若干个SQl
        // 后续使用jdbc执行的时候，是需要保证一次执行一个SQL的
        return stringBuilder.toString().split(";");
    }

    // 2、插入文件/目录数据到数据库中
    //        这里提供一个 “批量插入” 的方法
    // 此处进行了 “锁粗化”      将每个 sql语句 对应一个事务变成了所有sql对应一个事务
    public void add(List<FileMeta> fileMetas) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DButil.getConnection();
            // 关闭连接的自动提交功能
            connection.setAutoCommit(false);
            String sql = "insert into file_meta values(null, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            for (FileMeta fileMeta : fileMetas) {
                // 针对当前 FileMeta 对象， 替换到 SQL 语句中，把 ? 替换成具体数值
                preparedStatement.setString(1, fileMeta.getName());
                preparedStatement.setString(2, fileMeta.getPath());
                preparedStatement.setBoolean(3, fileMeta.isDirectory());
                preparedStatement.setString(4, fileMeta.getPinyin());
                preparedStatement.setString(5, fileMeta.getPinyinFirst());
                preparedStatement.setLong(6, fileMeta.getSize());
                preparedStatement.setTimestamp(7, new Timestamp(fileMeta.getLastModified()));
                // 使用 addBatch，把这个构造好的片段，累计起来
                // addBatch 会把已经构造好的 SQL 保存好，同时又会允许重新构造一个新的 SQL 出来
                preparedStatement.addBatch();
                System.out.println("[insert] 插入文件: " + fileMeta.getPath() + File.separator + fileMeta.getName());
            }
            // 执行所有的 sql 片段了
            preparedStatement.executeBatch();
            // 执行完毕之后， 通过 commit 告知数据库，添加完毕！
            connection.commit();
        } catch (SQLException e) {
            // 如果上述代码出现异常，就要进行回滚操作
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            DButil.close(connection, preparedStatement, null);
        }
    }

    // 3、按照特定的关键词进行查询
    //        这个是在实现文件搜索功能的时候，必备的部分
    //        此处查询的这个 pattern， 可能是文件名的一部分，也可能是文件名拼音的一部分，还可能是拼音首字母的一部分
    public List<FileMeta> searchByPattern(String pattern) {
        List<FileMeta> fileMetas = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DButil.getConnection();
            String sql = "select name, path, is_directory, size, last_modified from file_meta"
                    + " where name like ? or pinyin like ? or pinyin_first like ?"
                    + " order by path, name";
            statement = connection.prepareStatement(sql);
            // 假设 pattern 是 test
            // 这一段表示 name 等属性里是否包含 test
            // % 表示 0 个或任意个 任意字符
            // test前面可以有任意个字符，test后可以有任意个字符，翻译过来就是只要里面包含test就行
            statement.setString(1, "%" + pattern + "%");
            statement.setString(2, "%" + pattern + "%");
            statement.setString(3, "%" + pattern + "%");
            resultSet = statement.executeQuery();
            // 循环多次，每循环一次，就查到一行，构造成 fileMeta对象
            while (resultSet.next()) {
                // 把每一列都取出来
                String name = resultSet.getString("name");
                String path = resultSet.getString("path");
                boolean isDirectory = resultSet.getBoolean("is_directory");
                long size = resultSet.getLong("size");
                Timestamp lastModified = resultSet.getTimestamp("last_modified");
                FileMeta fileMeta = new FileMeta(name, path, isDirectory, size, lastModified.getTime());
                // 构造成 fileMeta 对象
                fileMetas.add(fileMeta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.close(connection, statement, resultSet);
        }

        return fileMetas;
    }

    // 4、给定路径，查询这个路径对应的结果。（这个路径下都有哪些文件）
    //       这个方法，会在后续重新扫描，更新数据库的时候用到
    public List<FileMeta> searchByPath(String targetPath) {
        List<FileMeta> fileMetas = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DButil.getConnection();
            String sql = "select name, path, is_directory, size, last_modified from file_meta"
                    + " where path = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, targetPath);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // 把每一列都取出来
                String name = resultSet.getString("name");
                String path = resultSet.getString("path");
                boolean isDirectory = resultSet.getBoolean("is_directory");
                long size = resultSet.getLong("size");
                Timestamp lastModified = resultSet.getTimestamp("last_modified");
                FileMeta fileMeta = new FileMeta(name, path, isDirectory, size, lastModified.getTime());
                // 构造成 fileMeta 对象
                fileMetas.add(fileMeta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.close(connection, statement, resultSet);
        }
        return fileMetas;
    }

    // 5、删除数据
    //       发现某个文件已经从磁盘上删掉了，此时就需要把表里的内容也进行更新
    //       进行删除的时候，可能当前被删除的是一个普通文件（直接删除对应的表记录就行了）
    //       也有可能删除的时候，被删除的是一个目录，此时，就需要把目录里包含的子文件/子目录也都同意删除掉
    public void delete(List<FileMeta> fileMetas) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DButil.getConnection();
            connection.setAutoCommit(false);

            // 此处构造的 SQL，要根据当前删除的内容情况，来区分对待
            for (FileMeta fileMeta : fileMetas) {
                String sql = null;
                if(!fileMeta.isDirectory()) {
                    // 针对普通文件的删除操作
                    sql = "delete from file_meta where name=? and path=?";
                } else {
                    // 针对目录的删除操作
                    // (name=? and path=?)先表示删除文件/目录本身
                    // 删除子文件
                    // 例如，当前要删除的 path 是 d:/test
                    // 此处 path like ？ 要替换成形如 'd:/test%' => 目的就是把当前这个被删除的目录下面的子文件和子目录都删掉
                    sql = "delete from file_meta where (name=? and path=?) or (path like ?)";
                }
                // 此处就不能像前面的 add 一样， 使用 addBatch 了，addBatch 前提是，sql 是一个模板
                // 把 ？ 替换成不同的值，此处 sql 是不一定相同的
                // 因此此处就需要重新构造出 statement 对象来表示这个 sql了
                statement = connection.prepareStatement(sql);
                if (!fileMeta.isDirectory()) {
                    // 普通文件，需要替换两个?
                    statement.setString(1, fileMeta.getName());
                    statement.setString(2, fileMeta.getPath());
                } else {
                    // 针对目录，需要替换三个 ?
                    statement.setString(1, fileMeta.getName());
                    statement.setString(2, fileMeta.getPath());
                    statement.setString(3,fileMeta.getPath() + File.separator + fileMeta.getName() + File.separator + "%");
                }
                // 真正执行这里的删除操作
                statement.executeUpdate();
                System.out.println("[delete] " + fileMeta.getPath() + fileMeta.getName());

                // 注意！！ 此处代码中是有多个 Statement 对象的，每个对象都得关闭一次
                statement.close();
            }
            // 告诉数据库，事务执行完毕了
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            DButil.close(connection, null, null);
        }
    }
}
