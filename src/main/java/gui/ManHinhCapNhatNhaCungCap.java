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
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
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
import dao.NhaCungCap_DAO;
import entities.NhaCungCap;

import javax.swing.JComboBox;

public class ManHinhCapNhatNhaCungCap extends JPanel {

	private JTextField txtSearch;
	private JButton btnSearch;

	private JScrollPane scr_Ds;
	private JTable tbl_Ds;
	private DefaultTableModel model_ds;

	private JLabel lbl_kqMa;

	private DecimalFormat df;
	private DateTimeFormatter dtf;

	private JTextField txt_tenNCC;
	private JTextField txt_sdtNCC;
	private JTextField txt_diaChiNCC;
	
	private NhaCungCap_DAO nhaCC_dao;
	private List<NhaCungCap> dsNCC;
	
	/**
	 * Create the panel.
	 */
	public ManHinhCapNhatNhaCungCap() {

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
		
		nhaCC_dao = new NhaCungCap_DAO();
		dsNCC = nhaCC_dao.getDsNCC();

		JPanel pn_thaotac = new JPanel();
		pn_thaotac.setBackground(new Color(255, 255, 255));
		pn_thaotac.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "C\u1EADp nh\u1EADt th\u00F4ng tin nh\u00E0 cung c\u1EA5p", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_thaotac.setBounds(10, 50, 1239, 231);
		add(pn_thaotac);
		pn_thaotac.setLayout(null);

		JPanel timKiem = new JPanel();
		timKiem.setBackground(new Color(255, 255, 255));
		timKiem.setBounds(10, 20, 321, 30);
		timKiem.setBorder(new RoundedCornerBorder());
		pn_thaotac.add(timKiem);
		timKiem.setLayout(null);

		txtSearch = new JTextField();
		txtSearch.setText("Nhập mã nhân viên...");
		txtSearch.setForeground(Color.GRAY);
		txtSearch.setEditable(false);
		txtSearch.setBackground(new Color(255, 255, 255));
		txtSearch.setBounds(10, 3, 200, 24);
		txtSearch.setBorder(null);
		txtSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txtSearch.setText("");
				txtSearch.setForeground(Color.BLACK);
				txtSearch.setEditable(true);
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
				txtSearch.setText("Nhập mã sản phẩm ...");
				txtSearch.setForeground(Color.GRAY);
			}
		});
		timKiem.add(txtSearch);
		txtSearch.setColumns(10);

		btnSearch = new JButton("Tìm");
		// btnSearch.setBorder(new RoundedCornerBorder());
		btnSearch.setBackground(new Color(65, 105, 225));
		btnSearch.setIcon(new ImageIcon(ManHinhCapNhatNhaCungCap.class.getResource("/images/search.png")));
		btnSearch.setBounds(220, 3, 91, 24);
		timKiem.add(btnSearch);

		JLabel lblKqTimKiem = new JLabel("Kết quả:");
		lblKqTimKiem.setBounds(10, 60, 50, 14);
		pn_thaotac.add(lblKqTimKiem);

		JLabel lbl_thongBaoKq = new JLabel();
		lbl_thongBaoKq.setBounds(60, 60, 100, 14);
		lbl_thongBaoKq.setForeground(Color.red);
		pn_thaotac.add(lbl_thongBaoKq);

		JPanel pn_kqTimKiem = new JPanel();
		pn_kqTimKiem.setLayout(null);
		pn_kqTimKiem.setBackground(new Color(255, 250, 240));
		pn_kqTimKiem.setBounds(10, 90, 1219, 132);
		pn_thaotac.add(pn_kqTimKiem);

		JLabel lblMaNCC = new JLabel("Mã nhà cung cấp:");
		lblMaNCC.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaNCC.setFont(new Font("Arial", Font.PLAIN, 11));
		lblMaNCC.setBounds(10, 15, 93, 20);
		pn_kqTimKiem.add(lblMaNCC);

		lbl_kqMa = new JLabel("");
		lbl_kqMa.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqMa.setBounds(98, 15, 100, 20);
		pn_kqTimKiem.add(lbl_kqMa);

		JLabel lblTenNCC = new JLabel("Họ tên:");
		lblTenNCC.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenNCC.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTenNCC.setBounds(230, 15, 72, 20);
		pn_kqTimKiem.add(lblTenNCC);

		JLabel lblsdtNCC = new JLabel("Số điện thoại:");
		lblsdtNCC.setHorizontalAlignment(SwingConstants.LEFT);
		lblsdtNCC.setFont(new Font("Arial", Font.PLAIN, 11));
		lblsdtNCC.setBounds(886, 15, 93, 20);
		pn_kqTimKiem.add(lblsdtNCC);

		JLabel lbldiaChi = new JLabel("Địa chỉ:");
		lbldiaChi.setHorizontalAlignment(SwingConstants.LEFT);
		lbldiaChi.setFont(new Font("Arial", Font.PLAIN, 11));
		lbldiaChi.setBounds(540, 15, 50, 20);
		pn_kqTimKiem.add(lbldiaChi);


		
		txt_tenNCC = new JTextField();
		txt_tenNCC.setBackground(new Color(255, 250, 240));
		txt_tenNCC.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_tenNCC.setHorizontalAlignment(SwingConstants.LEFT);
		txt_tenNCC.setBounds(301, 16, 173, 20);
		pn_kqTimKiem.add(txt_tenNCC);
		txt_tenNCC.setColumns(10);
		
		txt_sdtNCC = new JTextField();
		txt_sdtNCC.setBackground(new Color(255, 250, 240));
		txt_sdtNCC.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_sdtNCC.setHorizontalAlignment(SwingConstants.LEFT);
		txt_sdtNCC.setBounds(988, 16, 106, 20);
		pn_kqTimKiem.add(txt_sdtNCC);
		txt_sdtNCC.setColumns(10);
		
		txt_diaChiNCC = new JTextField();
		txt_diaChiNCC.setBackground(new Color(255, 250, 240));
		txt_diaChiNCC.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_diaChiNCC.setHorizontalAlignment(SwingConstants.LEFT);
		txt_diaChiNCC.setBounds(600, 16, 257, 20);
		pn_kqTimKiem.add(txt_diaChiNCC);
		txt_diaChiNCC.setColumns(10);
	
		JButton btnSua = new JButton("Sửa");
		btnSua.setBounds(459, 76, 100, 30);
		pn_kqTimKiem.add(btnSua);
		btnSua.setFont(new Font("Arial", Font.BOLD, 14));
		btnSua.setBackground(new Color(244, 164, 96));
		
		JButton btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setFont(new Font("Arial", Font.BOLD, 14));
		btnXoaTrang.setBackground(new Color(255, 0, 0));
		btnXoaTrang.setBounds(293, 76, 100, 30);
		pn_kqTimKiem.add(btnXoaTrang);
		
		JButton btnThemNV = new JButton("Thêm");
		btnThemNV.setBounds(638, 76, 100, 30);
		pn_kqTimKiem.add(btnThemNV);
		btnThemNV.setFont(new Font("Arial", Font.BOLD, 14));
		btnThemNV.setBackground(new Color(0, 128, 0));

		JPanel pn_dsnv = new JPanel();
		pn_dsnv.setBackground(new Color(255, 255, 255));
		pn_dsnv.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Danh s\u00E1ch nh\u00E0 cung c\u1EA5p", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_dsnv.setBounds(10, 280, 1239, 574);
		add(pn_dsnv);
		pn_dsnv.setLayout(null);

		model_ds = new DefaultTableModel(new Object[][] {
				
		}, new String[] { "Mã nhà cung cấp", "Họ tên", "Số điện thoại", "Địa chỉ" }

		) {
			boolean[] canEdit = new boolean[] { false, false, false, false };

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
		scr_Ds.setBounds(10, 20, 1219, 544);
		scr_Ds.getViewport().setBackground(Color.white);
		scr_Ds.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		pn_dsnv.add(scr_Ds);
		
		JLabel lblTenManHinh = new JLabel("CẬP NHẬT NHÀ CUNG CẤP");
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
				if(JOptionPane.showConfirmDialog(null, "Nhấn OK để thoát khỏi chương trình.", "Thoat", JOptionPane.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION) == 0) {
					System.exit(0);
				}
			}
		});

		updateTable();
		

		tbl_Ds.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int selected = tbl_Ds.getSelectedRow();
				String maNCC = (String) model_ds.getValueAt(selected, 0);
			
				NhaCungCap ncc = nhaCC_dao.getNCC(maNCC);
				hienThiThongTinKetQua(ncc);
				
			}
		});

		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				String ma = txtSearch.getText();
				NhaCungCap ncc = nhaCC_dao.getNCC(ma);
				if (ncc!=null) {
					hienThiThongTinKetQua(ncc);
				}
				else {
					lbl_thongBaoKq.setText("Không tìm thấy");
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
		btnThemNV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
				String hoten = txt_tenNCC.getText();
				String sdt = txt_sdtNCC.getText();
				String diaChi = txt_diaChiNCC.getText();
				String ma = lbl_kqMa.getText();
				NhaCungCap ncc = new NhaCungCap(ma, hoten, sdt, diaChi);
				if(validDataNhaCungCap()) {
					if (nhaCC_dao.capNhatNCC(ma,ncc)) {
						updateTable();
						JOptionPane.showMessageDialog(null, "thanh cong");
				
				String[] item = ManHinhCapNhatSanPham.capNhatCmbNhaCungCap();
				String[] t_item = ManHinhTimKiemSanPham.capNhatCmbNCC();
				ManHinhCapNhatSanPham.cmbNhaCungCap.setModel(new DefaultComboBoxModel<>(item));	
				ManHinhTimKiemSanPham.cmbNhaCungCap.setModel(new DefaultComboBoxModel<>(t_item));
					JOptionPane.showMessageDialog(null, "thanh cong");
				}
				else JOptionPane.showMessageDialog(null, "that bai!");
				}
			}
		});
		
		btnSua.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(validDataNhaCungCap()) { 
					String hoten = txt_tenNCC.getText();
					String sdt = txt_sdtNCC.getText();
					String diaChi = txt_diaChiNCC.getText();
					String ma = lbl_kqMa.getText();
					NhaCungCap ncc = new NhaCungCap(ma, hoten, sdt, diaChi);
					
					if (nhaCC_dao.capNhatNCC(ma,ncc)) {
						updateTable();
						JOptionPane.showMessageDialog(null, "thanh cong");
					}
					else JOptionPane.showMessageDialog(null, "that bai!");
				}
				
			}
		});
		
		ManHinhChinh.btnVN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblTenManHinh.setText("CẬP NHẬT NHÀ CUNG CẤP");
				((TitledBorder) pn_thaotac.getBorder()).setTitle("Cập nhật thông tin nhà cung cấp");
				pn_thaotac.repaint();
				((TitledBorder) pn_dsnv.getBorder()).setTitle("Danh sách nhà cung cấp");
				pn_dsnv.repaint();
				lblKqTimKiem.setText("Kết quả:");
				txtSearch.setText("Nhập mã nhân viên...");
				btnSearch.setText("Tìm");
				lblMaNCC.setText("Mã nhà cung cấp:");
				lblTenNCC.setText("Họ tên:");
				lblsdtNCC.setText("Số điện thoại:");
				lbldiaChi.setText("Địa chỉ:");
				btnXoaTrang.setText("Xoá trắng");
				btnSua.setText("Sửa");
				btnThemNV.setText("Thêm");
				String[] newColumns_ds = { "Mã nhà cung cấp", "Họ tên", "Số điện thoại", "Địa chỉ" };
				model_ds.setColumnIdentifiers(newColumns_ds);
			}
		});
		
		ManHinhChinh.btnEN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblTenManHinh.setText("UPDATE SUPPLIER");
				((TitledBorder) pn_thaotac.getBorder()).setTitle("Update supplier information");
				pn_thaotac.repaint();
				((TitledBorder) pn_dsnv.getBorder()).setTitle("Supplier List");
				pn_dsnv.repaint();
				lblKqTimKiem.setText("Result:");
				txtSearch.setText("Enter employee code...");
				btnSearch.setText("Search");
				lblMaNCC.setText("Vendor Id:");
				lblTenNCC.setText("Full name:");
				lblsdtNCC.setText("Phone number:");
				lbldiaChi.setText("Address:");
				btnXoaTrang.setText("Clear");
				btnSua.setText("Edit");
				btnThemNV.setText("Add");
				String[] newColumns_ds = { "Supplier Id", "Full name", "Phone number", "Address" };
				model_ds.setColumnIdentifiers(newColumns_ds);
			}
		});
	}
	
	private void xoaTrang() {
		lbl_kqMa.setText("");
		txt_tenNCC.setText("");
		txt_sdtNCC.setText("");
		txt_diaChiNCC.setText("");
	}
	
	private void hienThiThongTinKetQua(NhaCungCap ncc) {
		lbl_kqMa.setText(ncc.getMaNCC());
		txt_tenNCC.setText(ncc.getTenNCC());
		txt_sdtNCC.setText(ncc.getSdtNCC());
		txt_diaChiNCC.setText(ncc.getDiaChiNCC());
	
	}
	
	private void xoaTable() {
        DefaultTableModel dtm = (DefaultTableModel) tbl_Ds.getModel();
        dtm.getDataVector().removeAllElements();
    }
	private void updateTable() {
		xoaTable();
		dsNCC = nhaCC_dao.getDsNCC();
		for (NhaCungCap nv : dsNCC) {
			Object data[] = {nv.getMaNCC(), nv.getTenNCC(), nv.getSdtNCC(), nv.getDiaChiNCC()};
			model_ds.addRow(data);
		}
	}
	public boolean validDataNhaCungCap() {
		try {
			String ten = txt_tenNCC.getText();
			String diaChi = txt_diaChiNCC.getText();
			String soDT = txt_sdtNCC.getText();
			String tv = "[A-ZĐ|Ắ|Ắ|Ắ|Ằ|Ằ|Ằ|Ẳ|Ẳ|Ẳ|Ẵ|Ẵ|Ẵ|Ặ|Ặ|Ặ|Ặ|Ặ|Ặ|Ă|Ă|Ấ|Ấ|Ấ|Ầ|Ầ|Ầ|Ẩ|Ẩ|Ẩ|Ẫ|Ẫ|Ẫ|Ậ|Ậ|Ậ|Ậ|Ậ|Ậ|Â|Â|Á|Á|À|À|Ã|Ã|Ả|Ả|Ạ|Ạ|Ế|Ế|Ế|Ề|Ề|Ề|Ể|Ể|Ể|Ễ|Ễ|Ễ|Ệ|Ệ|Ệ|Ệ|Ệ|Ệ|Ê|Ê|É|É|È|È|Ẻ|Ẻ|Ẽ|Ẽ|Ẹ|Ẹ|Í|Í|Ì|Ì|Ỉ|Ỉ|Ĩ|Ĩ|Ị|Ị|Ố|Ố|Ố|Ồ|Ồ|Ồ|Ổ|Ổ|Ổ|Ỗ|Ỗ|Ỗ|Ộ|Ộ|Ộ|Ộ|Ộ|Ộ|Ô|Ô|Ớ|Ớ|Ớ|Ớ|Ớ|Ớ|Ờ|Ờ|Ờ|Ờ|Ờ|Ờ|Ở|Ở|Ở|Ở|Ở|Ở|Ỡ|Ỡ|Ỡ|Ỡ|Ỡ|Ỡ|Ợ|Ợ|Ợ|Ợ|Ợ|Ợ|Ơ|Ơ|Ó|Ó|Ò|Ò|Õ|Õ|Ỏ|Ỏ|Ọ|Ọ|Ứ|Ứ|Ứ|Ứ|Ứ|Ứ|Ừ|Ừ|Ừ|Ừ|Ừ|Ừ|Ử|Ử|Ử|Ử|Ử|Ử|Ữ|Ữ|Ữ|Ữ|Ữ|Ữ|Ự|Ự|Ự|Ự|Ự|Ự|Ư|Ư|Ú|Ú|Ù|Ù|Ủ|Ủ|Ũ|Ũ|Ụ|Ụ|Ý|Ý|Ỳ|Ỳ|Ỷ|Ỷ|Ỹ|Ỹ|Ỵ|Ỵ]";
			String ht = "["+tv.toLowerCase()+"]";
			if(!(ten.length() > 0 && ten.matches("[A-Za-z ]+"))) {
				txt_tenNCC.requestFocus();
				txt_tenNCC.selectAll();
				JOptionPane.showMessageDialog(this, "Họ tên không chứa số và viết chữ có dấu\\nHọ tên bắt đầu là chữ HOA và sau mỗi khoảng trắng bắt buộc viết HOA");
				return false;
			}
			if(!(soDT.length() == 10 && soDT.matches("(^0)([0-9]{9}$)"))) {
				txt_sdtNCC.requestFocus();
				txt_sdtNCC.selectAll();
				JOptionPane.showMessageDialog(this, "Số Điện thoại bắt đầu bằng số 0 và độ dài số điện thoại bằng 10");
				return false;
			}
		} catch (NumberFormatException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
