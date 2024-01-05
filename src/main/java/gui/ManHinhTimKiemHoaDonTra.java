package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import connectDB.ConnectDB;
import custom.CustomScrollBarUI;
import custom.GeneratorID;
import custom.RoundedCornerBorder;
import dao.DonDatHang_DAO;
import dao.HoaDonTra_DAO;
import dao.HoaDon_DAO;
import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entities.DonDatHang;
import entities.HoaDon;
import entities.HoaDonTra;
import entities.NhanVien;
import entities.TaiKhoan;
import report.Report;
import report.ReportB;
import entities.ChuongTrinhKhuyenMai;
import dao.ChuongTrinhKhuyenMai_DAO;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class ManHinhTimKiemHoaDonTra extends JPanel {

	private JTextField txtSearch;
	private JButton btnSearch;

	private JScrollPane scr_Ds;
	private static JTable tbl_Ds;
	private static DefaultTableModel model_ds;

	private static DecimalFormat df;
	private static DateTimeFormatter dtf;

	//private JComboBox cmb_chuongTrinhKM;

	private JTextField txt_tongTien;
	private JTextField txt_tienTra;

	private UtilDateModel model_date1;
	private JDatePanelImpl datePanel1;
	private JDatePickerImpl datePicker1;

	//private HoaDon_DAO hoaDon_DAO;
	private HoaDonTra_DAO hoaDonTra_DAO;
	private static List<HoaDonTra> dsHDT;
	private JTextField txt_maHD;
	private JTextField txt_tenNV;
	private JTextField txt_tenKH;
	private JTextField txt_sdtKH;
	private HoaDonTra hdt;
	private JTextField txt_maDT;

	/**
	 * Create the panel.
	 */
	public ManHinhTimKiemHoaDonTra() {

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
		dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		hoaDonTra_DAO = new HoaDonTra_DAO();
dsHDT = hoaDonTra_DAO.getDsHoaDonTra();

		JPanel pn_thaotac = new JPanel();
		pn_thaotac.setBackground(new Color(255, 255, 255));
		pn_thaotac.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Tìm Kiếm Hóa Đơn Trả",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_thaotac.setBounds(10, 50, 1239, 170);
		add(pn_thaotac);
		pn_thaotac.setLayout(null);

		btnSearch = new JButton("Tìm");
		// btnSearch.setBorder(new RoundedCornerBorder());
		btnSearch.setBackground(new Color(65, 105, 225));
		btnSearch.setIcon(new ImageIcon(ManHinhTimKiemHoaDon.class.getResource("/images/search.png")));
		btnSearch.setBounds(1109, 100, 100, 30);

		JPanel pn_kqTimKiem = new JPanel();
		pn_kqTimKiem.setLayout(null);
		pn_kqTimKiem.setBackground(new Color(255, 250, 240));
		pn_kqTimKiem.setBounds(10, 20, 1219, 140);
		pn_thaotac.add(pn_kqTimKiem);

		pn_kqTimKiem.add(btnSearch);

		JLabel lblmaHD = new JLabel("Mã Hóa Đơn:");
		lblmaHD.setHorizontalAlignment(SwingConstants.LEFT);
		lblmaHD.setFont(new Font("Arial", Font.PLAIN, 11));
		lblmaHD.setBounds(10, 18, 80, 20);
		pn_kqTimKiem.add(lblmaHD);

		JLabel lblnhanVien = new JLabel("Tên nhân viên:");
		lblnhanVien.setHorizontalAlignment(SwingConstants.LEFT);
		lblnhanVien.setFont(new Font("Arial", Font.PLAIN, 11));
		lblnhanVien.setBounds(590, 21, 90, 20);
		pn_kqTimKiem.add(lblnhanVien);

		JLabel lblngayLap = new JLabel("Ngày trả:");
		lblngayLap.setHorizontalAlignment(SwingConstants.LEFT);
		lblngayLap.setFont(new Font("Arial", Font.PLAIN, 11));
		lblngayLap.setBounds(10, 64, 67, 20);
		pn_kqTimKiem.add(lblngayLap);

		JLabel lbltongTien = new JLabel("Tổng tiền:");
		//lbltongTien.setVisible(false);
		lbltongTien.setHorizontalAlignment(SwingConstants.LEFT);
		lbltongTien.setFont(new Font("Arial", Font.PLAIN, 11));
		lbltongTien.setBounds(182, 109, 67, 20);
		//pn_kqTimKiem.add(lbltongTien);

		JLabel lbltienTra = new JLabel("Tiền trả:");
		lbltienTra.setVisible(false);
		lbltienTra.setHorizontalAlignment(SwingConstants.LEFT);
		lbltienTra.setFont(new Font("Arial", Font.PLAIN, 11));
		lbltienTra.setBounds(397, 121, 67, 20);
		//pn_kqTimKiem.add(lbltienTra);

		JLabel lblkhuyenMai = new JLabel("Khuyến mãi:");
		lblkhuyenMai.setVisible(false);
		lblkhuyenMai.setHorizontalAlignment(SwingConstants.LEFT);
		lblkhuyenMai.setFont(new Font("Arial", Font.PLAIN, 11));
		lblkhuyenMai.setBounds(362, 93, 67, 20);
		//pn_kqTimKiem.add(lblkhuyenMai);

		JLabel lblkhachHang = new JLabel("Tên khách hàng:");
		lblkhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		lblkhachHang.setFont(new Font("Arial", Font.PLAIN, 11));
		lblkhachHang.setBounds(278, 64, 90, 20);
		pn_kqTimKiem.add(lblkhachHang);
		
		txt_tienTra = new JTextField();
		txt_tienTra.setText("Nhập mã...");
		txt_tienTra.setForeground(Color.GRAY);
		txt_tienTra.setEditable(false);
		txt_tienTra.setColumns(10);
		txt_tienTra.setBorder(null);
		txt_tienTra.setBackground(Color.WHITE);
txt_tienTra.setBounds(441, 122, 160, 20);		
		
		txt_tongTien = new JTextField();
		txt_tongTien.setVisible(false);
		txt_tongTien.setBackground(new Color(255, 250, 240));
		txt_tongTien.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_tongTien.setHorizontalAlignment(SwingConstants.LEFT);
		txt_tongTien.setBounds(243, 110, 160, 20);
		//pn_kqTimKiem.add(txt_tongTien);
		txt_tongTien.setColumns(10);

		//
		model_date1 = new UtilDateModel();
		Properties p1 = new Properties();
		p1.put("text.today", "Today");
		p1.put("text.month", "Month");
		p1.put("text.year", "Year");
		datePanel1 = new JDatePanelImpl(model_date1, p1);
		model_date1.setValue(new Date(123, 00, 01));
		datePicker1 = new JDatePickerImpl(datePanel1, new custom.DateLabelFormatter());
		datePicker1.setBackground(new Color(255, 255, 255));
		datePicker1.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		datePicker1.setBounds(87, 63, 130, 27);
		datePicker1.getJDateInstantPanel().setShowYearButtons(true);
		datePicker1.getJFormattedTextField().setText("Tất cả");
		datePicker1.setButtonFocusable(false);
		// datePicker.getModel().setDate(2000, 1, 1);
		pn_kqTimKiem.add(datePicker1);

		JButton btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setFont(new Font("Arial", Font.BOLD, 14));
		btnXoaTrang.setBackground(new Color(255, 0, 0));
		btnXoaTrang.setBounds(999, 100, 100, 30);
		pn_kqTimKiem.add(btnXoaTrang);


		
		JPanel pnl_nhapMa = new JPanel();
		pnl_nhapMa.setLayout(null);
		pnl_nhapMa.setBorder(new RoundedCornerBorder());
		pnl_nhapMa.setBackground(Color.WHITE);
		pnl_nhapMa.setBounds(80, 15, 140, 30);
		pn_kqTimKiem.add(pnl_nhapMa);
		txt_maHD = new JTextField();
		txt_maHD.setText("Nhập mã...");
		txt_maHD.setForeground(Color.GRAY);
		txt_maHD.setEditable(false);
		txt_maHD.setColumns(10);
		txt_maHD.setBorder(null);
		txt_maHD.setBackground(Color.WHITE);
		txt_maHD.setBounds(15, 5, 120, 20);
		txt_maHD.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txt_maHD.setText("");
				txt_maHD.setForeground(Color.BLACK);
				txt_maHD.setEditable(true);
			}
		});
		pnl_nhapMa.add(txt_maHD);

		JPanel pnl_tenNV = new JPanel();
		pnl_tenNV.setLayout(null);
		pnl_tenNV.setBorder(new RoundedCornerBorder());
		pnl_tenNV.setBackground(Color.WHITE);
		pnl_tenNV.setBounds(680, 18, 180, 30);
		pn_kqTimKiem.add(pnl_tenNV);
		txt_tenNV = new JTextField();
		txt_tenNV.setText("Nhập tên NV..");
