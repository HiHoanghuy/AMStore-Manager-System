package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.AbstractDateModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import connectDB.ConnectDB;
import custom.CustomScrollBarUI;
import custom.GeneratorID;
import custom.RoundedCornerBorder;
import dao.DonDatHang_DAO;
import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entities.DonDatHang;
import entities.NhanVien;
import entities.TaiKhoan;
import qrCode.QRCodeGenerator;

import javax.swing.JComboBox;

public class ManHinhCapNhatNhanVien extends JPanel {

	private JTextField txt_tMa;
	private JButton btnTimNV;

	private JScrollPane scr_Ds;
	private JTable tbl_Ds;
	private DefaultTableModel model_ds;

	private JLabel lbl_kqMa;

	private DecimalFormat df;
	private DateTimeFormatter dtf;

	private JTextField txt_hoTenNV;
	private JTextField txt_ngaySinh;
	private JTextField txt_CCCD;
	private JTextField txt_soDienThoai;
	private JTextField txt_email;
	private JTextField txt_diaChi;
	private JTextField txt_LuongCoBan;
	private JTextField txt_heSoLuong;
	private JTextField txt_PhuCap;
	private JTextField txt_matKhau;
	private JComboBox cmb_gioiTinh;
	private JComboBox cmb_chucVu;
	private JComboBox cmb_caLam;

	private JLabel lbl_kqTGBD;
	private JLabel lbl_kqTGKT;

	private UtilDateModel model_date;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;

	private NhanVien_DAO nhanVien_dao;
	private List<NhanVien> dsNV;
	private TaiKhoan_DAO taiKhoan_dao;
	private JTextField txt_tTen;

	/**
	 * Create the panel.
	 */
	public ManHinhCapNhatNhanVien() {

		/**
		 * Create the panel.
		 */

		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setSize(1259, 864);

		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		df = new DecimalFormat("#,###");
		dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");// định dạng ngày kiểu vd:11-11-2023

		nhanVien_dao = new NhanVien_DAO();
		dsNV = nhanVien_dao.getDsNhanVien();
		taiKhoan_dao = new TaiKhoan_DAO();

		JPanel pn_thaotac = new JPanel();
		pn_thaotac.setBackground(new Color(255, 255, 255));
		pn_thaotac.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true),
				"C\u1EADp nh\u1EADt th\u00F4ng tin nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(65, 105, 225)));
		pn_thaotac.setBounds(10, 50, 1239, 270);
		add(pn_thaotac);
		pn_thaotac.setLayout(null);

		JPanel timKiem = new JPanel();
		timKiem.setBackground(new Color(255, 255, 255));
		timKiem.setBounds(40, 20, 190, 30);
		timKiem.setBorder(new RoundedCornerBorder());
		pn_thaotac.add(timKiem);
		timKiem.setLayout(null);

