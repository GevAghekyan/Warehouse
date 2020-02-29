package src.controllers;

import src.models.User;
import src.services.UserService;

import java.util.Scanner;

public class UserController {

    private static UserController instance = null;
    Scanner scanner = new Scanner(System.in);
    UserService userService = UserService.getInstance();

    public static UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }
        return instance;
    }

    public void createUser() {
        System.out.println("Enter user name:");
        String name = scanner.nextLine();
        System.out.println("Enter user surname:");
        String surname = scanner.nextLine();
        System.out.println("Enter user password:");
        String password = scanner.nextLine();
        User user = new User(name, surname, password);
        userService.createUser(user);
    }

    public void deleteUser() {
        System.out.println("Enter user id:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter user password:");
        String password = scanner.nextLine();
        userService.deleteUSerById(id, password);
    }
}