txt_tenNV.setForeground(Color.GRAY);
		txt_tenNV.setEditable(false);
		txt_tenNV.setColumns(10);
		txt_tenNV.setBorder(null);
		txt_tenNV.setBackground(Color.WHITE);
		txt_tenNV.setBounds(15, 5, 160, 20);
		txt_tenNV.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				txt_tenNV.setText("");
				txt_tenNV.setForeground(Color.BLACK);
				txt_tenNV.setEditable(true);
			}
		});
		pnl_tenNV.add(txt_tenNV);
		

		JPanel pnl_tenKH = new JPanel();
		pnl_tenKH.setLayout(null);
		pnl_tenKH.setBorder(new RoundedCornerBorder());
		pnl_tenKH.setBackground(Color.WHITE);
		pnl_tenKH.setBounds(368, 60, 180, 30);
		pn_kqTimKiem.add(pnl_tenKH);
		txt_tenKH = new JTextField();
		txt_tenKH.setText("Nhập tên KH..");
		txt_tenKH.setForeground(Color.GRAY);
		txt_tenKH.setEditable(false);
		txt_tenKH.setColumns(10);
		txt_tenKH.setBorder(null);
		txt_tenKH.setBackground(Color.WHITE);
		txt_tenKH.setBounds(15, 5, 160, 20);
		txt_tenKH.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txt_tenKH.setText("");
				txt_tenKH.setForeground(Color.BLACK);
				txt_tenKH.setEditable(true);
			}
		});
		pnl_tenKH.add(txt_tenKH);
		
		JLabel lblsdt = new JLabel("SĐT khách hàng:");
		lblsdt.setHorizontalAlignment(SwingConstants.LEFT);
		lblsdt.setFont(new Font("Arial", Font.PLAIN, 11));
		lblsdt.setBounds(278, 18, 90, 20);
		pn_kqTimKiem.add(lblsdt);
		
		
		JPanel pnl_sdtKH = new JPanel();
		pnl_sdtKH.setLayout(null);
		pnl_sdtKH.setBorder(new RoundedCornerBorder());
		pnl_sdtKH.setBackground(Color.WHITE);
		pnl_sdtKH.setBounds(368, 15, 140, 30);
		pn_kqTimKiem.add(pnl_sdtKH);
		txt_sdtKH = new JTextField();
		txt_sdtKH.setText("Nhập sdt KH..");
		txt_sdtKH.setForeground(Color.GRAY);
		txt_sdtKH.setEditable(false);
		txt_sdtKH.setColumns(10);
		txt_sdtKH.setBorder(null);
		txt_sdtKH.setBackground(Color.WHITE);
		txt_sdtKH.setBounds(15, 5, 120, 20);
		txt_sdtKH.addMouseListener(new MouseListener() {
			
			@Override
public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txt_sdtKH.setText("");
				txt_sdtKH.setForeground(Color.BLACK);
				txt_sdtKH.setEditable(true);
			}
		});
		pnl_sdtKH.add(txt_sdtKH);
		
		
		JButton btnXemChiTiet = new JButton("Xem chi tiết");
		btnXemChiTiet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReportB.xuatHoaDonPDF("sMaHD", hdt.getMaDT().toString(), "images\\", false);
				System.out.println(hdt.getMaDT());
			}
		});
		btnXemChiTiet.setBounds(889, 100, 100, 30);
		pn_kqTimKiem.add(btnXemChiTiet);
		
		JLabel lblmaDT = new JLabel("Mã Đơn Trả:");
		lblmaDT.setBounds(590, 67, 80, 20);
		pn_kqTimKiem.add(lblmaDT);
		lblmaDT.setHorizontalAlignment(SwingConstants.LEFT);
		lblmaDT.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JPanel pnl_maDT = new JPanel();
		pnl_maDT.setLayout(null);
		pnl_maDT.setBorder(new RoundedCornerBorder());
		pnl_maDT.setBackground(Color.WHITE);
		pnl_maDT.setBounds(680, 63, 140, 30);
		pn_kqTimKiem.add(pnl_maDT);
		
		txt_maDT = new JTextField();
		txt_maDT.setText("Nhập mã...");
		txt_maDT.setForeground(Color.GRAY);
		txt_maDT.setColumns(10);
		txt_maDT.setBorder(null);
		txt_maDT.setBackground(Color.WHITE);
		txt_maDT.setBounds(15, 5, 120, 20);
		txt_maDT.setEditable(false);
		pnl_maDT.add(txt_maDT);
		txt_maDT.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txt_maDT.setText("");
				txt_maDT.setForeground(Color.BLACK);
				txt_maDT.setEditable(true);
			}
		});

		JPanel pn_dsnv = new JPanel();
		pn_dsnv.setBackground(new Color(255, 255, 255));
		pn_dsnv.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Danh sách hóa đơn trả",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_dsnv.setBounds(10, 231, 1239, 604);
		add(pn_dsnv);
		pn_dsnv.setLayout(null);

		model_ds = new DefaultTableModel(new Object[][] {
}, new String[] { "Mã đơn trả", "Ngày lập", "Nhân viên", "Khách hàng", "Tổng tiền trả",
				"Mã hóa đơn" }

		) {
			boolean[] canEdit = new boolean[] { false, false, false, false, false, false };

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

		DefaultTableCellRenderer head_render = new DefaultTableCellRenderer();
		head_render.setBackground(new Color(135, 205, 230));
		tbl_Ds.getTableHeader().setDefaultRenderer(head_render);

		scr_Ds = new JScrollPane();
		scr_Ds.setViewportView(tbl_Ds);
		scr_Ds.setBounds(10, 20, 1219, 574);
		scr_Ds.getViewport().setBackground(Color.white);
		scr_Ds.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		pn_dsnv.add(scr_Ds);

		JLabel lblTenManHinh = new JLabel("Tìm Kiếm Hóa Đơn Trả");
		lblTenManHinh.setFont(new Font("Arial", Font.BOLD, 20));
		lblTenManHinh.setForeground(new Color(100, 149, 237));
		lblTenManHinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenManHinh.setBounds(20, 0, 1209, 50);
		add(lblTenManHinh);

		JLabel btnExit = new JLabel("");
		btnExit.setIcon(new ImageIcon(ManHinhChinh.class.getResource("/images/close.png")));
		btnExit.setBounds(1239, 0, 20, 20);
		add(btnExit);

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
		dsHDT = hoaDonTra_DAO.getDsHoaDonTra();
		updateTable(dsHDT);
		tbl_Ds.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int selected = tbl_Ds.getSelectedRow();
				hdt = dsHDT.get(selected);
				if (hdt.getKhachHang()==null) {
					txt_sdtKH.setText("");
					txt_tenKH.setText("Khách lẻ");
				}
				else {
					txt_sdtKH.setText(hdt.getKhachHang().getSdtKH());
					txt_tenKH.setText(hdt.getKhachHang().getTenKH());
				}
				txt_maHD.setText(hdt.getHoaDon().getMaHD());
				txt_tenNV.setText(hdt.getNhanVien().getTenNV());
				txt_maDT.setText(hdt.getMaDT());

			}
		});

		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				String maHD = txt_maHD.getText().trim().equalsIgnoreCase("Nhập mã...")?"":txt_maHD.getText().trim();
				String tenNV = txt_tenNV.getText().trim().equalsIgnoreCase("Nhập tên NV..")?"":txt_tenNV.getText().trim();
				String tenKH = txt_tenKH.getText().trim().equalsIgnoreCase("Nhập tên KH..")?"":txt_tenKH.getText().trim();
