package Method;

import java.sql.*;
import java.util.Scanner;

public class TrackPackage {
    public static void trackPackage() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection con = ConnectionMySql.getConnection();
        System.out.print("Enter the Package ID: ");
        String packageId = sc.nextLine();
        fetchTrackPackage(con,packageId);
    }
    // fetch the tracking status in the database
    public static void fetchTrackPackage(Connection con, String packageId) throws SQLException {
        // PLAN
        //1. take packageId for tracking
        //2. display the current location of package
            //2.1. what the upcoming location of package.

        // SQL to get current status from the Package table
        String trackPackageSQL = "SELECT Current_Status, Current_Location, Expected_Delivery_Date FROM Package WHERE Package_ID = ?";
        PreparedStatement preStTrack = con.prepareStatement(trackPackageSQL);
        preStTrack.setString(1, packageId); // through packageID it will
        ResultSet rs = preStTrack.executeQuery();

        if (rs.next()) {
            String currentStatus = rs.getString("Current_Status");
            String currentLocation = rs.getString("Current_Location");
            Date expectedDeliveryDate = rs.getDate("Expected_Delivery_Date");

            System.out.printf(""" 
                           # Package ID: %s
                           # Current Status: %s
                           # Current Location: %s
                           # Expected Delivery Date: %s%n""",
                    packageId, currentStatus, currentLocation, expectedDeliveryDate);
        }
    }
}
