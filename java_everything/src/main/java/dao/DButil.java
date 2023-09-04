package dao;

import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class DButil {
    // 使用 单例模式 来提供 getDataSource
    private static volatile DataSource dataSource = null;

    public static DataSource getDataSource() {
        // 外层 if 判断是否要加实例
        if (dataSource == null) {
            synchronized (DButil.class){
                // 里层 if 判断是否要创建实例
                if (dataSource == null) {
                    dataSource = new SQLiteDataSource();
                    ((SQLiteDataSource) dataSource).setUrl("jdbc:sqlite://d:/java_everything/fileSearcher.db");
                }
            }
        }
        return dataSource;
    }

    // 建立连接
    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    // 关闭连接
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
