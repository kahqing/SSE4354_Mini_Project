

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MakePayment {

	public void makePayment(String payment) {
	try {
		// Create a URL object for the web service endpoint
        URL url = new URL("http://localhost:8080/Bank_A/balance?acc=10000&pin=123456");

        // Open a connection to the web service
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // Set the request method to GET
        con.setRequestMethod("GET");

        // Print the response status code
        System.out.println(con.getResponseCode());

        // Print the response message
        System.out.println(con.getResponseMessage());

        // Read the response
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        // Print the response
        System.out.println(content.toString());
        
        String balance = content.toString();
        double dbalance = Double.parseDouble(balance);
        double dpayment = Double.parseDouble(payment);
        if(dbalance > dpayment) {
        	// Create a URL object for the web service endpoint
            URL url2 = new URL("http://localhost:8080/Bank_A/balance?acc=10000&pin=123456");

            // Open a connection to the web service
            HttpURLConnection con2 = (HttpURLConnection) url2.openConnection();

            // Set the request method to GET
            con2.setRequestMethod("GET");

            // Print the response status code
            System.out.println(con2.getResponseCode());

            // Print the response message
            System.out.println(con2.getResponseMessage());

            // Read the response
            BufferedReader in2 = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine2;
            StringBuffer content2 = new StringBuffer();
            while ((inputLine2 = in.readLine()) != null) {
                content.append(inputLine2);
            }
            in2.close();

            // Print the response
            System.out.println(content2.toString());
        }
        // Close the connection
        con.disconnect();
		}catch(Exception e) {
			System.out.println("Something went wrong!");
		}
	}
 
}