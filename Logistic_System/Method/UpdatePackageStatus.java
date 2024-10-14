package Method;

import java.sql.*;
import java.util.Scanner;

public class UpdatePackageStatus {

    // Method to update the package status and location
    public static void updatePackageStatus() throws SQLException {
        Connection con = ConnectionMySql.getConnection();
        Scanner scanner = new Scanner(System.in);

        // Prompt for Package ID
        System.out.println(ColorUtil.YELLOW+"Enter the Package ID to update: "+ColorUtil.RESET);
        String packageId = scanner.nextLine();

        // Validate Package ID (this step is assumed)
        if (!isValidPackageId(con, packageId)) {
            System.out.println(ColorUtil.RED+"Invalid Package ID. Please try again."+ColorUtil.RESET);
            return;
        }

        // Prompt for new status and location
        System.out.println(ColorUtil.YELLOW+"Enter new status (e.g., In Transit, Delivered): "+ColorUtil.RESET);
        String newStatus = scanner.nextLine();
        System.out.println(ColorUtil.YELLOW+"Enter new location: "+ColorUtil.RESET);
        String newLocation = scanner.nextLine();

        // Update the current status and location in the Package table
        String updateSQL = "UPDATE Package SET Current_Status = ?, Current_Location = ? WHERE Package_ID = ?";
        try (PreparedStatement pstmt = con.prepareStatement(updateSQL)) {
            pstmt.setString(1, newStatus);
            pstmt.setString(2, newLocation);
            pstmt.setString(3, packageId);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                // Step 5: Log the update in the Package_History table
                logPackageHistory(con, packageId, newStatus, newLocation);
                System.out.println(ColorUtil.GREEN+"Package status updated successfully."+ColorUtil.RESET);
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
