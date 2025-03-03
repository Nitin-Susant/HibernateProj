package org.hibernat.Factorys;

import org.hibernat.Entity.Student;
import org.hibernat.Entity.Users;
import org.hibernat.Service.StudentService;
import org.hibernat.Service.UserService;

import java.util.List;
import java.util.Scanner;

public class StudentFactory implements FactoryClass {

    private static final Scanner scanner = new Scanner(System.in);

    private static StudentService userService = null;

    public StudentFactory() {

        userService = new StudentService();
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
        System.out.println("1. Add student");
        System.out.println("2. Update student");
        System.out.println("3. Get student by ID");
        System.out.println("4. Get All student");
        System.out.println("5. Remove student by ID");
        System.out.println("6. Remove All student");
        System.out.println("0. Exit");
    }

    private static void addUser() {
        System.out.println("Enter user name:");
        String name = scanner.nextLine();
        Student user = new Student();

        user.setName(name);
        userService.addStudent(user);
        System.out.println("User added successfully.");
    }

    private static void updateUser() {
        System.out.println("Enter user ID:");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter new user name:");
        String name = scanner.nextLine();
        Student user = new Student();
        user.setName(name);
        userService.updateStudent(id, user);
        System.out.println("User updated successfully.");

    }

    private static void getUserById() {
        System.out.println("Enter user ID:");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        Student user = userService.getStudent(id);
        if (user != null) {
            System.out.println("User ID: " + user.getId() + ", Name: " + user.getName());
        } else {
            System.out.println("User not found.");
        }
    }

    private static void getAllUsers() {
        List<Student> users = userService.getAllStudent();
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
        boolean isremoved = userService.removeStudent(id);
        if (isremoved) {

            System.out.println("User removed successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    private static void removeAllUsers() {
        userService.removeAllStudent();
        System.out.println("All users removed successfully.");
    }




}
