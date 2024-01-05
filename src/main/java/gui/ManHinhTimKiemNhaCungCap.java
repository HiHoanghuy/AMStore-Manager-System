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
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.NhaCungCap_DAO;
import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entities.DonDatHang;
import entities.HoaDon;
import entities.KhachHang;
import entities.NhaCungCap;
import entities.NhanVien;
import entities.TaiKhoan;

import entities.ChuongTrinhKhuyenMai;
import dao.ChuongTrinhKhuyenMai_DAO;

import javax.swing.JComboBox;

public class ManHinhTimKiemNhaCungCap extends JPanel {

	private JTextField txtSearch;
	private JButton btnSearch;

	private JScrollPane scr_Ds;
	private JTable tbl_Ds;
	private DefaultTableModel model_ds;

	private JTextField txt_maNCC;

	private DecimalFormat df;
	private DateTimeFormatter dtf;
	
	private JTextField txt_tenNCC;
	private JTextField txt_SDT;
	

	private UtilDateModel model_date1;
	private JDatePanelImpl datePanel1;
	private JDatePickerImpl datePicker1;
	
	
	private NhaCungCap_DAO ncc_dao;
	private List<NhaCungCap> dsNCC;
	private JTextField txt_diaChi;


	
	/**
	 * Create the panel.
	 */
	public ManHinhTimKiemNhaCungCap() {

		ncc_dao = new NhaCungCap_DAO();
		
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

		
		JPanel pn_thaotac = new JPanel();
		pn_thaotac.setBackground(new Color(255, 255, 255));
		pn_thaotac.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Tìm Kiếm Nhà Cung Cấp", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_thaotac.setBounds(10, 50, 1239, 158);
		add(pn_thaotac);
		pn_thaotac.setLayout(null);


		btnSearch = new JButton("Tìm");
		// btnSearch.setBorder(new RoundedCornerBorder());
		btnSearch.setBackground(new Color(65, 105, 225));
		btnSearch.setIcon(new ImageIcon(ManHinhTimKiemNhaCungCap.class.getResource("/images/search.png")));
		btnSearch.setBounds(976, 82, 100, 30);
		

		JPanel pn_kqTimKiem = new JPanel();
		pn_kqTimKiem.setLayout(null);
		pn_kqTimKiem.setBackground(new Color(255, 250, 240));
		pn_kqTimKiem.setBounds(10, 22, 1219, 122);
		pn_thaotac.add(pn_kqTimKiem);

		pn_kqTimKiem.add(btnSearch);
		
		JLabel lblKqTimKiem = new JLabel("Kết quả:");
		lblKqTimKiem.setBounds(10, 88, 67, 30);
		pn_kqTimKiem.add(lblKqTimKiem);

		JLabel lbl_thongBaoKq = new JLabel();
		lbl_thongBaoKq.setBounds(62, 88, 100, 23);
		lbl_thongBaoKq.setForeground(Color.red);
		pn_kqTimKiem.add(lbl_thongBaoKq);
		
		JLabel lblmaHD = new JLabel("Mã Nhà Cung Cấp:");
		lblmaHD.setHorizontalAlignment(SwingConstants.LEFT);
		lblmaHD.setFont(new Font("Arial", Font.PLAIN, 11));
		lblmaHD.setBounds(10, 20, 121, 20);
		pn_kqTimKiem.add(lblmaHD);

		JPanel pnl_nhapMa = new JPanel();
		pnl_nhapMa.setLayout(null);
		pnl_nhapMa.setBorder(new RoundedCornerBorder());
		pnl_nhapMa.setBackground(Color.WHITE);
		pnl_nhapMa.setBounds(141, 16, 100, 30);
		pn_kqTimKiem.add(pnl_nhapMa);
		txt_maNCC = new JTextField();
		txt_maNCC.setText("Nhập mã...");
		txt_maNCC.setForeground(Color.GRAY);
		txt_maNCC.setEditable(false);
		txt_maNCC.setColumns(10);
		txt_maNCC.setBorder(null);
		txt_maNCC.setBackground(Color.WHITE);
		txt_maNCC.setBounds(15, 5, 80, 20);
		txt_maNCC.addMouseListener(new MouseListener() {
			
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
				txt_maNCC.setText("");
				txt_maNCC.setForeground(Color.BLACK);
				txt_maNCC.setEditable(true);
			}
		});
		pnl_nhapMa.add(txt_maNCC);

		JLabel lblnhanVien = new JLabel("Tên Nhà Cung Cấp:");
		lblnhanVien.setHorizontalAlignment(SwingConstants.LEFT);
		lblnhanVien.setFont(new Font("Arial", Font.PLAIN, 11));
		lblnhanVien.setBounds(267, 20, 116, 20);
		pn_kqTimKiem.add(lblnhanVien);

