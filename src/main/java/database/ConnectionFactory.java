package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public ConnectionFactory(){

        throw new UnsupportedOperationException();
    }

    public static Connection getConnection(){

        Connection connection = null;

        String urlConnection = "jdbc:sqlite::resource:database.db";

        try {
            connection = DriverManager.getConnection(urlConnection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

}
