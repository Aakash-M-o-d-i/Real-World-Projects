import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class Testing {

//    public static void main(String[] args) throws Exception {
//        String sql = "insert into Students (Name, Roll_No, Age) values(?,?,?)";
//
//        String url = "jdbc:mysql://localhost:3306/testing";
//        String userName = "root";
//        String password = "1111";
//
//        Connection con = DriverManager.getConnection(url,userName,password);
//
//        PreparedStatement preSt = con.prepareStatement(sql);
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter name");
//        String name = sc.nextLine();
//        System.out.println("Enter Roll_no");
//        int rollNo = sc.nextInt();
//        sc.nextLine();
//        System.out.println("Enter age");
//        int age = sc.nextInt();
//        sc.nextLine();
//
//        preSt.setString(1,name);
//        preSt.setInt(2,rollNo);
//        preSt.setInt(3,age);
//
//        int rowsInsert = preSt.executeUpdate();
//        if(rowsInsert > 0){
//            String sql1 = "select *from Students";
//             ResultSet rs = preSt.executeQuery(sql1); // THis will fetch the result from databases. // for executing query's.
//            rs.next(); // why this? , bcz pointer are pointing to name column not exact name.
//            while (rs.next()) {
//                String name1 = rs.getString("name");  // Fetch 'name' column value
//                int rollNo1 = rs.getInt("roll_no");  // Fetch 'roll_no' column value
//                int age1 = rs.getInt("age");  // Fetch 'age' column value
//
//                System.out.println("Name: " + name1 + ", Roll No: " + rollNo1 + ", Age: " + age1);
//            }
//        }
//
////        ResultSet rs = preSt.executeQuery(sql); // THis will fetch the result from databases. // for executing query's.
////        rs.next(); // why this? , bcz pointer are pointing to name column not exact name.
////
////        String name = rs.getString(1);
////        System.out.println(name);
////        System.out.printf("Connect successful!");
//        con.close();
//    }
//    static public void db(){
//
//    }
    static public void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void main(String[] args) {
        int[] arr = {10,9,5,6,8,4,3};
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if(arr[i] > arr[j]){
                    swap(arr,i,j);
            }
        }
//        int i = 0; int j = i+1;
//        while (i > j){
//            if (arr[i] > arr[j]) {
//                swap(arr, i, j);
//                i++;
//                j++;
//            }
        }
        System.out.println(Arrays.toString(arr));
    }

}
