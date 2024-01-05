package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import connectDB.ConnectDB;
import custom.CustomScrollBarUI;
import custom.GeneratorID;
import custom.RoundedCornerBorder;
import dao.ChatLieu_DAO;
import dao.KhachHang_DAO;
import dao.KichCo_DAO;
import dao.LoaiSanPham_DAO;
import dao.MauSac_DAO;
import dao.NhaCungCap_DAO;
import dao.SanPham_DAO;
import entities.ChatLieu;
import entities.KhachHang;
import entities.KichCo;
import entities.LoaiSanPham;
import entities.MauSac;
import entities.NhaCungCap;
import entities.SanPham;

public class ManHinhTimKiemSanPham extends JPanel implements ActionListener, MouseListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnXoaTrangSanPham;
	private JScrollPane scrDanhSachSanPham;
	private JTable tbl_DanhSachSanPham;
	private static DefaultTableModel model_danhSachSanPham;

	private static DecimalFormat df;
	private static DateTimeFormatter dtf;

	private JTextField txtTenSanPham;
	private JTextField txtNgaySinh;
	private JTextField txtGiaNhapSanPham;
	private JComboBox cmbMauSacSanPham;
	private JComboBox cmbLoaiSanPham;
	private JComboBox cmbKichCoSanPham ;
	private JComboBox cmbChatLieuSanPham ;
	public static JComboBox cmbNhaCungCap;
	private JComboBox cmbTinhTrangSanPham;
	private UtilDateModel model_date;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;
	private JTextField txtGiaBanSanPham;
	private JTextField txtSoLuongSanPham;
	private static NhaCungCap_DAO nhaCungCap_DAO = new NhaCungCap_DAO();
	private static SanPham_DAO sanPham_DAO = new SanPham_DAO();
	private LoaiSanPham_DAO loaiSanPham_DAO = new LoaiSanPham_DAO();
	private MauSac_DAO mauSac_DAO = new MauSac_DAO();
	private KichCo_DAO kichCo_DAO = new KichCo_DAO();
	private ChatLieu_DAO chatLieu_DAO = new ChatLieu_DAO();
	private JButton btnSearchSanPham;
	private JTextField txtMaSanPham;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManHinhTimKiemSanPham frame = new ManHinhTimKiemSanPham();
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
	public ManHinhTimKiemSanPham() {
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

		JPanel pnlManHinhThaoTac = new JPanel();
		pnlManHinhThaoTac.setBackground(new Color(255, 255, 255));
		pnlManHinhThaoTac.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Tìm thông tin sản phẩm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pnlManHinhThaoTac.setBounds(10, 50, 1239, 300);
		add(pnlManHinhThaoTac);
		pnlManHinhThaoTac.setLayout(null);

		JPanel pnlThaoTac = new JPanel();
		pnlThaoTac.setLayout(null);
		pnlThaoTac.setBackground(new Color(255, 250, 240));
		pnlThaoTac.setBounds(10, 32, 1219, 257);
		pnlManHinhThaoTac.add(pnlThaoTac);

		JLabel lblMaSanPham = new JLabel("Mã sản phẩm:");
		lblMaSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaSanPham.setFont(new Font("Arial", Font.PLAIN, 11));
		lblMaSanPham.setBounds(10, 15, 80, 20);
		pnlThaoTac.add(lblMaSanPham);

		JLabel lblTenSanPham = new JLabel("Tên sản phẩm:");
		lblTenSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenSanPham.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTenSanPham.setBounds(190, 15, 80, 20);
		pnlThaoTac.add(lblTenSanPham);

		JLabel lblMauSacSanPham = new JLabel("Màu sắc:");
		lblMauSacSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		lblMauSacSanPham.setFont(new Font("Arial", Font.PLAIN, 11));
		lblMauSacSanPham.setBounds(448, 100, 50, 20);
		pnlThaoTac.add(lblMauSacSanPham);

		JLabel lblNgayNhapSanPham = new JLabel("Ngày nhập:");
		lblNgayNhapSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgayNhapSanPham.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNgayNhapSanPham.setBounds(10, 100, 60, 20);
		pnlThaoTac.add(lblNgayNhapSanPham);


		txtTenSanPham = new JTextField();
		txtTenSanPham.setBackground(new Color(255, 250, 240));
		txtTenSanPham.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtTenSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		txtTenSanPham.setBounds(264, 16, 160, 20);
		pnlThaoTac.add(txtTenSanPham);
		txtTenSanPham.setColumns(10);

		txtNgaySinh = new JTextField();
		txtNgaySinh.setHorizontalAlignment(SwingConstants.LEFT);
		txtNgaySinh.setColumns(10);
		txtNgaySinh.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtNgaySinh.setBackground(new Color(255, 250, 240));
		txtNgaySinh.setBounds(520, 15, 140, 20);

		model_date = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(model_date,p);
		model_date.setValue(new Date(123, 00, 01));
		datePicker = new JDatePickerImpl(datePanel, new custom.DateLabelFormatter());
		datePicker.setBackground(new Color(255, 255, 255));
		datePicker.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		datePicker.setBounds(75, 100, 150, 27);
		datePicker.getJDateInstantPanel().setShowYearButtons(true);
		datePicker.getJFormattedTextField().setText("Tất cả");
		datePicker.setButtonFocusable(false);
		//datePicker.getModel().setDate(2000, 1, 1);
		pnlThaoTac.add(datePicker);

		String [] mauSac = capNhatCmbMauSac();
		cmbMauSacSanPham = new JComboBox();
		cmbMauSacSanPham.setModel(new DefaultComboBoxModel(mauSac));
		cmbMauSacSanPham.setBackground(new Color(245, 222, 179));
		cmbMauSacSanPham.setBounds(498, 100, 90, 25);
		pnlThaoTac.add(cmbMauSacSanPham);

		JLabel lblNhaCungCap = new JLabel("Nhà cung cấp:");
		lblNhaCungCap.setHorizontalAlignment(SwingConstants.LEFT);
		lblNhaCungCap.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNhaCungCap.setBounds(10, 56, 80, 20);
		pnlThaoTac.add(lblNhaCungCap);

		JLabel lblLoaiSanPham = new JLabel("Loại sản phẩm:");
		lblLoaiSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		lblLoaiSanPham.setFont(new Font("Arial", Font.PLAIN, 11));
		lblLoaiSanPham.setBounds(258, 100, 80, 20);
		pnlThaoTac.add(lblLoaiSanPham);

		String [] loai = capNhatCmbLoai();
		cmbLoaiSanPham = new JComboBox();
		cmbLoaiSanPham.setModel(new DefaultComboBoxModel(loai));
		cmbLoaiSanPham.setBackground(new Color(245, 222, 179));
		cmbLoaiSanPham.setBounds(338, 100, 100, 25);
		pnlThaoTac.add(cmbLoaiSanPham);

		JLabel lblSoLuongSanPham = new JLabel("Số lượng:");
		lblSoLuongSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		lblSoLuongSanPham.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSoLuongSanPham.setBounds(433, 56, 65, 20);
		pnlThaoTac.add(lblSoLuongSanPham);

		JLabel lblGiaNhapSanPham = new JLabel("Giá nhập:");
		lblGiaNhapSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		lblGiaNhapSanPham.setFont(new Font("Arial", Font.PLAIN, 11));
		lblGiaNhapSanPham.setBounds(434, 15, 60, 20);
		pnlThaoTac.add(lblGiaNhapSanPham);

		txtGiaNhapSanPham = new JTextField();
		txtGiaNhapSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		txtGiaNhapSanPham.setColumns(10);
		txtGiaNhapSanPham.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtGiaNhapSanPham.setBackground(new Color(255, 250, 240));
		txtGiaNhapSanPham.setBounds(498, 16, 120, 20);
		pnlThaoTac.add(txtGiaNhapSanPham);

		btnXoaTrangSanPham = new JButton("Xóa trắng");
		btnXoaTrangSanPham.setFont(new Font("Arial", Font.BOLD, 14));
		btnXoaTrangSanPham.setBackground(new Color(255, 0, 0));
		btnXoaTrangSanPham.setBounds(529, 174, 100, 30);
		pnlThaoTac.add(btnXoaTrangSanPham);

		

		JLabel lblGiaBanSanPham = new JLabel("Giá bán:");
		lblGiaBanSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		lblGiaBanSanPham.setFont(new Font("Arial", Font.PLAIN, 11));
		lblGiaBanSanPham.setBounds(651, 15, 90, 20);
		pnlThaoTac.add(lblGiaBanSanPham);

		txtGiaBanSanPham = new JTextField();
		txtGiaBanSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		txtGiaBanSanPham.setColumns(10);
		txtGiaBanSanPham.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtGiaBanSanPham.setBackground(new Color(255, 250, 240));
		txtGiaBanSanPham.setBounds(740, 14, 129, 20);
		pnlThaoTac.add(txtGiaBanSanPham);

		String [] chatlieu = capNhatCmbChatLieu();
		cmbChatLieuSanPham = new JComboBox();
		cmbChatLieuSanPham.setModel(new DefaultComboBoxModel(chatlieu));
		cmbChatLieuSanPham.setBackground(new Color(245, 222, 179));
		cmbChatLieuSanPham.setBounds(673, 100, 151, 25);
		pnlThaoTac.add(cmbChatLieuSanPham);

		JLabel lblChatLieuSanPham = new JLabel("Chất liệu:");
		lblChatLieuSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		lblChatLieuSanPham.setFont(new Font("Arial", Font.PLAIN, 11));
		lblChatLieuSanPham.setBounds(613, 100, 50, 20);
		pnlThaoTac.add(lblChatLieuSanPham);

		String [] kc = capNhatCmbKichCo();
		cmbKichCoSanPham = new JComboBox();
		cmbKichCoSanPham.setModel(new DefaultComboBoxModel(kc));
		cmbKichCoSanPham.setBackground(new Color(245, 222, 179));
		cmbKichCoSanPham.setBounds(884, 100, 80, 25);
		pnlThaoTac.add(cmbKichCoSanPham);

		JLabel lblKichCoSanPham = new JLabel("Kích cỡ:");
		lblKichCoSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		lblKichCoSanPham.setFont(new Font("Arial", Font.PLAIN, 11));
		lblKichCoSanPham.setBounds(834, 100, 50, 20);
		pnlThaoTac.add(lblKichCoSanPham);

		txtSoLuongSanPham = new JTextField();
		txtSoLuongSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		txtSoLuongSanPham.setColumns(10);
		txtSoLuongSanPham.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtSoLuongSanPham.setBackground(new Color(255, 250, 240));
		txtSoLuongSanPham.setBounds(498, 56, 120, 20);
		pnlThaoTac.add(txtSoLuongSanPham);

		JLabel lblTinhTrangSanPham = new JLabel("Tình trạng:");
		lblTinhTrangSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		lblTinhTrangSanPham.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTinhTrangSanPham.setBounds(651, 56, 60, 20);
		pnlThaoTac.add(lblTinhTrangSanPham);

		String[] ncc = capNhatCmbNCC();
		cmbNhaCungCap = new JComboBox();
		cmbNhaCungCap.setModel(new DefaultComboBoxModel(ncc));
		cmbNhaCungCap.setBounds(90, 55, 323, 22);
		pnlThaoTac.add(cmbNhaCungCap);
		
		btnSearchSanPham = new JButton("Tìm");
		btnSearchSanPham.setFont(new Font("Arial", Font.BOLD, 14));
		btnSearchSanPham.setBackground(Color.WHITE);
		btnSearchSanPham.setBounds(398, 174, 100, 30);
		pnlThaoTac.add(btnSearchSanPham);
		
		cmbTinhTrangSanPham = new JComboBox();
		cmbTinhTrangSanPham.setModel(new DefaultComboBoxModel(new String[] {"Đang kinh doanh", "Hết hàng", "Ngừng kinh doanh"}));
		cmbTinhTrangSanPham.setBounds(740, 54, 129, 22);
		pnlThaoTac.add(cmbTinhTrangSanPham);
		
		txtMaSanPham = new JTextField();
		txtMaSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		txtMaSanPham.setColumns(10);
		txtMaSanPham.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtMaSanPham.setBackground(new Color(255, 250, 240));
		txtMaSanPham.setBounds(90, 15, 90, 20);
		pnlThaoTac.add(txtMaSanPham);

		//hình
		Icon image = new ImageIcon(ManHinhCapNhatSanPham.class.getResource("/images/null.jpg"));
		Image ima = ((ImageIcon) image).getImage();
		Image newimg = ima.getScaledInstance(70, 70, Image.SCALE_SMOOTH);

		image = new ImageIcon(newimg);
		
		


		JPanel pnlDanhSachSanPham = new JPanel();
		pnlDanhSachSanPham.setBackground(new Color(255, 255, 255));
		pnlDanhSachSanPham.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Danh sách sản phẩm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pnlDanhSachSanPham.setBounds(10, 350, 1239, 504);
		add(pnlDanhSachSanPham);
		pnlDanhSachSanPham.setLayout(null);

		model_danhSachSanPham = new DefaultTableModel(new Object[][] {

		}, new String[] { "Mã sản phẩm", "Tên", "Giá nhập", "Giá bán", "Ngày nhập", "Loại", "Màu sắc", "Chất liệu",
				"Kích cỡ", "Nhà cung cấp","Tồn kho","Tình trạng"}

				) {
			boolean[] canEdit = new boolean[] { false, false, false, false, false, false, false, false };

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return canEdit[column];
			}
		};

		tbl_DanhSachSanPham = new JTable(model_danhSachSanPham);
		tbl_DanhSachSanPham.setSelectionBackground(new Color(65, 105, 225));
		tbl_DanhSachSanPham.setRowHeight(40);
		tbl_DanhSachSanPham.setGridColor(new Color(0, 0, 0));

		DefaultTableCellRenderer head_render = new DefaultTableCellRenderer();
		head_render.setBackground(new Color(135, 205, 230));
		tbl_DanhSachSanPham.getTableHeader().setDefaultRenderer(head_render);

		scrDanhSachSanPham = new JScrollPane();
		scrDanhSachSanPham.setViewportView(tbl_DanhSachSanPham);
		scrDanhSachSanPham.setBounds(10, 20, 1219, 474);
		scrDanhSachSanPham.getViewport().setBackground(Color.white);
		scrDanhSachSanPham.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		pnlDanhSachSanPham.add(scrDanhSachSanPham);


		JLabel btnExit = new JLabel("");
		btnExit.setIcon(new ImageIcon(ManHinhChinh.class.getResource("/images/close.png")));
		btnExit.setBounds(1239, 0, 20, 20);
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

		JLabel lblTenManHinh = new JLabel("TÌM THÔNG TIN SẢN PHẨM");
		lblTenManHinh.setFont(new Font("Arial", Font.BOLD, 20));
		lblTenManHinh.setForeground(new Color(100, 149, 237));
		lblTenManHinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenManHinh.setBounds(20, 0, 1209, 50);
		add(lblTenManHinh);
		
		btnSearchSanPham.addActionListener(this);
		btnXoaTrangSanPham.addActionListener(this);
		
		docDuLieuLenBang();
		
		tbl_DanhSachSanPham.addMouseListener(this);
		ManHinhChinh.btnVN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				String[] newColumns_ds = { "Mã nhà cung cấp", "Họ tên", "Số điện thoại", "Địa chỉ" };