String sdtKH = txt_sdtKH.getText().trim().equalsIgnoreCase("Nhập sdt KH..")?"":txt_sdtKH.getText().trim();
				String maDT = txt_maDT.getText().trim().equalsIgnoreCase("Nhập mã...")?"":txt_maDT.getText().trim();
				Date selectedDate = (Date) datePicker1.getModel().getValue();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String mydate = dateFormat.format(selectedDate);
				String ngay = datePicker1.getJFormattedTextField().getText();
				ngay=ngay.equals("Tất cả")?"":dateFormat.format(selectedDate);
				dsHDT = hoaDonTra_DAO.timKiemHDT(maHD, sdtKH, tenKH, tenNV, ngay,maDT);
				if (dsHDT.size()>0) {
					updateTable(dsHDT);
				} else {
					JOptionPane.showMessageDialog(null, "Không tìm thấy!");
					xoaTrang();
				}

			}
		});

		btnXoaTrang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				xoaTrang();
			}
		});
		
		
		ManHinhChinh.btnVN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblTenManHinh.setText("Tìm Kiếm Hóa Đơn Trả");
				((TitledBorder) pn_thaotac.getBorder()).setTitle("Tìm kiếm hóa đơn trả");
				pn_thaotac.repaint();
				((TitledBorder) pn_dsnv.getBorder()).setTitle("Danh sách hóa đơn trả");
				pn_dsnv.repaint();
				
				lblmaHD.setText("Mã Hóa Đơn:");
				lblsdt.setText("SĐT khách hàng::");
				lblnhanVien.setText("Tên nhân viên::");
				lblngayLap.setText("Ngày trả:");
				lblkhachHang.setText("Tên khách hàng:");
				lblmaDT.setText("Mã Đơn Trả:");
				btnXemChiTiet.setText("Xem chi tiết");
				btnXoaTrang.setText("Xóa trắng");
				btnSearch.setText("Tìm");
				String[] newColumns_ds = { "Mã đơn trả", "Ngày lập", "Nhân viên", "Khách hàng", "Tổng tiền trả","Mã hóa đơn"};
				model_ds.setColumnIdentifiers(newColumns_ds);

			}
		});
		
		ManHinhChinh.btnEN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblTenManHinh.setText("Search for Paid Invoices");
				((TitledBorder) pn_thaotac.getBorder()).setTitle("Search for paid invoices");
				pn_thaotac.repaint();
				((TitledBorder) pn_dsnv.getBorder()).setTitle("List of paid invoices");
				pn_dsnv.repaint();

				lblmaHD.setText("Invoice Code:");
				lblsdt.setText("Customer phone number::");
				lblnhanVien.setText("Employee name::");
				lblngayLap.setText("Payment date:");
				lblkhachHang.setText("Customer name:");
				lblmaDT.setText("Payment Code:");
				btnXemChiTiet.setText("View details");
				btnXoaTrang.setText("Clear");
				btnSearch.setText("Search");
				String[] newColumns_ds = { "Payment id", "Date", "Employee", "Customer", "Total payment", "Invoice code"};
				model_ds.setColumnIdentifiers(newColumns_ds);
			}
		});
	}

	private void xoaTrang() {
		txt_maHD.setText("");
		txt_sdtKH.setText("");
		txt_tenKH.setText("");
		txt_tenNV.setText("");
		txt_maDT.setText("");
		datePicker1.getJFormattedTextField().setText("Tất cả");
	}

	private static void xoaTable() {
		DefaultTableModel dtm = (DefaultTableModel) tbl_Ds.getModel();
		dtm.getDataVector().removeAllElements();
	}

	private static void updateTable(List<HoaDonTra> ds) {
		xoaTable();
		for (HoaDonTra hoaDon : ds) {
			Object data[] = { hoaDon.getMaDT(), dtf.format(hoaDon.getNgayTra()),hoaDon.getNhanVien().getTenNV(),
					hoaDon.getKhachHang()==null?"Khách lẻ":hoaDon.getKhachHang().getTenKH(),
					df.format(hoaDon.getTongTienTra()) + " VND",hoaDon.getHoaDon().getMaHD()};
			model_ds.addRow(data);
		}
	}

	public static void resetData() {
		HoaDonTra_DAO hoaDonTra_DAO = new HoaDonTra_DAO();
		dsHDT = hoaDonTra_DAO.getDsHoaDonTra();
		ManHinhTimKiemHoaDonTra.updateTable(dsHDT);
	}
}
//}