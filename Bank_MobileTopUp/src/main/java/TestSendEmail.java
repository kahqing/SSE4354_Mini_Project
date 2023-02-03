import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class TestSendEmail {

	public static void sendEmail(String email, String otp) {
	        
		Properties properties = new Properties();
		  properties = System.getProperties();
		  properties.put("mail.smtp.host", "smtp.gmail.com");
		  properties.put("mail.smtp.port", "465");
		  properties.put("mail.smtp.auth", "true");
		  properties.put("mail.imap.ssl.enable", "true");
	        
	        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
	        	   protected PasswordAuthentication getPasswordAuthentication() {
	        		    return new PasswordAuthentication("", 
	        		    		"");
	        		   }
	        		  });
	        session.setDebug(true);
	        try {
	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("kahqing.chong@gmail.com"));
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
	            message.setSubject("Top Up Payment");
	            message.setText("Dear customer!This is an verification email"
	            		+ "from Bank A. Your OTP: " + otp);

	          
	            Transport.send(message);
	            
	            System.out.println("Email sent successfully.");
	        } catch (MessagingException e) {
	            throw new RuntimeException(e);
	        }
}
	public static void main(String[] args) {
		
		sendEmail("","123456");
	}
}
