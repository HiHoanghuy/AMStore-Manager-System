package gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;
import java.awt.Color;
import java.awt.Font;

import javax.swing.border.TitledBorder;
import javax.swing.plaf.TableHeaderUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import custom.RoundedCornerBorder;
import dao.DonDatHang_DAO;
import entities.DonDatHang;
import entities.SanPham;

import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class ManHinhDatHang extends JPanel {

	private JTextField txtSdt;
	private JButton btnTim;
	
	private JScrollPane scr_Ds;
	private static JTable tbl_Ds;
	private static DefaultTableModel model_ds;
	
	private JLabel lbl_kqMa;
	private JLabel lbl_kqTenNV;
	private JLabel lbl_kqThanhTien;
	private JLabel lbl_kqNgayDat;
	private JLabel lbl_kqTenKH;
	private JLabel lbl_kqTrangThai;
	
	private static DecimalFormat df;
	private static DateTimeFormatter dtf;
	
	private DonDatHang_DAO donDatHang_dao;
	private JTextField txtTenKH;
	private JTextField txtTenNV;
	private JTextField txtMaDon;
	private JDateChooser dc_ngayLap;
	

	/**
	 * Create the panel.
	 */
	public ManHinhDatHang() {
		int w = (int)(ManHinhChinh.w*0.82), h=ManHinhChinh.h;
		
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setSize(w, h);
		
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		df = new DecimalFormat("#,### VND");
		dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
		
		donDatHang_dao = new DonDatHang_DAO();
		
		
		JPanel pn_timKiem = new JPanel();
		pn_timKiem.setBackground(new Color(255, 255, 255));
		pn_timKiem.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "T\u00ECm ki\u1EBFm \u0111\u01A1n \u0111\u1EB7t h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_timKiem.setBounds(10, 20, w-20, (int)(h*0.3));
		add(pn_timKiem);
		pn_timKiem.setLayout(null);
		
		JPanel pnl_tkSdt = new JPanel();
		pnl_tkSdt.setBackground(new Color(255, 255, 255));
		pnl_tkSdt.setBounds(440, 20, 190, 30);
		pnl_tkSdt.setBorder(new RoundedCornerBorder());
		pn_timKiem.add(pnl_tkSdt);
		pnl_tkSdt.setLayout(null);
		
		txtSdt = new JTextField();
		txtSdt.setText("Nhập sđt khách hàng...");
		txtSdt.setForeground(Color.GRAY);
		txtSdt.setEditable(false);
		txtSdt.setBackground(new Color(255, 255, 255));
		txtSdt.setBounds(10, 3, 170, 24);
		txtSdt.setBorder(null);
		txtSdt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txtSdt.setText("");
				txtSdt.setForeground(Color.BLACK);
				txtSdt.setEditable(true);
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				txtSdt.setText("Nhập mã sản phẩm ...");
				txtSdt.setForeground(Color.GRAY);
			}
		});
		pnl_tkSdt.add(txtSdt);
		txtSdt.setColumns(10);
		
		btnTim = new JButton("Tìm");
		btnTim.setBackground(new Color(65, 105, 225));
		btnTim.setIcon(new ImageIcon(ManHinhLapHoaDon.class.getResource("/images/search.png")));
		btnTim.setBounds(w-130, 20, 90, 30);
		pn_timKiem.add(btnTim);
		
		JLabel lbl_thongBaoKq = new JLabel();
		lbl_thongBaoKq.setBounds(60, 60, 100, 14);
		lbl_thongBaoKq.setForeground(Color.red);
		//pn_timKiem.add(lbl_thongBaoKq);
		
		JPanel pn_kqTimKiem = new JPanel();
		pn_kqTimKiem.setLayout(null);
		pn_kqTimKiem.setBackground(new Color(255, 250, 240));
		pn_kqTimKiem.setBounds(10, 140, w-40, 100);
		pn_timKiem.add(pn_kqTimKiem);
		
		JLabel lblMa = new JLabel("Mã đơn:");
		lblMa.setHorizontalAlignment(SwingConstants.LEFT);
		lblMa.setFont(new Font("Arial", Font.PLAIN, 11));
		lblMa.setBounds(10, 15, 50, 20);
		pn_kqTimKiem.add(lblMa);
		
		lbl_kqMa = new JLabel("");
		lbl_kqMa.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqMa.setBounds(60, 15, 80, 20);
		pn_kqTimKiem.add(lbl_kqMa);
		
		JLabel lblTenNV = new JLabel("Nhân viên:");
		lblTenNV.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenNV.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTenNV.setBounds(150, 15, 60, 20);
		pn_kqTimKiem.add(lblTenNV);
		
		lbl_kqTenNV = new JLabel("");
		lbl_kqTenNV.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqTenNV.setBounds(210, 15, 140, 20);
		pn_kqTimKiem.add(lbl_kqTenNV);
		
		JButton btnTiepTucThanhToan = new JButton("Tiếp tục thanh toán");
		btnTiepTucThanhToan.setFont(new Font("Arial", Font.BOLD, 14));
		btnTiepTucThanhToan.setBackground(new Color(0, 128, 0));
		btnTiepTucThanhToan.setBounds(w-220, 60, 170, 30);
		pn_kqTimKiem.add(btnTiepTucThanhToan);
		btnTiepTucThanhToan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//ManHinhChinh.manHinhLapHoaDon = new ManHinhLapHoaDon(true, lbl_kqMa.getText());
				ManHinhLapHoaDon manHinhLapHoaDon = new ManHinhLapHoaDon(true, lbl_kqMa.getText());
				ManHinhChinh.pn_body.removeAll();
				ManHinhChinh.pn_body.add(manHinhLapHoaDon);
				ManHinhChinh.pn_body.validate();
				ManHinhChinh.pn_body.repaint();
			}
			
		});
		
		JLabel lblThanhTien = new JLabel("Thành tiền:");
		lblThanhTien.setHorizontalAlignment(SwingConstants.LEFT);
		lblThanhTien.setFont(new Font("Arial", Font.PLAIN, 11));
		lblThanhTien.setBounds(710, 15, 60, 20);
		pn_kqTimKiem.add(lblThanhTien);
		
		lbl_kqThanhTien = new JLabel("");
		lbl_kqThanhTien.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqThanhTien.setBounds(770, 16, 90, 20);
		pn_kqTimKiem.add(lbl_kqThanhTien);
		
		JLabel lblNgayDat = new JLabel("Ngày đặt:");
		lblNgayDat.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgayDat.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNgayDat.setBounds(580, 15, 50, 20);
		pn_kqTimKiem.add(lblNgayDat);
		
		lbl_kqNgayDat = new JLabel("");
		lbl_kqNgayDat.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqNgayDat.setBounds(630, 15, 70, 20);
		pn_kqTimKiem.add(lbl_kqNgayDat);
		
		JLabel lblTenKH = new JLabel("Khách hàng:");
		lblTenKH.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenKH.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTenKH.setBounds(360, 15, 80, 20);
		pn_kqTimKiem.add(lblTenKH);
		
		lbl_kqTenKH = new JLabel("");
		lbl_kqTenKH.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqTenKH.setBounds(430, 15, 140, 20);
		pn_kqTimKiem.add(lbl_kqTenKH);
		
		JButton btnGiaoHang = new JButton("Giao hàng");
		btnGiaoHang.setBounds(900, 60, 170, 30);
		//pn_kqTimKiem.add(btnGiaoHang);
		btnGiaoHang.setFont(new Font("Arial", Font.BOLD, 14));
		btnGiaoHang.setBackground(new Color(244, 164, 96));
		
		lbl_kqTrangThai = new JLabel("");
		lbl_kqTrangThai.setForeground(new Color(255, 0, 0));
		lbl_kqTrangThai.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_kqTrangThai.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		lbl_kqTrangThai.setBounds(970, 10, 100, 30);
		pn_kqTimKiem.add(lbl_kqTrangThai);
		
		JPanel pnl_tkTenKH = new JPanel();
		pnl_tkTenKH.setLayout(null);
		pnl_tkTenKH.setBorder(new RoundedCornerBorder());
		pnl_tkTenKH.setBackground(Color.WHITE);
		pnl_tkTenKH.setBounds(440, 60, 190, 30);
		pn_timKiem.add(pnl_tkTenKH);
		
		txtTenKH = new JTextField();
		txtTenKH.setText("Nhập tên khách hàng...");
		txtTenKH.setForeground(Color.GRAY);
		txtTenKH.setEditable(false);
		txtTenKH.setColumns(10);
		txtTenKH.setBorder(null);
		txtTenKH.setBackground(Color.WHITE);
		txtTenKH.setBounds(10, 3, 170, 24);
		pnl_tkTenKH.add(txtTenKH);
		txtTenKH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txtTenKH.setText("");
				txtTenKH.setForeground(Color.BLACK);
				txtTenKH.setEditable(true);
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				txtTenKH.setText("Nhập tên khách hàng...");
				txtTenKH.setForeground(Color.GRAY);
			}
		});
		
		JPanel pnl_tkTenNV = new JPanel();
		pnl_tkTenNV.setLayout(null);
		pnl_tkTenNV.setBorder(new RoundedCornerBorder());
		pnl_tkTenNV.setBackground(Color.WHITE);
		pnl_tkTenNV.setBounds(100, 62, 190, 30);
		pn_timKiem.add(pnl_tkTenNV);
		
		txtTenNV = new JTextField();
		txtTenNV.setText("Nhập tên nhân viên...");
		txtTenNV.setForeground(Color.GRAY);
		txtTenNV.setEditable(false);
		txtTenNV.setColumns(10);
		txtTenNV.setBorder(null);
		txtTenNV.setBackground(Color.WHITE);
		txtTenNV.setBounds(10, 3, 170, 24);
		pnl_tkTenNV.add(txtTenNV);
		txtTenNV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txtTenNV.setText("");
				txtTenNV.setForeground(Color.BLACK);
				txtTenNV.setEditable(true);
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				txtTenNV.setText("Nhập tên nhân viên...");
				txtTenNV.setForeground(Color.GRAY);
			}
		});
		
		JPanel pnl_tkMa = new JPanel();
		pnl_tkMa.setLayout(null);
		pnl_tkMa.setBorder(new RoundedCornerBorder());
		pnl_tkMa.setBackground(Color.WHITE);
		pnl_tkMa.setBounds(100, 20, 190, 30);
		pn_timKiem.add(pnl_tkMa);
		
		txtMaDon = new JTextField();
		txtMaDon.setText("Nhập mã đơn...");
		txtMaDon.setForeground(Color.GRAY);
		txtMaDon.setEditable(false);
		txtMaDon.setColumns(10);
		txtMaDon.setBorder(null);
		txtMaDon.setBackground(Color.WHITE);
		txtMaDon.setBounds(10, 3, 170, 24);
		pnl_tkMa.add(txtMaDon);
		txtMaDon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txtMaDon.setText("");
				txtMaDon.setForeground(Color.BLACK);
				txtMaDon.setEditable(true);
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				txtMaDon.setText("Nhập mã đơn...");
				txtMaDon.setForeground(Color.GRAY);
			}
		});
		
		JLabel lbl_ngayDat = new JLabel("Ngày đặt:");
		lbl_ngayDat.setBounds(700, 20, 60, 30);
		pn_timKiem.add(lbl_ngayDat);
		
		dc_ngayLap = new JDateChooser();
		dc_ngayLap.setBounds(760, 20, 150, 30);
		dc_ngayLap.setDateFormatString("yyyy-MM-dd");
		pn_timKiem.add(dc_ngayLap);
		
		JLabel lbl_maDon = new JLabel("Mã đơn:");
		lbl_maDon.setBounds(10, 20, 100, 30);
		pn_timKiem.add(lbl_maDon);
		
		JLabel lbl_sdtKH = new JLabel("Sđt khách hàng:");
		lbl_sdtKH.setBounds(340, 20, 100, 30);
		pn_timKiem.add(lbl_sdtKH);
		
		JLabel lbl_tenNV = new JLabel("Tên nhân viên:");
		lbl_tenNV.setBounds(10, 62, 100, 30);
		pn_timKiem.add(lbl_tenNV);
		
		JLabel lbl_tenKH = new JLabel("Tên khách hàng:");
		lbl_tenKH.setBounds(340, 60, 100, 30);
		pn_timKiem.add(lbl_tenKH);
		
		JButton btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtMaDon.setText("Nhập mã đơn...");
				txtMaDon.setForeground(Color.GRAY);
				txtSdt.setText("Nhập sđt khách hàng...");
				txtSdt.setForeground(Color.GRAY);
				txtTenKH.setText("Nhập tên khách hàng...");
				txtTenKH.setForeground(Color.GRAY);
				txtTenNV.setText("Nhập tên nhân viên...");
				txtTenNV.setForeground(Color.GRAY);
				dc_ngayLap.setDate(null);
			}
		});
		btnXoaTrang.setBackground(new Color(255, 99, 71));
		btnXoaTrang.setBounds(w-130, 60, 90, 30);
		pn_timKiem.add(btnXoaTrang);
		
		JPanel pn_dsddh = new JPanel();
		pn_dsddh.setBackground(new Color(255, 255, 255));
		pn_dsddh.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Danh s\u00E1ch \u0111\u01A1n \u0111\u1EB7t h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_dsddh.setBounds(10, (int)(h*0.3)+20, w-20, (int)(h*0.7-30));
		add(pn_dsddh);
		pn_dsddh.setLayout(null);
		
		JLabel lblTitleHeader = new JLabel("ĐẶT HÀNG");
		lblTitleHeader.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitleHeader.setForeground(new Color(65, 105, 225));
		lblTitleHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleHeader.setBounds(20, 0, w-40, 20);
		add(lblTitleHeader);
		
		JLabel btnExit = new JLabel("");
		btnExit.setIcon(new ImageIcon(ManHinhChinh.class.getResource("/images/close.png")));
		btnExit.setBounds(w-20, 0, 20, 20);
		add(btnExit);
		
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(JOptionPane.showConfirmDialog(null, "Nhấn OK để thoát khỏi chương trình.", "Thoat", JOptionPane.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION) == 0) {
					System.exit(0);
				}
			}
		});
		
		model_ds = new DefaultTableModel(
			new Object[][] {
				/*
				{"DH001","Nguyễn Hoàng Huy","Phạm Thanh Hùng","15-10-2023","Chưa thanh toán","2.000.000 VND"},
				{"DH001","Nguyễn Hoàng Huy","Phạm Thanh Hùng","15-10-2023","Chưa thanh toán","2.000.000 VND"}*/
			},
			new String [] {
				"Mã đơn","Nhân viên","Khách hàng","Ngày đặt", "Thành tiền", "Tình trạng thanh toán"	
			}
			
		) {
			boolean[] canEdit = new boolean [] {
				false,false,false,false,false,false	
			};
			
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
		scr_Ds.setBounds(10, 20, w-40, (int)(h*0.7-60));
		scr_Ds.getViewport().setBackground(Color.white);
		pn_dsddh.add(scr_Ds);
		
		
		//cài dữ liệu mặc định khi khởi chạy chương trình
		List<DonDatHang> ds = donDatHang_dao.getDsDonDatHang();
		capNhatDuLieuCuaBang(ds);
		
		//xử lý sự kiện khi click chọn 1 dòng dữ liệu của bảng
		tbl_Ds.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int selected = tbl_Ds.getSelectedRow();
				lbl_kqMa.setText((String) model_ds.getValueAt(selected, 0));
				lbl_kqTenNV.setText((String) model_ds.getValueAt(selected, 1));
				lbl_kqTenKH.setText((String) model_ds.getValueAt(selected, 2));
				lbl_kqNgayDat.setText((String) model_ds.getValueAt(selected, 3).toString().replaceAll("\\d{2}:\\d{2}:\\d{2} ", ""));
				lbl_kqTrangThai.setText((String) model_ds.getValueAt(selected, 5));
				lbl_kqThanhTien.setText((String) model_ds.getValueAt(selected, 4));
				lbl_thongBaoKq.setText("");
				
				if (((String) model_ds.getValueAt(selected, 5)).equalsIgnoreCase("Đã thanh toán")) {
					btnGiaoHang.setVisible(true);
					btnTiepTucThanhToan.setVisible(false);
					lbl_kqTrangThai.setForeground(Color.green);
				}
				else {
					btnGiaoHang.setVisible(false);
					btnTiepTucThanhToan.setVisible(true);
					lbl_kqTrangThai.setForeground(Color.red);
				}
			}
		});
		//xử lý sự kiện khi click vào nút tìm kiếm
		btnTim.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
				String maDDH = txtMaDon.getText().trim();
				maDDH = maDDH.equalsIgnoreCase("Nhập mã đơn...")?"":maDDH;
				String sdtKH = txtSdt.getText().trim();
				sdtKH = sdtKH.equalsIgnoreCase("Nhập sđt khách hàng...")?"":sdtKH;
				String tenKH = txtTenKH.getText().trim();
				tenKH = tenKH.equalsIgnoreCase("Nhập tên khách hàng...")?"":tenKH;
				String tenNV = txtTenNV.getText().trim();
				tenNV = tenNV.equalsIgnoreCase("Nhập tên nhân viên...")?"":tenNV;
				Date ngay = dc_ngayLap.getDate();
				String ngayLap = ngay==null?"":dt1.format(ngay);
				List<DonDatHang> ds = donDatHang_dao.timKiemDDH(maDDH, sdtKH, tenKH, tenNV, ngayLap);
				System.out.println(maDDH);
				System.out.println(sdtKH);
				System.out.println(tenKH);
				System.out.println(tenNV);
				System.out.println(ngayLap);
				if (ds.size()>0) {
					capNhatDuLieuCuaBang(ds);
				}
				else {
					JOptionPane.showMessageDialog(null, "Không tìm thấy!");
				}
			}
		});
		
		ManHinhChinh.btnVN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblTitleHeader.setText("ĐẶT HÀNG");
				((TitledBorder) pn_timKiem.getBorder()).setTitle("Tìm kiếm đơn đặt hàng");
				pn_timKiem.repaint();
					
				((TitledBorder) pn_dsddh.getBorder()).setTitle("Danh sách đơn đặt hàng");
				pn_dsddh.repaint();
				lbl_maDon.setText("Mã đơn:");
				lbl_sdtKH.setText("Sđt khách hàng:");
				lbl_ngayDat.setText("Ngày đặt:");
				lbl_tenNV.setText("Tên nhân viên:");
				lbl_tenKH.setText("Tên khách hàng:");
				//txtMaDon.setText("Nhập mã đơn...");
				//txtSdt.setText("Nhập sđt khách hàng...");
				//txtTenNV.setText("Nhập tên nhân viên...");
				//txtTenKH.setText("Nhập tên khách hàng...");
				
				lblMa.setText("Mã đơn:");
				lblTenNV.setText("Nhân viên:");
				lblTenKH.setText("Khách hàng:");
				lblNgayDat.setText("Ngày đặt:");
				lblThanhTien.setText("Thành tiền:");
				
				btnTim.setText("Tìm");
				btnXoaTrang.setText("Xoá trắng");
				btnTiepTucThanhToan.setText("Tiếp tục thanh toán");
				String[] newColumns_ds = {"Mã đơn","Nhân viên","Khách hàng","Ngày đặt", "Thành tiền", "Tình trạng thanh toán"	};
				model_ds.setColumnIdentifiers(newColumns_ds);
			}
		});
		
		ManHinhChinh.btnEN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblTitleHeader.setText("ORDER");
				((TitledBorder) pn_timKiem.getBorder()).setTitle("Search for orders");
				pn_timKiem.repaint();

				((TitledBorder) pn_dsddh.getBorder()).setTitle("List of orders");
				pn_dsddh.repaint();
				lbl_maDon.setText("Order Id:");
				lbl_sdtKH.setText("Customer phone:");
				lbl_ngayDat.setText("Set date:");
				lbl_tenNV.setText("Employee name:");
				lbl_tenKH.setText("Customer name:");
				//txtMaDon.setText("Enter Order id...");
				//txtSdt.setText("Enter customer phone number...");
				//txtTenNV.setText("Enter employee name...");
				//txtTenKH.setText("Enter customer name...");

				lblMa.setText("Order Id:");
				lblTenNV.setText("Employee:");
				lblTenKH.setText("Customer:");
				lblNgayDat.setText("Set date:");
				lblThanhTien.setText("Amount:");

				btnTim.setText("Find");
				btnXoaTrang.setText("Clear");
				btnTiepTucThanhToan.setText("Continue payment");
				String[] newColumns_ds = {"Order Id","Employee","Customer","Order date", "Amount", "Payment status" };
				model_ds.setColumnIdentifiers(newColumns_ds);
			}
		});
	}
	/**
	 * xóa dữ liệu của bảng
	 * @param t là bảng cần xóa dữ liệu
	 */
	private static void xoaNhatDuLieuCuaBang(JTable t) {
		DefaultTableModel dm = (DefaultTableModel) t.getModel();
		dm.getDataVector().removeAllElements();
		t.revalidate();
	}
	/**
	 * cập nhật dữ liệu của bảng danh sách đơn đặt hàng
	 * @param ds là danh sách đơn đặt hàng
	 */
	public static void capNhatDuLieuCuaBang(List<DonDatHang> ds) {
		xoaNhatDuLieuCuaBang(tbl_Ds);
		for (DonDatHang donDatHang : ds) {
			Object data[] = {donDatHang.getMaDDH(),donDatHang.getNhanVien().getTenNV(),donDatHang.getKhachHang().getTenKH(),dtf.format(donDatHang.getNgayLap()).toString(),df.format(donDatHang.getTongTienDDH()),donDatHang.getTinhTrangThanhToan()};
			model_ds.addRow(data);
		}
	}
	/**
	 * hàm đọc lại dữ liệu của bảng khi có sự thay đổi dữ liệu do màn hình khác tác động
	 */
	public static void docLaiDuLieuCuaBang() {
		DonDatHang_DAO donDatHang_DAO = new DonDatHang_DAO();
		List<DonDatHang> ds = donDatHang_DAO.getDsDonDatHang();
		ManHinhDatHang.capNhatDuLieuCuaBang(ds);
	}
}
