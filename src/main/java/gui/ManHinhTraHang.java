package gui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import connectDB.ConnectDB;
import custom.CustomScrollBarUI;
import custom.RoundedCornerBorder;
import custom.cell.TableActionCellEditor;
import custom.cell.TableActionCellEditor1;
import custom.cell.TableActionCellRender;
import custom.cell.TableActionCellRender1;
import custom.cell.TableActionEvent;
import custom.cell.TableActionEvent1;
import dao.ChatLieu_DAO;
import dao.ChiTietDonDatHang_DAO;
import dao.ChiTietHoaDonTra_DAO;
import dao.ChiTietHoaDon_DAO;
import dao.DonDatHang_DAO;
import dao.HoaDonTra_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.KichCo_DAO;
import dao.LoaiSanPham_DAO;
import dao.MauSac_DAO;
import dao.SanPham_DAO;
import entities.ChiTietHoaDon;
import entities.ChiTietHoaDonTra;
import entities.HoaDon;
import entities.HoaDonTra;
import entities.KhachHang;
import entities.SanPham;
import report.Report;
import report.ReportB;

import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ManHinhTraHang extends JPanel {
	private JPanel pn_timKiem;
	private JPanel pnl_hd;
	private JButton btnSearch;
	private JButton btnXoaTrang;
	private JButton btnXacNhan;

	private int rowselect;

	private HoaDonTra hoaDonTra;
	private JTable tbl_cthd;
	private DefaultTableModel model_dscthd;
	private JTable tbl_dshd;
	private DefaultTableModel model_dshd;
	
	private ChiTietHoaDonTra_DAO chiTietHoaDonTra_DAO;
	private SanPham_DAO sanPham_dao;
	private SanPham sp;
	private KhachHang_DAO khachHang_dao;
	public static KhachHang khachHang;
	private List<ChiTietHoaDonTra> dsTra;
	private List<ChiTietHoaDon> dsThayDoi;
	private List<HoaDon> dshd;

	private LoaiSanPham_DAO loaiSanPham_DAO;
	private KichCo_DAO kichCo_DAO;
	private MauSac_DAO mauSac_DAO;
	private ChatLieu_DAO chatLieu_DAO;
	private ChiTietHoaDon_DAO chiTietHoaDon_DAO;
	private HoaDon_DAO hoaDon_DAO;
	private DonDatHang_DAO donDatHang_DAO;
	private ChiTietDonDatHang_DAO chiTietDonDatHang_DAO;
	private HoaDonTra_DAO hoaDonTra_DAO;
	
	private static DateTimeFormatter dtf;
	private DecimalFormat df;
	private JTextField txt_tienKhachTra;
	
	private JLabel lbl_kqTenKH;
	private JLabel lbl_kqTraTienKhach;
	private JLabel lbl_kqtienthua;
	private JLabel lbl_kqSDT;
	private JLabel lbl_maSP;
	private JLabel lbl_maHD;
	
	private JButton btn_hoanTat;
	private JButton btn_huyBo;
	
	private List<ChiTietHoaDon> ds_cthd;
	private JTextField txt_tkMaDon;
	private JTextField txt_tkSdt;
	private JTextField txt_tkTenNV;
	private JTextField txt_tkTenKH;
	private int demSoLuong;
	
	private double sum = 0;
	/**
	 * Create the panel.
	 */

	
	
	@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
	public ManHinhTraHang() {
		//int w = (int)(ManHinhChinh.w*0.82), h=ManHinhChinh.h;
		
		setBackground(new Color(255, 255, 255));
		setAutoscrolls(true);
		setSize((int) (1536*0.82), 864);
		setLayout(null);
		
		
	    try {
				ConnectDB.getInstance().connect();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		
	    dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
		df = new DecimalFormat("#,### VND");
		sanPham_dao = new SanPham_DAO();
        khachHang_dao = new KhachHang_DAO();
        chiTietHoaDon_DAO = new ChiTietHoaDon_DAO();
        hoaDon_DAO = new HoaDon_DAO();
        hoaDonTra_DAO = new HoaDonTra_DAO();
        chiTietHoaDonTra_DAO= new ChiTietHoaDonTra_DAO();
        
        dsTra = new ArrayList<ChiTietHoaDonTra>();
		dsThayDoi = new ArrayList<ChiTietHoaDon>();
        
        JLabel lblTitleHeader = new JLabel("TRẢ HÀNG");
		lblTitleHeader.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitleHeader.setForeground(new Color(65, 105, 225));
		lblTitleHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleHeader.setBounds(20, 0, (int) (1536*0.82)-40, 20);
		add(lblTitleHeader);
		
		JLabel btnExit = new JLabel("");
		btnExit.setIcon(new ImageIcon(ManHinhChinh.class.getResource("/images/close.png")));
		btnExit.setBounds((int) (1536*0.82)-20, 0, 20, 20);
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
        
		pn_timKiem = new JPanel();
		pn_timKiem.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "T\u00ECm ki\u1EBFm h\u00F3a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_timKiem.setBackground(new Color(255, 255, 255));
		pn_timKiem.setBounds(10, 20, 1239, 504);
		add(pn_timKiem);
		pn_timKiem.setLayout(null);
		
	
		btnXoaTrang = new JButton("RESET");
		btnXoaTrang.setBackground(new Color(255, 0, 0));
		btnXoaTrang.setBounds(598, 61, 119, 25);
		pn_timKiem.add(btnXoaTrang);
		btnXoaTrang.addMouseListener(new MouseListener() {
			
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
				sum = 0;
				demSoLuong = 0;
				txt_tkTenKH.setText("Nhập tên khách hàng...");
				txt_tkTenNV.setText("Nhập tên nhân viên...");
				txt_tkSdt.setText("Nhập sđt khách hàng...");
				txt_tkMaDon.setText("Nhập mã đơn...");
				lbl_kqSDT.setText("");
				lbl_kqTenKH.setText("Khách lẻ");
				lbl_kqTraTienKhach.setText("0 VND");	
				btn_hoanTat.setVisible(true);
				btn_huyBo.setVisible(false);
				btnXacNhan.setVisible(false);
				List<HoaDon> ds = hoaDon_DAO.getDSTraHang();
				dsTra = new ArrayList<ChiTietHoaDonTra>();
				dsThayDoi = new ArrayList<ChiTietHoaDon>();
				updateDataTableDsHD(ds);
				xoaTrangTable(tbl_cthd);
				
			}
		});
		
		
		btnSearch = new JButton("Tìm");
		btnSearch.setBounds(367, 61, 119, 25);
		pn_timKiem.add(btnSearch);
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
				String maHD = txt_tkMaDon.getText().trim();
				maHD = maHD.equalsIgnoreCase("Nhập mã đơn...")?"":maHD.trim();
				String sdtKH = txt_tkSdt.getText().trim();
				sdtKH = sdtKH.equalsIgnoreCase("Nhập sđt khách hàng...")?"":sdtKH.trim();
				String tenKH = txt_tkTenKH.getText().trim();
				tenKH = tenKH.equalsIgnoreCase("Nhập tên khách hàng...")?"":tenKH.trim();
				String tenNV = txt_tkTenNV.getText().trim();
				tenNV = tenNV.equalsIgnoreCase("Nhập tên nhân viên...")?"":tenNV.trim();
				List<HoaDon> ds = hoaDon_DAO.timKiemTraHang(maHD,tenNV, sdtKH, tenKH );
				System.out.println(maHD);
				System.out.println(sdtKH);
				System.out.println(tenKH);
				System.out.println(tenNV);
				if (ds.size()>0) {
					updateDataTableDsHD(ds);
				}
				else {
					JOptionPane.showMessageDialog(null, "Không tìm thấy!");
				}
				
			}
		});
		btnSearch.setBackground(new Color(65, 105, 225));
		btnSearch.setIcon(new ImageIcon(ManHinhLapHoaDon.class.getResource("/images/search.png")));
		
		
		JScrollPane scr_dssp = new JScrollPane();
		scr_dssp.setBounds(10, 100, 1219, 394);
		pn_timKiem.add(scr_dssp);
		model_dshd = new DefaultTableModel(
	            new Object [][] {
	    
	            },
	            new String [] {
	                "Mã Hóa Đơn","Tên Nhân Viên", "Tên Khách Hàng", "Ngày Lập", "Khuyến Mãi","Tổng Tiền Hóa Đơn","Tiền Khách Trả"
	            }
	        ) {
	            boolean[] canEdit = new boolean [] {
	                false, false, false, false, false, false, false
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	        };
		tbl_dshd = new JTable(model_dshd);
		tbl_dshd.setBackground(Color.white);
		
		tbl_dshd.getColumnModel().getColumn(0).setMinWidth(50);
		tbl_dshd.getColumnModel().getColumn(1).setMinWidth(100);
		tbl_dshd.getColumnModel().getColumn(2).setMinWidth(100);		
		
		tbl_dshd.setRowHeight(80);
		tbl_dshd.setSelectionBackground(new java.awt.Color(65, 105, 225));
		
		
		DefaultTableCellRenderer head_render = new DefaultTableCellRenderer(); 
	    head_render.setBackground(new Color(135, 205, 230));
	    tbl_dshd.getTableHeader().setDefaultRenderer(head_render);
		
	    tbl_dshd.addMouseListener(new MouseListener() {
			
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
				int selected = tbl_dshd.getSelectedRow();
				String maHD =(String) model_dshd.getValueAt(selected, 0);
				HoaDon hd = hoaDon_DAO.getHD(maHD);
				txt_tkMaDon.setText(hd.getMaHD());
				txt_tkSdt.setText(hd.getKhachHang().getSdtKH());
				txt_tkTenKH.setText(hd.getKhachHang().getTenKH());
				txt_tkTenNV.setText(hd.getNhanVien().getTenNV());
				
				lbl_maHD.setText(maHD);
				hoaDonTra = new HoaDonTra(hoaDonTra_DAO.taoMaHoaDon(maHD), LocalDateTime.now(),hd.getNhanVien() , hd.getKhachHang(), 0,hd);
				System.out.println(hoaDonTra.getMaDT());
				List<ChiTietHoaDon> cthd = chiTietHoaDon_DAO.getChiTietHD(maHD);
				updateDataTableDsCTHD(cthd);
				dsThayDoi = new ArrayList<ChiTietHoaDon>();
				dsTra = new ArrayList<ChiTietHoaDonTra>();
				KhachHang kh = khachHang_dao.getKHMaKH(hd.getKhachHang().getMaKH());
				capNhatThongTinKhachHang(kh);
				sum=0;
				lbl_kqTraTienKhach.setText(df.format(sum));
			}
		});
	    
		scr_dssp.setViewportView(tbl_dshd);
		
		
		JLabel lblMa = new JLabel("Mã đơn:");
		lblMa.setBounds(10, 20, 60, 30);
		pn_timKiem.add(lblMa);
		
		JPanel pnl_tkMa = new JPanel();
		pnl_tkMa.setLayout(null);
		pnl_tkMa.setBorder(new RoundedCornerBorder());
		pnl_tkMa.setBackground(Color.WHITE);
		pnl_tkMa.setBounds(68, 20, 171, 30);
		pn_timKiem.add(pnl_tkMa);
		
		txt_tkMaDon = new JTextField();
		txt_tkMaDon.setText("Nhập mã đơn...");
		txt_tkMaDon.setForeground(Color.GRAY);
		txt_tkMaDon.setEditable(false);
		txt_tkMaDon.setColumns(10);
		txt_tkMaDon.setBorder(null);
		txt_tkMaDon.setBackground(Color.WHITE);
		txt_tkMaDon.setBounds(10, 3, 151, 24);
		pnl_tkMa.add(txt_tkMaDon);
		txt_tkMaDon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txt_tkMaDon.setText("");
				txt_tkMaDon.setForeground(Color.BLACK);
				txt_tkMaDon.setEditable(true);
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				txt_tkMaDon.setText("Nhập mã đơn...");
				txt_tkMaDon.setForeground(Color.GRAY);
			}
		});
		
		JLabel lblsoDienThoaiKH = new JLabel("Sđt khách hàng:");
		lblsoDienThoaiKH.setBounds(249, 20, 131, 30);
		pn_timKiem.add(lblsoDienThoaiKH);
		
		JPanel pnl_tkSdt = new JPanel();
		pnl_tkSdt.setLayout(null);
		pnl_tkSdt.setBorder(new RoundedCornerBorder());
		pnl_tkSdt.setBackground(Color.WHITE);
		pnl_tkSdt.setBounds(375, 20, 160, 30);
		pn_timKiem.add(pnl_tkSdt);
		
		txt_tkSdt = new JTextField();
		txt_tkSdt.setText("Nhập sđt khách hàng...");
		txt_tkSdt.setForeground(Color.GRAY);
		txt_tkSdt.setEditable(false);
		txt_tkSdt.setColumns(10);
		txt_tkSdt.setBorder(null);
		txt_tkSdt.setBackground(Color.WHITE);
		txt_tkSdt.setBounds(10, 3, 140, 24);
		pnl_tkSdt.add(txt_tkSdt);
		txt_tkSdt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txt_tkSdt.setText("");
				txt_tkSdt.setForeground(Color.BLACK);
				txt_tkSdt.setEditable(true);
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				txt_tkSdt.setText("Nhập sđt khách hàng...");
				txt_tkSdt.setForeground(Color.GRAY);
			}
		});
		
		JLabel lblTenNV = new JLabel("Tên nhân viên:");
		lblTenNV.setBounds(570, 21, 119, 30);
		pn_timKiem.add(lblTenNV);
		
		JPanel pnl_tkTenNV = new JPanel();
		pnl_tkTenNV.setLayout(null);
		pnl_tkTenNV.setBorder(new RoundedCornerBorder());
		pnl_tkTenNV.setBackground(Color.WHITE);
		pnl_tkTenNV.setBounds(699, 21, 190, 30);
		pn_timKiem.add(pnl_tkTenNV);
		
		txt_tkTenNV = new JTextField();
		txt_tkTenNV.setText("Nhập tên nhân viên...");
		txt_tkTenNV.setForeground(Color.GRAY);
		txt_tkTenNV.setEditable(false);
		txt_tkTenNV.setColumns(10);
		txt_tkTenNV.setBorder(null);
		txt_tkTenNV.setBackground(Color.WHITE);
		txt_tkTenNV.setBounds(10, 3, 170, 24);
		pnl_tkTenNV.add(txt_tkTenNV);
		txt_tkTenNV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txt_tkTenNV.setText("");
				txt_tkTenNV.setForeground(Color.BLACK);
				txt_tkTenNV.setEditable(true);
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				txt_tkTenNV.setText("Nhập tên nhân viên...");
				txt_tkTenNV.setForeground(Color.GRAY);
			}
		});
		
		JLabel lblTenKH = new JLabel("Tên khách hàng:");
		lblTenKH.setBounds(899, 20, 119, 30);
		pn_timKiem.add(lblTenKH);
		
		JPanel pnl_tkTenKH = new JPanel();
		pnl_tkTenKH.setLayout(null);
		pnl_tkTenKH.setBorder(new RoundedCornerBorder());
		pnl_tkTenKH.setBackground(Color.WHITE);
		pnl_tkTenKH.setBounds(1018, 20, 190, 30);
		pn_timKiem.add(pnl_tkTenKH);
		
		txt_tkTenKH = new JTextField();
		txt_tkTenKH.setText("Nhập tên khách hàng...");
		txt_tkTenKH.setForeground(Color.GRAY);
		txt_tkTenKH.setEditable(false);
		txt_tkTenKH.setColumns(10);
		txt_tkTenKH.setBorder(null);
		txt_tkTenKH.setBackground(Color.WHITE);
		txt_tkTenKH.setBounds(10, 3, 170, 24);
		pnl_tkTenKH.add(txt_tkTenKH);
		txt_tkTenKH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txt_tkTenKH.setText("");
				txt_tkTenKH.setForeground(Color.BLACK);
				txt_tkTenKH.setEditable(true);
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				txt_tkTenKH.setText("Nhập tên khách hàng...");
				txt_tkTenKH.setForeground(Color.GRAY);
			}
		});
		
		scr_dssp.getViewport().setBackground(new Color(255,255,255));
		scr_dssp.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		scr_dssp.getVerticalScrollBar().setPreferredSize(new Dimension(8, 300));
		
		dshd = hoaDon_DAO.getDSTraHang();
		updateDataTableDsHD(dshd);
		
		JPanel pn_thongTinHD = new JPanel();
		pn_thongTinHD.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Th\u00F4ng tin h\u00F3a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_thongTinHD.setBackground(new Color(255, 255, 255));
		pn_thongTinHD.setBounds(919, 529, 330, 325);
		add(pn_thongTinHD);
		pn_thongTinHD.setLayout(null);
		
		
		
		pnl_hd = new JPanel();
		pnl_hd.setBackground(new Color(255, 255, 255));
		pnl_hd.setBounds(10, 23, 310, 291);
		pn_thongTinHD.add(pnl_hd);
		pnl_hd.setLayout(null);
		
		JLabel lbl_tenKH = new JLabel("Khách hàng:");
		lbl_tenKH.setBounds(0, 0, 90, 20);
		pnl_hd.add(lbl_tenKH);
		
		lbl_kqTenKH = new JLabel("Khách lẻ");
		lbl_kqTenKH.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_kqTenKH.setBounds(110, 0, 190, 20);
		pnl_hd.add(lbl_kqTenKH);
		
		JLabel lbl_Tongtien = new JLabel("Tổng tiền trả khách:");
		lbl_Tongtien.setBounds(0, 100, 100, 20);
		pnl_hd.add(lbl_Tongtien);
		
		lbl_kqTraTienKhach = 	new JLabel("0 VND");
		lbl_kqTraTienKhach.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_kqTraTienKhach.setBounds(110, 100, 190, 20);
		pnl_hd.add(lbl_kqTraTienKhach);
		
		btn_hoanTat = new JButton("Trả Hàng");
		btn_hoanTat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hoaDonTra.setTongTienTra(sum);
				if(hoaDonTra_DAO.themHDT(hoaDonTra)) {
					for (ChiTietHoaDonTra chiTietHoaDonTra : dsTra) {
						chiTietHoaDonTra_DAO.themChiTietHDT(chiTietHoaDonTra);
						sanPham_dao.capNhatSoLuongTra(chiTietHoaDonTra.getSanPham().getMaSP(), chiTietHoaDonTra.getSoLuong());
					}
					for (ChiTietHoaDon chiTietHoaDon : dsThayDoi) {
						chiTietHoaDon_DAO.capNhatCTHD(chiTietHoaDon);
					}
					hoaDon_DAO.capNhatHD(lbl_maHD.getText(), sum);
					dshd = hoaDon_DAO.getDSTraHang();
					updateDataTableDsHD(dshd);
					ReportB.xuatHoaDonPDF("sMaHD",hoaDonTra.getMaDT(), "C:\\hoadontra.pdf", false);
					JOptionPane.showMessageDialog(null, "Thành Công!");
					
				}else {
					JOptionPane.showMessageDialog(null, "Thất Bại!");
				}
				
			}
		});
		btn_hoanTat.setBackground(new Color(255, 127, 80));
		btn_hoanTat.setBounds(98, 241, 130, 30);
		pnl_hd.add(btn_hoanTat);
		
		btnXacNhan = new JButton("Xác Nhận");
		btnXacNhan.setBounds(98,241, 130, 30);
		btnXacNhan.setVisible(false);
		pnl_hd.add(btnXacNhan);
		btnXacNhan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btn_hoanTat.setVisible(true);
				btn_huyBo.setVisible(false);
				btnXacNhan.setVisible(false);
				if((int)model_dscthd.getValueAt(rowselect, 6) == 0)
					return;
				
				ChiTietHoaDon cthd_old = chiTietHoaDon_DAO.get1ChiTietHD(lbl_maHD.getText(), lbl_maSP.getText());
				int soLuong =(int) model_dscthd.getValueAt(rowselect, 6);
				cthd_old.setSoLuong(cthd_old.getSoLuong()-soLuong);
				ChiTietHoaDonTra cthd_new =  new ChiTietHoaDonTra(hoaDonTra.getMaDT(),cthd_old.getSanPham(),soLuong,cthd_old.getGiaBan());
				int i = 0,flag = 0;
				if(dsThayDoi != null) {
					for (ChiTietHoaDon chiTietHoaDon : dsThayDoi) {
						if(chiTietHoaDon.getSanPham().getMaSP().equalsIgnoreCase(cthd_old.getSanPham().getMaSP())) {
							System.out.println("a");
							dsThayDoi.remove(i);
							dsTra.remove(i);
							dsThayDoi.add(cthd_old);
							dsTra.add(cthd_new);
							flag = 1;
							break;
						}
						i++;
					}
					
				}
				if(flag != 1) {
					dsThayDoi.add(cthd_old);
					dsTra.add(cthd_new);
				}
				
				for (ChiTietHoaDon cthd : dsThayDoi) {
					System.out.println(cthd.getMaHD());
					System.out.println(cthd.getSoLuong());
				}
				
				for (ChiTietHoaDonTra chiTietHoaDon : dsTra) {
					System.out.println(chiTietHoaDon.getMaDT());
					System.out.println(chiTietHoaDon.getSoLuong());
				}
			}
		});
		
		
		btn_huyBo = new JButton("Hủy bỏ");
		btn_huyBo.setBackground(new Color(255, 0, 0));
		btn_huyBo.setBounds(98,180, 130, 30);
		btn_huyBo.setVisible(false);
		pnl_hd.add(btn_huyBo);
		btn_huyBo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btn_hoanTat.setVisible(true);
				btn_huyBo.setVisible(false);
				btnXacNhan.setVisible(false);
				ChiTietHoaDon cthd = chiTietHoaDon_DAO.get1ChiTietHD(lbl_maHD.getText(), lbl_maSP.getText());
				int i = 0,flag = 0;
				if(dsThayDoi!=null){
					for (ChiTietHoaDon chiTietHoaDon : dsThayDoi) {
						if(chiTietHoaDon.getSanPham().getMaSP().equalsIgnoreCase(lbl_maSP.getText())){
							flag = 1;
							model_dscthd.setValueAt((int)model_dscthd.getValueAt(rowselect, 3) -chiTietHoaDon.getSoLuong(), rowselect, 6);
							for (ChiTietHoaDonTra chiTietHoaDonTra : dsTra) {
								if(chiTietHoaDon.getSanPham().getMaSP().equalsIgnoreCase(lbl_maSP.getText())) {
									sum = sum - (demSoLuong)*chiTietHoaDonTra.getGiaBan();
									break;
								}
							}
							break;
						}
						i++;	
					}
				}
				if(flag == 0) {
					sum = sum - cthd.getGiaBan()*((int)model_dscthd.getValueAt(rowselect, 6));
					model_dscthd.setValueAt(0, rowselect, 6);
				}
				lbl_kqTraTienKhach.setText(df.format(sum));
				demSoLuong = 0;
			}
		});
		
		JLabel lbl_sdtKH = new JLabel("Số điện thoại:");
		lbl_sdtKH.setBounds(0, 50, 80, 14);
		pnl_hd.add(lbl_sdtKH);
		
		lbl_kqSDT = new JLabel();
		lbl_kqSDT.setBounds(227, 50, 74, 14);
		pnl_hd.add(lbl_kqSDT);
		
		lbl_maSP = new JLabel("");
		lbl_maSP.setBounds(0, 167, 46, 14);
		lbl_maSP.setVisible(false);
		pnl_hd.add(lbl_maSP);
		
		lbl_maHD = new JLabel("");
		lbl_maHD.setBounds(0, 131, 74, 14);
		lbl_maHD.setVisible(false);
		pnl_hd.add(lbl_maHD);
		
		
		JPanel pn_DSGioHang = new JPanel();
		pn_DSGioHang.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Danh s\u00E1ch chi ti\u1EBFt h\u00F3a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_DSGioHang.setBackground(new Color(255, 255, 255));
		pn_DSGioHang.setBounds(10, 529, 899, 325);
		add(pn_DSGioHang);
		
		JScrollPane scr_giohang = new JScrollPane();
		scr_giohang.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		scr_giohang.getVerticalScrollBar().setPreferredSize(new Dimension(8,300));
		model_dscthd = new DefaultTableModel(
	            new Object [][] {
	    
	            },
	            new String [] {
	                "Mã Hóa Đơn","Mã Sản Phẩm","Tên Sản Phẩm", "Số Lượng","Giá Bán","Thành tiền","Số Lượng Tồn",""
	            }
	        ) {
	            boolean[] canEdit = new boolean [] {
	                false, false, false,false,false, false, false, true
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	        };
	    tbl_cthd = new JTable(model_dscthd);
	    tbl_cthd.setBackground(Color.white);
		
		
		
	    tbl_cthd.setRowHeight(80);
	    tbl_cthd.setSelectionBackground(new java.awt.Color(65, 105, 225));
		
	    tbl_cthd.getColumnModel().getColumn(0).setMinWidth(25);
	    tbl_cthd.getColumnModel().getColumn(1).setMinWidth(30);
	    tbl_cthd.getColumnModel().getColumn(2).setMinWidth(150);
	    tbl_cthd.getColumnModel().getColumn(3).setMinWidth(40);
	    tbl_cthd.getColumnModel().getColumn(4).setMinWidth(50);
		tbl_cthd.getColumnModel().getColumn(5).setMinWidth(60);
		tbl_cthd.getColumnModel().getColumn(6).setMinWidth(70);
		tbl_cthd.getColumnModel().getColumn(7).setMinWidth(90);
		
		
	    head_render.setBackground(new Color(135, 205, 230));
	    tbl_cthd.getTableHeader().setDefaultRenderer(head_render);
		tbl_cthd.addMouseListener(new MouseListener() {
			
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
				demSoLuong = 0;
				btn_hoanTat.setVisible(false);
				btnXacNhan.setVisible(true);
				btn_huyBo.setVisible(true);
				lbl_maHD.setText((String)model_dscthd.getValueAt(tbl_cthd.getSelectedRow(), 0));
				lbl_maSP.setText((String)model_dscthd.getValueAt(tbl_cthd.getSelectedRow(), 1));
//				lbl_maHD.setVisible(true);
//				lbl_maSP.setVisible(true);
			}
		});
	    
	    
		scr_giohang.setViewportView(tbl_cthd);
		scr_giohang.getViewport().setBackground(new Color(255,255,255));
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pn_DSGioHang);
        pn_DSGioHang.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scr_giohang, javax.swing.GroupLayout.DEFAULT_SIZE, 818, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scr_giohang, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                .addContainerGap())
        );
        
        
        TableActionEvent1 event = new TableActionEvent1() {
        	/**
        	 * khi click vào dấu cộng thì tăng số lượng sản phẩm tại row được chọn
        	 * cập nhật lại các kết quả tính toán tiền
        	 * @param row
        	 */
            @Override
            public void onAdd(int row) {
            	 System.out.println("Edit row : " + row);
            	 if((int)model_dscthd.getValueAt(rowselect, 3) > demSoLuong) demSoLuong++;
            	 int soluong = (int)model_dscthd.getValueAt(row, 6);
            	 double tam = 1;
            	 ChiTietHoaDon cthd = chiTietHoaDon_DAO.get1ChiTietHD((String)model_dscthd.getValueAt(row, 0),(String) model_dscthd.getValueAt(row, 1));
            	  if (soluong < cthd.getSoLuong()) {
            		  soluong+=1;
            		  //model_dscthd.setValueAt(soluong, row, 3);
            		  //model_dscthd.setValueAt(df.format(soluong*cthd.getGiaBan()), row, 5);
            		 // tam = cthd.getGiaBan();
            		  sum += cthd.getGiaBan();;
            		  lbl_kqTraTienKhach.setText(df.format(sum));
            		  model_dscthd.setValueAt(soluong, row, 6);
            		  rowselect=row;
                 }
            }   
            /**
        	 * khi click vào dấu trừ thì giảm số lượng sản phẩm tại row được chọn
        	 * cập nhật lại các kết quả tính toán tiền
        	 * @param row
        	 */
            @Override
            public void onMinus(int row) {
            	 System.out.println("Edit row : " + row);
            	 if(demSoLuong!= 0) demSoLuong--;
            	 
            	 int soluong = (int)model_dscthd.getValueAt(row,6);
                 ChiTietHoaDon cthd = chiTietHoaDon_DAO.get1ChiTietHD((String)model_dscthd.getValueAt(row, 0),(String) model_dscthd.getValueAt(row, 1));
            	 if (soluong>= 1) {
            		 soluong-=1;
            		 //model_dscthd.setValueAt(soluong, row, 3);
           		  	 //model_dscthd.setValueAt(df.format(soluong*cthd.getGiaBan()), row, 5);
           		  	 sum -= cthd.getGiaBan();;
           		  	 lbl_kqTraTienKhach.setText(df.format(sum));
           		  	 model_dscthd.setValueAt(soluong, row, 6);
                     rowselect=row;
                 }
            }
        };
		tbl_cthd.getColumnModel().getColumn(7).setCellRenderer(new TableActionCellRender1());
		tbl_cthd.getColumnModel().getColumn(7).setCellEditor(new TableActionCellEditor1(event));
		
		ManHinhChinh.btnVN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblTitleHeader.setText("TRẢ HÀNG");
				((TitledBorder) pn_timKiem.getBorder()).setTitle("Tìm kiếm hoá đơn");
				pn_timKiem.repaint();
				((TitledBorder) pn_DSGioHang.getBorder()).setTitle("Danh sách chi tiết hoá đơn");
				pn_DSGioHang.repaint();
				((TitledBorder) pn_thongTinHD.getBorder()).setTitle("Thông tin hoá đơn");
				pn_thongTinHD.repaint();
				lblTenKH.setText("Khách hàng:");
				lbl_tenKH.setText("Khách hàng:");
				lbl_sdtKH.setText("Số điện thoại:");
				lbl_Tongtien.setText("Tổng tiền trả khách:");
				lblMa.setText("Mã đơn:");
				lblsoDienThoaiKH.setText("Sđt khách hàng:");
				lblTenNV.setText("Tên nhân viên:");
				btnSearch.setText("Tìm");
				btnXoaTrang.setText("RESET");
				btn_huyBo.setText("Hủy bỏ");
				btn_hoanTat.setText("Trả Hàng");
				String[] newColumns_ds = { "Mã Hóa Đơn","Tên Nhân Viên", "Tên Khách Hàng", "Ngày Lập", "Khuyến Mãi","Tổng Tiền Hóa Đơn","Tiền Khách Trả"};
				model_dshd.setColumnIdentifiers(newColumns_ds);
				String[] newColumns_dscthd = { "Mã Hóa Đơn","Mã Sản Phẩm","Tên Sản Phẩm", "Số Lượng","Giá Bán","Thành tiền","Số Lượng Tồn",""};
				model_dscthd.setColumnIdentifiers(newColumns_dscthd);
			}
		});

		ManHinhChinh.btnEN.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblTitleHeader.setText("RETURNS");
				((TitledBorder) pn_timKiem.getBorder()).setTitle("Search for invoices");
				pn_timKiem.repaint();
				((TitledBorder) pn_DSGioHang.getBorder()).setTitle("List of invoice details");
				pn_DSGioHang.repaint();
				((TitledBorder) pn_thongTinHD.getBorder()).setTitle("Invoice information");
				pn_thongTinHD.repaint();
				lblTenKH.setText("Customer:");
				lbl_tenKH.setText("Customer:");
				lbl_sdtKH.setText("Phone number:");
				lbl_Tongtien.setText("Total payment:");
				lblMa.setText("Invoice ID:");
				lblsoDienThoaiKH.setText("Customer phone:");
				lblTenNV.setText("Employee name:");
				btnSearch.setText("Search");
				btnXoaTrang.setText("RESET");
				btn_huyBo.setText("Cancel");
				btn_hoanTat.setText("Return Goods");
				String[] newColumns_ds = { "Invoice ID","Employee Name", "Customer Name", "Date", "Promotion","Total Bill Amount","Amount Paid by Customer"};
				model_dshd.setColumnIdentifiers(newColumns_ds);
				String[] newColumns_dscthd = { "Invoice ID","Product Code","Product Name", "Quantity","Sales Price","Amount","Amount in Stock",""};
				model_dscthd.setColumnIdentifiers(newColumns_dscthd);
			}
		});
	}
	

	public void capNhatThongTinKhachHang(KhachHang kh) {
		lbl_kqTenKH.setText(kh.getTenKH());
		lbl_kqSDT.setText(kh.getSdtKH());		
	}

	/**
	 * xóa hết dữ liệu trên bảng
	 * @param t
	 */
	private void xoaTrangTable(JTable t) {
		DefaultTableModel dm = (DefaultTableModel) t.getModel();
		dm.getDataVector().removeAllElements();
		t.revalidate();
	}
	
	/**
	 * cập nhật dữ liệu cho bảng danh sách hóa đơn
	 * @param ds
	 */
	private void updateDataTableDsHD(List<HoaDon> ds) {
		xoaTrangTable(tbl_dshd);
		for (HoaDon hd : ds) {
			Object data[] = {hd.getMaHD(), hd.getNhanVien().getTenNV(), hd.getKhachHang()==null?"Khách lẻ":hd.getKhachHang().getTenKH() ,dtf.format(hd.getNgayLap()),hd.getCtKhuyenMai()==null?0:hd.getCtKhuyenMai().getPhanTramKhuyenMai(),df.format(hd.getTongTienHD()), df.format(hd.getTienKhachTra())};
			model_dshd.addRow(data);
		}
	}
	
	/**
	 * cập nhật dữ liệu cho bảng danh sách chi tiết hóa đơn
	 * @param ds
	 */
	private void updateDataTableDsCTHD(List<ChiTietHoaDon> ds) {
		xoaTrangTable(tbl_cthd);
		for (ChiTietHoaDon cthd : ds) {
			Object data[] = {cthd.getMaHD(), cthd.getSanPham().getMaSP(),cthd.getSanPham().getTenSP(), cthd.getSoLuong(),df.format(cthd.getGiaBan()),df.format(cthd.getThanhTien()),0,false};
			model_dscthd.addRow(data);
		}
	}
}
