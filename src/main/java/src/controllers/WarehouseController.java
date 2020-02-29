package src.controllers;

import src.services.WarehouseService;

import java.util.Scanner;

public class WarehouseController {

    private static WarehouseController instance = null;
    Scanner scanner = new Scanner(System.in);
    WarehouseService warehouseService = WarehouseService.getInstance();

    public static WarehouseController getInstance() {
        if (instance == null) {
            instance = new WarehouseController();
        }
        return instance;
    }

    public void login() {
        System.out.println("Enter user id:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter user password:");
        String password = scanner.nextLine();
        warehouseService.logIn(id, password);
    }
}
