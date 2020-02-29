package src.services;

import src.models.Product;

import java.sql.*;
import java.util.ArrayList;

import static src.constants.MariaDBConstants.*;

public class ProductService {

    private static ProductService instance = null;

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }

    public void createProduct(Product product, int userID) {

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                String query = "INSERT INTO Products(name, description,userId) " +
                        "VALUES (?,?,?);";

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, product.getName());
                preparedStatement.setString(2, product.getDescription());
                preparedStatement.setInt(3, userID);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Something goes wrong");
            ex.printStackTrace();
        }
    }

    public ArrayList<Product> findAllProducts() {

        ArrayList<Product> products = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                String query = "SELECT * FROM Products";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Product product = new Product();
                    product.setName(resultSet.getString("name"));
                    product.setDescription(resultSet.getString("description"));
                    products.add(product);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    public ArrayList<Product> searchProducts(String keyword) {

        ArrayList<Product> products = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                String partOfQuery = "'%" + keyword + "%'";
                String query = "SELECT * FROM Products WHERE name LIKE " + partOfQuery + " OR description LIKE " + partOfQuery;
                PreparedStatement preparedStatement = conn.prepareStatement(query);
//                preparedStatement.setString(1,keyword);
//                preparedStatement.setString(2,keyword);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Product product = new Product();
                    product.setName(resultSet.getString("name"));
                    product.setDescription(resultSet.getString("description"));
                    products.add(product);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    public Product findProductById(int enteredId) {

        Product product = new Product();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                String query = "SELECT * FROM Products WHERE id = ?";

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, enteredId);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    product.setName(resultSet.getString("name"));
                    product.setDescription(resultSet.getString("description"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return product;
    }

    public void updateProduct(int enteredId, Product product) {

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                String query = "UPDATE Products SET name = ?, description = ?  WHERE id = ?";

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(3, enteredId);
                preparedStatement.setString(1, product.getName());
                preparedStatement.setString(2, product.getDescription());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteProductById(int enteredId) {

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                String query = "DELETE FROM Products WHERE id = ?";

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, enteredId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
