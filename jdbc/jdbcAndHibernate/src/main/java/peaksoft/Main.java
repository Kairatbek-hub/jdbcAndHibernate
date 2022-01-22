package peaksoft;

import peaksoft.model.User;
import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        System.out.println("----------------------------------------");
//        userService.saveUser("Asan", "Asanov", (byte) 25);
//        userService.saveUser("Sezim", "Kalykova", (byte) 18);
//        userService.saveUser("Uson", "Asanov", (byte) 25);
//        userService.saveUser("Aiperi", "Joldoshova", (byte) 20);
        System.out.println("-----------------------------------------");
        for (User user: userService.getAllUsers()) {
            System.out.println(user);
        }
        System.out.println("-----------------------------------------");
//        userService.cleanUsersTable();
//        userService.dropUsersTable();
    }
}
