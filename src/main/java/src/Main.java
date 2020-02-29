package src;

import src.controllers.ProductController;
import src.controllers.UserController;
import src.controllers.WarehouseController;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        CreateDatabaseAndTables.createDatabaseAndTables();
        Scanner scanner = new Scanner(System.in);
        boolean check = true;
        ProductController productController = ProductController.getInstance();
        UserController userController = UserController.getInstance();
        WarehouseController warehouseController = WarehouseController.getInstance();


        while (check) {
            System.out.println("1. Log in");
            System.out.println("2. For Search");
            System.out.println("3. Sign up");
            switch (Integer.parseInt(scanner.nextLine())) {
                case 1:
                    warehouseController.login();
                    break;
                case 2:
                    productController.searchProducts();
                    break;
                case 3:
                    userController.createUser();
                    break;
            }
        }
    }
}
