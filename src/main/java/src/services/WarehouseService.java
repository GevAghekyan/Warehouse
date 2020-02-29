package src.services;

import org.mindrot.jbcrypt.BCrypt;
import src.controllers.ProductController;

import java.sql.*;
import java.util.Scanner;

import static src.constants.MariaDBConstants.*;

public class WarehouseService {

    private static WarehouseService instance = null;
    private ProductController productController = ProductController.getInstance();

    public static WarehouseService getInstance() {
        if (instance == null) {
            instance = new WarehouseService();
        }
        return instance;
    }

    public boolean checkPassword(int userId, String password) {
        boolean check = false;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                String userPassword = null;
                String query = "SELECT password FROM Users WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, userId);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    userPassword = resultSet.getString("password");
                }

                if (BCrypt.checkpw(password, userPassword)) {
                    check = BCrypt.checkpw(password, userPassword);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Something goes wrong");
            ex.printStackTrace();
        }
        return check;
    }

    public void logIn(int userId, String password) {

        Scanner scanner = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                if (checkPassword(userId, password)) {
                    System.out.println("Loged in");
                    String userName = null;
                    String query = "SELECT name FROM Users WHERE id = ?";
                    PreparedStatement preparedStatement = conn.prepareStatement(query);
                    preparedStatement.setInt(1, userId);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        userName = resultSet.getString("name");
                    }
                    System.out.println("------Welcome-" + userName + "------");
                    System.out.println("1. Create product");
                    System.out.println("2. Find all products");
                    System.out.println("3. Find product by id");
                    System.out.println("4. Update product by id");
                    System.out.println("5. Delete product by id");
                    System.out.println("6. Previous menu");
                    switch (Integer.parseInt(scanner.nextLine())) {
                        case 1:
                            productController.createProduct(userId);
                            break;
                        case 2:
                            System.out.println(productController.findAllProducts());
                            break;
                        case 3:
                            System.out.println(productController.findProduct());
                            break;
                        case 4:
                            productController.updateProduct();
                            break;
                        case 5:
                            productController.deleteProduct();
                            break;
                        case 6:
                            break;
                    }
                } else {
                    System.out.println("Incorrect password!!!");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Something goes wrong");
            ex.printStackTrace();
        }
    }
}
