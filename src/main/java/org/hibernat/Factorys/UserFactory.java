package org.hibernat.Factorys;

import org.hibernat.Entity.Users;
import org.hibernat.Main;
import org.hibernat.Service.UserService;

import java.util.List;
import java.util.Scanner;

public class UserFactory implements FactoryClass{

    private static final Scanner scanner = new Scanner(System.in);

    private static UserService userService = null;

    public UserFactory() {

        userService = new UserService();
    }

    public void operation() {

        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    updateUser();
                    break;
                case 3:
                    getUserById();
                    break;
                case 4:
                    getAllUsers();
                    break;
                case 5:
                    removeUser();
                    break;
                case 6:
                    removeAllUsers();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("Choose an operation:");
        System.out.println("1. Add User");
        System.out.println("2. Update User");
        System.out.println("3. Get User by ID");
        System.out.println("4. Get All Users");
        System.out.println("5. Remove User by ID");
        System.out.println("6. Remove All Users");
        System.out.println("0. Exit");
    }

    private static void addUser() {
        System.out.println("Enter user name:");
        String name = scanner.nextLine();
        Users user = new Users();

        user.setName(name);
        userService.addUser(user);
        System.out.println("User added successfully.");
    }

    private static void updateUser() {
        System.out.println("Enter user ID:");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter new user name:");
        String name = scanner.nextLine();
        Users user = new Users();
        user.setName(name);
        userService.updateUser(id, user);
        System.out.println("User updated successfully.");

    }

    private static void getUserById() {
        System.out.println("Enter user ID:");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        Users user = userService.getUser(id);
        if (user != null) {
            System.out.println("User ID: " + user.getId() + ", Name: " + user.getName());
        } else {
            System.out.println("User not found.");
        }
    }

    private static void getAllUsers() {
        List<Users> users = userService.getAllUser();
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.println("Users List:");
            users.forEach(user -> System.out.println("User ID: " + user.getId() + ", Name: " + user.getName()));
        }
    }

    private static void removeUser() {
        System.out.println("Enter user ID:");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        boolean isremoved = userService.removeUser(id);
        if (isremoved) {

            System.out.println("User removed successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    private static void removeAllUsers() {
        userService.removeAllUser();
        System.out.println("All users removed successfully.");
    }
}


