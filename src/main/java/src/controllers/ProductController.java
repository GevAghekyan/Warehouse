package src.controllers;

import src.models.Product;
import src.services.ProductService;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductController {

    private static ProductController instance = null;
    Scanner scanner = new Scanner(System.in);
    ProductService productService = ProductService.getInstance();

    public static ProductController getInstance() {
        if (instance == null) {
            instance = new ProductController();
        }
        return instance;
    }

    public void createProduct(int id) {
        System.out.println("Enter product name:");
        String name = scanner.nextLine();
        System.out.println("Enter product description:");
        String description = scanner.nextLine();
        Product product = new Product(name, description);
        productService.createProduct(product, id);
    }

    public ArrayList<Product> findAllProducts() {
        return productService.findAllProducts();
    }

    public Product findProduct() {
        System.out.println("Enter product id:");
        return productService.findProductById(Integer.parseInt(scanner.nextLine()));
    }

    public void searchProducts() {
        System.out.println("Enter keyword:");
        System.out.println(productService.searchProducts(scanner.nextLine()));
    }

    public void updateProduct() {
        System.out.println("Enter product id:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter product name:");
        String name = scanner.nextLine();
        System.out.println("Enter product description:");
        String description = scanner.nextLine();
        Product product = new Product(name, description);
        productService.updateProduct(id, product);
    }

    public void deleteProduct() {
        System.out.println("Enter product id:");
        productService.deleteProductById(Integer.parseInt(scanner.nextLine()));
    }
}
