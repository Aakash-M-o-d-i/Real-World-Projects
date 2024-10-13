import java.security.SecureRandom;
import java.sql.*;
import java.util.Scanner;

import static Method.AddNewPackage.addNewPackage;
import static Method.TrackPackage.trackPackage;
import static Method.UpdatePackageStatus.updatePackageStatus;
import static Method.ViewPackageHistory.viewPackageHistory;

public class Menu {
    static public void menu() throws SQLException {
        // for clean view of input information
        try {
            // Check the operating system
            ProcessBuilder processBuilder;
            if (System.getProperty("os.name").contains("Windows")) {
                // Windows OS - Run "cls" command
                processBuilder = new ProcessBuilder("cmd", "/c", "cls");
            } else {
                // Unix-like OS (Linux, macOS) - Run "clear" command
                processBuilder = new ProcessBuilder("clear");
                processBuilder.environment().put("TERM", "xterm"); // Set TERM variable
            }
            processBuilder.inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String reset = "\033[0m";  // Resets color to default
        String red = "\033[31m";
        String green = "\033[32m";
        String yellow = "\033[33m";
        String magenta = "\033[35m";
        String cyan = "\033[36m";

        String input = """
        Select Option
            1. %s Add New Package %s
            2. %s Track Package %s
            3. %s Update Package Status %s
            4. %s View Package History %s
            5. %s Exit %s
            └─>""";
        String formattedInput = String.format(input,
                green, reset,  // 1. Add New Package
                cyan, reset,   // 2. Track Package
                yellow, reset, // 3. Update Package Status
                magenta, reset,   // 4. View Package History
                red, reset     // 5. Exit
        );
        Scanner sc = new Scanner(System.in);
        int user;
        while (true) {
            System.out.print(formattedInput+" ");
            // Check if the user input is an integer
            if (sc.hasNextInt()) {
                user = sc.nextInt();
                sc.nextLine();  // Consume the newline character after the integer input

                // Switch based on the user's choice
                switch (user) {
                    case 1:
                        addNewPackage();
                        break;
                    case 2:
                        trackPackage();
                        break;
                    case 3:
                        updatePackageStatus();
                        break;
                    case 4:
                        viewPackageHistory();
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        sc.close();
                        return;  // Exit the loop and method (i.e., exit the program)
                    default:
                        System.out.println("Invalid option, please try again.");
                }
            } else {
                // If the input is not an integer, handle it gracefully
                System.out.println("Invalid input. Please enter a number.");
                if (sc.hasNextLine()) {
                    sc.nextLine();  // Consume the invalid input (if it's not an integer)
                }
            }
        }
    }

    private static void Exit() {
    }
}
