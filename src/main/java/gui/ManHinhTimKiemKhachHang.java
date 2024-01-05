package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
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


import connectDB.ConnectDB;
import custom.CustomScrollBarUI;
import dao.KhachHang_DAO;
import entities.KhachHang;

public class ManHinhTimKiemKhachHang extends JPanel implements ActionListener,MouseListener{

	private static final long serialVersionUID = 1L;
	private JButton btnTim_KhachHang;
	private JButton btnXoaTrangKhachHang;
	private JScrollPane scr_DanhSachKhachHang;
	private JTable tbl_DanhSachKhachHang;
	private static DefaultTableModel model_danhSachKhachHanh;

	private JTextField txtHoTenKhachHang;
	private JTextField txt_ngaySinh;
	private JTextField txtSoDienThoaiKhachHang;
	private JTextField txtDiaChiKhachHang;
	private JComboBox cmbGioiTinhKhachHang;

	private JTextField txtMaKhachHang;
	private JTextField txtEmailKhachHang;
	/**
	 * Launch the application.
	 */
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManHinhTimKiemKhachHang frame = new ManHinhTimKiemKhachHang();
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
	public ManHinhTimKiemKhachHang() {
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
		pnlManHinhThaoTac.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Tìm kiếm khách hàng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pnlManHinhThaoTac.setBounds(10, 50, 1239, 143);
		add(pnlManHinhThaoTac);
		pnlManHinhThaoTac.setLayout(null);

		JPanel pnlThaoTac = new JPanel();
		pnlThaoTac.setLayout(null);
		pnlThaoTac.setBackground(new Color(255, 250, 240));
		pnlThaoTac.setBounds(10, 36, 1219, 95);
		pnlManHinhThaoTac.add(pnlThaoTac);

		JLabel lblMaKhachHang = new JLabel("Mã khách hàng:");
		lblMaKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaKhachHang.setFont(new Font("Arial", Font.PLAIN, 11));
		lblMaKhachHang.setBounds(10, 15, 80, 20);
		pnlThaoTac.add(lblMaKhachHang);

		JLabel lblTenKhachHang = new JLabel("       Họ tên:");
		lblTenKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenKhachHang.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTenKhachHang.setBounds(228, 15, 70, 20);
		pnlThaoTac.add(lblTenKhachHang);

		JLabel lblGioiTinhKhachHang = new JLabel("Giới tính:");
		lblGioiTinhKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		lblGioiTinhKhachHang.setFont(new Font("Arial", Font.PLAIN, 11));
		lblGioiTinhKhachHang.setBounds(732, 17, 50, 20);
		pnlThaoTac.add(lblGioiTinhKhachHang);

		txtHoTenKhachHang = new JTextField();
		txtHoTenKhachHang.setBackground(new Color(255, 250, 240));
		txtHoTenKhachHang.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtHoTenKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		txtHoTenKhachHang.setBounds(308, 16, 160, 20);
		pnlThaoTac.add(txtHoTenKhachHang);
		txtHoTenKhachHang.setColumns(10);

		txt_ngaySinh = new JTextField();
		txt_ngaySinh.setHorizontalAlignment(SwingConstants.LEFT);
		txt_ngaySinh.setColumns(10);
		txt_ngaySinh.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_ngaySinh.setBackground(new Color(255, 250, 240));
		txt_ngaySinh.setBounds(520, 15, 140, 20);
	


		String[] itemGioiTinh = {"Tất cả","Nam","Nữ"};
		cmbGioiTinhKhachHang = new JComboBox(itemGioiTinh);
		//combo_gioiTinh.setModel(new DefaultComboBoxModel(new String[] {"Tất cả","Nam", "Nữ"}));
		cmbGioiTinhKhachHang.setBackground(new Color(245, 222, 179));
		cmbGioiTinhKhachHang.setBounds(805, 15, 90, 25);
		pnlThaoTac.add(cmbGioiTinhKhachHang);

		JLabel lblSoDienThoaiKhachHang = new JLabel("Số điện thoại:");
		lblSoDienThoaiKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		lblSoDienThoaiKhachHang.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSoDienThoaiKhachHang.setBounds(497, 14, 90, 20);
		pnlThaoTac.add(lblSoDienThoaiKhachHang);

		txtSoDienThoaiKhachHang = new JTextField();
		txtSoDienThoaiKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		txtSoDienThoaiKhachHang.setColumns(10);
		txtSoDienThoaiKhachHang.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtSoDienThoaiKhachHang.setBackground(new Color(255, 250, 240));
		txtSoDienThoaiKhachHang.setBounds(587, 15, 125, 20);
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


		btnXoaTrangKhachHang = new JButton("Xóa trắng");
		btnXoaTrangKhachHang.setFont(new Font("Arial", Font.BOLD, 14));
		btnXoaTrangKhachHang.setBackground(new Color(255, 0, 0));
		btnXoaTrangKhachHang.setBounds(1099, 55, 110, 30);
		pnlThaoTac.add(btnXoaTrangKhachHang);

		btnTim_KhachHang = new JButton("Tìm");
		btnTim_KhachHang.setFont(new Font("Arial", Font.BOLD, 14));
		btnTim_KhachHang.setBounds(1099, 10, 110, 30);
		pnlThaoTac.add(btnTim_KhachHang);
		btnTim_KhachHang.setBackground(new Color(255, 255, 255));
		btnTim_KhachHang.setIcon(new ImageIcon(ManHinhChinh.class.getResource("/images/search.png")));

		txtMaKhachHang = new JTextField();
		txtMaKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		txtMaKhachHang.setColumns(10);
		txtMaKhachHang.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtMaKhachHang.setBackground(new Color(255, 250, 240));
		txtMaKhachHang.setBounds(88, 15, 130, 20);
		pnlThaoTac.add(txtMaKhachHang);
		
		txtEmailKhachHang = new JTextField();
		txtEmailKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		txtEmailKhachHang.setColumns(10);
		txtEmailKhachHang.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtEmailKhachHang.setBackground(new Color(255, 250, 240));
		txtEmailKhachHang.setBounds(555, 58, 340, 20);
		pnlThaoTac.add(txtEmailKhachHang);
		
		JLabel lblEmailKhachHang = new JLabel("Email:");
		lblEmailKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmailKhachHang.setFont(new Font("Arial", Font.PLAIN, 11));
		lblEmailKhachHang.setBounds(500, 58, 55, 20);
		pnlThaoTac.add(lblEmailKhachHang);

		JPanel pnlDanhSachKhachHang = new JPanel();
		pnlDanhSachKhachHang.setBackground(new Color(255, 255, 255));
		pnlDanhSachKhachHang.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Danh sách khách hàng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pnlDanhSachKhachHang.setBounds(10, 204, 1239, 650);
		add(pnlDanhSachKhachHang);
		pnlDanhSachKhachHang.setLayout(null);

		model_danhSachKhachHanh = new DefaultTableModel(new Object[][] {

		}, new String[] { "Mã khách hàng", "Họ tên", "Số điện thoại", "Giới tính", "Địa chỉ","Email"}

				) {
			boolean[] canEdit = new boolean[] { false, false, false, false, false, false, false, false };

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return canEdit[column];
			}
		};

