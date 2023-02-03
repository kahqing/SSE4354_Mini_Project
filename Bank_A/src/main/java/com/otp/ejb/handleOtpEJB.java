package com.otp.ejb;
import javax.ejb.*;
import javax.ws.rs.*;

//This class consists web services for the merchant to request
//bank for to send OTP using EJB 
//also to access the EJB for OTP verification on Bank Site
@Path("/")
public class handleOtpEJB {

	@EJB
	private OTPSessionBean otpSession;
	
	@POST
	@Path("/sendOTP")
	public void processOTP(@QueryParam("id")String id) {
		// Request EJB to get the OTP
		String sotp = otpSession.getOTPFromCloud();
		String motp = sotp.substring(1, sotp.length()-1);
		int otp = Integer.valueOf(motp);
		otpSession.sendEmail(otp);
		// store the OTP to Database
		otpSession.storeOTP(otp,id);
	}
	
	@POST
	@Path("/verifyOTP")
	@Produces("text/plain")
	public boolean verifyOTP(@QueryParam("id")String id, @QueryParam("otp")int otp) {
		//Request EJB to verify OTP that filled by user
		boolean is_ValidOTP = otpSession.verifyOTP(id,otp);
		return is_ValidOTP;
		
	}
}
