import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestBank {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// Create a URL object for the web service endpoint
	        URL url = new URL("https://fastidious-pothos-4bd0f8.netlify.app/.netlify/functions/generateOTP");

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

	        // Close the connection
	        con.disconnect();
			}catch(Exception e) {
				System.out.println("Something went wrong!");
			}

	}

}
