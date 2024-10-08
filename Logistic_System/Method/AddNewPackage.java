package Method;

import java.security.SecureRandom;
import java.sql.*;
import java.util.Scanner;

public class AddNewPackage {

    public static void addNewPackage() throws SQLException {
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
//
//        // Package Details
//        System.out.println("Enter Package Details: ");
//        System.out.println("1. Package Weight");
//        float packageWeight = sc.nextFloat();
//        sc.nextLine();  // Consume newline after float input
//        // Package dimensions input corrected as individual inputs (length, width, height)
//        System.out.println("2. Package Dimensions (Length, Width, Height)");
//        System.out.println("Length: ");
//        int length = sc.nextInt();
//        System.out.println("Width: ");
//        int width = sc.nextInt();
//        System.out.println("Height: ");
//        int height = sc.nextInt();
//        sc.nextLine();  // Consume newline after int inputs
//        System.out.println("3. Package Description (optional)");
//        String packageDesc = sc.nextLine();
//
//        // Shipping Details
//        System.out.println("Enter Shipping Details:");
//        System.out.println("1. Delivery Type (Standard/Express)");
//        String shippingType = sc.next();
//        sc.nextLine();  // Consume newline after next()
//        System.out.println("2. Origin Location");
//        String shippingOrgLocation = sc.nextLine();
//        System.out.println("3. Destination Location");
//        String shippingDesLocation = sc.nextLine();
//        System.out.println("4. Expected Delivery Date (DD/MM/YYYY)");
//        String shippingDate = sc.nextLine();  // For a date, store as a String for now

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

        sc.close();  // Close the Scanner when done

        // Generate Unique Package ID
        String uniqueID = generateSecureId();

        // Store Package Data in Database.
        String url = "jdbc:mysql://localhost:3306/logisticSys";
        String userName = "root";
        String password = "1111";

        try (Connection con = DriverManager.getConnection(url, userName, password)) {

            // Insert Sender and Receiver
            insertEntity(con, "Sender", nameSen, addrSen, phoneNoSen, emailIdSen);
            insertEntity(con, "Receiver", nameRec, addrRec, phoneNoRec, emailIdRec);

            // Fetch and display details
            fetchAndDisplayEntity(con, "Sender", "Sender_Phone_No", phoneNoSen);
            fetchAndDisplayEntity(con, "Receiver", "Receiver_Phone_No", phoneNoRec);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // this for displaying details for 5 second
        try {
            // Sleep for 5 seconds (5,000 milliseconds)
            System.out.println("5 second remaining to go back in Main Menu!");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // display store package data


//
//        System.out.printf("""
//                # Package Details:
//                1. Package Weight: %f
//                2. Package Dimensions (Length, Width, Height): %d, %d, %d
//                3. Package Description (optional): %s
//                """,packageWeight,length,width,height,packageDesc);
//
//        System.out.printf("""
//                # Shipping Details:
//                1. Delivery Type (Standard/Express): %s
//                2. Origin Location: %s
//                3. Destination Location: %s
//                4. Expected Delivery Date: %s
//                """,shippingType,shippingOrgLocation,shippingDesLocation,shippingDate);

        // Display Confirmation Message
        System.out.println("Package has been successfully added.");
        System.out.println("Package ID: "+uniqueID);
    }

    // Method to insert either Sender or Receiver
    private static void insertEntity(Connection con, String entityType, String name, String address, String phoneNumber, String email) throws SQLException {
        String sql = String.format("INSERT INTO %s (%s_Name, %s_Address, %s_Phone_No, %s_Email_Id) VALUES (?, ?, ?, ?);",
                entityType, entityType, entityType, entityType, entityType);

        try (PreparedStatement preSt = con.prepareStatement(sql)) {
            preSt.setString(1, name);
            preSt.setString(2, address);
            preSt.setString(3, phoneNumber);
            preSt.setString(4, email);
            int rowsInserted = preSt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.printf("%s Added successfully.\n", entityType);
            }
        }
    }

    // Method to fetch and display details based on phone number
    private static void fetchAndDisplayEntity(Connection con, String entityType, String phoneColumn, String phoneNumber) throws SQLException {
        String sql = String.format("SELECT * FROM %s WHERE %s = ?", entityType, phoneColumn);

        try (PreparedStatement preSt = con.prepareStatement(sql)) {
            preSt.setString(1, phoneNumber);
            ResultSet rs = preSt.executeQuery();

            while (rs.next()) {
                String name = rs.getString(entityType + "_Name");
                String address = rs.getString(entityType + "_Address");
                String phone = rs.getString(entityType + "_Phone_No");
                String email = rs.getString(entityType + "_Email_Id");

                System.out.printf("""
                # %s Information:
                1. %s Name: %s
                2. %s Address: %s
                3. %s Contact Info:
                      3.1 Phone Number: %s
                      3.2 Email ID: %s
                """, entityType, entityType, name, entityType, address, entityType, phone, email);
            }
        }
    }

    // just for this optional
    private static String generateSecureId() {
        SecureRandom random = new SecureRandom();
        return new java.math.BigInteger(40, random).toString(32);  // Secure random ID
    }


}
