

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class topUpServlet
 */
//@WebServlet(urlPatterns = {"/mobile_topup.jsp", "/otp.jsp", "/paymentStatus.jsp"})
public class topUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public topUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("account");
		String pin = request.getParameter("pin");
		String payment = request.getParameter("topup");
		String requestURI = request.getRequestURI();
	        if (requestURI.endsWith("/mobile_topup.jsp")) {
	        	//call web service from Bank A to approve payment
	        	// Create a URL object for the web service endpoint
		        URL url = new URL("http://localhost:8080/Bank_A/balance?acc="+id+"&pin="+pin+"&payment="+payment);

		        // Open a connection to the web service
		        HttpURLConnection con = (HttpURLConnection) url.openConnection();

		        // Set the request method to GET
		        con.setRequestMethod("POST");

		        // Read the response
		        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		        String inputLine;
		        StringBuffer content = new StringBuffer();
		        while ((inputLine = in.readLine()) != null) {
		            content.append(inputLine);
		        }
		        in.close();
		        boolean paymentApproved = false;
		        String c = content.toString();
		       paymentApproved = Boolean.parseBoolean(c);
		        
		        if (paymentApproved)
		        	request.getRequestDispatcher("otp.jsp").forward(request, response);
	        } else if (requestURI.endsWith("/otp.jsp")) {
	            //call EJB from Bank A to send OTP 
	        } else if (requestURI.endsWith("/paymentStatus")) {
	            //call EJB from Bank A to verify OTP
	        }
		
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	


}
