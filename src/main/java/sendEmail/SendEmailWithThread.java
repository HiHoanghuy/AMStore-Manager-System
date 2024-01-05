package sendEmail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class SendEmailWithThread extends Thread {
	String toEmail;
	String mess;

    @Override
    public void run() {
        
		sendEmail(toEmail,mess);
    }

    public static void sendEmail(String toEmail,String mess) {
        final String username = "vmstore.hcm";
        final String password = "bfoe oydh zmyx hvju";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("vmstore.hcm"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Hoá đơn thanh toán tại cửa hàng vmstore");
            message.setText(mess);

            Transport.send(message);

            // Hiển thị thông báo khi email được gửi thành công
            JOptionPane.showMessageDialog(null, "Email sent");
            System.out.println("Done");

        } catch (MessagingException e1) {
            e1.printStackTrace();
        }
    }

	public SendEmailWithThread(String toEmail, String mess) {
		super();
		this.toEmail = toEmail;
		this.mess = mess;
	}

	
}
