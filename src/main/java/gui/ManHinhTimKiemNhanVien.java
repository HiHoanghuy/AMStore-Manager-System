package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import custom.RoundedCornerBorder;
import dao.NhanVien_DAO;
import entities.NhanVien;

public class ManHinhTimKiemNhanVien extends JPanel {

	private JScrollPane scr_Ds;
	private JTable tbl_Ds;
	private DefaultTableModel model_ds;

	private DecimalFormat df;
	private DateTimeFormatter dtf;
	private JTextField txtMa;
	
	private NhanVien_DAO nhanVien_DAO;
	private List<NhanVien> dsNV;
	private JTextField txtHoTen;
	private JTextField txtSdt;
	private JTextField txtDiaChi;
	private JTextField txtCCCD;

	/**
	 * Create the panel.
	 */
	public ManHinhTimKiemNhanVien() {

		/**
		 * Create the panel.
		 */

		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setSize(1260, 864);

		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		df = new DecimalFormat("#,### VND");
		dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		nhanVien_DAO = new NhanVien_DAO();
		
		
		JPanel pn_timKiem = new JPanel();
		pn_timKiem.setBackground(new Color(255, 255, 255));
		pn_timKiem.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true),
				"Nh\u1EADp th\u00F4ng tin nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(65, 105, 225)));
		pn_timKiem.setBounds(10, 50, 1240, 180);
		add(pn_timKiem);
		pn_timKiem.setLayout(null);

		JLabel lbl_thongBaoKq = new JLabel();
		lbl_thongBaoKq.setBounds(60, 60, 100, 14);
		lbl_thongBaoKq.setForeground(Color.red);
		pn_timKiem.add(lbl_thongBaoKq);

		JPanel pn_TimKiem = new JPanel();
		pn_TimKiem.setLayout(null);
		pn_TimKiem.setBackground(new Color(255, 250, 240));
		pn_TimKiem.setBounds(10, 20, 1220, 150);
		pn_timKiem.add(pn_TimKiem);

		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setHorizontalAlignment(SwingConstants.LEFT);
		lblGioiTinh.setFont(new Font("Arial", Font.PLAIN, 11));
		lblGioiTinh.setBounds(520, 110, 50, 30);
		pn_TimKiem.add(lblGioiTinh);

		String[] item_gt = { "Tất cả", "Nam", "Nữ" };
		JComboBox cmb_gioiTinh = new JComboBox(item_gt);
		cmb_gioiTinh.setBackground(new Color(245, 222, 179));
		cmb_gioiTinh.setBounds(600, 110, 140, 30);
		pn_TimKiem.add(cmb_gioiTinh);

		JLabel lblChucVu = new JLabel("Chức vụ:");
		lblChucVu.setHorizontalAlignment(SwingConstants.LEFT);
		lblChucVu.setFont(new Font("Arial", Font.PLAIN, 11));
		lblChucVu.setBounds(10, 110, 50, 30);
		pn_TimKiem.add(lblChucVu);

		String[] item_cv = { "Tất cả", "Nhân viên", "Quản lí" };
		JComboBox cmb_chucVu = new JComboBox(item_cv);
		cmb_chucVu.setBackground(new Color(245, 222, 179));
		cmb_chucVu.setBounds(90, 110, 140, 30);
		pn_TimKiem.add(cmb_chucVu);

		JButton btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setFont(new Font("Arial", Font.BOLD, 14));
		btnXoaTrang.setBackground(new Color(255, 0, 0));
		btnXoaTrang.setBounds(1000, 110, 100, 30);
		pn_TimKiem.add(btnXoaTrang);

		JButton btnTimNV = new JButton("Tìm");
		btnTimNV.setIcon(new ImageIcon(ManHinhTimKiemNhanVien.class.getResource("/images/icon_timkiem.png")));
		btnTimNV.setBounds(1110, 110, 100, 30);
		pn_TimKiem.add(btnTimNV);
		btnTimNV.setFont(new Font("Arial", Font.BOLD, 14));
		btnTimNV.setBackground(new Color(65, 105, 225));

