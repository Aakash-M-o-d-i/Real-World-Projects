import java.security.SecureRandom;
import java.sql.*;
import java.util.Scanner;

import static Method.AddNewPackage.addNewPackage;

public class Menu {
    static public void menu() throws SQLException {
        String input = """
                Select Option
                1. Add New Package
                2. Track Package
                3. Update Package Status
                4. View Package History
                5. Exit""";
        System.out.println(input);
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            int user = sc.nextInt();
            while (true) {
                switch (user) {
                    case 1:
                        addNewPackage();
                        menu();
                        break;
                    case 2:
                        trackPackage();
                        menu();
                        break;
                    case 3:
                        updatePackageStatus();
                        menu();
                        break;
                    case 4:
                        viewPackageHistory();
                        menu();
                        break;
                    case 5:
//                    Exit();
                        break;
                }
                break;
            }
        }
    }

    private static void Exit() {
    }

    private static void viewPackageHistory() {
    }

    private static void updatePackageStatus() {
    }

    private static void trackPackage() {
    }
}