//				model_ds.setColumnIdentifiers(newColumns_ds);
				lblTenManHinh.setText("TÌM THÔNG TIN SẢN PHẨM");
				((TitledBorder) pnlManHinhThaoTac.getBorder()).setTitle("Tìm thông tin sản phẩm");
				pnlManHinhThaoTac.repaint();
				((TitledBorder) pnlDanhSachSanPham.getBorder()).setTitle("Danh sách sản phẩm");
				pnlDanhSachSanPham.repaint();
				lblMaSanPham.setText("Mã sản phẩm:");
				lblTenSanPham.setText("Tên sản phẩm");
				lblSoLuongSanPham.setText("Số lượng:");
				lblNhaCungCap.setText("Nhà cung cấp");
				lblGiaBanSanPham.setText("Giá bán:");
				lblGiaNhapSanPham.setText("Giá nhập");
				lblTinhTrangSanPham.setText("Tình trạng:");
				lblChatLieuSanPham.setText("Chất liệu:");
				lblKichCoSanPham.setText("Kích cỡ:");
				lblMauSacSanPham.setText("Màu sắc:");
				lblLoaiSanPham.setText("Loại sản phẩm");
				lblNgayNhapSanPham.setText("Ngày nhập");
				btnXoaTrangSanPham.setText("Xoá trắng");
				String[] newColumns_ds = {"Mã sản phẩm", "Tên", "Giá nhập", "Giá bán", "Ngày nhập", "Loại", "Màu sắc", "Chất liệu",
						"Kích cỡ", "Nhà cung cấp", "Tồn kho", "Tình trạng"};
				model_danhSachSanPham.setColumnIdentifiers(newColumns_ds);
				
			
			}
		});
		
		ManHinhChinh.btnEN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblTenManHinh.setText("UPDATE PRODUCT INFORMATION");
				((TitledBorder) pnlManHinhThaoTac.getBorder()).setTitle("Search product information");
				pnlManHinhThaoTac.repaint();
				((TitledBorder) pnlDanhSachSanPham.getBorder()).setTitle("Product List");
				pnlDanhSachSanPham.repaint();
			
				lblMaSanPham.setText("Product Id:");
				lblTenSanPham.setText("Product name");
				lblSoLuongSanPham.setText("Quantity:");
				lblNhaCungCap.setText("Supplier");
				lblGiaBanSanPham.setText("Selling price:");
				lblGiaNhapSanPham.setText("Entry price");
				lblTinhTrangSanPham.setText("Status:");
				lblChatLieuSanPham.setText("Material:");
				lblKichCoSanPham.setText("Size:");
				lblMauSacSanPham.setText("Color:");
				lblLoaiSanPham.setText("Product type");
				lblNgayNhapSanPham.setText("Input date");
				btnXoaTrangSanPham.setText("Clear");
				String[] newColumns_ds = { "Product Id", "Name", "Import price", "Sales price", "Import date", "Type", "Color", "Material",
				"Size", "Vendor", "Inventory", "Condition" };
				model_danhSachSanPham.setColumnIdentifiers(newColumns_ds);
				
			}
		});
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tbl_DanhSachSanPham.getSelectedRow();
		txtMaSanPham.setText(tbl_DanhSachSanPham.getValueAt(row, 0).toString());
		txtTenSanPham.setText(tbl_DanhSachSanPham.getValueAt(row, 1).toString());
		txtGiaNhapSanPham.setText(tbl_DanhSachSanPham.getValueAt(row, 2).toString());
		txtGiaBanSanPham.setText(tbl_DanhSachSanPham.getValueAt(row, 3).toString());
		
		String date = tbl_DanhSachSanPham.getValueAt(row, 4).toString();
		datePicker.getJFormattedTextField().setText(date);
		SanPham sp = sanPham_DAO.getSanPham(tbl_DanhSachSanPham.getValueAt(row, 0).toString());
		model_date.setValue(new Date(sp.getNgayNhap().getYear() - 1900, sp.getNgayNhap().getMonthValue() - 1,
				sp.getNgayNhap().getDayOfMonth()));
		
		cmbLoaiSanPham.setSelectedItem(tbl_DanhSachSanPham.getValueAt(row, 5).toString());
		cmbMauSacSanPham.setSelectedItem(tbl_DanhSachSanPham.getValueAt(row, 6).toString());
		cmbChatLieuSanPham.setSelectedItem(tbl_DanhSachSanPham.getValueAt(row, 7).toString());
		cmbKichCoSanPham.setSelectedItem(tbl_DanhSachSanPham.getValueAt(row, 8).toString());
		cmbNhaCungCap.setSelectedItem(tbl_DanhSachSanPham.getValueAt(row, 9).toString());
		txtSoLuongSanPham.setText(tbl_DanhSachSanPham.getValueAt(row, 10).toString());
		cmbTinhTrangSanPham.setSelectedItem(tbl_DanhSachSanPham.getValueAt(row, 11).toString());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnSearchSanPham)) {
			String maSanPham = txtMaSanPham.getText();
			String tenSanPham = txtTenSanPham.getText();
			String giaBan = txtGiaBanSanPham.getText();
			String giaNhap = txtGiaNhapSanPham.getText();
			String tenNCC = cmbNhaCungCap.getSelectedItem().toString();
			tenNCC = tenNCC.equalsIgnoreCase("Tất cả")?"":tenNCC;
			String tonKho = txtSoLuongSanPham.getText();
			String tinhTrang = cmbTinhTrangSanPham.getSelectedItem().toString();
			String ngay = datePicker.getJFormattedTextField().getText();
			ngay = ngay.equals("Tất cả")?"":ngay;
			String tenloaiSP = cmbLoaiSanPham.getSelectedItem().toString();
			tenloaiSP = tenloaiSP.equalsIgnoreCase("Tất cả")?"":tenloaiSP;
			String tenMauSac = cmbMauSacSanPham.getSelectedItem().toString();
			tenMauSac = tenMauSac.equalsIgnoreCase("Tất cả")?"":tenMauSac;
			String tenChatLieu = cmbChatLieuSanPham.getSelectedItem().toString();
			tenChatLieu = tenChatLieu.equalsIgnoreCase("Tất cả")?"":tenChatLieu;
			String tenKichCo = cmbKichCoSanPham.getSelectedItem().toString();
			tenKichCo = tenKichCo.equalsIgnoreCase("Tất cả")?"":tenKichCo;

			

			DefaultTableModel model = (DefaultTableModel) tbl_DanhSachSanPham.getModel();
			TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
			tbl_DanhSachSanPham.setRowSorter(sorter);
			List<RowFilter<Object, Object>> filters = new ArrayList<>();
			filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(maSanPham), 0));
			filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(tenSanPham), 1));
			filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(giaBan), 3));
			filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(giaNhap),2));
			filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(ngay), 4));
			filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(tenloaiSP), 5));
			filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(tenMauSac), 6));
			filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(tenChatLieu), 7));
			filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(tenKichCo), 8));
			filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(tenNCC), 9));
			filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(tonKho), 10));
			filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(tinhTrang), 11));
			
			RowFilter<Object, Object> af = RowFilter.andFilter(filters);
			sorter.setRowFilter(af);
		}
		else if (o.equals(btnXoaTrangSanPham)) {
			xoaRong();
		}
	}
	public void docDuLieuLenBang() {
		// TODO Auto-generated method stub
		df = new DecimalFormat("#,###");
		dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		SanPham_DAO danhSach = new SanPham_DAO(); 
		List<SanPham> list = danhSach.getDsSanPham(); 
		for (SanPham sanPham : list) {
			String [] rowData = {
					sanPham.getMaSP(),
					sanPham.getTenSP(),
					Double.toString(sanPham.getGiaNhap()),
					Double.toString(sanPham.getGiaBan()),
					dtf.format(sanPham.getNgayNhap()).substring(0, 10),
					sanPham.getLoaiSP().getTenLoai(),
					sanPham.getMauSac().getTenMauSac(),
					sanPham.getChatLieu().getTenChatLieu(),
					sanPham.getKichCo().getTenKichCo(),
					sanPham.getNhaCungCap().getTenNCC(),
					Integer.toString(sanPham.getSoLuongTon()),
					sanPham.getTrangThai()
			};
			model_danhSachSanPham.addRow(rowData);
		}
		tbl_DanhSachSanPham.setModel (model_danhSachSanPham);
	}
	private void xoaRong() {
		txtMaSanPham.setText("");
		txtTenSanPham.setText("");
		txtGiaNhapSanPham.setText("");
		txtGiaBanSanPham.setText("");
		txtSoLuongSanPham.setText("");
		cmbChatLieuSanPham.setSelectedIndex(0);
		cmbKichCoSanPham.setSelectedIndex(0);
		cmbLoaiSanPham.setSelectedIndex(0);
		cmbMauSacSanPham.setSelectedIndex(0);
		cmbNhaCungCap.setSelectedIndex(0);
		cmbTinhTrangSanPham.setSelectedIndex(0);
		datePicker.getJFormattedTextField().setText("Tất cả");
		txtMaSanPham.requestFocus();
	}

	public static String[] capNhatCmbNCC() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		list.add("Tất cả");
		for (NhaCungCap nhaCungCap : nhaCungCap_DAO.getDsNCC()) {
			list.add(nhaCungCap.getTenNCC());
		}
		s = list.toArray(new String[0]);
		return s;
	}
	private String[] capNhatCmbLoai() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		list.add("Tất cả");
		for (LoaiSanPham loai : loaiSanPham_DAO.getDsLoaiSP()) {
			list.add(loai.getTenLoai());
		}
		s = list.toArray(new String[0]);
		return s;
	}

	private String[] capNhatCmbMauSac() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		list.add("Tất cả");
		for (MauSac mauSac : mauSac_DAO.getDsMauSac()) {
			list.add(mauSac.getTenMauSac());
		}
		s = list.toArray(new String[0]);
		return s;
	}

	private String[] capNhatCmbKichCo() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		list.add("Tất cả");

		for (KichCo kichCo : kichCo_DAO.getDsKichCo()) {
			list.add(kichCo.getTenKichCo());
		}
		s = list.toArray(new String[0]);
		return s;
	}


	private String[] capNhatCmbChatLieu() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		list.add("Tất cả");
	
		for (ChatLieu chatLieu : chatLieu_DAO.getDsChatLieu()) {
			list.add(chatLieu.getTenChatLieu());
		}
		s = list.toArray(new String[0]);
		return s;
	}
	public  boolean validDataSanPham() {
		String tenSanPham = txtTenSanPham.getText().trim();
		String giaNhapSanPham = txtGiaNhapSanPham.getText().trim();
		String giaBanSanPham = txtGiaBanSanPham.getText();
		String soLuongSanPham = txtSoLuongSanPham.getText().trim();
		if(!(tenSanPham.length() > 0 )) {
			JOptionPane.showMessageDialog(null, "Tên sản phẩm không được rỗng! ");
			return false;
		}
		
		if(!(giaNhapSanPham.length() > 0 && giaNhapSanPham.matches("^[0-9]+$"))) {
			JOptionPane.showMessageDialog(null, "Giá bán không hợp lệ! ");
			return false;
		}
		if(!(soLuongSanPham.length() > 0 && soLuongSanPham.matches("^[0-9]+$"))) {
			JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ! ");
			return false;
		}
		if(!(giaBanSanPham.length() > 0 && giaBanSanPham.matches("^[0-9]+$"))) {
			JOptionPane.showMessageDialog(null, "Giá nhập không hợp lệ! ");
			return false;
		}
		return true;
	}
	
	public static void updateDataForResetData(List<SanPham> ds)  {
		df = new DecimalFormat("#,###");
		dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	

		List<SanPham> list = sanPham_DAO.getDsSanPham();
		for (SanPham sanPham : list) {
			String[] rowData = { sanPham.getMaSP(), sanPham.getTenSP(), Double.toString(sanPham.getGiaNhap()),
					Double.toString(sanPham.getGiaBan()), dtf.format(sanPham.getNgayNhap()).substring(0, 10),
					sanPham.getLoaiSP().getTenLoai(), sanPham.getMauSac().getTenMauSac(), sanPham.getChatLieu().getTenChatLieu(),
					sanPham.getKichCo().getTenKichCo(), sanPham.getNhaCungCap().getTenNCC(), Integer.toString(sanPham.getSoLuongTon()),
					sanPham.getTrangThai() };
			model_danhSachSanPham.addRow(rowData);
		}
	}
	public static void resetData() {
		SanPham_DAO sanPham_DAO = new SanPham_DAO();
		List<SanPham> danhSach = sanPham_DAO.getDsSanPham();
		ManHinhTimKiemSanPham.updateDataForResetData(danhSach);
	}
}