		JLabel lblngayLap = new JLabel("Số Điện Thoại:");
		lblngayLap.setHorizontalAlignment(SwingConstants.LEFT);
		lblngayLap.setFont(new Font("Arial", Font.PLAIN, 11));
		lblngayLap.setBounds(577, 20, 100, 20);
		pn_kqTimKiem.add(lblngayLap);
		
		JLabel lbldiaChi = new JLabel("Địa chỉ:");
		lbldiaChi.setHorizontalAlignment(SwingConstants.LEFT);
		lbldiaChi.setFont(new Font("Arial", Font.PLAIN, 11));
		lbldiaChi.setBounds(849, 20, 67, 20);
		pn_kqTimKiem.add(lbldiaChi);	
		
		JPanel pnl_diaChi = new JPanel();
		pnl_diaChi.setLayout(null);
		pnl_diaChi.setBorder(new RoundedCornerBorder());
		pnl_diaChi.setBackground(Color.WHITE);
		pnl_diaChi.setBounds(916, 16, 160, 30);
		pn_kqTimKiem.add(pnl_diaChi);
		txt_diaChi = new JTextField();
		txt_diaChi.setText("Nhập địa chỉ..");
		txt_diaChi.setForeground(Color.GRAY);
		txt_diaChi.setEditable(false);
		txt_diaChi.setColumns(10);
		txt_diaChi.setBorder(null);
		txt_diaChi.setBackground(Color.WHITE);
		txt_diaChi.setBounds(15, 5, 140, 20);
		txt_diaChi.addMouseListener(new MouseListener() {
			
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
				txt_diaChi.setText("");
				txt_diaChi.setForeground(Color.BLACK);
				txt_diaChi.setEditable(true);
			}
		});
		pnl_diaChi.add(txt_diaChi);
		
		txt_SDT = new JTextField("Nhập số đt..");
		txt_SDT.setBackground(new Color(255, 250, 240));
		txt_SDT.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_SDT.setHorizontalAlignment(SwingConstants.LEFT);
		txt_SDT.setBounds(595, 16, 160, 20);
		JPanel pnl_sDT = new JPanel();
		pnl_sDT.setLayout(null);
		pnl_sDT.setBorder(new RoundedCornerBorder());
		pnl_sDT.setBackground(Color.WHITE);
		pnl_sDT.setBounds(679, 16, 160, 30);
		pn_kqTimKiem.add(pnl_sDT);
		txt_SDT = new JTextField();
		txt_SDT.setText("Nhập số ĐT..");
		txt_SDT.setForeground(Color.GRAY);
		txt_SDT.setEditable(false);
		txt_SDT.setColumns(10);
		txt_SDT.setBorder(null);
		txt_SDT.setBackground(Color.WHITE);
		txt_SDT.setBounds(15, 5, 140, 20);
		txt_SDT.addMouseListener(new MouseListener() {
			
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
				txt_SDT.setText("");
				txt_SDT.setForeground(Color.BLACK);
				txt_SDT.setEditable(true);
			}
		});
		pnl_sDT.add(txt_SDT);
		

		JPanel pnl_tenNCC = new JPanel();
		pnl_tenNCC.setLayout(null);
		pnl_tenNCC.setBorder(new RoundedCornerBorder());
		pnl_tenNCC.setBackground(Color.WHITE);
		pnl_tenNCC.setBounds(393, 16, 160, 30);
		pn_kqTimKiem.add(pnl_tenNCC);
		txt_tenNCC = new JTextField();
		txt_tenNCC.setText("Nhập tên NCC..");
		txt_tenNCC.setForeground(Color.GRAY);
		txt_tenNCC.setEditable(false);
		txt_tenNCC.setColumns(10);
		txt_tenNCC.setBorder(null);
		txt_tenNCC.setBackground(Color.WHITE);
		txt_tenNCC.setBounds(15, 5, 140, 20);
		txt_tenNCC.addMouseListener(new MouseListener() {
			
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
				txt_tenNCC.setText("");
				txt_tenNCC.setForeground(Color.BLACK);
				txt_tenNCC.setEditable(true);
			}
		});
		pnl_tenNCC.add(txt_tenNCC);
			
		JButton btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setFont(new Font("Arial", Font.BOLD, 14));
		btnXoaTrang.setBackground(new Color(255, 0, 0));
		btnXoaTrang.setBounds(1109, 81, 100, 30);
		pn_kqTimKiem.add(btnXoaTrang);
		

		JPanel pn_dsnv = new JPanel();
		pn_dsnv.setBackground(new Color(255, 255, 255));
		pn_dsnv.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Danh sách kết quả", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_dsnv.setBounds(10, 210, 1239, 644);
		add(pn_dsnv);
		pn_dsnv.setLayout(null);

		model_ds = new DefaultTableModel(new Object[][] {
				
		}, new String[] { "Mã Nhà Cung Cấp", "Tên Nhà Cung Cấp", "Số Điện Thoại", "Địa chỉ"}

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
		scr_Ds.setBounds(10, 20, 1219, 614);
		scr_Ds.getViewport().setBackground(Color.white);
		scr_Ds.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		pn_dsnv.add(scr_Ds);
		
		JLabel lblTenManHinh = new JLabel("Tìm Kiếm Nhà Cung Cấp");
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
		dsNCC = ncc_dao.getDsNCC();
		updateTable(dsNCC);

		tbl_Ds.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int selected = tbl_Ds.getSelectedRow();
				String maNCC = (String) model_ds.getValueAt(selected, 0);
				NhaCungCap ncc = ncc_dao.getNCC(maNCC);
				hienThiThongTinKetQua(ncc);
				
			}
		});

		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String ma = txt_maNCC.getText();
				ma = ma.equalsIgnoreCase("Nhập mã..")?"":ma;
				String ten = txt_tenNCC.getText();
				ten = ten.equalsIgnoreCase("Nhập tên..")?"":ten;
				String sdt = txt_SDT.getText();
				sdt = sdt.equalsIgnoreCase("Nhập số đt..")?"":sdt;
				String diaChi = txt_diaChi.getText();
				diaChi = diaChi.equalsIgnoreCase("Nhập địa chỉ..")?"":diaChi;
				dsNCC = ncc_dao.timKiem("%"+ma+"%", "%"+ten+"%","%"+ sdt+"%", "%"+diaChi+"%"); 
				if (dsNCC.size()==0) {
					JOptionPane.showMessageDialog(null, "Không tìm thấy!");
				}
				else {					
					updateTable(dsNCC);
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
				lblTenManHinh.setText("Tìm Kiếm Nhà Cung Cấp");
				((TitledBorder) pn_thaotac.getBorder()).setTitle("Tìm kiếm Nhà Cung Cấp");
				pn_thaotac.repaint();
				((TitledBorder) pn_dsnv.getBorder()).setTitle("Danh sách nhà cung cấp");
				pn_dsnv.repaint();
				lblKqTimKiem.setText("Kết quả:");
				
				lblmaHD.setText("Mã nhà cung cấp:");
				lblnhanVien.setText("Họ tên:");
				lblngayLap.setText("Số điện thoại:");
				lbldiaChi.setText("Địa chỉ:");
				btnSearch.setText("Tìm");
				btnXoaTrang.setText("Xoá trắng");
				String[] newColumns_ds = { "Mã nhà cung cấp", "Họ tên", "Số điện thoại", "Địa chỉ" };
				model_ds.setColumnIdentifiers(newColumns_ds);
				
//				txt_maNCC.setText("Nhập mã...");
//				txt_tenNCC.setText("Nhập tên NCC..");
//				txt_SDT.setText("Nhập số ĐT..");
//				txt_diaChi.setText("Nhập địa chỉ..");
			}
		});
		
		ManHinhChinh.btnEN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblTenManHinh.setText("Search Supplier");
				((TitledBorder) pn_thaotac.getBorder()).setTitle("Search Supplier");
				pn_thaotac.repaint();
				((TitledBorder) pn_dsnv.getBorder()).setTitle("Supplier List");
				pn_dsnv.repaint();
				lblKqTimKiem.setText("Result:");
				lblmaHD.setText("Vendor Id:");
				lblnhanVien.setText("Full name:");
				lblngayLap.setText("Phone number:");
				lbldiaChi.setText("Address:");
				btnSearch.setText("Search");
				btnXoaTrang.setText("Clear");
				String[] newColumns_ds = { "Supplier Id", "Full name", "Phone number", "Address" };
				model_ds.setColumnIdentifiers(newColumns_ds);
