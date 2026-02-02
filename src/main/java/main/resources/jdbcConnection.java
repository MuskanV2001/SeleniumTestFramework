package main.resources;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class jdbcConnection {

    public static Object[][] retrieveData() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/qa_dbtest";

        Connection conn = DriverManager.getConnection(url, "root", "Simran@2001#");

        Statement s = conn.createStatement();

        ResultSet result = s.executeQuery("select * from userDetails;");
//        displayResult(result);

        List<HashMap<String,String>> userData = new ArrayList<>();

        while(result.next()){
            HashMap<String, String> dataVal = new HashMap<>();
            dataVal.put("email", result.getString("Email"));
            dataVal.put("password", result.getString("Password"));
            dataVal.put("product", result.getString("ProductName"));
            userData.add(dataVal);
        }
        return new Object[][] {{userData.get(0)}, {userData.get(1)}, {userData.get(2)}};
    }

    public static void displayResult(ResultSet result) throws SQLException {
        while(result.next()){
            System.out.println(result.getString("Email") + "\t" + result.getString("Password"));
        }
    }
}
