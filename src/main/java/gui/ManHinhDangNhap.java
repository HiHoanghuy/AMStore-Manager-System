package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import connectDB.ConnectDB;
import custom.ScaledImg;
import dao.NhanVien_DAO;
import entities.NhanVien;
import qrCode.CamQR;
import qrCode.ReadCodeQr;

@SuppressWarnings("serial")
public class ManHinhDangNhap extends JFrame{
	private JPanel contentPane;
	private JTextField txtMa;
	private JPasswordField pFDangNhap;
	private JLabel logo;
	private JButton btnDangNhap;
	private NhanVien_DAO nhanVien_DAO;
	public static NhanVien actNV;
	
	private static Webcam webcam;
	//public CamQR camQR;
	public static String codeQR;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManHinhDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManHinhDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManHinhDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManHinhDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManHinhDangNhap frame = new ManHinhDangNhap();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ManHinhDangNhap() {
		// Lấy đối tượng Toolkit
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        // Lấy kích thước màn hình
        Dimension ss = toolkit.getScreenSize();

		int w = ss.width, h=ss.height;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(w,h);
		setLocationRelativeTo(null);
		setUndecorated(true);
		getContentPane().setLayout(null);
		contentPane = new JPanel();
		contentPane.setVerifyInputWhenFocusTarget(false);
		contentPane.setBounds(0, 0, w, h);
		getContentPane().add(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(0, 0, 0, 30));
		
		
		
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		nhanVien_DAO = new NhanVien_DAO();
		
		JPanel pnLogin = new JPanel();
		
		int wlg=(int) (w*0.5), hlg=(int) (h*0.5);
		int xlg = (int) (w*0.25);
		int ylg = (int) (h*0.25);
		pnLogin.setBackground(Color.white);
		pnLogin.setBounds(xlg, ylg, wlg, hlg);
		pnLogin.setLayout(null);
		contentPane.add(pnLogin);
		
		txtMa = new JTextField();
		txtMa.setBounds((int)(wlg*0.5-70), (int)(hlg*0.5+0), 300, 30);
		txtMa.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtMa.setBackground(new Color(255,255,255,0));
		pnLogin.add(txtMa);
		txtMa.setColumns(10);
		
		JLabel lblMa = new JLabel("Mã nhân viên:");
		lblMa.setFont(new Font("Arial", Font.BOLD, 12));
		lblMa.setBounds((int)(wlg*0.5-70), (int)(hlg*0.5-30), 90, 30);
		pnLogin.add(lblMa);
		
		JLabel lblMatKhau = new JLabel("Mật Khẩu:");
		lblMatKhau.setFont(new Font("Arial", Font.BOLD, 12));
		lblMatKhau.setBounds((int)(wlg*0.5-70), (int)(hlg*0.5+30), 90, 30);
		pnLogin.add(lblMatKhau);
		
		pFDangNhap = new JPasswordField();
		pFDangNhap.setBounds((int)(wlg*0.5-70), (int)(hlg*0.5+60), 300, 30);
		pFDangNhap.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		pFDangNhap.setBackground(new Color(255,255,255,0));
		pnLogin.add(pFDangNhap);
		
		JLabel btnTroVe = new JLabel("Thoát");
		btnTroVe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Nhấn OK để thoát khỏi chương trình.", "Thoat", JOptionPane.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION) == 0) {
					webcam.close();
					ManHinhDangNhap.this.dispose();
					System.exit(0);
				}
			}
		});
		btnTroVe.setIcon(new ImageIcon(ManHinhDangNhap.class.getResource("/images/close.png")));
		btnTroVe.setBounds(wlg-60, 10, 55, 20);
		pnLogin.add(btnTroVe);
		
		JLabel btnShowPass = new JLabel("");
		btnShowPass.setIcon(new ImageIcon(ManHinhDangNhap.class.getResource("/images/show.png")));
		btnShowPass.setBounds((int)(wlg*0.5-70)+300, (int)(hlg*0.5+60), 40, 40);
		pnLogin.add(btnShowPass);
		
		char dot = pFDangNhap.getEchoChar();
		
		JLabel btnHidePass = new JLabel("");
		btnHidePass.setIcon(new ImageIcon(ManHinhDangNhap.class.getResource("/images/eye-off.png")));
		btnHidePass.setBounds((int)(wlg*0.5-70)+300, (int)(hlg*0.5+60), 40, 40);
		btnHidePass.setVisible(false);
		pnLogin.add(btnHidePass);
		
		logo = new JLabel();
		logo.setBounds((int)(wlg*0.5-70), 20, 140, 100);
		File f = new File(ManHinhDangNhap.class.getResource("/images/logoAM.jpg").getFile());
		BufferedImage img = null;
		try {
			img = ImageIO.read(f);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		logo.setIcon(new ImageIcon(ScaledImg.scaledImage(img, logo.getWidth(), logo.getHeight())));
		pnLogin.add(logo);
		
		btnShowPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pFDangNhap.setEchoChar((char)0);
				btnHidePass.setVisible(true);
				btnShowPass.setVisible(false);
			}
		});
		
		btnHidePass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pFDangNhap.setEchoChar(dot);
				btnShowPass.setVisible(true);
				btnHidePass.setVisible(false);
			}
		});
		
		btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.setBackground(new Color(0, 0, 255, 160));
		btnDangNhap.setForeground(new Color(245, 255, 250));
		btnDangNhap.setBounds((int)(wlg*0.5-70), (int)(hlg*0.85), 140, 40);
		pnLogin.add(btnDangNhap);
		
		
		webcam = Webcam.getDefault();
		webcam.setViewSize(new Dimension(176,144));
		//webcam.close();
		WebcamPanel webcamPanel = new WebcamPanel(webcam);
		webcamPanel.setBounds((int)(wlg*0.1), (int)(hlg*0.5-30), 176, 144);
		webcamPanel.setMirrored(false);
		pnLogin.add(webcamPanel);
		
		
		
		
		btnDangNhap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String maNV = txtMa.getText().trim();
				String matKhau = pFDangNhap.getText().trim();
				actNV = nhanVien_DAO.getNhanVien(maNV);
				if (actNV!=null) {
					if (maNV.equalsIgnoreCase(actNV.getMaNV()) && matKhau.equalsIgnoreCase(actNV.getTaiKhoan().getMatKhau())) {
						webcam.close();
						ManHinhChinh mhc = new ManHinhChinh(actNV.getChucVu().equalsIgnoreCase("Nhân viên")?false:true);
						mhc.setVisible(true);
						ManHinhDangNhap.this.dispose();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Sai thông tin đăng nhập!");
				}
			}
		});
		
		
		Thread thread = new ReadCodeQr(webcam, txtMa, pFDangNhap);
		thread.start();
		
		
		
	}
}
