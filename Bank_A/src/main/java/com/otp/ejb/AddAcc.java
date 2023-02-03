package com.otp.ejb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddAcc {

	public static void show() {
		try {
			DBConnection db = new DBConnection();
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = db.createConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Accounts");
	           
			while (rs.next()) {
			    String id = rs.getString("id");
			    System.out.print(id);
			    String name = rs.getString("name");
			    System.out.print(name);
			    String otp = rs.getString("otp");
			    System.out.print(otp);
			    String trans = rs.getString("last_transaction_seq");
			    System.out.print(trans);
			    // Do something with the values
			}
	        
	            
	            // Close the connection
		         conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void addAcc(String name,String balance,String email, String pin) {
		try {
			DBConnection db = new DBConnection();
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = db.createConnection();
			  String insertSQL = "INSERT INTO accounts (name,balance,email,online_pin) VALUES (?,?,?,?) ";
	            PreparedStatement insertStatement = conn.prepareStatement(insertSQL);

	            // Set the values for the statement's parameters

	            insertStatement.setString(1, name);
	            insertStatement.setString(2, balance);
	            insertStatement.setString(3, email);
	            insertStatement.setString(4, pin);
	           

	            // Execute the statement
	            insertStatement.executeUpdate();

	            // Close the connection
	            conn.close();
	            System.out.print("new account added");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//addAcc("Kah Qing","5000","kahqing.chong@gmail.com","123456");
		show();
		

	}

}
