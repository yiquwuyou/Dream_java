import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class TestSQLite {
    public static void main(String[] args) throws SQLException {
//        testInsert();
        testSelect();
    }

    private static void testInsert() throws SQLException {
        // 往 SQLite 中插入数据
        // 1、创建数据源.DataSource
        DataSource dataSource = new SQLiteDataSource();
        ((SQLiteDataSource)dataSource).setUrl("jdbc:sqlite://d:/java_everything/test.db");
        // 2、建立连接
        Connection connection = dataSource.getConnection();
        // 3、构造 SQL 语句
        //    静态
//        String sql = "insert into test values(10, '李四')";
//        PreparedStatement statement = connection.prepareStatement(sql);
        //    动态
        String sql = "insert into test values(?, ?)";
        //       通过调用 connection.prepareStatement(sql) 方法，将 SQL 语句传递给连接对象，返回一个 PreparedStatement 对象。
        //       PreparedStatement 接口是 Statement 接口的子接口，它允许我们预编译 SQL 语句，以便多次执行相同的 SQL 语句时能够提高性能。
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, 11);
        statement.setString(2, "李四");
        // 4、执行 SQL 语句
        statement.executeUpdate();
        // 5、释放资源
        statement.close();
        connection.close();
    }

    private static void testSelect() throws SQLException {
        // 从 SQLite 中进行查询
        // 1、创建数据源 DataSource
        DataSource dataSource = new SQLiteDataSource();
        ((SQLiteDataSource)dataSource).setUrl("jdbc:sqlite://d:/java_everything/test.db");
        // 2、建立连接
        Connection connection = dataSource.getConnection();
        // 3、构造 SQL 语句
        String sql = "select * from test";
        PreparedStatement statement = connection.prepareStatement(sql);
        // 4、执行 SQL 语句
        ResultSet resultSet = statement.executeQuery();
        // 5、遍历结果集合
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            System.out.println("id" + id + ", name" + name);
        }
        // 6、释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }

}
