package console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private final String url;
    private final String username;
    private final String password;
//класс для подключения к бд
    public DatabaseConnection() {
        try {
            Properties properties = PropertiesProvider.getAppProperties();
            this.url = properties.getProperty("datasource.url");
            this.username = properties.getProperty("datasource.username");
            this.password = properties.getProperty("datasource.password");
        } catch (Exception e) {
            throw new RuntimeException("Error loading database properties", e);
        }
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