		String[] item_cl = { "Tất cả", "Ca 1", "Ca 2" };
		JComboBox cmb_caLam = new JComboBox(item_cl);
		cmb_caLam.setBounds(320, 110, 140, 30);
		pn_TimKiem.add(cmb_caLam);
		cmb_caLam.setBackground(new Color(245, 222, 179));

		JLabel lblCaLam = new JLabel("Ca làm:");
		lblCaLam.setBounds(240, 110, 45, 30);
		pn_TimKiem.add(lblCaLam);

		JPanel pnl_nhapMa = new JPanel();
		pnl_nhapMa.setLayout(null);
		pnl_nhapMa.setBorder(new RoundedCornerBorder());
		pnl_nhapMa.setBackground(Color.WHITE);
		pnl_nhapMa.setBounds(90, 10, 140, 30);
		pn_TimKiem.add(pnl_nhapMa);

		txtMa = new JTextField();
		txtMa.setText("Nhập mã...");
		txtMa.setForeground(Color.GRAY);
		txtMa.setEditable(false);
		txtMa.setColumns(10);
		txtMa.setBorder(null);
		txtMa.setBackground(Color.WHITE);
		txtMa.setBounds(10, 3, 120, 24);
		txtMa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txtMa.setText("");
				txtMa.setForeground(Color.BLACK);
				txtMa.setEditable(true);
			}
		});
		pnl_nhapMa.add(txtMa);

		JLabel lblMa = new JLabel("Mã nhân viên:");
		lblMa.setHorizontalAlignment(SwingConstants.LEFT);
		lblMa.setFont(new Font("Arial", Font.PLAIN, 11));
		lblMa.setBounds(10, 10, 80, 30);
		pn_TimKiem.add(lblMa);
		
		JLabel lblTen = new JLabel("Tên nhân viên:");
		lblTen.setHorizontalAlignment(SwingConstants.LEFT);
		lblTen.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTen.setBounds(240, 10, 80, 30);
		pn_TimKiem.add(lblTen);
		
		JPanel pnl_nhapHoten = new JPanel();
		pnl_nhapHoten.setLayout(null);
		pnl_nhapHoten.setBorder(new RoundedCornerBorder());
		pnl_nhapHoten.setBackground(Color.WHITE);
		pnl_nhapHoten.setBounds(320, 10, 190, 30);
		pn_TimKiem.add(pnl_nhapHoten);
		
		txtHoTen = new JTextField();
		txtHoTen.setText("Nhập họ tên...");
		txtHoTen.setForeground(Color.GRAY);
		txtHoTen.setEditable(false);
		txtHoTen.setColumns(10);
		txtHoTen.setBorder(null);
		txtHoTen.setBackground(Color.WHITE);
		txtHoTen.setBounds(10, 3, 170, 24);
		pnl_nhapHoten.add(txtHoTen);
		txtHoTen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txtHoTen.setText("");
				txtHoTen.setForeground(Color.BLACK);
				txtHoTen.setEditable(true);
			}
		});
		
		JLabel lblSdt = new JLabel("Số điện thoại:");
		lblSdt.setHorizontalAlignment(SwingConstants.LEFT);
		lblSdt.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSdt.setBounds(520, 10, 80, 30);
		pn_TimKiem.add(lblSdt);
		
		JPanel pnl_nhapSdt = new JPanel();
		pnl_nhapSdt.setLayout(null);
		pnl_nhapSdt.setBorder(new RoundedCornerBorder());
		pnl_nhapSdt.setBackground(Color.WHITE);
		pnl_nhapSdt.setBounds(600, 10, 140, 30);
		pn_TimKiem.add(pnl_nhapSdt);
		
		txtSdt = new JTextField();
		txtSdt.setText("Nhập số điện thoại...");
		txtSdt.setForeground(Color.GRAY);
		txtSdt.setEditable(false);
		txtSdt.setColumns(10);
		txtSdt.setBorder(null);
		txtSdt.setBackground(Color.WHITE);
		txtSdt.setBounds(10, 3, 120, 24);
		pnl_nhapSdt.add(txtSdt);
		txtSdt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txtSdt.setText("");
				txtSdt.setForeground(Color.BLACK);
				txtSdt.setEditable(true);
			}
		});
		
		JLabel lblNgaySinh = new JLabel("CCCD:");
		lblNgaySinh.setBounds(10, 60, 80, 30);
		pn_TimKiem.add(lblNgaySinh);
		
		JPanel pnl_nhapMa_1 = new JPanel();
		pnl_nhapMa_1.setLayout(null);
		pnl_nhapMa_1.setBorder(new RoundedCornerBorder());
		pnl_nhapMa_1.setBackground(Color.WHITE);
		pnl_nhapMa_1.setBounds(320, 60, 420, 30);
		pn_TimKiem.add(pnl_nhapMa_1);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setText("Nhập địa chỉ...");
		txtDiaChi.setForeground(Color.GRAY);
		txtDiaChi.setEditable(false);
		txtDiaChi.setColumns(10);
		txtDiaChi.setBorder(null);
		txtDiaChi.setBackground(Color.WHITE);
		txtDiaChi.setBounds(10, 3, 400, 24);
		pnl_nhapMa_1.add(txtDiaChi);
		txtDiaChi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txtDiaChi.setText("");
				txtDiaChi.setForeground(Color.BLACK);
				txtDiaChi.setEditable(true);
			}
		});
		
		JLabel lblaCh = new JLabel("Địa chỉ:");
		lblaCh.setHorizontalAlignment(SwingConstants.LEFT);
		lblaCh.setFont(new Font("Arial", Font.PLAIN, 11));
		lblaCh.setBounds(240, 60, 80, 30);
		pn_TimKiem.add(lblaCh);
		
		JPanel pnl_nhapMa_2 = new JPanel();
		pnl_nhapMa_2.setLayout(null);
		pnl_nhapMa_2.setBorder(new RoundedCornerBorder());
		pnl_nhapMa_2.setBackground(Color.WHITE);
		pnl_nhapMa_2.setBounds(90, 60, 140, 30);
		pn_TimKiem.add(pnl_nhapMa_2);
		
		txtCCCD = new JTextField();
		txtCCCD.setText("Nhập cccd...");
		txtCCCD.setForeground(Color.GRAY);
		txtCCCD.setEditable(false);
		txtCCCD.setColumns(10);
		txtCCCD.setBorder(null);
		txtCCCD.setBackground(Color.WHITE);
		txtCCCD.setBounds(10, 3, 120, 24);
		pnl_nhapMa_2.add(txtCCCD);
		txtCCCD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txtCCCD.setText("");
				txtCCCD.setForeground(Color.BLACK);
				txtCCCD.setEditable(true);
			}
		});

		JPanel pn_dsnv = new JPanel();
		pn_dsnv.setBackground(new Color(255, 255, 255));
		pn_dsnv.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true),
				"Danh s\u00E1ch k\u1EBFt qu\u1EA3 t\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(65, 105, 225)));
		pn_dsnv.setBounds(10, 230, 1240, 624);
		add(pn_dsnv);
		pn_dsnv.setLayout(null);

		model_ds = new DefaultTableModel(new Object[][] {

		}, new String[] { "Mã nhân viên", "Họ tên", "Ngày sinh", "Giới tính", "Số điện thoại", "Địa chỉ", "Chức vụ",
				"Ca làm","Lương" }

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
		scr_Ds.setBounds(10, 20, 1220, 594);
		scr_Ds.getViewport().setBackground(Color.white);
		pn_dsnv.add(scr_Ds);
		
		JLabel btnExit = new JLabel("");
		btnExit.setIcon(new ImageIcon(ManHinhChinh.class.getResource("/images/close.png")));
		btnExit.setBounds(1240, 0, 20, 20);
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
		
		JLabel lblHeader = new JLabel("TÌM KIẾM NHÂN VIÊN");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setForeground(new Color(100, 149, 237));
		lblHeader.setFont(new Font("Arial", Font.BOLD, 20));
		lblHeader.setBounds(20, 0, 1210, 50);
		add(lblHeader);
		
		//hiển thị dữ liệu danh sách nhân viên khi khởi chạy chương trình
		dsNV = nhanVien_DAO.getDsNhanVien();
		capNhatDuLieuCuaBang(dsNV);
		
		//xử lý sự kiện khi click chọn một dòng trong bảng danh sách nhân viên
		tbl_Ds.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int selected = tbl_Ds.getSelectedRow();

			}
		});
		//xử lý sự kiện khi click chọn nút tìm nhân viên
		btnTimNV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//txtMa.setText("Nhập mã, tên, số điện thoại của  nhân viên...");
				//txtMa.setForeground(Color.GRAY);
				String maNV = txtMa.getText();
				maNV = maNV.equalsIgnoreCase("Nhập mã...")?"":maNV;
				String hoTenNV = txtHoTen.getText();
				hoTenNV = hoTenNV.equalsIgnoreCase("Nhập họ tên...")?"":hoTenNV;
				String sdtNV = txtSdt.getText();
				sdtNV = sdtNV.equalsIgnoreCase("Nhập số điện thoại...")?"":sdtNV;
				String cccdNV = txtCCCD.getText();
				cccdNV = cccdNV.equalsIgnoreCase("Nhập cccd...")?"":cccdNV;
				String diaChiNV = txtDiaChi.getText();
				diaChiNV = diaChiNV.equalsIgnoreCase("Nhập địa chỉ...")?"":diaChiNV;
				String chucVu = (String) cmb_chucVu.getSelectedItem();
				chucVu=chucVu.equalsIgnoreCase("Tất cả")?"%":chucVu;
				String caLam = (String) cmb_caLam.getSelectedItem();
				caLam = caLam.equalsIgnoreCase("Tất cả")?"%":caLam;
				String gioiTinh = (String) cmb_gioiTinh.getSelectedItem();
				if (gioiTinh.equalsIgnoreCase("Tất cả")) {
					gioiTinh = "%";
				} else if (gioiTinh.equalsIgnoreCase("Nam")) {
					gioiTinh = "1";
				} else gioiTinh= "0";
				dsNV = nhanVien_DAO.timKiem("%"+maNV+"%", "%"+hoTenNV+"%", "%"+sdtNV+"%", "%"+cccdNV+"%", "%"+diaChiNV+"%", chucVu, caLam, gioiTinh);
				//dsNV= nhanVien_DAO.timKiem("%", "%", "%", "%", "%", chucVu, caLam, gioiTinh);
				if (dsNV.size()==0) {
					JOptionPane.showMessageDialog(null, "Không tìm thấy!");
				}
				else {					
					capNhatDuLieuCuaBang(dsNV);
				}
				
			}
		});
		//xử lý sự kiện khi click chọn nút xóa trắng
		btnXoaTrang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txtMa.setText("Nhập mã...");
				txtMa.setForeground(Color.GRAY);
				txtHoTen.setText("Nhập họ tên...");
				txtHoTen.setForeground(Color.GRAY);
				txtSdt.setText("Nhập số điện thoại...");
				txtSdt.setForeground(Color.GRAY);
				txtCCCD.setText("Nhập cccd...");
				txtCCCD.setForeground(Color.GRAY);
				txtDiaChi.setText("Nhập địa chỉ...");
				txtDiaChi.setForeground(Color.GRAY);
				cmb_caLam.setSelectedIndex(0);
				cmb_chucVu.setSelectedIndex(0);
				cmb_gioiTinh.setSelectedIndex(0);
			}
		});
		
		ManHinhChinh.btnVN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblHeader.setText("TÌM KIẾM NHÂN VIÊN");
				((TitledBorder) pn_timKiem.getBorder()).setTitle("Nhập thông tinh nhân viên");
				pn_timKiem.repaint();
				((TitledBorder) pn_dsnv.getBorder()).setTitle("Danh sách nhân viên");
				pn_dsnv.repaint();
				lblMa.setText("Mã nhân viên:");
				lblTen.setText("Tên nhân viên:");
				lblSdt.setText("Số điện thoại:");
				lblNgaySinh.setText("CCCD:");
				lblaCh.setText("Địa chỉ:");
				lblChucVu.setText("Chức vụ:");
				lblCaLam.setText("Ca làm:");
				lblGioiTinh.setText("Giới tính:");
				
				//txtMa.setText("Nhập mã...");
				//txtHoTen.setText("Nhập họ tên...");
				//txtSdt.setText("Nhập số điện thoại...");
				//txtCCCD.setText("Nhập cccd...");
				//txtDiaChi.setText("Nhập địa chỉ...");
				btnXoaTrang.setText("Xoá trắng");
				btnTimNV.setText("Tìm");
				
				String[] newColumn = {"Mã nhân viên", "Họ tên", "Ngày sinh", "Giới tính", "Số điện thoại", "Địa chỉ", "Chức vụ",
						"Ca làm","Lương" };
				model_ds.setColumnIdentifiers(newColumn);
			
				
			}
		});
		
		ManHinhChinh.btnEN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblHeader.setText("SEARCH EMPLOYEE");
				((TitledBorder) pn_timKiem.getBorder()).setTitle("Enter employee information");
				pn_timKiem.repaint();
				((TitledBorder) pn_dsnv.getBorder()).setTitle("List of employees");
				pn_dsnv.repaint();
				lblMa.setText("Employee Id:");
				lblTen.setText("Name:");
				lblSdt.setText("Phone number:");
				lblNgaySinh.setText("CCCD:");
				lblaCh.setText("Address:");
				lblChucVu.setText("Position:");
				lblCaLam.setText("Shift:");
				lblGioiTinh.setText("Gender:");

				//txtMa.setText("Enter id...");
				//txtHoTen.setText("Enter full name...");
				//txtSdt.setText("Enter phone number...");
				//txtCCCD.setText("Enter cccd...");
				//txtDiaChi.setText("Enter address...");
				btnXoaTrang.setText("Clear");
				btnTimNV.setText("Find");

				String[] newColumn = {"Employee id", "Full name", "Birthday", "Gender", "Phone number", "Address", "Position",
				"Shift","Salary" };
				model_ds.setColumnIdentifiers(newColumn);
			}
		});
	}
	/**
	 * hàm xóa dữ liệu trên bảng
	 * @param t là bảng cần xóa
	 */
	private void xoaDuLieuCuaBang(JTable t) {
		DefaultTableModel dm = (DefaultTableModel) t.getModel();
		dm.getDataVector().removeAllElements();
	}
	/**
	 * hàm cập nhật dữ liệu bảng danh sách nhân viên
	 * @param dsNV là dánh sách nhân viên cần hiển thị lên bảng
	 */
	private void capNhatDuLieuCuaBang(List<NhanVien> dsNV) {
		xoaDuLieuCuaBang(tbl_Ds);
		for (NhanVien nv : dsNV) {
			Object data[] = {nv.getMaNV(), nv.getTenNV(), dtf.format(nv.getNgaySinh()), nv.isGioiTinh()?"Nam":"Nữ", nv.getSdt(), nv.getDiaChi(), nv.getChucVu(), nv.getCaLamViec(), df.format(nv.tinhLuong())};
			model_ds.addRow(data);
		}
	}
}
