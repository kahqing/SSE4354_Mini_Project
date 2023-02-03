package com.otp.ejb;

import java.sql.*;
import javax.ws.rs.*;
//This class consist web services offered by Bank A 
// 2 method is offered to merchant, which is to get Approval for payment
// if the balance of user is still enough
// and update the balance of account once the payment is made successfully.
@Path("/")
public class PaymentRequest {
@POST
@Path("/checkPin")
@Produces("text/plain")
public boolean authenticateUser(@QueryParam("id")String id, @QueryParam("pin")String pin) {
	boolean isValid = false;
	String online_pin="";
	try {
	// create database connection
	DBConnection db = new DBConnection();
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection conn = db.createConnection();
	//Obtain the OTP from database
	PreparedStatement stmt = conn.prepareStatement("SELECT online_pin FROM accounts where id=?");
	stmt.setString(1, id);
	ResultSet rs = stmt.executeQuery();
	if (rs.next()) {
        online_pin =  rs.getString("online_pin");
        //set to true if there is enough balance for payment
        if(pin.equals(online_pin)) {
            isValid = true;
        }
    }
	}catch(Exception e) {
		e.printStackTrace();
		System.out.print("Something went wrong...");
	}
	return isValid;
}
@POST
@Path("/checkBalance")
@Produces("text/plain")
public boolean approvePaymentRequest(@QueryParam("acc")String acc, 
		                    @QueryParam("pin")String pin,
							@QueryParam("payment")String payment) {
	String balance = "0";
	Double dbalance= 0.0;
	Double dpayment = Double.valueOf(payment);
	boolean enough_balance = false;
	try {
	// create database connection
	DBConnection db = new DBConnection();
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection conn = db.createConnection();
	//Obtain the OTP from database
	PreparedStatement stmt = conn.prepareStatement("SELECT balance FROM accounts where id=?");
	stmt.setString(1, acc);
	ResultSet rs = stmt.executeQuery();
	if (rs.next()) {
        balance =  rs.getString("balance");
        dbalance = Double.valueOf(balance);
        //set to true if there is enough balance for payment
        if(dbalance > dpayment) {
            enough_balance = true;
        }
    }
	}catch(Exception e) {
		e.printStackTrace();
		System.out.print("Something went wrong...");
	}
	return enough_balance;
}
@POST
@Path("/updateBalance")
public void updateBalance(@QueryParam("id")String id,@QueryParam("payment")String payment) {
	try {
		// create connection for database
		DBConnection db = new DBConnection();
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = db.createConnection();
		Double dpayment = Double.valueOf(payment);
		String balance = "0";
		Double dbalance= 0.0;
		//Retrieve the balance from database
		PreparedStatement stmt = conn.prepareStatement("SELECT balance FROM accounts where id=?");
		stmt.setString(1, id);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
	       balance = rs.getString("balance");
	     }
	    dbalance = Double.valueOf(balance);
	    double newBalance = dbalance - dpayment;
	    String dnBalance = Double.toString(newBalance);
	    //Deduct the balance with payment if it is successfully done
		String insertSQL = "UPDATE accounts SET balance = ? WHERE id = ?";
        PreparedStatement insertStatement = conn.prepareStatement(insertSQL);

            // Set the values for the statement's parameters
            insertStatement.setString(1, dnBalance);
            insertStatement.setString(2, id);
            // Execute the statement
            insertStatement.executeUpdate();

            // Close the connection
            conn.close();
	}catch(Exception e){
		e.printStackTrace();
	}
}
}
