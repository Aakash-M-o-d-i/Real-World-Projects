package Method;

import java.sql.*;
import java.util.Scanner;

import static Method.UpdatePackageStatus.isValidPackageId;

public class ViewPackageHistory {
    // Method to view the package history
    public static void viewPackageHistory() throws SQLException {
        Connection con = ConnectionMySql.getConnection();
        Scanner scanner = new Scanner(System.in);

        // Prompt for Package ID
        System.out.println("Enter the Package ID to view history: ");
        String packageId = scanner.nextLine();

        // Validate Package ID (assuming the isValidPackageId method exists) 
        if (!isValidPackageId(con, packageId)) {
            System.out.println("Invalid Package ID. Please try again.");
            return;
        }
        fetchLogPackageHistory(con,packageId); // using from UpdatePackageStatus
        // Ask the user if they want to view the current status
        System.out.println("Would you like to view the current package status? (yes/no)");
        String userInput = scanner.nextLine();
        if (userInput.equalsIgnoreCase("yes")) {
            viewCurrentStatus(con, packageId); // Fetch and display current status
        }
    }

    public static void viewCurrentStatus(Connection con, String packageId) throws SQLException {
        String queryCurrent = "SELECT Current_Status, Current_Location FROM Package WHERE Package_ID = ?";
        try (PreparedStatement pstmt = con.prepareStatement(queryCurrent)) {
            pstmt.setString(1, packageId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String currentStatus = rs.getString("Current_Status");
                    String currentLocation = rs.getString("Current_Location");
                    System.out.println("Current Status: " + currentStatus + ", \nCurrent Location: " + currentLocation);
                } else {
                    System.out.println("Package not found.");
                }
            }
        }
    }
    // fetch the log of package status to the use
    public static void fetchLogPackageHistory(Connection con, String packageId) throws SQLException {
        String fetchHistoryQuery = "SELECT Status, Location, Timestamp FROM Package_History WHERE Package_ID = ? ORDER BY Timestamp ASC";
        PreparedStatement preparedStatement = con.prepareStatement(fetchHistoryQuery);
        preparedStatement.setString(1,packageId);

        ResultSet rsHistory = preparedStatement.executeQuery();
        System.out.println("# Package History");
        while (rsHistory.next()){
            // fetch details for database
            String status = rsHistory.getString("Status");
            String location = rsHistory.getString("Location");
            Timestamp timestamp = rsHistory.getTimestamp("Timestamp");

            // display package history details
            System.out.printf("""
                    # Status: %s
                    # Location: %s
                    # Timestamp: %s
                    -----------------------------------------------------------------------------------------------------------
                    """,status,location,timestamp);
        }
    }
}
