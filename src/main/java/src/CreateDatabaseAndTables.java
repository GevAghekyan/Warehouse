package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static src.constants.MariaDBConstants.*;

public class CreateDatabaseAndTables {

    public static void createDatabaseAndTables() {
        createDatabase();
        createTableUsers();
        createTableProducts();
    }

    private static void createDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL_DATA, USER, PASS)) {
            if (conn != null) {
                System.out.println("Database warehouse created");
                String query = "CREATE DATABASE IF NOT EXISTS warehouse;";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Don't connected something wrong");
            ex.printStackTrace();
        }
    }

    private static void createTableProducts() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {
                System.out.println("Table Products created");
                String query = "CREATE TABLE IF NOT EXISTS Products (" +
                        "id int NOT NULL AUTO_INCREMENT," +
                        "name VARCHAR(255)," +
                        "description VARCHAR(255)," +
                        "userId int," +
                        "PRIMARY KEY (id)," +
                        "FOREIGN KEY (userId) REFERENCES Users(id) ON DELETE CASCADE" +
                        ");";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Don't connected something wrong");
            ex.printStackTrace();
        }
    }

    private static void createTableUsers() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {
                System.out.println("Table Users created");
                String query = "CREATE TABLE IF NOT EXISTS Users (" +
                        "id int NOT NULL AUTO_INCREMENT," +
                        "name VARCHAR(255)," +
                        "surname VARCHAR(255)," +
                        "password VARCHAR (255)," +
                        "PRIMARY KEY (id)" +
                        ");";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Don't connected something wrong");
            ex.printStackTrace();
        }
    }
}
