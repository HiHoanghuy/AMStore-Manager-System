package qrCode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import gui.ManHinhChinh;
import gui.ManHinhDangNhap;

public class CamQR extends JDialog{
	public static Webcam webcam;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CamQR dialog = new CamQR();
			System.out.println(ManHinhDangNhap.codeQR);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CamQR() {
		setBounds(100, 100, 320, 260);
		setBackground(Color.white);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setUndecorated(true);
		webcam= Webcam.getDefault();
		webcam.setViewSize(new Dimension(320,240));
		//webcam.close();
		WebcamPanel webcamPanel = new WebcamPanel(webcam);
		webcamPanel.setBounds(0, 0, 320, 240);
		webcamPanel.setMirrored(false);
		JPanel pn_cam = new JPanel();
		getContentPane().add(pn_cam);
		pn_cam.setLayout(null);
		pn_cam.add(webcamPanel);
		pn_cam.setVisible(true);
		setVisible(true);
		
		JLabel btnExit = new JLabel("");
		btnExit.setIcon(new ImageIcon(ManHinhChinh.class.getResource("/images/close.png")));
		btnExit.setBounds(300, 0, 20, 20);
		add(btnExit);
		
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				dispose();
				webcamPanel.setVisible(false);
				webcam.close();
				revalidate();
			}
		});
		
		pn_cam.setBounds(0, 20, 320, 240);
		do {
			try {
			
				BufferedImage image = webcam.getImage();
				LuminanceSource source = new BufferedImageLuminanceSource(image);
				BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
				Result result = new MultiFormatReader().decode(bitmap);
				if (result.getText()!=null) {
					ManHinhDangNhap.codeQR = result.getText();
					//System.out.println(c);
					//JOptionPane.showMessageDialog(null, result.getText(),"", JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
					dispose();
					webcam.close();
					webcamPanel.setVisible(false);
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		} while (true);
	}

}
