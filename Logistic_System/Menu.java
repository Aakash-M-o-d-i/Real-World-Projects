import java.security.SecureRandom;
import java.util.Scanner;

public class Menu {
    static public void menu(){
        String input = """
                Select Option
                1. Add New Package
                2. Track Package
                3. Update Package Status
                4. View Package History
                5. Exit""";
        System.out.println(input);
        Scanner sc = new Scanner(System.in);
        int user = sc.nextInt();
        while (true){
            switch (user){
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

    private static void Exit() {
    }

    private static void viewPackageHistory() {
    }

    private static void updatePackageStatus() {
    }

    private static void trackPackage() {
    }

    private static void addNewPackage() {
        Scanner sc = new Scanner(System.in);

        // Sender Information
        System.out.println("Enter Sender Information:");
        System.out.println("1. Sender Name: ");
        String nameSen = sc.nextLine();
        System.out.println("2. Sender Address: ");
        String addrSen = sc.nextLine();
        System.out.println("Sender Contact Info:");
        System.out.println("1.1. Phone Number: ");
        String phoneNoSen = sc.nextLine();
        System.out.println("1.2. Email ID: ");
        String emailIdSen = sc.nextLine();

        // Receiver Information
        System.out.println("Enter Receiver Information:");
        System.out.println("1. Receiver Name: ");
        String nameRec = sc.nextLine();
        System.out.println("2. Receiver Address");
        String addrRec = sc.nextLine();
        System.out.println("Receiver Contact Info:");
        System.out.println("1.1. Phone Number: ");
        String phoneNoRec = sc.nextLine();
        System.out.println("1.2. Email ID: ");
        String emailIdRec = sc.nextLine();

        // Package Details
        System.out.println("Enter Package Details: ");
        System.out.println("1. Package Weight");
        float packageWeight = sc.nextFloat();
        sc.nextLine();  // Consume newline after float input
        // Package dimensions input corrected as individual inputs (length, width, height)
        System.out.println("2. Package Dimensions (Length, Width, Height)");
        System.out.println("Length: ");
        int length = sc.nextInt();
        System.out.println("Width: ");
        int width = sc.nextInt();
        System.out.println("Height: ");
        int height = sc.nextInt();
        sc.nextLine();  // Consume newline after int inputs
        System.out.println("3. Package Description (optional)");
        String packageDesc = sc.nextLine();

        // Shipping Details
        System.out.println("Enter Shipping Details:");
        System.out.println("1. Delivery Type (Standard/Express)");
        String shippingType = sc.next();
        sc.nextLine();  // Consume newline after next()
        System.out.println("2. Origin Location");
        String shippingOrgLocation = sc.nextLine();
        System.out.println("3. Destination Location");
        String shippingDesLocation = sc.nextLine();
        System.out.println("4. Expected Delivery Date (DD/MM/YYYY)");
        String shippingDate = sc.nextLine();  // For a date, store as a String for now

        sc.close();  // Close the Scanner when done

        // Generate Unique Package ID
        String uniqueID = generateSecureId();

        // Store Package Data in Database. Pending!!
            // display store package data
        System.out.printf("""
                # Sender Information:
                1. Sender Name: %s
                2. Sender Address: %s
                3. Sender Contact Info:\s
                              3.1 Phone Number: %s
                              3.2 Email ID: %s
                """,nameSen,addrSen,phoneNoSen,emailIdSen);
        System.out.printf("""
                # Receiver Information:
                1. Receiver Name: %s
                2. Receiver Address: %s
                3. Receiver Contact Info:\s
                              3.1 Phone Number: %s
                              3.2 Email ID: %s
                """,nameRec,addrRec,phoneNoRec,emailIdRec);

        System.out.printf("""
                # Package Details:
                1. Package Weight: %f
                2. Package Dimensions (Length, Width, Height): %d, %d, %d
                3. Package Description (optional): %s
                """,packageWeight,length,width,height,packageDesc);

        System.out.printf("""
                # Shipping Details:
                1. Delivery Type (Standard/Express): %s
                2. Origin Location: %s
                3. Destination Location: %s
                4. Expected Delivery Date: %s
                """,shippingType,shippingOrgLocation,shippingDesLocation,shippingDate);

        // Display Confirmation Message
        System.out.println("Package has been successfully added.");
        System.out.println("Package ID: "+uniqueID);
    }

    private static String generateSecureId() {
        SecureRandom random = new SecureRandom();
        return new java.math.BigInteger(40, random).toString(32);  // Secure random ID
    }

}
