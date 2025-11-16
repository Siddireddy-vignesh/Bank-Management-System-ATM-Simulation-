import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Windows Authentication (Integrated Security)
            String url = "jdbc:sqlserver://localhost:1433;"
                       + "databaseName=master;"
                       + "integratedSecurity=true;"
                       + "encrypt=false;";

            conn = DriverManager.getConnection(url);

        } catch (Exception e) {
            System.out.println("❌ Database Connection Failed: " + e.getMessage());
        }
        return conn;
    }
}
