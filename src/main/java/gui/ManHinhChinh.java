package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import connectDB.ConnectDB;
import custom.ScaledImg;
import dao.NhanVien_DAO;
import entities.NhanVien;
import qrCode.QRCodeScanner;
import sendSMS.SendSMS;
import custom.CustomScrollBarUI;
import custom.MenuItem;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ManHinhChinh extends JFrame{

	private JPanel contentPane;
	private JPanel pn_listMenu;
	public static JPanel pn_body;
	public static NhanVien nvAct;
	public static FormNhapThongTinKhachHangMoi form;
	
	public static ManHinhLapHoaDon manHinhLapHoaDon;
	public static ManHinhDatHang mh_dathang;
	
	public static SendSMS sms;
	public static Thread thread;
	public static Thread thread1;
	public static JButton btnEN ;
	public static JButton btnVN;
	
	// Lấy đối tượng Toolkit
    Toolkit toolkit = Toolkit.getDefaultToolkit();

    // Lấy kích thước màn hình
    Dimension ss = toolkit.getScreenSize();

	public static int w,h;
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
            java.util.logging.Logger.getLogger(ManHinhChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManHinhChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManHinhChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManHinhChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManHinhChinh frame = new ManHinhChinh(true);
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
	public ManHinhChinh(boolean isQL) {
		//w = ss.width; h=ss.height;
		w=1536;h=864; 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(w,h);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		sms = new SendSMS("");
		
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//manHinhLapHoaDon = new ManHinhLapHoaDon(true, "");
		form = new FormNhapThongTinKhachHangMoi();
		form.setVisible(false);
		
		JPanel pn_menu = new JPanel();
		pn_menu.setBackground(new Color(70, 130, 180));
		int wmn=(int)(w*0.18);
		int hmn=h;
		pn_menu.setBounds(0, 0, wmn, hmn);
		pn_menu.setBorder(new MatteBorder(0,0,0,2, new Color(0,0,0,100)));
		contentPane.add(pn_menu);
		pn_menu.setLayout(null);
		
		JLabel logo = new JLabel();
		logo.setBounds((int)(wmn*0.15), (int)(hmn*0.03), (int)(wmn*0.7), (int)(hmn*0.15));
		File f_logo = new File(ManHinhChinh.class.getResource("/images/logoAM.jpg").getFile());
		BufferedImage imgLogo = null;
		try {
			imgLogo = ImageIO.read(f_logo);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		logo.setIcon(new ImageIcon(ScaledImg.scaledImage(imgLogo, logo.getWidth(), logo.getHeight())));
		pn_menu.add(logo);
		
		JPanel pn_info = new JPanel();
		pn_info.setBackground(new Color(70, 130, 180));
		pn_info.setBounds(0, (int)(hmn*0.8), wmn-2, (int)(hmn*0.2));
		pn_info.setBorder(new MatteBorder(2,0,0,0, new Color(0,0,0,100)));
		pn_menu.add(pn_info);
		pn_info.setLayout(null);
		
		JLabel btnLogout = new JLabel("");
		btnLogout.setBounds(wmn-30, 10, 30, 18);
		btnLogout.setIcon(new ImageIcon(ManHinhChinh.class.getResource("/images/logOut.png")));
		pn_info.add(btnLogout);
		
		JLabel lblChucvu = new JLabel("Quản lý");
		lblChucvu.setFont(new Font("Arial", Font.BOLD, 14));
		lblChucvu.setForeground(new Color(255, 255, 255));
		lblChucvu.setBounds(10, (int)(hmn*0.2*0.5)-30, 120, 30);
		pn_info.add(lblChucvu);
		if (isQL) lblChucvu.setText("Quản lý");
		else lblChucvu.setText("NV Bán hàng");
		
		NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
		nvAct = ManHinhDangNhap.actNV;
		JLabel lblHoTen = new JLabel("");
		lblHoTen.setText(nvAct.getTenNV());
		lblHoTen.setForeground(new Color(255, 255, 255));
		lblHoTen.setText(ManHinhChinh.nvAct.getTenNV());
		lblHoTen.setFont(new Font("Arial", Font.PLAIN, 14));
		lblHoTen.setBounds(60, (int)(hmn*0.2*0.5)+30, 140, 30);
		pn_info.add(lblHoTen);
		
		JLabel lblIconNV = new JLabel("");
		lblIconNV.setIcon(new ImageIcon(ManHinhChinh.class.getResource("/images/userInfo.png")));
		lblIconNV.setBounds(10, (int)(hmn*0.2*0.5), 60, 60);
		pn_info.add(lblIconNV);
		
		
		
		pn_listMenu = new JPanel();
		pn_listMenu.setBackground(new Color(70, 130, 180));
		pn_listMenu.setBounds(0, (int)(hmn*0.2), wmn-2, (int)(hmn*0.6));
		
		JScrollPane jsp_menu = new JScrollPane(pn_listMenu);
		jsp_menu.setBounds(0, (int)(hmn*0.2), wmn-2, (int)(hmn*0.6));
		
		pn_listMenu.setLayout(new javax.swing.BoxLayout(pn_listMenu, javax.swing.BoxLayout.Y_AXIS));
		jsp_menu.setBorder(null);
        jsp_menu.setViewportView(pn_listMenu);
        jsp_menu.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        jsp_menu.getVerticalScrollBar().setPreferredSize(new Dimension(6,100));
        pn_menu.add(jsp_menu);
		
		pn_body = new JPanel();
		pn_body.setBackground(new Color(255, 255, 255));
		pn_body.setBounds(wmn, 0, w-wmn, h);
		contentPane.add(pn_body);
		pn_body.setLayout(null);
		
		JLabel hinhNen = new JLabel("");
		hinhNen.setBounds(0, 0, w-wmn, h);
		File f_hinhNen = new File(ManHinhDangNhap.class.getResource("/images/hinhNen.jpg").getFile());
		BufferedImage imgHinhNen = null;
		try {
			imgHinhNen = ImageIO.read(f_hinhNen);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		hinhNen.setIcon(new ImageIcon(ScaledImg.scaledImage(imgHinhNen, hinhNen.getWidth(), hinhNen.getHeight())));
		//pn_body.add(hinhNen);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 20, 1120, 710);
		pn_body.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("HỆ THỐNG QUẢN LÝ BÁN HÀNG CỬA HÀNG THỜI TRANG");
		lblNewLabel.setBounds(0, (int)(h*0.5)-18, w-wmn, 36);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(65, 105, 225));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		panel.add(lblNewLabel);
		
		JLabel btnExit = new JLabel("");
		System.out.println(w);
		btnExit.setIcon(new ImageIcon(ManHinhChinh.class.getResource("/images/close.png")));
		btnExit.setBounds(w-wmn-20, 0, 20, 20);
		pn_body.add(btnExit);
		
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(JOptionPane.showConfirmDialog(null, "Nhấn OK để thoát khỏi chương trình.", "Thoat", JOptionPane.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION) == 0) {
					ManHinhChinh.this.dispose();
					System.exit(0);
				}
			}
		});
		
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất không?.", "Đăng xuất", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == 0) {
					QRCodeScanner.webcam.close();
					ManHinhChinh.this.dispose();
					ManHinhDangNhap frame = new ManHinhDangNhap();
		    		frame.setVisible(true);
		    		ManHinhDangNhap.actNV=null;
		    		//sms.sendSMS("Bạn đã đăng xuất");
				}
			}
		});
		
		//menuVertical(,, );
		
		btnVN = new JButton("VN");
		btnVN.setBounds(10, 10, 45, 24);
		pn_info.add(btnVN);
		btnEN = new JButton("EN");
		btnEN.setBounds(60, 10, 45, 24);
		pn_info.add(btnEN);
		
		
		//-----------------------------------------------------------------------------------------------------------------------
		
		//boolean isQL  = true;
		ManHinhLapHoaDon mh_lapHoaDon = new ManHinhLapHoaDon(false,"");
		mh_dathang = new ManHinhDatHang();
		ManHinhTraHang mh_traHang = new ManHinhTraHang();
		ManHinhCapNhatNhanVien mh_capNhatNhanVien = new ManHinhCapNhatNhanVien();
		ManHinhTimKiemNhanVien mh_timKiemNhanVien = new ManHinhTimKiemNhanVien();
		ManHinhThongKeDoanhThu mh_thongKeDoanhThu = new ManHinhThongKeDoanhThu();
		ManHinhThongKeTonKho mh_thongKeTonKho = new ManHinhThongKeTonKho();
		ManHinhThongKeBanChay mh_thongKeBanChay = new ManHinhThongKeBanChay();
		ManHinhCapNhatKhachHang mh_capNhatKhachHang = new ManHinhCapNhatKhachHang();
		ManHinhTimKiemKhachHang mh_timKiemKhachHang = new ManHinhTimKiemKhachHang();
		ManHinhCapNhatSanPham mh_capNhatSanPham = new ManHinhCapNhatSanPham();
		ManHinhTimKiemSanPham mh_timKiemSanPham = new ManHinhTimKiemSanPham();
		ManHinhCapNhatNhaCungCap mh_capNhatNCC = new ManHinhCapNhatNhaCungCap();
		ManHinhTimKiemNhaCungCap mh_timKiemNCC = new ManHinhTimKiemNhaCungCap();
		ManHinhTimKiemHoaDon mh_timKiemHoaDon = new ManHinhTimKiemHoaDon();
		ManHinhCapNhatChuongTrinhKhuyenMai mh_capNhatCTKM = new ManHinhCapNhatChuongTrinhKhuyenMai();
		ManHinhTimKiemChuongTrinhKhuyenMai mh_timKiemCTKM = new ManHinhTimKiemChuongTrinhKhuyenMai();
		ManHinhTimKiemHoaDonTra mh_timKiemHoaDontra = new ManHinhTimKiemHoaDonTra();
		
		ImageIcon icon_submenu = new ImageIcon(ManHinhChinh.class.getResource("/images/point.png"));
		
		Color color1  = new Color(70, 130, 180);
		Color color2 =  new Color(55, 60, 255);
		
		MenuItem mn_capnhatKH = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_capnhat.png")), "Cập nhật khách hàng", swithPanel(mh_capNhatKhachHang), true, color1, color2);
		MenuItem mn_timkiemKH = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_timkiem.png")), "Tìm kiếm khách hàng", swithPanel(mh_timKiemKhachHang), true, color1, color2);
		
		//submenu sản phẩm
		MenuItem mn_capnhatSP = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_capnhat.png")), "Cập nhật sản phẩm", swithPanel(mh_capNhatSanPham), true, color1, color2);
		MenuItem mn_timkiemSP = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_timkiem.png")), "Tìm kiếm sản phẩm", swithPanel(mh_timKiemSanPham), true, color1, color2);
		
		//submenu nhân viên
		MenuItem mn_laphoadon = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_banhang.png")), "Lập hóa đơn", swithPanel(mh_lapHoaDon), true, color1, color2);
		MenuItem mn_datHang = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_dathang.png")), "Đặt hàng", swithPanel(mh_dathang), true, color1, color2);
		MenuItem mn_traHang = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_dathang.png")), "Trả hàng", swithPanel(mh_traHang), true, color1, color2);
		MenuItem mn_capnhatNV = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_capnhat.png")), "Cập nhật nhân viên", swithPanel(mh_capNhatNhanVien), true, color1, color2);
		MenuItem mn_timkiemNV = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_timkiem.png")), "Tìm kiếm nhân viên", swithPanel(mh_timKiemNhanVien), true, color1, color2);
		MenuItem mn_thongkeSPbanchay = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_tkbanchay.png")), "Thống kê sản phẩm bán chạy", swithPanel(mh_thongKeBanChay), true, color1, color2);
		MenuItem mn_thongkehangtonkho = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_tktonkho.png")), "Thống kê sản phẩm tồn kho", swithPanel(mh_thongKeTonKho), true, color1, color2);
		MenuItem mn_thongkedoanhthu = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_tkdoanhthu.png")), "Thống kê doanh thu", swithPanel(mh_thongKeDoanhThu), true, color1, color2);

		//submenu hóa đơn
		MenuItem mn_hdbanhang = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_timkiem.png")), "Tìm kiếm hóa đơn", swithPanel(mh_timKiemHoaDon), true, color1, color2);
		MenuItem mn_hdtrahang = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_timkiem.png")), "Tìm kiếm trả hàng", swithPanel(mh_timKiemHoaDontra), true, color1, color2);
		
		//submenu chương trình khuyến mãi
		MenuItem mn_capNhatCTKM = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_capnhat.png")), "Cập nhật CTKM", swithPanel(mh_capNhatCTKM), true, color1, color2);
		MenuItem mn_timkiemCTKM = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_timkiem.png")), "Tìm kiếm CTKM", swithPanel(mh_timKiemCTKM), true, color1, color2);
		
		//submenu nhà cung cấp
		MenuItem mn_capNhatNCC = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_capnhat.png")), "Cập nhật nhà cung cấp", swithPanel(mh_capNhatNCC), true, color1, color2);
		MenuItem mn_timkiemNCC = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_timkiem.png")), "Tìm kiếm nhà cung cấp", swithPanel(mh_timKiemNCC), true, color1, color2);
		
		//menu cha		
		MenuItem mn_khachhang = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_khachhang.png")), "Khách hàng", null, false, color1, color2, mn_capnhatKH, mn_timkiemKH);
		MenuItem mn_sanpham;
		MenuItem mn_nhacungcap = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_ncc.png")), "Nhà cung cấp", null, false, color1, color2, mn_capNhatNCC, mn_timkiemNCC);
		MenuItem mn_nhanvien;
		MenuItem mn_hoadon = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_hoadon.png")), "Hóa đơn", null, false, color1, color2, mn_hdbanhang, mn_hdtrahang);
		MenuItem mn_CTKM = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_ctkm.png")), "Chương trình khuyến mãi", null, false, color1, color2, mn_capNhatCTKM, mn_timkiemCTKM);
		
		//menu của quản lý
		if (isQL) {
			mn_nhanvien = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_nhanvien.png")), "Nhân viên", null, false, color1, color2, mn_laphoadon, mn_datHang,mn_traHang ,mn_capnhatNV, mn_timkiemNV, mn_thongkeSPbanchay, mn_thongkehangtonkho, mn_thongkedoanhthu);
			mn_sanpham= new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_sanpham.png")), "Sản phẩm", null, false, color1, color2, mn_capnhatSP, mn_timkiemSP);
			addMenu(mn_nhanvien, mn_khachhang, mn_hoadon, mn_sanpham,mn_nhacungcap, mn_CTKM);
		}
		//menu của nhân viên
		else {
			mn_nhanvien = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_nhanvien.png")), "Nhân viên", null, false, color1, color2, mn_laphoadon, mn_datHang, mn_thongkeSPbanchay, mn_thongkehangtonkho, mn_thongkedoanhthu);
			mn_sanpham= new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_sanpham.png")), "Sản phẩm", null, false, color1, color2, mn_timkiemSP);
			addMenu(mn_nhanvien, mn_khachhang, mn_hoadon, mn_sanpham);
		}
	
		
		btnVN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblNewLabel.setText("HỆ THỐNG QUẢN LÝ BÁN HÀNG CỬA HÀNG THỜI TRANG");
				if (isQL) lblChucvu.setText("Quản lý");
				else lblChucvu.setText("NV Bán hàng");
				//nhân viên
				mn_nhanvien.translateName("Nhân viên");
				mn_laphoadon.translateName("Lập hóa đơn");
				mn_datHang.translateName("Đặt hàng");
				mn_traHang.translateName("Trả hàng");
				mn_capnhatNV.translateName("Cập nhật");
				mn_timkiemNV.translateName("Tìm kiếm");
				mn_thongkeSPbanchay.translateName("Thống kê hàng bán chạy");
				mn_thongkehangtonkho.translateName("Thống kê hàng tồn kho");
				mn_thongkedoanhthu.translateName("Thống kê doanh thu");
				//khách hàng
				mn_khachhang.translateName("Khách hàng");
				mn_capnhatKH.translateName("Cập nhật");
				mn_timkiemKH.translateName("Tìm kiếm");
				//hóa đơn
				mn_hoadon.translateName("Hóa đơn");
				mn_hdbanhang.translateName("Tìm kiếm hóa đơn");
				mn_hdtrahang.translateName("Tìm kiếm trả hàng");
				//sản phẩm
				mn_sanpham.translateName("Sản phẩm");
				mn_capnhatSP.translateName("Cập nhật");
				mn_timkiemSP.translateName("Tìm kiếm");
				//nhà cung cấp
				mn_nhacungcap.translateName("Nhà cung cấp");
				mn_capNhatNCC.translateName("Cập nhật");
				mn_timkiemNCC.translateName("Tìm kiếm");
				//chương trình khuyến mãi
				mn_CTKM.translateName("Chương trình khuyến mãi");
				mn_capNhatCTKM.translateName("Cập nhật CTKM");
				mn_timkiemCTKM.translateName("Tìm kiếm CTKM");
			}
		});
		
		btnEN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblNewLabel.setText("FASHION STORE SALES MANAGEMENT SYSTEM");
				if (lblChucvu.getText()== "Quản lý") lblChucvu.setText("Manager");
				else lblChucvu.setText("Staff");
				//nhân viên
				mn_nhanvien.translateName("Staff");
				mn_laphoadon.translateName("Make an invoice");
				mn_datHang.translateName("Order");
				mn_traHang.translateName("Returns");
				mn_capnhatNV.translateName("Update");
				mn_timkiemNV.translateName("Search");
				mn_thongkeSPbanchay.translateName("Best-selling Statistics");
				mn_thongkehangtonkho.translateName("Inventory statistics");
				mn_thongkedoanhthu.translateName("Revenue statistics");
				//khách hàng
				mn_khachhang.translateName("Customer");
				mn_capnhatKH.translateName("Update");
				mn_timkiemKH.translateName("Search");
				//hóa đơn
				mn_hoadon.translateName("Invoice");
				mn_hdbanhang.translateName("Search for invoice");
				mn_hdtrahang.translateName("Search for return invoice");
				//sản phẩm
				mn_sanpham.translateName("Products");
				mn_capnhatSP.translateName("Update");
				mn_timkiemSP.translateName("Search");
				//nhà cung cấp
				mn_nhacungcap.translateName("Supplier");
				mn_capNhatNCC.translateName("Update");
				mn_timkiemNCC.translateName("Search");
				//chương trình khuyến mãi
				mn_CTKM.translateName("Promotions");
				mn_capNhatCTKM.translateName("Update");
				mn_timkiemCTKM.translateName("Search");
				
			}
		});
	}
	
	public static ActionListener swithPanel(JPanel panel) {
		ActionListener action = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pn_body.removeAll();
				pn_body.add(panel);
				pn_body.validate();
				pn_body.repaint();
			}
		};
		return action;
	}
	
	public void menuVertical() {
		//
		
	}
	
	
	private void addMenu(MenuItem... menu) {
        for (int i = 0; i < menu.length; i++) {
            pn_listMenu.add(menu[i]);
            ArrayList<MenuItem> subMenu = menu[i].getSubMenu();
            for (MenuItem m : subMenu) {
                addMenu(m);
            }
        }
        pn_listMenu.revalidate();
    }
}


	