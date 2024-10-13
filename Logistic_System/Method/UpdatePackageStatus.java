package Method;

import java.sql.*;
import java.util.Scanner;

public class UpdatePackageStatus {

    // Method to update the package status and location
    public static void updatePackageStatus() throws SQLException {
        Connection con = ConnectionMySql.getConnection();
        Scanner scanner = new Scanner(System.in);

        // Prompt for Package ID
        System.out.println("Enter the Package ID to update: ");
        String packageId = scanner.nextLine();

        // Validate Package ID (this step is assumed)
        if (!isValidPackageId(con, packageId)) {
            System.out.println("Invalid Package ID. Please try again.");
            return;
        }

        // Prompt for new status and location
        System.out.println("Enter new status (e.g., In Transit, Delivered): ");
        String newStatus = scanner.nextLine();
        System.out.println("Enter new location: ");
        String newLocation = scanner.nextLine();

        // Step 4: Update the current status and location in the Package table
        String updateSQL = "UPDATE Package SET Current_Status = ?, Current_Location = ? WHERE Package_ID = ?";
        try (PreparedStatement pstmt = con.prepareStatement(updateSQL)) {
            pstmt.setString(1, newStatus);
            pstmt.setString(2, newLocation);
            pstmt.setString(3, packageId);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                // Step 5: Log the update in the Package_History table
                logPackageHistory(con, packageId, newStatus, newLocation);
                fetchLogPackageHistory(con,packageId);
                System.out.println("Package status updated successfully.");
            }
        }
    }

    // method to log the package status update in the history table
    public static void logPackageHistory(Connection con, String packageId, String status, String location) throws SQLException {
        String insertHistorySQL = "INSERT INTO Package_History (Package_ID, Status, Location) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(insertHistorySQL)) {
            pstmt.setString(1, packageId);
            pstmt.setString(2, status);
            pstmt.setString(3, location);
            pstmt.executeUpdate();
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
                    1. Status: %s
                    2. Location: %s
                    3. Timestamp: %s
                    """,status,location,timestamp);
        }
    }
    // Placeholder validation method for Package_ID
    public static boolean isValidPackageId(Connection con, String packageId) throws SQLException {
        // Logic to check if the Package ID exists in the database
        String query = "SELECT Package_ID FROM Package WHERE Package_ID = ?";
        try(PreparedStatement preparedStatement = con.prepareStatement(query)){
            preparedStatement.setString(1,packageId);
            try (ResultSet rsCheck = preparedStatement.executeQuery()){
                // If the ResultSet has at least one result, the Package_ID exists
                return rsCheck.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
