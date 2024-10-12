import java.security.SecureRandom;
import java.sql.*;
import java.util.Scanner;

import static Method.AddNewPackage.addNewPackage;
import static Method.TrackPackage.trackPackage;

public class Menu {
    static public void menu() throws SQLException {
        String input = """
        Select Option
        1. Add New Package
        2. Track Package
        3. Update Package Status
        4. View Package History
        5. Exit
        └─>""";
        Scanner sc = new Scanner(System.in);
        int user;
        while (true) {
            System.out.print(input+" ");
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

    private static void viewPackageHistory() {
    }

    private static void updatePackageStatus() {
    }
}
