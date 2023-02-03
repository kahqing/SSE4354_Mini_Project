
public class otp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int randomNo = (int)(Math.random()*9000)+1000;
		String otp = String.valueOf(randomNo);
		System.out.println(otp);
	}

}