		tbl_DanhSachKhachHang = new JTable(model_danhSachKhachHanh);
		tbl_DanhSachKhachHang.setSelectionBackground(new Color(65, 105, 225));
		tbl_DanhSachKhachHang.setRowHeight(40);
		tbl_DanhSachKhachHang.setGridColor(new Color(0, 0, 0));

		DefaultTableCellRenderer head_render = new DefaultTableCellRenderer();
		head_render.setBackground(new Color(135, 205, 230));
		tbl_DanhSachKhachHang.getTableHeader().setDefaultRenderer(head_render);

		scr_DanhSachKhachHang = new JScrollPane();
		scr_DanhSachKhachHang.setViewportView(tbl_DanhSachKhachHang);
		scr_DanhSachKhachHang.setBounds(10, 20, 1219, 620);
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

		JLabel lblTenManHinh = new JLabel("TÌM KIẾM KHÁCH HÀNG");
		lblTenManHinh.setFont(new Font("Arial", Font.BOLD, 20));
		lblTenManHinh.setForeground(new Color(100, 149, 237));
		lblTenManHinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenManHinh.setBounds(20, 0, 1209, 50);
		add(lblTenManHinh);

		btnTim_KhachHang.addActionListener(this);
		btnXoaTrangKhachHang.addActionListener(this);
		tbl_DanhSachKhachHang.addMouseListener(this);
		docDuLieuLenBang();
		
