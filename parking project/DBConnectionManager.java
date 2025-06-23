package com.management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

public class DBConnectionManager {

    private static Connection connection;

    public static Connection getConnection() throws SQLException, IOException, ClassNotFoundException {
        if (connection == null || connection.isClosed()) {
            Properties props = new Properties();

            InputStream input = DBConnectionManager.class.getClassLoader().getResourceAsStream("database.properties");
            if (input == null) {
                throw new FileNotFoundException("database.properties file not found in classpath.");
            }
            props.load(input);

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");
            String driver = props.getProperty("db.driver");

            if (driver == null) {
                throw new NullPointerException("JDBC driver not specified. Check 'db.driver' key in properties file.");
            }
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }
}
