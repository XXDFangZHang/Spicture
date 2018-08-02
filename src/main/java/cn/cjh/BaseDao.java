package cn.cjh;

import java.sql.*;


public class BaseDao {
    protected  static Connection connection = null;
    protected  static PreparedStatement ps = null;
    protected static ResultSet rs = null;


    static {
        try {
            // 001.加载驱动
            Class.forName(ConfigManager.getValue("jdbc.driver"));
            // 002.连接数据库
            connection = DriverManager.getConnection(
                    ConfigManager.getValue("jdbc.url"),
                    ConfigManager.getValue("jdbc.userName"),
                    ConfigManager.getValue("jdbc.password"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public int executeUpdate(String sql, Object... params) {
        int rowNum = 0; // 影响的行数
            // 创建执行sql的对象
            try {
                ps = connection.prepareStatement(sql);
                if (params != null) { // 有参数？ 有几个呀？
                    for (int i = 0; i < params.length; i++) {
                        ps.setObject(i + 1, params[i]);
                    }
                }
                rowNum = ps.executeUpdate();// 没有参数的
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return rowNum;
    }


    public ResultSet executeQuery(String sql, Object... params) {
            // 创建执行sql的对象
            try {
                ps = connection.prepareStatement(sql);
                if (params != null) { // 有参数？ 有几个呀？
                    for (int i = 0; i < params.length; i++) {
                        ps.setObject(i + 1, params[i]);
                    }
                }
                rs = ps.executeQuery();// 没有参数的
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return rs;
    }

}