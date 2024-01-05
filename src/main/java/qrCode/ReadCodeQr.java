package qrCode;

import java.awt.image.BufferedImage;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.github.sarxos.webcam.Webcam;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import gui.ManHinhChinh;
import gui.ManHinhDangNhap;

public class ReadCodeQr extends Thread{
	private Webcam webcam;
	private JTextField textField;
	private JPasswordField passwordField;
	public ReadCodeQr() {
		
	}
	
	public ReadCodeQr(Webcam webcam, JTextField textField, JPasswordField passwordField) {
		super();
		this.webcam = webcam;
		this.textField = textField;
		this.passwordField = passwordField;
	}

	public void readQR(Webcam webcam, JTextField textField, JPasswordField passwordField) {
		do {
			try {
				BufferedImage image = webcam.getImage();
				LuminanceSource source = new BufferedImageLuminanceSource(image);
				BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
				Result result = new MultiFormatReader().decode(bitmap);
				if (result.getText()!=null) {
					ManHinhDangNhap.codeQR = result.getText();
					
					
					//webcamPanel.setVisible(false);
					System.out.println(ManHinhDangNhap.codeQR);
					int vt=0;
					for (int i = 0; i < ManHinhDangNhap.codeQR.length(); i++) {
						if (ManHinhDangNhap.codeQR.charAt(i)==';') {
							vt=i;
						}
					}
					String id = ManHinhDangNhap.codeQR.substring(0, vt);
					String pass = ManHinhDangNhap.codeQR.substring(vt+1, ManHinhDangNhap.codeQR.length());
					textField.setText(id);
					passwordField.setText(pass);
					//break;
					
					
				}
				sleep(1500);
			} catch (Exception e1) {
				// TODO: handle exception
			}
		} while (true);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		readQR(webcam, textField, passwordField);
	}
}
