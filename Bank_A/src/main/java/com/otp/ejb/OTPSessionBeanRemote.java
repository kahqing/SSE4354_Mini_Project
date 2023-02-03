package com.otp.ejb;

import java.util.Date;

import javax.ejb.Remote;

@Remote
public interface OTPSessionBeanRemote {

	public String getOTPFromCloud();
	public void sendEmail(int otp);
	public void storeOTP(int otp,String id);
	public boolean verifyOTP(String id, int enteredOTP);
	
}
