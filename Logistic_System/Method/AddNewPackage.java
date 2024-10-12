package Method;

import java.security.SecureRandom;
import java.sql.*;
import java.util.Scanner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


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

        // Package details and shipping details in one table
        // Package Details
        System.out.println("Enter Package Details: ");
        System.out.println("1. Package Weight");
        float packageWeight = sc.nextFloat();
        sc.nextLine();  // Consume newline after float input
        System.out.println("2. Package Dimensions (Length, Width, Height)"); // to calculate package dimension use l*w*h/139
        System.out.println("Length: ");
        String length = sc.nextLine();
        System.out.println("Width: ");
        String width = sc.nextLine();
        System.out.println("Height: ");
        String height = sc.nextLine();
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

        // Store Package Data in Database.
        try (Connection con = ConnectionMySql.getConnection()) {
            // Generate Unique Package ID
            String uniqueID = generateSecureId();

            // Insert Sender and Receiver
            insertEntity(con,uniqueID ,"Sender", nameSen, addrSen, phoneNoSen, emailIdSen);
            insertEntity(con, uniqueID,"Receiver", nameRec, addrRec, phoneNoRec, emailIdRec);

            // Fetch and display details
            fetchAndDisplayEntity(con, "Sender", "Sender_Phone_No", phoneNoSen);
            fetchAndDisplayEntity(con, "Receiver", "Receiver_Phone_No", phoneNoRec);

            // Insert Package
            packageInsert(con, uniqueID, uniqueID, packageWeight, length, width, height, packageDesc, shippingType, shippingOrgLocation, shippingDesLocation, shippingDate);

            // fetch Package
            fetchAndDisplayPackage(con, uniqueID, uniqueID);

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



        // Display Confirmation Message
//        System.out.println("Package has been successfully added.");
//        System.out.println("Package ID: "+ uniqueID);
    }

    // Method to insert both Sender or Receiver.
    // Database store. Error ?
    private static void insertEntity(Connection con, String id, String entityType, String name, String address, String phoneNumber, String email) throws SQLException {
        String sql = String.format("INSERT INTO %s (%s_ID, %s_Name, %s_Address, %s_Phone_No, %s_Email_Id) VALUES (?, ?, ?, ?,?);",
                entityType, entityType, entityType, entityType, entityType,entityType);

        try (PreparedStatement preSt = con.prepareStatement(sql)) {
            preSt.setString(1, id);  // Use the same unique ID
            preSt.setString(2, name);
            preSt.setString(3, address);
            preSt.setString(4, phoneNumber);
            preSt.setString(5, email);
            int rowsInsertedSenAndRec = preSt.executeUpdate();

            if (rowsInsertedSenAndRec > 0) {
                System.out.printf("%s Added successfully.\n", entityType);
            }
        }
        catch (Exception e){
            System.out.println("Please, check you details.");
        }
    }

    // Method to fetch and display details based on phone number.
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

    // Insert package details into the mysql database.
    // Package Insert with same Sender_ID and Receiver_ID.
    private static void packageInsert(Connection con, String senderId, String receiverId, float packageWeight, String l, String w, String h, String packageDesc, String shippingType, String shippingOrgLocation, String shippingDesLocation, String shippingDate) throws SQLException {
        String sql = "INSERT INTO Package (Sender_ID, Receiver_ID, Package_ID, Package_Weight, Package_Dimensions, Package_Description, Delivery_Type, Origin_Location, Destination_Location, Expected_Delivery_Date, Current_Status, Current_Location) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'Shipped', 'Warehouse A')";

        String formattedDate = convertDateToMySQLFormat(shippingDate);
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, senderId);
            preparedStatement.setString(2, receiverId);
            preparedStatement.setString(3, senderId);  // Use the same ID for Package_ID
            preparedStatement.setFloat(4, packageWeight);
            preparedStatement.setString(5, l + "x" + w + "x" + h);  // Combine dimensions into a single string
            preparedStatement.setString(6, packageDesc);
            preparedStatement.setString(7, shippingType);
            preparedStatement.setString(8, shippingOrgLocation);
            preparedStatement.setString(9, shippingDesLocation);
            preparedStatement.setString(10, formattedDate);

            int rowInsertPackage = preparedStatement.executeUpdate();
            if (rowInsertPackage > 0) {
                System.out.println("Package details added successfully with Package_ID: " + senderId + "!\n");
            }
        }
    }

    // Package fetch and display with the help of SenderId and ReceiverId.
    private static void fetchAndDisplayPackage(Connection con, String senderId, String receiverId) throws SQLException {
        String sql = "SELECT * FROM Package WHERE Sender_ID = ? AND Receiver_ID = ?";

        try (PreparedStatement preSt = con.prepareStatement(sql)) {
            preSt.setString(1, senderId);
            preSt.setString(2, receiverId);
            ResultSet rs = preSt.executeQuery();

            while (rs.next()) {
                String packageId = rs.getString("Package_ID");
                float packageWeight = rs.getFloat("Package_Weight");
                String packageDimensions = rs.getString("Package_Dimensions");
                String packageDesc = rs.getString("Package_Description");
                String deliveryType = rs.getString("Delivery_Type");
                String originLocation = rs.getString("Origin_Location");
                String destinationLocation = rs.getString("Destination_Location");
                String expectedDate = rs.getString("Expected_Delivery_Date");

                System.out.printf("""
                        # Package Information:
                        1. Package ID: %s
                        2. Sender ID: %s
                        3. Receiver ID: %s
                        4. Package Weight: %f
                        5. Package Dimensions: %s
                        6. Package Description: %s
                        7. Delivery Type: %s
                        8. Origin Location: %s
                        9. Destination Location: %s
                        10. Expected Delivery Date: %s
                        """, packageId, senderId, receiverId, packageWeight, packageDimensions, packageDesc, deliveryType, originLocation, destinationLocation, expectedDate);


                System.out.println("Please, NOTE down your Package ID: "+packageId);
            }
        }
    }

    // Method for generate unique ID for sender,receiver,package
    // That help to identify the package owner and customer.
    public static String generateSecureId() {
        SecureRandom random = new SecureRandom();
        return new java.math.BigInteger(40, random).toString(32);  // Secure random ID
    }

    // Method to convert date from DD/MM/YYYY or MM/DD/YYYY to YYYY-MM-DD for storing data into the "MYSQL".
    private static String convertDateToMySQLFormat(String date) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");  // Change format to "MM/dd/yyyy" if needed
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date parsedDate = inputFormat.parse(date);  // Parse the input date
            return outputFormat.format(parsedDate);  // Format to MySQL compatible date
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format. Please use DD/MM/YYYY.", e);
        }
    }


}
