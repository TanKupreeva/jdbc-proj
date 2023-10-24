import java.sql.*;

public class JDBCTester {
    public static void main(String[] args) {

        String user = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:8889/users_db";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users_db.employees")
        ) {

            if (!conn.isClosed()) {
                System.out.println("Connection is active " + conn);

            }

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString(2);
                String lName = rs.getString(3);
                Timestamp created = rs.getTimestamp("created_ts");

                System.out.println(String.format("ROW: id:%d, name:%s, last name:%s . Created at %s"
                        , id, name, lName, created));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}