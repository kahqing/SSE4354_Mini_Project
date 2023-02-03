package com.otp.ejb;
import java.sql.*;
import java.util.Date;
import java.util.Properties;
import java.time.LocalDateTime;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Session Bean implementation class OTPSessionBean
 */
@Stateless
@LocalBean
public class OTPSessionBean implements OTPSessionBeanRemote, OTPSessionBeanLocal {

    /**
     * Default constructor. 
     */
    public OTPSessionBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String getOTPFromCloud() {
		String otp = "";
		try {
			// Create a URL object for the web service endpoint
	        URL url = new URL("https://fastidious-pothos-4bd0f8.netlify.app/.netlify/functions/generateOTP");

	        // Open a connection to the web service
	        HttpURLConnection con = (HttpURLConnection) url.openConnection();

	        // Set the request method to GET
	        con.setRequestMethod("GET");

	        // Read the response
	        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        String inputLine;
	        StringBuffer content = new StringBuffer();
	        while ((inputLine = in.readLine()) != null) {
	            content.append(inputLine);
	        }
	        in.close();
	        otp = content.toString();
	        // Close the connection
	        con.disconnect();
			}catch(Exception e) {
				System.out.println("Something went wrong!");
			}
		return otp;
	}

	@Override
	public void sendEmail(int otp) {
		System.out.println("Dear Customer!");
		System.out.println("This is an Email from Bank A");
		System.out.println("Your OTP: " + otp);
	}

	@Override
	public void storeOTP(int otp,String id) {
		// TODO Auto-generated method stub
		try {
			DBConnection db = new DBConnection();
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = db.createConnection();
			  String insertSQL = "UPDATE accounts SET otp = ? WHERE id = ?";
	            PreparedStatement insertStatement = conn.prepareStatement(insertSQL);

	            // Set the values for the statement's parameters
	            insertStatement.setInt(1, otp);
	            insertStatement.setString(2, id);
	           

	            // Execute the statement
	            insertStatement.executeUpdate();

	            // Close the connection
	            conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean verifyOTP(String id,int enteredOTP) {
		int otp_db = 0;
		try {
	     // get OTP from the database
		DBConnection db = new DBConnection();
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = db.createConnection();
		PreparedStatement stmt = conn.prepareStatement("SELECT otp FROM accounts where id=?");
		stmt.setString(1, id);
		ResultSet rs = stmt.executeQuery();
		 while (rs.next()) {
			 otp_db =  rs.getInt("otp");
		 }
		
		 // set the expiry time for OTP
		 LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(5);
		 // this is the current time to submit OTP
		 LocalDateTime now = LocalDateTime.now();
		 // if valid return true flag and update flag in database
	        if (enteredOTP == otp_db && now.isBefore(expiryTime)) {
	        	//stmt = conn.prepareStatement("UPDATE accounts SET opt_expire = ? WHERE id = ?");
	            return true;
	        }else {
	        	return false;
	        }
	        
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
		
	}

}