		txt_tMa = new JTextField();
		txt_tMa.setText("Nhập mã nhân viên...");
		txt_tMa.setForeground(Color.GRAY);
		txt_tMa.setEditable(false);
		txt_tMa.setBackground(new Color(255, 255, 255));
		txt_tMa.setBounds(10, 3, 170, 24);
		txt_tMa.setBorder(null);
		txt_tMa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txt_tMa.setText("");
				txt_tMa.setForeground(Color.BLACK);
				txt_tMa.setEditable(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				txt_tMa.setText("Nhập mã sản phẩm ...");
				txt_tMa.setForeground(Color.GRAY);
			}
		});
		timKiem.add(txt_tMa);
		txt_tMa.setColumns(10);

		JPanel pn_kqTimKiem = new JPanel();
		pn_kqTimKiem.setLayout(null);
		pn_kqTimKiem.setBackground(new Color(255, 250, 240));
		pn_kqTimKiem.setBounds(10, 60, 1219, 200);
		pn_thaotac.add(pn_kqTimKiem);

		JLabel lblMaNV = new JLabel("Mã nhân viên:");
		lblMaNV.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaNV.setFont(new Font("Arial", Font.PLAIN, 11));
		lblMaNV.setBounds(10, 15, 80, 20);
		pn_kqTimKiem.add(lblMaNV);

		lbl_kqMa = new JLabel("");
		lbl_kqMa.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqMa.setBounds(90, 15, 100, 20);
		pn_kqTimKiem.add(lbl_kqMa);

		JLabel lblTenNV = new JLabel("Họ tên:");
		lblTenNV.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenNV.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTenNV.setBounds(210, 15, 60, 20);
		pn_kqTimKiem.add(lblTenNV);

		JLabel lblCCCD = new JLabel("CCCD:");
		lblCCCD.setHorizontalAlignment(SwingConstants.LEFT);
		lblCCCD.setFont(new Font("Arial", Font.PLAIN, 11));
		lblCCCD.setBounds(210, 64, 60, 20);
		pn_kqTimKiem.add(lblCCCD);

		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setHorizontalAlignment(SwingConstants.LEFT);
		lblGioiTinh.setFont(new Font("Arial", Font.PLAIN, 11));
		lblGioiTinh.setBounds(710, 16, 50, 20);
		pn_kqTimKiem.add(lblGioiTinh);

		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgaySinh.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNgaySinh.setBounds(460, 15, 60, 20);
		pn_kqTimKiem.add(lblNgaySinh);

		JButton btnSua = new JButton("Sửa");
		btnSua.setBounds(999, 160, 100, 30);
		pn_kqTimKiem.add(btnSua);
		btnSua.setFont(new Font("Arial", Font.BOLD, 14));
		btnSua.setBackground(new Color(244, 164, 96));

		txt_hoTenNV = new JTextField();
		txt_hoTenNV.setBackground(new Color(255, 250, 240));
		txt_hoTenNV.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_hoTenNV.setHorizontalAlignment(SwingConstants.LEFT);
		txt_hoTenNV.setBounds(270, 15, 160, 20);
		pn_kqTimKiem.add(txt_hoTenNV);
		txt_hoTenNV.setColumns(10);

		txt_ngaySinh = new JTextField();
		txt_ngaySinh.setHorizontalAlignment(SwingConstants.LEFT);
		txt_ngaySinh.setColumns(10);
		txt_ngaySinh.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_ngaySinh.setBackground(new Color(255, 250, 240));
		txt_ngaySinh.setBounds(520, 15, 140, 20);
		// pn_kqTimKiem.add(txt_ngaySinh);

		//
		model_date = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(model_date, p);
		model_date.setValue(new Date(100, 00, 01));
		datePicker = new JDatePickerImpl(datePanel, new custom.DateLabelFormatter());
		datePicker.setBackground(new Color(255, 255, 255));
		datePicker.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		datePicker.setBounds(520, 13, 150, 27);
		datePicker.getJDateInstantPanel().setShowYearButtons(true);
		datePicker.getJFormattedTextField().setText("01-01-2000");
		datePicker.setButtonFocusable(false);
		// datePicker.getModel().setDate(2000, 1, 1);
		pn_kqTimKiem.add(datePicker);

		String[] item_gt = { "Nam", "Nữ" };
		cmb_gioiTinh = new JComboBox(item_gt);
		cmb_gioiTinh.setBackground(new Color(245, 222, 179));
		cmb_gioiTinh.setBounds(790, 15, 90, 25);
		pn_kqTimKiem.add(cmb_gioiTinh);

		txt_CCCD = new JTextField();
		txt_CCCD.setHorizontalAlignment(SwingConstants.LEFT);
		txt_CCCD.setColumns(10);
		txt_CCCD.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_CCCD.setBackground(new Color(255, 250, 240));
		txt_CCCD.setBounds(270, 65, 160, 20);
		pn_kqTimKiem.add(txt_CCCD);

		JLabel lblSoDienThoai = new JLabel("Số điện thoại:");
		lblSoDienThoai.setHorizontalAlignment(SwingConstants.LEFT);
		lblSoDienThoai.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSoDienThoai.setBounds(10, 65, 80, 20);
		pn_kqTimKiem.add(lblSoDienThoai);

		txt_soDienThoai = new JTextField();
		txt_soDienThoai.setHorizontalAlignment(SwingConstants.LEFT);
		txt_soDienThoai.setColumns(10);
		txt_soDienThoai.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_soDienThoai.setBackground(new Color(255, 250, 240));
		txt_soDienThoai.setBounds(90, 65, 100, 20);
		pn_kqTimKiem.add(txt_soDienThoai);

		JLabel lblemail = new JLabel("Email:");
		lblemail.setHorizontalAlignment(SwingConstants.LEFT);
		lblemail.setFont(new Font("Arial", Font.PLAIN, 11));
		lblemail.setBounds(460, 115, 30, 20);
		// pn_kqTimKiem.add(lblemail);

		txt_email = new JTextField();
		txt_email.setHorizontalAlignment(SwingConstants.LEFT);
		txt_email.setColumns(10);
		txt_email.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_email.setBackground(new Color(255, 250, 240));
		txt_email.setBounds(490, 115, 360, 20);
		// pn_kqTimKiem.add(txt_email);

		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setHorizontalAlignment(SwingConstants.LEFT);
		lblDiaChi.setFont(new Font("Arial", Font.PLAIN, 11));
		lblDiaChi.setBounds(10, 115, 80, 20);
		pn_kqTimKiem.add(lblDiaChi);

		txt_diaChi = new JTextField();
		txt_diaChi.setHorizontalAlignment(SwingConstants.LEFT);
		txt_diaChi.setColumns(10);
		txt_diaChi.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_diaChi.setBackground(new Color(255, 250, 240));
		txt_diaChi.setBounds(90, 115, 340, 20);
		pn_kqTimKiem.add(txt_diaChi);

		JLabel lblChucVu = new JLabel("Chức vụ:");
		lblChucVu.setHorizontalAlignment(SwingConstants.LEFT);
		lblChucVu.setFont(new Font("Arial", Font.PLAIN, 11));
		lblChucVu.setBounds(709, 61, 50, 20);
		pn_kqTimKiem.add(lblChucVu);

		String[] item_cv = { "Nhân viên", "Quản lí" };
		cmb_chucVu = new JComboBox(item_cv);
		cmb_chucVu.setBackground(new Color(245, 222, 179));
		cmb_chucVu.setBounds(790, 60, 90, 25);
		pn_kqTimKiem.add(cmb_chucVu);

		JLabel lblLuongCoBan = new JLabel("Lương cơ bản:");
		lblLuongCoBan.setHorizontalAlignment(SwingConstants.LEFT);
		lblLuongCoBan.setFont(new Font("Arial", Font.PLAIN, 11));
		lblLuongCoBan.setBounds(460, 115, 80, 20);
		pn_kqTimKiem.add(lblLuongCoBan);

		txt_LuongCoBan = new JTextField();
		txt_LuongCoBan.setHorizontalAlignment(SwingConstants.LEFT);
		txt_LuongCoBan.setColumns(10);
		txt_LuongCoBan.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_LuongCoBan.setBackground(new Color(255, 250, 240));
		txt_LuongCoBan.setBounds(540, 115, 100, 20);
		pn_kqTimKiem.add(txt_LuongCoBan);

		JLabel lblNewLabel = new JLabel("VND");
		lblNewLabel.setBounds(640, 115, 25, 20);
		pn_kqTimKiem.add(lblNewLabel);

		JLabel lblHeSoLuong = new JLabel("Hệ số lương:");
		lblHeSoLuong.setHorizontalAlignment(SwingConstants.LEFT);
		lblHeSoLuong.setFont(new Font("Arial", Font.PLAIN, 11));
		lblHeSoLuong.setBounds(710, 115, 100, 20);
		pn_kqTimKiem.add(lblHeSoLuong);

		txt_heSoLuong = new JTextField();
		txt_heSoLuong.setHorizontalAlignment(SwingConstants.LEFT);
		txt_heSoLuong.setColumns(10);
		txt_heSoLuong.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_heSoLuong.setBackground(new Color(255, 250, 240));
		txt_heSoLuong.setBounds(810, 116, 70, 20);
		pn_kqTimKiem.add(txt_heSoLuong);

		JLabel lblPhuCap = new JLabel("Phụ cấp:");
		lblPhuCap.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhuCap.setFont(new Font("Arial", Font.PLAIN, 11));
		lblPhuCap.setBounds(460, 65, 60, 20);
		pn_kqTimKiem.add(lblPhuCap);

		txt_PhuCap = new JTextField();
		txt_PhuCap.setHorizontalAlignment(SwingConstants.LEFT);
		txt_PhuCap.setColumns(10);
		txt_PhuCap.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_PhuCap.setBackground(new Color(255, 250, 240));
		txt_PhuCap.setBounds(520, 65, 120, 20);
		pn_kqTimKiem.add(txt_PhuCap);

		JLabel lblNewLabel_1 = new JLabel("VND");
		lblNewLabel_1.setBounds(640, 65, 25, 20);
		pn_kqTimKiem.add(lblNewLabel_1);

		JLabel lblMatKhau = new JLabel("*Mật khẩu:");
		lblMatKhau.setHorizontalAlignment(SwingConstants.LEFT);
		lblMatKhau.setFont(new Font("Arial", Font.PLAIN, 11));
		lblMatKhau.setBounds(10, 160, 60, 20);
		pn_kqTimKiem.add(lblMatKhau);

		txt_matKhau = new JTextField();
		txt_matKhau.setHorizontalAlignment(SwingConstants.LEFT);
		txt_matKhau.setColumns(10);
		txt_matKhau.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_matKhau.setBackground(new Color(255, 250, 240));
		txt_matKhau.setBounds(90, 160, 140, 20);
		pn_kqTimKiem.add(txt_matKhau);

		JPanel pnl_calam = new JPanel();
		pnl_calam.setBackground(new Color(255, 250, 240));
		pnl_calam.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(245, 222, 179)));
		pnl_calam.setBounds(947, 10, 210, 120);
		pn_kqTimKiem.add(pnl_calam);
		pnl_calam.setLayout(null);

		JLabel lblCaLam = new JLabel("Ca làm:");
		lblCaLam.setBounds(10, 5, 45, 20);
		pnl_calam.add(lblCaLam);

		String[] item_cl = { "Ca 1", "Ca 2" };
		cmb_caLam = new JComboBox(item_cl);
		cmb_caLam.setBackground(new Color(245, 222, 179));
		cmb_caLam.setBounds(55, 4, 80, 25);
		pnl_calam.add(cmb_caLam);

		JLabel lblTGBD = new JLabel("Thời gian bắt đầu:");
		lblTGBD.setBounds(10, 50, 100, 20);
		pnl_calam.add(lblTGBD);

		lbl_kqTGBD = new JLabel("8:00");
		lbl_kqTGBD.setBounds(110, 50, 46, 20);
		pnl_calam.add(lbl_kqTGBD);

		JLabel lblTGKT = new JLabel("Thời gian kết thúc:");
		lblTGKT.setBounds(10, 80, 100, 20);
		pnl_calam.add(lblTGKT);

		lbl_kqTGKT = new JLabel("14:00");
		lbl_kqTGKT.setBounds(110, 80, 46, 20);
		pnl_calam.add(lbl_kqTGKT);

		JButton btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setFont(new Font("Arial", Font.BOLD, 14));
		btnXoaTrang.setBackground(new Color(255, 0, 0));
		btnXoaTrang.setBounds(889, 160, 100, 30);
		pn_kqTimKiem.add(btnXoaTrang);

		JButton btnThemNV = new JButton("Thêm");
		btnThemNV.setBounds(1109, 160, 100, 30);
		pn_kqTimKiem.add(btnThemNV);
		btnThemNV.setFont(new Font("Arial", Font.BOLD, 14));
		btnThemNV.setBackground(new Color(0, 128, 0));

		btnTimNV = new JButton("Tìm");
		btnTimNV.setBounds(1129, 20, 100, 30);
		pn_thaotac.add(btnTimNV);
		// btnSearch.setBorder(new RoundedCornerBorder());
		btnTimNV.setBackground(new Color(65, 105, 225));
		btnTimNV.setIcon(new ImageIcon(ManHinhLapHoaDon.class.getResource("/images/search.png")));
		
		JLabel lbl_tMa = new JLabel("Mã:");
		lbl_tMa.setBounds(10, 20, 30, 30);
		pn_thaotac.add(lbl_tMa);
		
		JLabel lbl_tTen = new JLabel("Tên:");
		lbl_tTen.setBounds(240, 20, 50, 30);
		pn_thaotac.add(lbl_tTen);
		
		JPanel timKiem_1 = new JPanel();
		timKiem_1.setLayout(null);
		timKiem_1.setBorder(new RoundedCornerBorder());
		timKiem_1.setBackground(Color.WHITE);
		timKiem_1.setBounds(300, 20, 190, 30);
		pn_thaotac.add(timKiem_1);
		
		txt_tTen = new JTextField();
		txt_tTen.setText("Nhập tên nhân viên...");
		txt_tTen.setForeground(Color.GRAY);
		txt_tTen.setEditable(false);
		txt_tTen.setColumns(10);
		txt_tTen.setBorder(null);
		txt_tTen.setBackground(Color.WHITE);
		txt_tTen.setBounds(10, 3, 170, 24);
		timKiem_1.add(txt_tTen);
		txt_tTen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txt_tTen.setText("");
				txt_tTen.setForeground(Color.BLACK);
				txt_tTen.setEditable(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				txt_tTen.setText("Nhập tên sản phẩm ...");
				txt_tTen.setForeground(Color.GRAY);
			}
		});
		
		JLabel lbl_tChucVu = new JLabel("Chức vụ:");
		lbl_tChucVu.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_tChucVu.setFont(new Font("Arial", Font.PLAIN, 11));
		lbl_tChucVu.setBounds(510, 20, 50, 30);
		pn_thaotac.add(lbl_tChucVu);
		
		String[] item_tcv = { "Tất cả", "Nhân viên", "Quản lí" };
		JComboBox cmb_tChucVu = new JComboBox(item_tcv);
		cmb_tChucVu.setBackground(new Color(245, 222, 179));
		cmb_tChucVu.setBounds(560, 20, 110, 30);
		pn_thaotac.add(cmb_tChucVu);
		
		JLabel lbl_tCaLam = new JLabel("Ca làm:");
		lbl_tCaLam.setBounds(680, 20, 50, 30);
		pn_thaotac.add(lbl_tCaLam);
		
		String[] item_tcl = { "Tất cả", "Ca 1", "Ca 2" };
		JComboBox cmb_tCaLam = new JComboBox(item_tcl);
		cmb_tCaLam.setBackground(new Color(245, 222, 179));
		cmb_tCaLam.setBounds(730, 20, 110, 30);
		pn_thaotac.add(cmb_tCaLam);
		
		JLabel lbl_tGioiTinh = new JLabel("Giới tính:");
		lbl_tGioiTinh.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_tGioiTinh.setFont(new Font("Arial", Font.PLAIN, 11));
		lbl_tGioiTinh.setBounds(850, 20, 50, 30);
		pn_thaotac.add(lbl_tGioiTinh);
		
		String[] item_tgt = { "Tất cả", "Nam", "Nữ" };
		JComboBox cmb_tGioiTinh = new JComboBox(item_tgt);
		cmb_tGioiTinh.setBackground(new Color(245, 222, 179));
		cmb_tGioiTinh.setBounds(900, 20, 110, 30);
		pn_thaotac.add(cmb_tGioiTinh);

		JPanel pn_dsnv = new JPanel();
		pn_dsnv.setBackground(new Color(255, 255, 255));
		pn_dsnv.setBorder(
				new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Danh s\u00E1ch nh\u00E2n vi\u00EAn",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_dsnv.setBounds(10, 320, 1239, 534);
		add(pn_dsnv);
		pn_dsnv.setLayout(null);

		model_ds = new DefaultTableModel(new Object[][] {

		}, new String[] { "Mã nhân viên", "Họ tên", "Ngày sinh", "Giới tính", "Số điện thoại", "Địa chỉ", "Chức vụ",
				"Ca làm", "Lương" }

		) {
			boolean[] canEdit = new boolean[] { false, false, false, false, false, false, false, false, false };

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return canEdit[column];
			}
		};

		tbl_Ds = new JTable(model_ds);
		tbl_Ds.setSelectionBackground(new Color(65, 105, 225));
		tbl_Ds.setRowHeight(40);
		tbl_Ds.setGridColor(new Color(0, 0, 0));
		tbl_Ds.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tbl_Ds.getColumnModel().getColumn(0).setPreferredWidth(60);
		tbl_Ds.getColumnModel().getColumn(1).setPreferredWidth(130);
		tbl_Ds.getColumnModel().getColumn(2).setPreferredWidth(50);
		tbl_Ds.getColumnModel().getColumn(3).setPreferredWidth(30);
		tbl_Ds.getColumnModel().getColumn(4).setPreferredWidth(60);
		tbl_Ds.getColumnModel().getColumn(5).setPreferredWidth(240);
		tbl_Ds.getColumnModel().getColumn(6).setPreferredWidth(40);
		tbl_Ds.getColumnModel().getColumn(7).setPreferredWidth(30);
		tbl_Ds.getColumnModel().getColumn(8).setPreferredWidth(70);

		DefaultTableCellRenderer head_render = new DefaultTableCellRenderer();
		head_render.setBackground(new Color(135, 205, 230));
		tbl_Ds.getTableHeader().setDefaultRenderer(head_render);

		scr_Ds = new JScrollPane();
		scr_Ds.setViewportView(tbl_Ds);
		scr_Ds.setBounds(10, 20, 1219, 504);
		scr_Ds.getViewport().setBackground(Color.white);
		scr_Ds.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		pn_dsnv.add(scr_Ds);

		JLabel btnExit = new JLabel("");
		btnExit.setIcon(new ImageIcon(ManHinhChinh.class.getResource("/images/close.png")));
		btnExit.setBounds(1239, 0, 20, 20);
		add(btnExit);
		
		//xử lý sự kiện khi click vào nút thoát chương trình
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (JOptionPane.showConfirmDialog(null, "Nhấn OK để thoát khỏi chương trình.", "Thoat",
						JOptionPane.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION) == 0) {
					System.exit(0);
				}
			}
		});

		JLabel lblTenManHinh = new JLabel("CẬP NHẬT THÔNG TIN NHÂN VIÊN");
		lblTenManHinh.setFont(new Font("Arial", Font.BOLD, 20));
		lblTenManHinh.setForeground(new Color(100, 149, 237));
		lblTenManHinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenManHinh.setBounds(20, 0, 1209, 50);
		add(lblTenManHinh);
		
		//lấy dữ liệu ban đầu khi khởi chạy chương trình
		dsNV = nhanVien_dao.getDsNhanVien();
		capNhatDuLieuCuaBang(dsNV);
		
		//xử lý sự kiện khi click vào 1 dòng trong bảng danh sách nhân viên
		tbl_Ds.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int selected = tbl_Ds.getSelectedRow();
				String maNV = (String) model_ds.getValueAt(selected, 0);
				NhanVien nv = nhanVien_dao.getNhanVien(maNV);
				hienThiThongTinNhanVien(nv);
			}
		});
		
		//xử lý sự kiện khi click vào nút tìm kiếm nhân viên
		btnTimNV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String maNV = txt_tMa.getText();
				maNV = maNV.equalsIgnoreCase("Nhập mã nhân viên...")?"":maNV;
				String hoTenNV = txt_tTen.getText();
				hoTenNV = hoTenNV.equalsIgnoreCase("Nhập tên nhân viên...")?"":hoTenNV;
				String chucVu = (String) cmb_tChucVu.getSelectedItem();
				chucVu=chucVu.equalsIgnoreCase("Tất cả")?"%":chucVu;
				String caLam = (String) cmb_tCaLam.getSelectedItem();
				caLam = caLam.equalsIgnoreCase("Tất cả")?"%":caLam;
				String gioiTinh = (String) cmb_tGioiTinh.getSelectedItem();
				if (gioiTinh.equalsIgnoreCase("Tất cả")) {
					gioiTinh = "%";
				} else if (gioiTinh.equalsIgnoreCase("Nam")) {
					gioiTinh = "1";
				} else gioiTinh= "0";
				dsNV = nhanVien_dao.timKiem("%"+maNV+"%", "%"+hoTenNV+"%", "%%", "%%", "%%", chucVu, caLam, gioiTinh);
				//dsNV= nhanVien_DAO.timKiem("%", "%", "%", "%", "%", chucVu, caLam, gioiTinh);
				if (dsNV.size()==0) {
					JOptionPane.showMessageDialog(null, "Không tìm thấy!");
				}
				else {					
					capNhatDuLieuCuaBang(dsNV);
				}
			}
		});
		
		//xử lý sự kiện khi click vào nút xóa trắng 
		btnXoaTrang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				xoaTrang();
			}
		});
		
		//xử lý sự kiện khi click vào nút thêm nhân viên mới
		btnThemNV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (kiemTraDuLieuNhap()) {
					String hoten = txt_hoTenNV.getText();
					String cccd = txt_CCCD.getText();
					String diaChi = txt_diaChi.getText();
					String email = txt_email.getText();
					double heSoLuong = Double.parseDouble(txt_heSoLuong.getText());
					double luongCoBan = Double.parseDouble(txt_LuongCoBan.getText().replaceAll(",", ""));
					String matKhau = txt_matKhau.getText();
					Date selectedDate = (Date) datePicker.getModel().getValue();
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					String mydate = dateFormat.format(selectedDate);
					int nam = Integer.parseInt(mydate.substring(6, 10));
					int thang = Integer.parseInt(mydate.substring(3, 5));
					int ngay = Integer.parseInt(mydate.substring(0, 2));
					LocalDateTime ngaySinh = LocalDateTime.of(nam, thang, ngay, 0, 0);
					double phuCap = Double.parseDouble(txt_PhuCap.getText().replaceAll(",", ""));
					String sdt = txt_soDienThoai.getText();
					boolean gt = cmb_gioiTinh.getSelectedItem().toString().equalsIgnoreCase("Nam") ? true : false;
					String chucVu = cmb_chucVu.getSelectedIndex() == 0 ? "Nhân viên" : "Quản lí";
					String caLam = cmb_caLam.getSelectedIndex() == 0 ? "Ca 1" : "Ca 2";
					String ma = GeneratorID.generateIDNhanVien(chucVu);
					TaiKhoan tk = new TaiKhoan(ma, matKhau);
					NhanVien nv = new NhanVien(ma, hoten, sdt, gt, ngaySinh, chucVu, caLam, diaChi, email, cccd, tk,
							phuCap, heSoLuong, luongCoBan);
					if (nhanVien_dao.themNhanVien(nv) && taiKhoan_dao.themTaiKhoan(tk)) {
						dsNV = nhanVien_dao.getDsNhanVien();
						capNhatDuLieuCuaBang(dsNV);
						QRCodeGenerator.generateQRCodeImage(ma+";"+matKhau, "C://MaQRNhanVien/", ma+"_"+hoten);
						JOptionPane.showMessageDialog(null, "Thành công!");
					} else
						JOptionPane.showMessageDialog(null, "Thất bại!");
				}

			}
		});
		
		//xử lý sự kiện khi click vào nút Sửa
		btnSua.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (kiemTraDuLieuNhap()) {
					String hoten = txt_hoTenNV.getText();
					String cccd = txt_CCCD.getText();
					String diaChi = txt_diaChi.getText();
					String email = txt_email.getText();
					double heSoLuong = Double.parseDouble(txt_heSoLuong.getText());
					double luongCoBan = Double.parseDouble(txt_LuongCoBan.getText().replaceAll(",", ""));
					String matKhau = txt_matKhau.getText();
					Date selectedDate = (Date) datePicker.getModel().getValue();
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					String mydate = dateFormat.format(selectedDate);
					int nam = Integer.parseInt(mydate.substring(6, 10));
					int thang = Integer.parseInt(mydate.substring(3, 5));
					int ngay = Integer.parseInt(mydate.substring(0, 2));
					LocalDateTime ngaySinh = LocalDateTime.of(nam, thang, ngay, 0, 0);
					double phuCap = Double.parseDouble(txt_PhuCap.getText().replaceAll(",", ""));
					String sdt = txt_soDienThoai.getText();
					boolean gt = cmb_gioiTinh.getSelectedItem().toString().equalsIgnoreCase("Nam") ? true : false;
					String chucVu = cmb_chucVu.getSelectedIndex() == 0 ? "Nhân viên" : "Quản lí";
					String caLam = cmb_caLam.getSelectedIndex() == 0 ? "Ca 1" : "Ca 2";
					String ma = lbl_kqMa.getText();
					TaiKhoan tk = new TaiKhoan(ma, matKhau);
					NhanVien nv = new NhanVien(ma, hoten, sdt, gt, ngaySinh, chucVu, caLam, diaChi, email, cccd, tk,
							phuCap, heSoLuong, luongCoBan);					
					if (nhanVien_dao.capNhatNhanVien(ma, nv) && taiKhoan_dao.capNhatTaiKhoan(ma, tk)) {
						dsNV = nhanVien_dao.getDsNhanVien();
						capNhatDuLieuCuaBang(dsNV);
						JOptionPane.showMessageDialog(null, "Thành công!");
					} else
						JOptionPane.showMessageDialog(null, "Thất bại!");
				}
			}
		});
		ManHinhChinh.btnVN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblTenManHinh.setText("CẬP NHẬT THÔNG TIN NHÂN VIÊN");
				((TitledBorder) pn_thaotac.getBorder()).setTitle("Cập nhật thông tinh nhân viên");
				pn_thaotac.repaint();
				((TitledBorder) pn_dsnv.getBorder()).setTitle("Danh sách nhân viên");
				pn_dsnv.repaint();
				lbl_tMa.setText("Mã:");
				lbl_tTen.setText("Tên:");
				lbl_tChucVu.setText("Chức vụ:");
				lbl_tCaLam.setText("Ca làm:");
				lbl_tGioiTinh.setText("Giới tính:");
				
				//txt_tMa.setText("Nhập mã nhân viên...");
				//txt_tTen.setText("Nhập tên nhân viên...");
				
				lblMaNV.setText("Mã nhân viên:");
				lblTenNV.setText("Họ tên:");
				lblNgaySinh.setText("Ngày sinh:");
				lblGioiTinh.setText("Giới tính:");
				lblCaLam.setText("Ca làm:");
				lblSoDienThoai.setText("Số điện thoại:");
				lblCCCD.setText("CCCD:");
				lblPhuCap.setText("Phụ cấp:");
				lblChucVu.setText("Chức vụ:");
				lblTGBD.setText("Thời gian bắt đầu:");
				lblTGKT.setText("Thời gian kết thúc:");
				lblDiaChi.setText("Địa chỉ:");
				lblLuongCoBan.setText("Lương cơ bản:");
				lblHeSoLuong.setText("Hệ số lương:");
				lblMatKhau.setText("*Mật khẩu:");
				
				btnTimNV.setText("Tìm");
				btnXoaTrang.setText("Xoá trắng");
				btnSua.setText("Sửa");
				btnThemNV.setText("Thêm");
				String[] newColumn = {"Mã nhân viên", "Họ tên", "Ngày sinh", "Giới tính", "Số điện thoại", "Địa chỉ", "Chức vụ",
						"Ca làm", "Lương" };
				model_ds.setColumnIdentifiers(newColumn);
			
				
			}
		});
		
		ManHinhChinh.btnEN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblTenManHinh.setText("UPDATE EMPLOYEE INFORMATION");
				((TitledBorder) pn_thaotac.getBorder()).setTitle("Update employee information");
				pn_thaotac.repaint();
				((TitledBorder) pn_dsnv.getBorder()).setTitle("List of employees");
				pn_dsnv.repaint();
				lbl_tMa.setText("Id:");
				lbl_tTen.setText("Name:");
				lbl_tChucVu.setText("Position:");
				lbl_tCaLam.setText("Shift:");
				lbl_tGioiTinh.setText("Gender:");

				//txt_tMa.setText("Enter employee code...");
				//txt_tTen.setText("Enter employee name...");

				lblMaNV.setText("Employee ID:");
				lblTenNV.setText("Full name:");
				lblNgaySinh.setText("Birthday:");
				lblGioiTinh.setText("Gender:");
				lblCaLam.setText("Shift:");
				lblSoDienThoai.setText("Phone number:");
				lblCCCD.setText("CCCD:");
				lblPhuCap.setText("Allowance:");
				lblChucVu.setText("Position:");
				lblTGBD.setText("Start time:");
				lblTGKT.setText("End time:");
				lblDiaChi.setText("Address:");
				lblLuongCoBan.setText("Basic salary:");
				lblHeSoLuong.setText("Salary coefficient:");
				lblMatKhau.setText("*Password:");

				btnTimNV.setText("Find");
				btnXoaTrang.setText("Clear blank");
				btnSua.setText("Edit");
				btnThemNV.setText("Add");
				String[] newColumn = {"Employee Id", "Full name", "Date of birth", "Gender", "Phone number", "Address", "Position",
				"Shift", "Salary" };
				model_ds.setColumnIdentifiers(newColumn);
			}
		});
	}
	
	/**
	 * xóa trắng dữ liệu trên phần hiển thị thông tin nhân viên
	 */
	private void xoaTrang() {
		lbl_kqMa.setText("");
		txt_hoTenNV.setText("");
		txt_CCCD.setText("");
		txt_diaChi.setText("");
		txt_email.setText("");
		txt_heSoLuong.setText("");
		txt_LuongCoBan.setText("");
		txt_matKhau.setText("");
		datePicker.getJFormattedTextField().setText("01-01-2000");
		txt_PhuCap.setText("");
		txt_soDienThoai.setText("");
		cmb_gioiTinh.setSelectedIndex(0);
		cmb_chucVu.setSelectedIndex(0);
		cmb_caLam.setSelectedIndex(0);
		lbl_kqTGBD.setText("9:00");
		lbl_kqTGKT.setText("15:00");
		txt_tMa.setText("Nhập mã nhân viên...");
		txt_tMa.setForeground(Color.gray);
		txt_tTen.setText("Nhập tên nhân viên...");
		txt_tTen.setForeground(Color.gray);
		
	}
	
	/**
	 * hiển thị thông tin nhân viên khi click chọn 1 dòng dưới bảng danh sách nhân viên
	 * @param nv
	 */
	private void hienThiThongTinNhanVien(NhanVien nv) {
		lbl_kqMa.setText(nv.getMaNV());
		txt_hoTenNV.setText(nv.getTenNV());
		txt_CCCD.setText(nv.getCccd());
		txt_diaChi.setText(nv.getDiaChi());
		txt_email.setText(nv.getEmail());
		txt_heSoLuong.setText(nv.getHeSoLuong() + "");
		txt_LuongCoBan.setText(df.format(nv.getLuongCoBan()));
		txt_matKhau.setText(nv.getTaiKhoan().getMatKhau());
		model_date.setValue(new Date(nv.getNgaySinh().getYear() - 1900, nv.getNgaySinh().getMonthValue() - 1,
				nv.getNgaySinh().getDayOfMonth()));
		datePicker.getJFormattedTextField().setText(dtf.format(nv.getNgaySinh()));
		txt_PhuCap.setText(df.format(nv.getPhuCap()));
		txt_soDienThoai.setText(nv.getSdt());
		cmb_gioiTinh.setSelectedIndex(nv.isGioiTinh() ? 0 : 1);
		cmb_chucVu.setSelectedIndex(nv.getChucVu().equalsIgnoreCase("Nhân viên") ? 0 : 1);
		if (nv.getCaLamViec().equalsIgnoreCase("Ca 1")) {
			cmb_caLam.setSelectedIndex(0);
			lbl_kqTGBD.setText("9:00");
			lbl_kqTGKT.setText("15:00");
		} else {
			cmb_caLam.setSelectedIndex(1);
			lbl_kqTGBD.setText("15:00");
			lbl_kqTGKT.setText("21:00");
		}
	}
	
	/**
	 * xóa dữ liệu trên bảng 
	 */
	private void xoaDuLieuCuaBang() {
		DefaultTableModel dtm = (DefaultTableModel) tbl_Ds.getModel();
		dtm.getDataVector().removeAllElements();
	}
	
	/**
	 * cập nhật lại dữ liệu cho bảng chứa danh sách nhân viên
	 * @param ds
	 */
	private void capNhatDuLieuCuaBang(List<NhanVien> ds) {
		xoaDuLieuCuaBang();
		for (NhanVien nv : ds) {
			Object data[] = { nv.getMaNV(), nv.getTenNV(), dtf.format(nv.getNgaySinh()), nv.isGioiTinh() ? "Nam" : "Nữ",
					nv.getSdt(), nv.getDiaChi(), nv.getChucVu(), nv.getCaLamViec(),
					df.format(nv.tinhLuong()) + " VND" };
			model_ds.addRow(data);
		}
	}
	
	
	/**
	 * hàm kiểm tra thông tin của nhân viên được nhập vào
	 * @return true khi đúng, nếu sai thông báo và trả về false;
	 */
	private boolean kiemTraDuLieuNhap() {
		String hoTen = txt_hoTenNV.getText().trim();
		String sdt = txt_soDienThoai.getText().trim();
		String cccd = txt_CCCD.getText().trim();
		String diaChi = txt_diaChi.getText().trim();
		String phuCap = txt_PhuCap.getText().trim();
		String luongCoBan = txt_LuongCoBan.getText().trim();
		String heSoLuong = txt_heSoLuong.getText().trim();
		Date selectedDate = (Date) datePicker.getModel().getValue();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String mydate = dateFormat.format(selectedDate);
		int nam = Integer.parseInt(mydate.substring(6, 10));
		int thang = Integer.parseInt(mydate.substring(3, 5));
		int ngay = Integer.parseInt(mydate.substring(0, 2));
		LocalDate ngaySinh = LocalDate.of(nam, thang, ngay);
		String matKhau = txt_matKhau.getText().trim();
		if (!(hoTen.length() > 0 && hoTen
				.matches("^(" + GeneratorID.tiengVietLow().toUpperCase() + GeneratorID.tiengVietLow() + "*((\\s)))+"
						+ GeneratorID.tiengVietLow().toUpperCase() + GeneratorID.tiengVietLow() + "*$"))) {
			JOptionPane.showMessageDialog(null, "Họ Tên không chứa ký tự số. VD: Nguyễn Văn A");
			txt_hoTenNV.requestFocus();
			txt_hoTenNV.selectAll();
			return false;
		}
		if (!(sdt.length() == 10 && sdt.matches("([0])([0-9]{9})"))) {
			JOptionPane.showMessageDialog(null, "Số Điện thoại bắt đầu bằng số 0 và độ dài số điện thoại bằng 10");
			txt_soDienThoai.requestFocus();
			txt_soDienThoai.selectAll();
			return false;
		}
		if (!(diaChi.length() > 0)) {
			JOptionPane.showMessageDialog(null, "Nhập địa chỉ khách hàng");
			txt_diaChi.requestFocus();
			txt_diaChi.selectAll();
			return false;
		}
		if (!(phuCap.length() > 0 && phuCap.matches("[0-9]+((\\.|\\,)[0-9]+)*"))) {
			JOptionPane.showMessageDialog(null, "Phụ cấp là số thực!");
			txt_PhuCap.requestFocus();
			txt_PhuCap.selectAll();
			return false;
		}
		if (!(luongCoBan.length() > 0 && luongCoBan.matches("[0-9]+((\\.|\\,)[0-9]+)*"))) {
			JOptionPane.showMessageDialog(null, "Lương cơ bản là số thực!");
			txt_LuongCoBan.requestFocus();
			txt_LuongCoBan.selectAll();
			return false;
		}
		if (!(heSoLuong.length() > 0 && heSoLuong.matches("[0-9]+(\\.[0-9]+)?"))) {
			JOptionPane.showMessageDialog(null, "Hệ số lương là số thực!");
			txt_heSoLuong.requestFocus();
			txt_heSoLuong.selectAll();
			return false;
		}
		if (!(cccd.length() > 0 && cccd.matches("[0-9]{12}"))) {
			JOptionPane.showMessageDialog(null, "Căn cước công dân có độ dài 12 kí tự!");
			txt_CCCD.requestFocus();
			txt_CCCD.selectAll();
			return false;
		}
		if (Period.between(ngaySinh, LocalDate.now()).getYears() < 18) {
			JOptionPane.showMessageDialog(null, "Chưa đủ 18 tuổi");
			return false;
		}
		if (!(matKhau.length()>0)) {
			JOptionPane.showMessageDialog(null, "Nhập mật khẩu đăng nhập!");
			txt_matKhau.requestFocus();
			txt_matKhau.selectAll();
			return false;
		}
		return true;
	}
}
