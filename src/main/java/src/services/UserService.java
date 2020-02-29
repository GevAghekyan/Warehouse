package src.services;

import src.models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static src.constants.MariaDBConstants.*;

public class UserService {

    private static UserService instance = null;
    private WarehouseService warehouseService = WarehouseService.getInstance();

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public void createUser(User user) {

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                String query = "INSERT INTO Users(name, surname, password) " +
                        "VALUES (?,?,?);";

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getSurname());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Something goes wrong");
            ex.printStackTrace();
        }
    }

    public void deleteUSerById(int enteredId, String password) {

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                String query = "DELETE FROM Users WHERE id = ?";

                if (warehouseService.checkPassword(enteredId, password)) {

                    PreparedStatement preparedStatement = conn.prepareStatement(query);
                    preparedStatement.setInt(1, enteredId);
                    preparedStatement.executeUpdate();
                } else {
                    System.out.println("Incorrect password!!!");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
