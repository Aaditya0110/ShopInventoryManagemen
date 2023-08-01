
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
class ConnectionManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/shop";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "aadiskcet123";

    public static Statement getConnection()throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            Statement st = con.createStatement();
             return st;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

     public static Connection getStatement() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
