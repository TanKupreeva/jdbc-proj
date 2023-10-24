package util;

import java.sql.*;

public class DBUtils {
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "root";
    public static final String DB_URL = "jdbc:mysql://localhost:8889/users_db";

    private DBUtils() {

    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void release(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (conn != null) {
                System.out.println("Connection " + conn + " is closed");
                conn.close();
            }
            if (stmt != null) {
                System.out.println("Statement " + stmt + " is closed");
                stmt.close();
            }
            if (rs != null) {
                System.out.println("Result set " + rs + " is closed");
                rs.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