		ManHinhChinh.btnVN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblTenManHinh.setText("TÌM KIẾM KHÁCH HÀNG");
				((TitledBorder) pnlManHinhThaoTac.getBorder()).setTitle("Tìm kiếm khách hàng");
				pnlManHinhThaoTac.repaint();
				((TitledBorder) pnlDanhSachKhachHang.getBorder()).setTitle("Danh sách khách hàng");
				pnlDanhSachKhachHang.repaint();
				btnTim_KhachHang.setText("Tìm");
				lblMaKhachHang.setText("Mã khách hàng:");
				lblTenKhachHang.setText("Họ tên:");
				lblSoDienThoaiKhachHang.setText("Số điện thoại:");
				lblGioiTinhKhachHang.setText("Giới tính:");
				lblDiaChiKhachHang.setText("Địa chỉ:");
				btnXoaTrangKhachHang.setText("Xoá trắng");
				String[] newColumns_ds = { "Mã khách hàng", "Họ tên", "Số điện thoại", "Giới tính", "Địa chỉ","Email"};
				model_danhSachKhachHanh.setColumnIdentifiers(newColumns_ds);
			}
		});
		
		ManHinhChinh.btnEN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblTenManHinh.setText("SEARCH FOR CUSTOMERS");
				((TitledBorder) pnlManHinhThaoTac.getBorder()).setTitle("Search for customers");
				pnlManHinhThaoTac.repaint();
				((TitledBorder) pnlDanhSachKhachHang.getBorder()).setTitle("List of customers");
				pnlDanhSachKhachHang.repaint();
				btnTim_KhachHang.setText("Find");
				lblMaKhachHang.setText("Customer Id:");
				lblTenKhachHang.setText("Name:");
				lblSoDienThoaiKhachHang.setText("Phone number:");
				lblGioiTinhKhachHang.setText("Gender:");
				lblDiaChiKhachHang.setText("Address:");
				btnXoaTrangKhachHang.setText("Clear");
				String[] newColumns_ds = { "Customer Id", "Full name", "Phone number", "Gender", "Address","Email"};
				model_danhSachKhachHanh.setColumnIdentifiers(newColumns_ds);
			}
		});
	}
	private void xoaRong() {
		txtDiaChiKhachHang.setText("");
		txtHoTenKhachHang.setText("");
		txtMaKhachHang.setText(""); 
		txtSoDienThoaiKhachHang.setText(""); 
		btnTim_KhachHang.setText(""); 
		txtEmailKhachHang.setText("");
		txtMaKhachHang.requestFocus();
	}
	private void docDuLieuLenBang() {
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
			model_danhSachKhachHanh.addRow(rowData);
		}
		tbl_DanhSachKhachHang.setModel (model_danhSachKhachHanh);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tbl_DanhSachKhachHang.getSelectedRow();
		txtMaKhachHang.setText(tbl_DanhSachKhachHang.getValueAt(row, 0).toString());
		txtHoTenKhachHang.setText(tbl_DanhSachKhachHang.getValueAt(row, 1).toString());
		txtSoDienThoaiKhachHang.setText(tbl_DanhSachKhachHang.getValueAt(row, 2).toString()); 
		cmbGioiTinhKhachHang.setSelectedIndex(tbl_DanhSachKhachHang.getValueAt(row, 3).toString().equals("Nam")?0:1);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnTim_KhachHang)) {
			String tenKh = txtHoTenKhachHang.getText();
			String sdt = txtSoDienThoaiKhachHang.getText();
			String gt = cmbGioiTinhKhachHang.getSelectedItem().toString();
			gt = gt.equals("Tất cả")?"":gt;
			String diaChi = txtDiaChiKhachHang.getText();
			String email = txtEmailKhachHang.getText();
			String maKh = txtMaKhachHang.getText();

			DefaultTableModel model = (DefaultTableModel) tbl_DanhSachKhachHang.getModel();
			TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
			tbl_DanhSachKhachHang.setRowSorter(sorter);
			List<RowFilter<Object, Object>> filters = new ArrayList<>();
			filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(maKh), 0));
			filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(tenKh), 1));
			filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(sdt), 2));
			filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(gt), 3));
			filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(diaChi), 4));
			filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(email), 5));
			RowFilter<Object, Object> af = RowFilter.andFilter(filters);
			sorter.setRowFilter(af);
		}
		else if (o.equals(btnXoaTrangKhachHang)) {
			xoaRong();
		}
	}
	public static void updateData(List<KhachHang> ds) {
		
		for (KhachHang kh : ds) {
			String [] rowData = {kh.getMaKH()
					,kh.getTenKH()
					,kh.getSdtKH()
					,kh.isGioiTinh()?"Nam":"Nữ"
					,kh.getDiaChi()
					,kh.getEmail()};
			model_danhSachKhachHanh.addRow(rowData);
		}
	}
	public static void resetData() {
		model_danhSachKhachHanh.setRowCount(0);
		KhachHang_DAO kh_DAO = new KhachHang_DAO();
		List<KhachHang> ds = kh_DAO.getDsKhachHang();
		ManHinhTimKiemKhachHang.updateData(ds);
	}
}
