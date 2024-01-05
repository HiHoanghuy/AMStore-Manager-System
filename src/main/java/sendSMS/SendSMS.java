package sendSMS; 

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;


public class SendSMS extends Thread{
    // Find your Account SID and Auth Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
    public static final String ACCOUNT_SID = "AC5d64aa963bd0c05df5cf61675d69fad1";//cần tạo tài khoản trên twilo.com.vn để lấy mã
    public static final String AUTH_TOKEN = "4e07f98101ae1f0f5bf1f7b7aa85d0bf";
    private String mess;
    
    
    
    public SendSMS(String mess) {
		super();
		this.mess = mess;
	}
	public void sendSMS(String mess) {
    	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+84963245242"),
                new com.twilio.type.PhoneNumber("+17147016359"),
                mess)
            .create();

        System.out.println(message.getSid());
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		sendSMS(mess);
	}
}