//				txt_maNCC.setText("Id...");
//				txt_tenNCC.setText("Supplier name..");
//				txt_SDT.setText("Phone number..");
//				txt_diaChi.setText("Address..");
			}
		});
	}
	
	private void xoaTrang() {
		txt_diaChi.setText("Nhập địa chỉ..");
		txt_maNCC.setText("Nhập mã..");
		txt_SDT.setText("Nhập số đt..");
		txt_tenNCC.setText("Nhập tên..");
		dsNCC = ncc_dao.getDsNCC();
		updateTable(dsNCC);
	}
	
	private void hienThiThongTinKetQua(NhaCungCap ncc) {
		txt_maNCC.setText(ncc.getMaNCC());
		txt_SDT.setText(ncc.getSdtNCC());
		txt_diaChi.setText(ncc.getDiaChiNCC());
		txt_tenNCC.setText(ncc.getTenNCC());
	}
	
	private void xoaTable() {
        DefaultTableModel dtm = (DefaultTableModel) tbl_Ds.getModel();
        dtm.getDataVector().removeAllElements();
    }
	private void updateTable(List<NhaCungCap> dsNCC) {
		xoaTable();
		for (NhaCungCap ncc : dsNCC) {
			Object data[] = {ncc.getMaNCC(),ncc.getTenNCC(),ncc.getSdtNCC(),ncc.getDiaChiNCC()};
			model_ds.addRow(data);	
		}
	}
}
//}
