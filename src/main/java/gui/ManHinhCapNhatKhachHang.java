package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;

import connectDB.ConnectDB;
import custom.CustomScrollBarUI;
import custom.GeneratorID;
import custom.RoundedCornerBorder;
import dao.KhachHang_DAO;
import entities.KhachHang;
import qrCode.QRCodeGenerator;

import javax.swing.DefaultComboBoxModel;

public class ManHinhCapNhatKhachHang extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtTim_TenKhachHang;
	private JButton btnTim_KhachHang;
	private JScrollPane scr_DanhSachKhachHang;
	private JTable tbl_DanhSachKhachHang;
	private static DefaultTableModel model_DanhSachKhachHang;
	private JTextField txtHoTenKhachHang;
	private JTextField txtSoDienThoaiKhachHang;
	private JTextField txtDiaChiKhachHang;
	private JComboBox cmbGioiTinhKhachHang;
	private JButton btnThemKhachHang;
	private JButton btnXoaTrangKhachHang;
	private JButton btnSuaKhachHang;
	private JTextField txtMaKhachHang;
	private ArrayList<KhachHang> danhSachKhachHang = new ArrayList<KhachHang>();
	private KhachHang_DAO khachHang_DAO = new KhachHang_DAO(); 
	private JTextField txtTim_SoDienThoaiKhachHang;
	private JTextField txtEmailKhachHang;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManHinhCapNhatKhachHang frame = new ManHinhCapNhatKhachHang();
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
	public ManHinhCapNhatKhachHang() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setSize(1259, 864);

		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		JPanel pnlManHinhThaoTac = new JPanel();
		pnlManHinhThaoTac.setBackground(new Color(255, 255, 255));
		pnlManHinhThaoTac.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Cập nhật thông tin khách hàng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pnlManHinhThaoTac.setBounds(10, 50, 1239, 190);
		add(pnlManHinhThaoTac);
		pnlManHinhThaoTac.setLayout(null);

		JPanel pnlTim_TenKhachHang = new JPanel();
		pnlTim_TenKhachHang.setBackground(new Color(255, 255, 255));
		pnlTim_TenKhachHang.setBounds(128, 26, 190, 30);
		pnlTim_TenKhachHang.setBorder(new RoundedCornerBorder());
		pnlManHinhThaoTac.add(pnlTim_TenKhachHang);
		pnlTim_TenKhachHang.setLayout(null);

		txtTim_TenKhachHang = new JTextField();
		txtTim_TenKhachHang.setText("");
		txtTim_TenKhachHang.setForeground(Color.GRAY);
		txtTim_TenKhachHang.setEditable(false);
		txtTim_TenKhachHang.setBackground(new Color(255, 255, 255));
		txtTim_TenKhachHang.setBounds(10, 3, 170, 24);
		txtTim_TenKhachHang.setBorder(null);
		txtTim_TenKhachHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txtTim_TenKhachHang.setText("");
				txtTim_TenKhachHang.setForeground(Color.BLACK);
				txtTim_TenKhachHang.setEditable(true);
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
				txtTim_TenKhachHang.setText("");
				txtTim_TenKhachHang.setForeground(Color.GRAY);
			}
		});
		pnlTim_TenKhachHang.add(txtTim_TenKhachHang);
		txtTim_TenKhachHang.setColumns(10);

		JPanel pnlThaoTac = new JPanel();
		pnlThaoTac.setLayout(null);
		pnlThaoTac.setBackground(new Color(255, 250, 240));
		pnlThaoTac.setBounds(10, 74, 1219, 111);
		pnlManHinhThaoTac.add(pnlThaoTac);

		JLabel lblMaKhachHang = new JLabel("Mã khách hàng:");
		lblMaKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaKhachHang.setFont(new Font("Arial", Font.PLAIN, 11));
		lblMaKhachHang.setBounds(10, 15, 80, 20);
		pnlThaoTac.add(lblMaKhachHang);

		JLabel lblTenKhachHang = new JLabel("Họ tên:");
		lblTenKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenKhachHang.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTenKhachHang.setBounds(238, 15, 65, 20);
		pnlThaoTac.add(lblTenKhachHang);

		JLabel lblGioiTinhKhachHang = new JLabel("Giới tính:");
		lblGioiTinhKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		lblGioiTinhKhachHang.setFont(new Font("Arial", Font.PLAIN, 11));
		lblGioiTinhKhachHang.setBounds(722, 17, 50, 20);
		pnlThaoTac.add(lblGioiTinhKhachHang);



		txtHoTenKhachHang = new JTextField();
		txtHoTenKhachHang.setBackground(new Color(255, 250, 240));
		txtHoTenKhachHang.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtHoTenKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		txtHoTenKhachHang.setBounds(308, 16, 160, 20);
		pnlThaoTac.add(txtHoTenKhachHang);
		txtHoTenKhachHang.setColumns(10);

		String[] item_gt = {"Nam","Nữ"};
		cmbGioiTinhKhachHang = new JComboBox(item_gt);
		cmbGioiTinhKhachHang.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ"}));
		cmbGioiTinhKhachHang.setBackground(new Color(245, 222, 179));
		cmbGioiTinhKhachHang.setBounds(785, 15, 90, 25);
		pnlThaoTac.add(cmbGioiTinhKhachHang);

		JLabel lblSoDienThoaiKhachHang = new JLabel("Số điện thoại:");
		lblSoDienThoaiKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		lblSoDienThoaiKhachHang.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSoDienThoaiKhachHang.setBounds(500, 17, 90, 20);
		pnlThaoTac.add(lblSoDienThoaiKhachHang);

		txtSoDienThoaiKhachHang = new JTextField();
		txtSoDienThoaiKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		txtSoDienThoaiKhachHang.setColumns(10);
		txtSoDienThoaiKhachHang.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtSoDienThoaiKhachHang.setBackground(new Color(255, 250, 240));
		txtSoDienThoaiKhachHang.setBounds(598, 17, 114, 20);
		pnlThaoTac.add(txtSoDienThoaiKhachHang);

		JLabel lblDiaChiKhachHang = new JLabel("Địa chỉ:");
		lblDiaChiKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		lblDiaChiKhachHang.setFont(new Font("Arial", Font.PLAIN, 11));
		lblDiaChiKhachHang.setBounds(10, 58, 80, 20);
		pnlThaoTac.add(lblDiaChiKhachHang);

		txtDiaChiKhachHang = new JTextField();
		txtDiaChiKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		txtDiaChiKhachHang.setColumns(10);
		txtDiaChiKhachHang.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtDiaChiKhachHang.setBackground(new Color(255, 250, 240));
		txtDiaChiKhachHang.setBounds(90, 58, 340, 20);
		pnlThaoTac.add(txtDiaChiKhachHang);

		btnThemKhachHang = new JButton("Thêm");
		btnThemKhachHang.setBounds(1109, 10, 100, 20);
		pnlThaoTac.add(btnThemKhachHang);
		btnThemKhachHang.setFont(new Font("Arial", Font.BOLD, 14));
		btnThemKhachHang.setBackground(new Color(0, 128, 0));

		btnSuaKhachHang = new JButton("Sửa");
		btnSuaKhachHang.setBounds(1109, 41, 100, 20);
		pnlThaoTac.add(btnSuaKhachHang);
		btnSuaKhachHang.setFont(new Font("Arial", Font.BOLD, 14));
		btnSuaKhachHang.setBackground(new Color(244, 164, 96));

		btnXoaTrangKhachHang = new JButton("Xóa trắng");
		btnXoaTrangKhachHang.setFont(new Font("Arial", Font.BOLD, 14));
		btnXoaTrangKhachHang.setBackground(new Color(255, 0, 0));
		btnXoaTrangKhachHang.setBounds(1109, 71, 100, 20);
		pnlThaoTac.add(btnXoaTrangKhachHang);



		txtMaKhachHang = new JTextField();
		txtMaKhachHang.setEditable(false);
		txtMaKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		txtMaKhachHang.setColumns(10);
		txtMaKhachHang.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtMaKhachHang.setBackground(new Color(255, 250, 240));
		txtMaKhachHang.setBounds(90, 15, 127, 20);
		pnlThaoTac.add(txtMaKhachHang);
		
		JLabel lblEmailKhachHang = new JLabel("Email:");
		lblEmailKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmailKhachHang.setFont(new Font("Arial", Font.PLAIN, 11));
		lblEmailKhachHang.setBounds(500, 58, 50, 20);
		pnlThaoTac.add(lblEmailKhachHang);
		
		txtEmailKhachHang = new JTextField();
		txtEmailKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		txtEmailKhachHang.setColumns(10);
		txtEmailKhachHang.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtEmailKhachHang.setBackground(new Color(255, 250, 240));
		txtEmailKhachHang.setBounds(535, 58, 340, 20);
		pnlThaoTac.add(txtEmailKhachHang);

		btnTim_KhachHang = new JButton("Tìm");
		btnTim_KhachHang.setBounds(1120, 29, 88, 24);
		pnlManHinhThaoTac.add(btnTim_KhachHang);
		// btnSearch.setBorder(new RoundedCornerBorder());
		btnTim_KhachHang.setBackground(new Color(65, 105, 225));
		btnTim_KhachHang.setIcon(new ImageIcon(ManHinhChinh.class.getResource("/images/search.png")));

		JLabel lblTim_TenKhachHang = new JLabel("Tên khách hàng:");
		lblTim_TenKhachHang.setBounds(24, 26, 100, 30);
		pnlManHinhThaoTac.add(lblTim_TenKhachHang);

		JPanel pnlTim_SoDienThoai = new JPanel();
		pnlTim_SoDienThoai.setLayout(null);
		pnlTim_SoDienThoai.setBorder(new RoundedCornerBorder());
		pnlTim_SoDienThoai.setBackground(Color.WHITE);
		pnlTim_SoDienThoai.setBounds(472, 26, 190, 30);
		pnlManHinhThaoTac.add(pnlTim_SoDienThoai);

		txtTim_SoDienThoaiKhachHang = new JTextField();
		txtTim_SoDienThoaiKhachHang.setText("");
		txtTim_SoDienThoaiKhachHang.setForeground(Color.GRAY);
		txtTim_SoDienThoaiKhachHang.setEditable(false);
		txtTim_SoDienThoaiKhachHang.setColumns(10);
		txtTim_SoDienThoaiKhachHang.setBorder(null);
		txtTim_SoDienThoaiKhachHang.setBackground(Color.WHITE);
		txtTim_SoDienThoaiKhachHang.setBounds(10, 3, 170, 24);
		pnlTim_SoDienThoai.add(txtTim_SoDienThoaiKhachHang);
		txtTim_SoDienThoaiKhachHang.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				txtTim_SoDienThoaiKhachHang.setText("");
				txtTim_SoDienThoaiKhachHang.setForeground(Color.BLACK);
				txtTim_SoDienThoaiKhachHang.setEditable(true);
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

			}
		});
		JLabel lblTim_SoDienThoaiKhachHang = new JLabel("Số điện thoại:");
		lblTim_SoDienThoaiKhachHang.setBounds(382, 26, 80, 30);
		pnlManHinhThaoTac.add(lblTim_SoDienThoaiKhachHang);
		btnTim_KhachHang.addActionListener(this);

		JPanel pnlDanhSachKhachHang = new JPanel();
		pnlDanhSachKhachHang.setBackground(new Color(255, 255, 255));
		pnlDanhSachKhachHang.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Danh sách khách hàng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pnlDanhSachKhachHang.setBounds(10, 253, 1239, 601);
		add(pnlDanhSachKhachHang);
		pnlDanhSachKhachHang.setLayout(null);

		model_DanhSachKhachHang = new DefaultTableModel(new Object[][] {

		}, new String[] { "Mã khách hàng", "Họ tên", "Số điện thoại", "Giới tính", "Địa chỉ","Email"}) {
			boolean[] canEdit = new boolean[] { false, false, false, false, false, false, false, false };

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return canEdit[column];
			}
		};

		tbl_DanhSachKhachHang = new JTable(model_DanhSachKhachHang);
		tbl_DanhSachKhachHang.setSelectionBackground(new Color(65, 105, 225));
		tbl_DanhSachKhachHang.setRowHeight(40);
		tbl_DanhSachKhachHang.setGridColor(new Color(0, 0, 0));

		DefaultTableCellRenderer head_render = new DefaultTableCellRenderer();
		head_render.setBackground(new Color(135, 205, 230));
		tbl_DanhSachKhachHang.getTableHeader().setDefaultRenderer(head_render);

		scr_DanhSachKhachHang = new JScrollPane();
		scr_DanhSachKhachHang.setViewportView(tbl_DanhSachKhachHang);
		scr_DanhSachKhachHang.setBounds(10, 20, 1219, 571);
		scr_DanhSachKhachHang.getViewport().setBackground(Color.white);
		scr_DanhSachKhachHang.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		pnlDanhSachKhachHang.add(scr_DanhSachKhachHang);

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

		JLabel lblTenManHinh = new JLabel("CẬP NHẬT THÔNG TIN KHÁCH HÀNG");
		lblTenManHinh.setFont(new Font("Arial", Font.BOLD, 20));
		lblTenManHinh.setForeground(new Color(100, 149, 237));
		lblTenManHinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenManHinh.setBounds(20, 0, 1209, 50);
		add(lblTenManHinh);

		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		docDuLieu();

		btnThemKhachHang.addActionListener(this);
		btnSuaKhachHang.addActionListener(this);
		btnXoaTrangKhachHang.addActionListener(this);
		btnSuaKhachHang.addActionListener(this);
		tbl_DanhSachKhachHang.addMouseListener(this);
		
		ManHinhChinh.btnVN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblTenManHinh.setText("CẬP NHẬT THÔNG TIN KHÁCH HÀNG");
				((TitledBorder) pnlManHinhThaoTac.getBorder()).setTitle("Cập nhật thông tin khách hàng");
				pnlManHinhThaoTac.repaint();
				((TitledBorder) pnlDanhSachKhachHang.getBorder()).setTitle("Danh sách khách hàng");
				pnlDanhSachKhachHang.repaint();
				
				lblTim_TenKhachHang.setText("Tên khách hàng");
				lblTim_SoDienThoaiKhachHang.setText("Số điện thoại:");
				btnTim_KhachHang.setText("Tìm");
				lblMaKhachHang.setText("Mã khách hàng:");
				lblTenKhachHang.setText("Họ tên:");
				lblSoDienThoaiKhachHang.setText("Số điện thoại:");
				lblGioiTinhKhachHang.setText("Giới tính:");
				lblDiaChiKhachHang.setText("Địa chỉ:");
				btnThemKhachHang.setText("Thêm");
				btnSuaKhachHang.setText("Sửa");
				btnXoaTrangKhachHang.setText("Xoá trắng");
				String[] newColumns_ds = { "Mã khách hàng", "Họ tên", "Số điện thoại", "Giới tính", "Địa chỉ","Email"};
				model_DanhSachKhachHang.setColumnIdentifiers(newColumns_ds);
				//cmbGioiTinhKhachHang.setModel(new DefaultComboBoxModel<>(new String[]{"Nam", "Nữ"}));
			}
		});
		
		ManHinhChinh.btnEN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((TitledBorder) pnlManHinhThaoTac.getBorder()).setTitle("Update customer information");
				pnlManHinhThaoTac.repaint();
				((TitledBorder) pnlDanhSachKhachHang.getBorder()).setTitle("List of customers");
				pnlDanhSachKhachHang.repaint();
				lblTim_TenKhachHang.setText("Customer name:");
				lblTim_SoDienThoaiKhachHang.setText("Phone number:");
				btnTim_KhachHang.setText("Find");
				lblMaKhachHang.setText("Customer Id:");
				lblTenKhachHang.setText("Customer name:");
				lblSoDienThoaiKhachHang.setText("Phone number:");
				lblGioiTinhKhachHang.setText("Gender:");
				lblDiaChiKhachHang.setText("Address:");
				btnThemKhachHang.setText("Add");
				btnSuaKhachHang.setText("Edit");
				btnXoaTrangKhachHang.setText("Clear");
				String[] newColumns_ds = { "Customer Id", "Full name", "Phone number", "Gender", "Address","Email"};
				model_DanhSachKhachHang.setColumnIdentifiers(newColumns_ds);
				//cmbGioiTinhKhachHang.setModel(new DefaultComboBoxModel<>(new String[]{"Male", "Female"}));
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThemKhachHang)) {
			if(validDataKhachHang()) {
				// TODO Auto-generated method stub
				String tenKhachHang = txtHoTenKhachHang.getText();
				String soDienThoaiKhachHang = txtSoDienThoaiKhachHang.getText();
				boolean gioiTinhKhachHang = cmbGioiTinhKhachHang.getSelectedItem().toString().equalsIgnoreCase("Nam")?true:false;
				String diaChiKhachHang = txtDiaChiKhachHang.getText();
				String email = txtEmailKhachHang.getText();
				String maKhachHang = GeneratorID.generateIDKhachHang();
				KhachHang khachHang = new KhachHang(maKhachHang,tenKhachHang,soDienThoaiKhachHang,gioiTinhKhachHang,diaChiKhachHang,email);
				if(khachHang_DAO.themKH(khachHang)) {
					model_DanhSachKhachHang.setRowCount(0);
					docDuLieu();
					xoaRong();
					ManHinhTimKiemKhachHang.resetData();
					QRCodeGenerator.generateQRCodeImage(maKhachHang, "C://MaQRKhachHang/", maKhachHang+"_"+tenKhachHang+"_"+soDienThoaiKhachHang);
					JOptionPane.showMessageDialog(null, "Thêm khách hàng mới thành công");				

				}
				else {
					JOptionPane.showMessageDialog(this, "Lỗi thêm!");
				}}
		}
		else if (o.equals (btnSuaKhachHang)) { 
			if(validDataKhachHang()) {
				int row = tbl_DanhSachKhachHang.getSelectedRow(); 
				if (row >= 0){
					String tenKh = txtHoTenKhachHang.getText();
					String sdt = txtSoDienThoaiKhachHang.getText();
					boolean gt = cmbGioiTinhKhachHang.getSelectedItem().toString().equalsIgnoreCase("Nam")?true:false;
					String diaChi = txtDiaChiKhachHang.getText();
					String maKh = txtMaKhachHang.getText();
					String email = txtEmailKhachHang.getText();
					KhachHang hk = new KhachHang(maKh,tenKh,sdt,gt,diaChi,email);
					if(khachHang_DAO.capNhatKhachHang(hk)) {
						model_DanhSachKhachHang.setRowCount(0);
						danhSachKhachHang.add(hk);
						docDuLieu();
						//xoaRong();
						JOptionPane.showMessageDialog(null, "Sửa thành công");
					}	
					ManHinhTimKiemKhachHang.resetData();
				}
			}
		}
		else if(o.equals (btnTim_KhachHang)){
			String ten = txtTim_TenKhachHang.getText();
			String sdt = txtTim_SoDienThoaiKhachHang.getText();
			DefaultTableModel model = (DefaultTableModel) tbl_DanhSachKhachHang.getModel();
			TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
			tbl_DanhSachKhachHang.setRowSorter(sorter);
			List<RowFilter<Object, Object>> filters = new ArrayList<>();
			filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(ten), 1));
			filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(sdt), 2));
			RowFilter<Object, Object> af = RowFilter.andFilter(filters);
			sorter.setRowFilter(af);
		}
		else if (o.equals (btnXoaTrangKhachHang)) {
			xoaRong();
		}
	}
	public void docDuLieu() {
		// TODO Auto-generated method stub
		KhachHang_DAO ds = new KhachHang_DAO(); 
		List<KhachHang> list = ds.getDsKhachHang(); 
		for (KhachHang kh : list) {
			String [] rowData = {kh.getMaKH()
					,kh.getTenKH()
					,kh.getSdtKH()
					,kh.isGioiTinh()?"Nam":"Nữ"
					,kh.getDiaChi()
					,kh.getEmail()};
			model_DanhSachKhachHang.addRow(rowData);
		}
		tbl_DanhSachKhachHang.setModel (model_DanhSachKhachHang);
	}
	private void xoaRong() {
		txtDiaChiKhachHang.setText("");
		txtHoTenKhachHang.setText("");
		txtMaKhachHang.setText(""); 
		txtSoDienThoaiKhachHang.setText(""); 
		txtEmailKhachHang.setText("");
		txtTim_TenKhachHang.setText(""); 
		txtTim_SoDienThoaiKhachHang.setForeground(Color.gray);
		txtTim_SoDienThoaiKhachHang.setText("");
		txtTim_SoDienThoaiKhachHang.setForeground(Color.gray);
		txtMaKhachHang.requestFocus();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tbl_DanhSachKhachHang.getSelectedRow();
		txtMaKhachHang.setText(tbl_DanhSachKhachHang.getValueAt(row, 0).toString());
		txtHoTenKhachHang.setText(tbl_DanhSachKhachHang.getValueAt(row, 1).toString());
		txtSoDienThoaiKhachHang.setText(tbl_DanhSachKhachHang.getValueAt(row, 2).toString()); 
		cmbGioiTinhKhachHang.setSelectedItem(tbl_DanhSachKhachHang.getValueAt(row, 3).toString());
		txtDiaChiKhachHang.setText(tbl_DanhSachKhachHang.getValueAt(row, 4).toString()); 
		if(tbl_DanhSachKhachHang.getValueAt(row, 5)!=null) {
			txtEmailKhachHang.setText(tbl_DanhSachKhachHang.getValueAt(row, 5).toString()); 
		}
		else {
			txtEmailKhachHang.setText(""); 
		}
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
	public static void updateData(List<KhachHang> danhSach) {
		for (KhachHang kh : danhSach) {
			String [] rowData = {kh.getMaKH()
					,kh.getTenKH()
					,kh.getSdtKH()
					,kh.isGioiTinh()?"Nam":"Nữ"
					,kh.getDiaChi(),
					kh.getEmail()};
			model_DanhSachKhachHang.addRow(rowData);
		}
	}

	public static void resetData() {
		model_DanhSachKhachHang.setRowCount(0);
		KhachHang_DAO khachHang_DAO = new KhachHang_DAO();
		List<KhachHang> danhSach = khachHang_DAO.getDsKhachHang();
		ManHinhCapNhatKhachHang.updateData(danhSach);
	}
	public  boolean validDataKhachHang() {
		String hoTen = txtHoTenKhachHang.getText().trim();
		String soDienThoai = txtSoDienThoaiKhachHang.getText().trim();
		String diaChi = txtDiaChiKhachHang.getText();
		if(!(hoTen.length() > 0 && hoTen.matches("^("+GeneratorID.tiengVietLow().toUpperCase()+GeneratorID.tiengVietLow()+"*((\\s)))+"+GeneratorID.tiengVietLow().toUpperCase()+GeneratorID.tiengVietLow()+"*$"))) {
			JOptionPane.showMessageDialog(null, "Họ Tên không chứa ký tự số. VD: Nguyễn Văn A");
			return false;
		}
		if (!(soDienThoai.length() == 10 && soDienThoai.matches("0[0-9]{9}"))) {
			JOptionPane.showMessageDialog(null, "Số Điện thoại bắt đầu bằng số 0 và độ dài số điện thoại bằng 10");
			return false;
		}
		if (!(diaChi.length()>0)) {
			JOptionPane.showMessageDialog(null, "Nhập địa chỉ khách hàng");
			return false;
		}

		return true;
	}
}
