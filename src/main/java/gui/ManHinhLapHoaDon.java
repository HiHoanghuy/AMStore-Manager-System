package gui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import connectDB.ConnectDB;
import custom.CustomScrollBarUI;
import custom.GeneratorID;
import custom.RoundedCornerBorder;
import custom.cell.TableActionCellEditor;
import custom.cell.TableActionCellRender;
import custom.cell.TableActionEvent;
import dao.ChatLieu_DAO;
import dao.ChiTietDonDatHang_DAO;
import dao.ChiTietHoaDon_DAO;
import dao.ChuongTrinhKhuyenMai_DAO;
import dao.DonDatHang_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.KichCo_DAO;
import dao.LoaiSanPham_DAO;
import dao.MauSac_DAO;
import dao.NhaCungCap_DAO;
import dao.NhanVien_DAO;
import dao.SanPham_DAO;
import entities.ChatLieu;
import entities.ChiTietDonDatHang;
import entities.ChiTietHoaDon;
import entities.ChuongTrinhKhuyenMai;
import entities.DonDatHang;
import entities.HoaDon;
import entities.KhachHang;
import entities.KichCo;
import entities.LoaiSanPham;
import entities.MauSac;
import entities.NhaCungCap;
import entities.NhanVien;
import entities.SanPham;
import qrCode.QRCodeGenerator;
import qrCode.QRCodeScanner;
import report.Report;
import sendEmail.SendEmailWithThread;
import sendSMS.SendSMS;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.border.MatteBorder;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.table.TableModel;

import com.github.sarxos.webcam.WebcamPanel;


import com.github.sarxos.webcam.WebcamPanel;

import javax.swing.JTextArea;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class ManHinhLapHoaDon extends JPanel {
	private JPanel pn_timKiem;
	private JPanel timKiem;
	private JPanel pnl_hd;
	private JTextField txt_tkTenSp;
	private JTextField txt_timkiemKH;
	private JButton btnSearch;
	private JSpinner spn_soluong;
	
	private JButton btnThem;
	private JButton btn_timKH;
	private JButton btn_themKH;
	
	private JComboBox cmbLoai;
	private JComboBox cmbMauSac;
	private JComboBox cmbKichCo;
	private JComboBox cmbChatLieu;

	private JTable tbl_gioHang;
	private DefaultTableModel model_giohang;
	private JTable tbl_dssp;
	private DefaultTableModel model_dssp;
	
	private SanPham_DAO sanPham_dao;
	private SanPham sp;
	private KhachHang_DAO khachHang_dao;
	public static KhachHang khachHang;
	private NhanVien nhanVien;
	private NhanVien_DAO nhanVien_DAO;
	private static ChuongTrinhKhuyenMai_DAO ctkm_dao;
	private ChuongTrinhKhuyenMai ctkm;
	private LoaiSanPham_DAO loaiSanPham_DAO;
	private KichCo_DAO kichCo_DAO;
	private MauSac_DAO mauSac_DAO;
	private NhaCungCap_DAO nhaCungCap_DAO;
	private ChatLieu_DAO chatLieu_DAO;
	private ChiTietHoaDon_DAO chiTietHoaDon_DAO;
	private HoaDon_DAO hoaDon_DAO;
	private DonDatHang_DAO donDatHang_DAO;
	private ChiTietDonDatHang_DAO chiTietDonDatHang_DAO;
	
	private int rowselect;
	
	private DecimalFormat df;
	private JTextField txt_tienKhachTra;
	
	public JLabel lbl_kqTenKH;
	private JLabel lbl_kqTongtien;
	public static JComboBox cmb_khuyenmai;
	private JLabel lbl_ptkm;
	private JLabel lbl_kqtongthanhtoan;
	private JLabel lbl_kqtienthua;
	
	private JButton btn_dathang;
	private JButton btn_thanhtoan;
	
	
	private JButton startButton;
    private JLabel resultLabel;
    private WebcamPanel webcamPanel;
    private QRCodeScanner qrCodeScanner;
    private JButton btnQR;
	
	private List<ChiTietHoaDon> ds_cthd;
	private JLabel lblTen;
	private JTextField txt_tkMaSp;
	
	private List<ChiTietDonDatHang> ds_ctddh;
	private JButton btn_troLai;
	
	private TableActionEvent event;
	
	/**
	 * Create the panel.
	 */

	
	
	@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
	public ManHinhLapHoaDon(boolean isDH, String maDDH) {
		setBackground(new Color(255, 255, 255));
		setAutoscrolls(true);
		int w= (int)(ManHinhChinh.w*0.82);
		int h = ManHinhChinh.h;
		setSize(w, h);
		setLayout(null);
		
		
	    try {
				ConnectDB.getInstance().connect();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		
		df = new DecimalFormat("#,### VND");
		sanPham_dao = new SanPham_DAO();
        khachHang_dao = new KhachHang_DAO();
        ctkm_dao = new ChuongTrinhKhuyenMai_DAO();
        loaiSanPham_DAO = new LoaiSanPham_DAO();
        mauSac_DAO = new MauSac_DAO();
        kichCo_DAO = new KichCo_DAO();
        nhaCungCap_DAO = new NhaCungCap_DAO();
        chatLieu_DAO = new ChatLieu_DAO();
        chiTietHoaDon_DAO = new ChiTietHoaDon_DAO();
        hoaDon_DAO = new HoaDon_DAO();
        nhanVien_DAO = new NhanVien_DAO();
        chiTietDonDatHang_DAO = new ChiTietDonDatHang_DAO();
        donDatHang_DAO = new DonDatHang_DAO();
        
        nhanVien = ManHinhChinh.nvAct;
		khachHang = khachHang_dao.getKHMaKH("KHACHLE");
        
        JLabel lblTitleHeader = new JLabel("LẬP HÓA ĐƠN");
		lblTitleHeader.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitleHeader.setForeground(new Color(65, 105, 225));
		lblTitleHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleHeader.setBounds(20, 0, w, 20);
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
        
		pn_timKiem = new JPanel();
		pn_timKiem.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "T\u00ECm ki\u1EBFm s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_timKiem.setBackground(new Color(255, 255, 255));
		pn_timKiem.setBounds(10, 20, w-20, (int)(h*0.6)-20);
		add(pn_timKiem);
		pn_timKiem.setLayout(null);
		
		timKiem = new JPanel();
		timKiem.setBackground(new Color(255, 255, 255));
		timKiem.setBounds(260, 20, 200, 30);
		timKiem.setBorder(new RoundedCornerBorder());
		pn_timKiem.add(timKiem);
		timKiem.setLayout(null);
		
		txt_tkTenSp = new JTextField();
		txt_tkTenSp.setText("Nhập tên sản phẩm ...");
		txt_tkTenSp.setForeground(Color.GRAY);
		txt_tkTenSp.setEditable(false);
		txt_tkTenSp.setBackground(new Color(255, 255, 255));
		txt_tkTenSp.setBounds(10, 3, 180, 24);
		txt_tkTenSp.setBorder(null);
		txt_tkTenSp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txt_tkTenSp.setText("");
				txt_tkTenSp.setForeground(Color.BLACK);
				txt_tkTenSp.setEditable(true);
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				txt_tkTenSp.setText("Nhập mã sản phẩm ...");
				txt_tkTenSp.setForeground(Color.GRAY);
			}
		});
		
		FormNhapThongTinKhachHangMoi.btnThemKHMoi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (FormNhapThongTinKhachHangMoi.validDataKHNew()) {
					String tenKH = FormNhapThongTinKhachHangMoi.txt_kqHotenKHMoi.getText();
					String sdt = FormNhapThongTinKhachHangMoi.txt_kqSdtKHMoi.getText();
					String diaChi = FormNhapThongTinKhachHangMoi.txt_kqDiaChiKHMoi.getText();
					boolean gt = (String)FormNhapThongTinKhachHangMoi.cmb_gioiTinhKHMoi.getSelectedItem()=="Nam"?true:false;
					String maKH = KhachHang_DAO.taoMaKhachHang();
					String email = FormNhapThongTinKhachHangMoi.txt_kqEmailKHMoi.getText();
					khachHang = new KhachHang(maKH, tenKH, sdt, gt, diaChi,email);
					if (khachHang_dao.themKH(ManHinhLapHoaDon.khachHang)) {
						lbl_kqTenKH.setText(khachHang.getTenKH());
						FormNhapThongTinKhachHangMoi.txt_kqHotenKHMoi.setText("");
						FormNhapThongTinKhachHangMoi.txt_kqSdtKHMoi.setText("");
						FormNhapThongTinKhachHangMoi.txt_kqDiaChiKHMoi.setText("");
						ManHinhChinh.form.setVisible(false);
						ManHinhTimKiemKhachHang.resetData();
						ManHinhCapNhatKhachHang.resetData();
						QRCodeGenerator.generateQRCodeImage(maKH, "C://MaQRKhachHang/", maKH+"_"+tenKH+"_"+sdt);
						JOptionPane.showMessageDialog(null, "Thêm khách hàng mới thành công!");
					}
				}	
			}
		});
		
		timKiem.add(txt_tkTenSp);
		txt_tkTenSp.setColumns(10);
		
		btnSearch = new JButton("Tìm");
		btnSearch.setBounds(1020, 20, 70, 30);
		pn_timKiem.add(btnSearch);
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String maSP = txt_tkMaSp.getText();
				maSP = maSP.equals("Nhập mã sản phẩm ...")?"":maSP;
				String tenSP = txt_tkTenSp.getText();
				tenSP = tenSP.equalsIgnoreCase("Nhập tên sản phẩm ...")?"":tenSP;
				String tenLoai = (String) cmbLoai.getSelectedItem();
				tenLoai = tenLoai.equalsIgnoreCase("Tất cả")?"%":tenLoai;
				String tenMauSac = (String) cmbMauSac.getSelectedItem();
				tenMauSac = tenMauSac.equalsIgnoreCase("Tất cả")?"%":tenMauSac;
				String tenKichCo = (String) cmbKichCo.getSelectedItem();
				tenKichCo = tenKichCo.equalsIgnoreCase("Tất cả")?"%":tenKichCo;
				String tenChatLieu = (String) cmbChatLieu.getSelectedItem();
				tenChatLieu = tenChatLieu.equalsIgnoreCase("Tất cả")?"%":tenChatLieu;
				List<SanPham> dskq = sanPham_dao.timKiemSP("%"+maSP+"%","%"+tenSP+"%", tenLoai, tenMauSac, tenKichCo, tenChatLieu);
				if (dskq.size()==0) {
					JOptionPane.showMessageDialog(null, "Không tìm thấy!");
				}
				else {					
					capNhatDuLieuCuaBang(dskq);
				}
				txt_tkMaSp.setText("Nhập mã sản phẩm ...");
				txt_tkMaSp.setForeground(Color.gray);
				txt_tkTenSp.setText("Nhập tên sản phẩm ...");
				txt_tkTenSp.setForeground(Color.GRAY);
				cmbLoai.setSelectedIndex(0);
				cmbMauSac.setSelectedIndex(0);
				cmbKichCo.setSelectedIndex(0);
				cmbChatLieu.setSelectedIndex(0);
				
			}
		});
		btnSearch.setBackground(new Color(65, 105, 225));
		btnSearch.setIcon(new ImageIcon(ManHinhLapHoaDon.class.getResource("/images/search.png")));
		
		JLabel lblSoluong = new JLabel("Số lượng:");
		lblSoluong.setBounds((int)(w*0.4), (int)(h*0.5+20), 50, 30);
		pn_timKiem.add(lblSoluong);
		lblSoluong.setHorizontalAlignment(SwingConstants.LEFT);
		lblSoluong.setFont(new Font("Arial", Font.PLAIN, 11));
		
		spn_soluong = new JSpinner(new SpinnerNumberModel(1,1,20,1));
		spn_soluong.setBounds((int)(w*0.4)+50, (int)(h*0.5+20), 50, 30);
		pn_timKiem.add(spn_soluong);
		
		btnThem = new JButton("Thêm");
		btnThem.setBounds((int)(w*0.4)+120, (int)(h*0.5+20), 120, 30);
		pn_timKiem.add(btnThem);
		btnThem.setBackground(new Color(0, 128, 0));
		btnThem.setFont(new Font("Arial", Font.BOLD, 14));
		btnThem.setIcon(new ImageIcon(ManHinhLapHoaDon.class.getResource("/images/them.png")));
		btnThem.setEnabled(false);
		
		JScrollPane scr_dssp = new JScrollPane();
		scr_dssp.setBounds(10, 60, w-40, (int)(h*0.5)-60);
		pn_timKiem.add(scr_dssp);
		model_dssp = new DefaultTableModel(
	            new Object [][] {
	    
	            },
	            new String [] {
	                "Hình ảnh","Mã", "Tên", "Loại", "Đơn giá","Số lượng","Chất liệu","Màu sắc","Kích cỡ"
	            }
	        ) {
	            boolean[] canEdit = new boolean [] {
	                false, false, false, false, false, false, false, false, false
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	        };
		tbl_dssp = new JTable(model_dssp);
		tbl_dssp.setBackground(Color.white);
		
		tbl_dssp.getColumnModel().getColumn(0).setMinWidth(50);
		tbl_dssp.getColumnModel().getColumn(1).setMinWidth(30);
		tbl_dssp.getColumnModel().getColumn(2).setMinWidth(170);		
		
		tbl_dssp.setRowHeight(80);
		tbl_dssp.setSelectionBackground(new java.awt.Color(65, 105, 225));
		
		
		DefaultTableCellRenderer head_render = new DefaultTableCellRenderer(); 
	    head_render.setBackground(new Color(135, 205, 230));
	    tbl_dssp.getTableHeader().setDefaultRenderer(head_render);
		
		scr_dssp.setViewportView(tbl_dssp);
		
		JLabel lblLoai = new JLabel("Loại:");
		lblLoai.setBounds(470, 20, 30, 30);
		pn_timKiem.add(lblLoai);
		
		String[] itemLoai = capNhatCmbLoai();
		cmbLoai = new JComboBox(itemLoai);
		cmbLoai.setBackground(new Color(255, 255, 255));
		cmbLoai.setBounds(500, 20, 80, 30);
		pn_timKiem.add(cmbLoai);
		
		JLabel lblMauSac = new JLabel("Màu sắc:");
		lblMauSac.setBounds(590, 20, 50, 30);
		pn_timKiem.add(lblMauSac);
		
		String[] itemMauSac = capNhatCmbMauSac();
		cmbMauSac = new JComboBox(itemMauSac);
		cmbMauSac.setBackground(new Color(255, 255, 255));
		cmbMauSac.setBounds(640, 20, 80, 30);
		pn_timKiem.add(cmbMauSac);
		
		JLabel lblKichCo = new JLabel("Kích cỡ:");
		lblKichCo.setBounds(730, 20, 50, 30);
		pn_timKiem.add(lblKichCo);
		
		String[] itemKichCo = capNhatCmbKichCo();
		cmbKichCo = new JComboBox(itemKichCo);
		cmbKichCo.setBackground(new Color(255, 255, 255));
		cmbKichCo.setBounds(780, 20, 70, 30);
		pn_timKiem.add(cmbKichCo);
		
		
		
		JLabel lblChatLieu = new JLabel("Chất liệu:");
		lblChatLieu.setBounds(860, 20, 60, 30);
		pn_timKiem.add(lblChatLieu);
		
		String[] itemChatLieu = capNhatCmbChatLieu();
		cmbChatLieu = new JComboBox(itemChatLieu);
		cmbChatLieu.setBackground(Color.WHITE);
		cmbChatLieu.setBounds(920, 20, 90, 30);
		pn_timKiem.add(cmbChatLieu);
		
		lblTen = new JLabel("Tên sp:");
		lblTen.setBounds(210, 20, 50, 30);
		pn_timKiem.add(lblTen);
		
		JLabel lblMa = new JLabel("Mã sp:");
		lblMa.setBounds(10, 20, 40, 30);
		pn_timKiem.add(lblMa);
		
		JPanel timKiem_1 = new JPanel();
		timKiem_1.setLayout(null);
		timKiem_1.setBorder(new RoundedCornerBorder());
		timKiem_1.setBackground(Color.WHITE);
		timKiem_1.setBounds(50, 20, 150, 30);
		pn_timKiem.add(timKiem_1);
		
		txt_tkMaSp = new JTextField();
		txt_tkMaSp.setText("Nhập mã sản phẩm ...");
		txt_tkMaSp.setForeground(Color.GRAY);
		txt_tkMaSp.setEditable(false);
		txt_tkMaSp.setColumns(10);
		txt_tkMaSp.setBorder(null);
		txt_tkMaSp.setBackground(Color.WHITE);
		txt_tkMaSp.setBounds(10, 3, 130, 24);
		timKiem_1.add(txt_tkMaSp);
		txt_tkMaSp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txt_tkMaSp.setText("");
				txt_tkMaSp.setForeground(Color.BLACK);
				txt_tkMaSp.setEditable(true);
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				txt_tkMaSp.setText("Nhập mã sản phẩm ...");
				txt_tkMaSp.setForeground(Color.GRAY);
			}
		});
		
		scr_dssp.getViewport().setBackground(new Color(255,255,255));
		scr_dssp.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		scr_dssp.getVerticalScrollBar().setPreferredSize(new Dimension(8, 300));
		
		tbl_dssp.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                setHorizontalAlignment(SwingConstants.CENTER);          
                
                Component com = super.getTableCellRendererComponent(tbl_gioHang, o, bln, bln1, i, i1);
                
                if (o == null) {
                	Icon image = new ImageIcon(ManHinhLapHoaDon.class.getResource("/images/null.jpg"));
            		Image ima = ((ImageIcon) image).getImage();
            		Image newimg = ima.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            		image = new ImageIcon(newimg);
            		JLabel label = new JLabel(image);
                	label.setHorizontalAlignment(JLabel.CENTER);
            		label.setOpaque(true);
                	label.setBackground(com.getBackground());
                	return label;
                } 
                else {
                	if (o instanceof Icon) {
                		Icon image = (ImageIcon)o;
                		Image ima = ((ImageIcon) image).getImage(); 
                		Image newimg = ima.getScaledInstance(70, 70,Image.SCALE_SMOOTH); 
                		image = new ImageIcon(newimg); 
                		JLabel label = new JLabel(image);
                		label.setHorizontalAlignment(JLabel.CENTER);
                		label.setOpaque(true);
                		label.setBackground(com.getBackground());
                		return label;
                	} 
                	else {
                		Icon image = new ImageIcon(ManHinhLapHoaDon.class.getResource("/images/null.jpg"));
                		Image ima = ((ImageIcon) image).getImage();
                		Image newimg = ima.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                		image = new ImageIcon(newimg);
                		JLabel label = new JLabel(image);
                    	label.setHorizontalAlignment(JLabel.CENTER);
                		label.setOpaque(true);
                    	label.setBackground(com.getBackground());
                    	return label;
                	}
                }
                
            }
        });
		
		tbl_dssp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				btnThem.setEnabled(true);
			}
		});
		
		List<SanPham> dssp = sanPham_dao.getDsSanPham();
		capNhatDuLieuCuaBang(dssp);
		
		btnThem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				btnThem.setEnabled(false);
				int row = tbl_dssp.getSelectedRow();
				sp = sanPham_dao.getSanPham(model_dssp.getValueAt(row, 1).toString());
				
				boolean ktra = false;
				for (int r = 0; r < tbl_gioHang.getRowCount(); r++) {
					if (model_giohang.getValueAt(r, 1).toString().equalsIgnoreCase(sp.getMaSP())) {
						row=r;
						ktra=true;
						break;
					}
					else ktra=false;
				}
				if (ktra) {
					int soluong = (int) model_giohang.getValueAt(row, 3);
	                SanPham s = sanPham_dao.getSanPham((String)model_giohang.getValueAt(row, 1));
	                if (soluong<s.getSoLuongTon()) {
	                	soluong+=Integer.parseInt(spn_soluong.getValue().toString());
	                	model_giohang.setValueAt(soluong, row, 3);
	                	double gia = dinhDangTien((String) model_giohang.getValueAt(row, 4));
						model_giohang.setValueAt(df.format((double)(soluong*gia)), row, 5);
		                lbl_kqTongtien.setText(df.format(tinhTongTienHD()));
		                lbl_kqtongthanhtoan.setText(df.format(tinhTongThanhToan()));
		                thayDoiGiaTriTienThua();
	                }
	                else {
	                	JOptionPane.showMessageDialog(null, "số lượng hàng không đủ!");
	                }
				}
				else {
					ImageIcon im = new ImageIcon(sp.getHinhAnh());
					Object data[] = {im,sp.getMaSP(),sp.getTenSP(),spn_soluong.getValue(),df.format(sp.getGiaBan()),df.format((int)spn_soluong.getValue()*sp.getGiaBan())};
					model_giohang.addRow(data);
					lbl_kqTongtien.setText(df.format(tinhTongTienHD()));
					lbl_kqtongthanhtoan.setText(df.format(tinhTongThanhToan()));
					thayDoiGiaTriTienThua();
				}
				
				spn_soluong.setValue(1);
			}
		});
		spn_soluong.getModel().setValue(1);
		
		JPanel pn_thongTinHD = new JPanel();
		int xhd=(int)(w*0.7)+10;
		int yhd=(int)(h*0.6);
		int whd = (int)(w*0.3)-20;
		int hhd=(int)(h*0.4);
		pn_thongTinHD.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Th\u00F4ng tin h\u00F3a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_thongTinHD.setBackground(new Color(255, 255, 255));
		pn_thongTinHD.setBounds(xhd, yhd, whd, hhd);
		add(pn_thongTinHD);
		pn_thongTinHD.setLayout(null);
		
		JPanel pn_KH = new JPanel();
		pn_KH.setBackground(new Color(255, 255, 255));
		pn_KH.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		pn_KH.setBounds(15, 20, whd-30, 30);
		pn_thongTinHD.add(pn_KH);
		pn_KH.setLayout(null);
		
		txt_timkiemKH = new JTextField();
		txt_timkiemKH.setBorder(null);
		txt_timkiemKH.setToolTipText("");
		txt_timkiemKH.setForeground(new Color(192, 192, 192));
		txt_timkiemKH.setText("Nhập số điện thoại khách hàng ...");
		txt_timkiemKH.setBounds(5, 5, (int)(whd*0.5)-5, 20);
		pn_KH.add(txt_timkiemKH);
		txt_timkiemKH.setColumns(10);
		txt_timkiemKH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txt_timkiemKH.setText("");
				txt_timkiemKH.setForeground(Color.BLACK);
				txt_timkiemKH.setEditable(true);
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				txt_timkiemKH.setText("Nhập số điện thoại khách hàng ...");
				txt_timkiemKH.setForeground(Color.GRAY);
			}
		});
		
		btn_timKH = new JButton("");
		btn_timKH.setHorizontalAlignment(SwingConstants.CENTER);
		btn_timKH.setIcon(new ImageIcon(ManHinhLapHoaDon.class.getResource("/images/search.png")));
		btn_timKH.setBounds((int)(whd*0.65)+5, 5, (int)(whd*0.12)+5, 20);
		pn_KH.add(btn_timKH);
		
		btn_timKH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				String sdtkh = txt_timkiemKH.getText();
				khachHang = khachHang_dao.getKH(sdtkh);
				if (khachHang!=null) {
					lbl_kqTenKH.setText(khachHang.getTenKH());
					txt_timkiemKH.setForeground(new Color(192, 192, 192));
					txt_timkiemKH.setText("Nhập số điện thoại khách hàng ...");
				} 
				else {
					JOptionPane.showMessageDialog(null,"Không tìm thấy khách hàng!");
					khachHang = khachHang_dao.getKHMaKH("KHACHLE");
					lbl_kqTenKH.setText(khachHang.getTenKH());
					txt_timkiemKH.setForeground(new Color(192, 192, 192));
					txt_timkiemKH.setText("Nhập số điện thoại khách hàng ...");
				}
			}
		});
		
		btn_themKH = new JButton("");
		btn_themKH.setIcon(new ImageIcon(ManHinhLapHoaDon.class.getResource("/images/icon_themKH.png")));
		btn_themKH.setHorizontalAlignment(SwingConstants.CENTER);
		btn_themKH.setBounds((int)(whd*0.8)+5, 5, (int)(whd*0.12)-5, 20);
		pn_KH.add(btn_themKH);
		
		
		
		pnl_hd = new JPanel();
		pnl_hd.setBackground(new Color(255, 255, 255));
		pnl_hd.setBounds(15, 50, whd-30, hhd-60);
		pn_thongTinHD.add(pnl_hd);
		pnl_hd.setLayout(null);
		
		JLabel lbl_tenKH = new JLabel("Khách hàng:");
		lbl_tenKH.setBounds(0, 0, (int)(whd*0.3), 20);
		pnl_hd.add(lbl_tenKH);
		
		lbl_kqTenKH = new JLabel("Khách lẻ");
		lbl_kqTenKH.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_kqTenKH.setBounds((int)(whd*0.3), 0, (int)(whd*0.6), 20);
		pnl_hd.add(lbl_kqTenKH);
		
		JLabel lbl_Tongtien = new JLabel("Tổng tiền:");
		lbl_Tongtien.setBounds(0, 30, (int)(whd*0.3), 20);
		pnl_hd.add(lbl_Tongtien);
		
		lbl_kqTongtien = new JLabel("0 VND");
		lbl_kqTongtien.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_kqTongtien.setBounds((int)(whd*0.3), 30, (int)(whd*0.6), 20);
		pnl_hd.add(lbl_kqTongtien);
		
		JLabel lbl_khuyenmai = new JLabel("Mã khuyến mãi:");
		lbl_khuyenmai.setBounds(0, 60, (int)(whd*0.3), 20);
		pnl_hd.add(lbl_khuyenmai);
		
		/*
		String[] strKhuyenMai = capNhatCmbMaCTKM();
		cmb_khuyenmai = new JComboBox(strKhuyenMai);
		cmb_khuyenmai.setBorder(null);
		cmb_khuyenmai.setBackground(Color.WHITE);
		cmb_khuyenmai.setBounds((int)(whd*0.3), 60, (int)(whd*0.6), 20);
		pnl_hd.add(cmb_khuyenmai);
		
		cmb_khuyenmai.getModel().addListDataListener(new ListDataListener() {
			
			@Override
			public void intervalRemoved(ListDataEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void intervalAdded(ListDataEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void contentsChanged(ListDataEvent e) {
				// TODO Auto-generated method stub
				String maKM = (String)cmb_khuyenmai.getSelectedItem();
				ctkm = ctkm_dao.getCTKM(maKM);
				if (ctkm==null) {
					lbl_ptkm.setText("Khuyến mãi 0%");
					lbl_kqtongthanhtoan.setText(df.format(tinhTongThanhToan()));
				} 
				else {
					lbl_ptkm.setText("Khuyến mãi "+ctkm.getPhanTramKhuyenMai()+"%");
					lbl_kqtongthanhtoan.setText(df.format(tinhTongThanhToan()));
				}
			}
		});
		*/
		JTextField txt_khuyenMai = new JTextField();
		txt_khuyenMai.setBounds((int)(whd*0.3)-3, 60, (int)(whd*0.6)-50, 25);
		pnl_hd.add(txt_khuyenMai);
		
		JButton btn_timKM = new JButton("Tìm");
		btn_timKM.setBounds((int)(whd*0.9)-50, 60, 50, 20);
		pnl_hd.add(btn_timKM);
		
		btn_timKM.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				String maKM = (String)txt_khuyenMai.getText();
				ctkm = ctkm_dao.getCTKM(maKM);
				if (ctkm==null) {
					lbl_ptkm.setText("Khuyến mãi 0%");
					lbl_kqtongthanhtoan.setText(df.format(tinhTongThanhToan()));
					JOptionPane.showMessageDialog(null, "Không tìm thấy mã khuyến mãi");
				} 
				else {
					lbl_ptkm.setText("Khuyến mãi "+ctkm.getPhanTramKhuyenMai()+"%");
					lbl_kqtongthanhtoan.setText(df.format(tinhTongThanhToan()));
				}
			}
		});
		
		lbl_ptkm = new JLabel("Khuyến mãi 0%");
		lbl_ptkm.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_ptkm.setForeground(Color.RED);
		lbl_ptkm.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lbl_ptkm.setBounds((int)(whd*0.3), 80, (int)(whd*0.6), 20);
		pnl_hd.add(lbl_ptkm);
		
		JLabel lbl_tongthanhtoan = new JLabel("Tổng thanh toán:");
		lbl_tongthanhtoan.setBounds(0, 100, (int)(whd*0.3), 20);
		pnl_hd.add(lbl_tongthanhtoan);
		
		lbl_kqtongthanhtoan = new JLabel("0 VND");
		lbl_kqtongthanhtoan.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_kqtongthanhtoan.setBounds((int)(whd*0.3), 100, (int)(whd*0.6), 20);
		pnl_hd.add(lbl_kqtongthanhtoan);
		
		JLabel lbl_tienkhachtra = new JLabel("Tiền khách trả:");
		lbl_tienkhachtra.setBounds(0, 134, (int)(whd*0.3), 20);
		pnl_hd.add(lbl_tienkhachtra);
		
		txt_tienKhachTra = new JTextField();
		txt_tienKhachTra.setHorizontalAlignment(SwingConstants.RIGHT);
		txt_tienKhachTra.setColumns(10);
		txt_tienKhachTra.setBounds((int)(whd*0.3), 130, (int)(whd*0.6)-25, 25);
		pnl_hd.add(txt_tienKhachTra);
		
		txt_tienKhachTra.addCaretListener(new CaretListener() {
			
			@Override
			public void caretUpdate(CaretEvent e) {
				// TODO Auto-generated method stub
				thayDoiGiaTriTienThua();
			}
		});
		
		
		JLabel lbl_kqtienkhachtra = new JLabel("VND");
		lbl_kqtienkhachtra.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_kqtienkhachtra.setBounds((int)(whd*0.9)-25, 134, 25, 20);
		pnl_hd.add(lbl_kqtienkhachtra);
		
		JLabel lbl_thienthua = new JLabel("Tiền thừa trả khách:");
		lbl_thienthua.setBounds(0, 165, (int)(whd*0.3), 20);
		pnl_hd.add(lbl_thienthua);
		
		lbl_kqtienthua = new JLabel("0 VND");
		lbl_kqtienthua.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_kqtienthua.setBounds((int)(whd*0.3), 165, (int)(whd*0.6), 20);
		pnl_hd.add(lbl_kqtienthua);
		
		btn_dathang = new JButton("Đặt hàng");
		btn_dathang.setBackground(new Color(255, 127, 80));
		btn_dathang.setBounds(0, (int)(hhd*0.7), (int)(whd*0.4), 30);
		pnl_hd.add(btn_dathang);
		
		
		btn_dathang.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (kiemTraDuLieuDatHang()) {
					LocalDateTime ngayLap = LocalDateTime.now();
					double tongTienHD = dinhDangTien(lbl_kqtongthanhtoan.getText());
					String maDDH = donDatHang_DAO.taoMaDonDatHang();
					DonDatHang ddh = new DonDatHang(maDDH, khachHang, ngayLap, nhanVien, tongTienHD, 0, "Chưa thanh toán");
					if (donDatHang_DAO.themDDH(ddh)) {
						for (int row = 0; row < tbl_gioHang.getRowCount(); row++) {
							String maSP = (String) model_giohang.getValueAt(row, 1);
							SanPham sp = sanPham_dao.getSanPham(maSP);
							int soLuong = (int) model_giohang.getValueAt(row, 3);
							double giaBan = dinhDangTien((String) model_giohang.getValueAt(row, 4));
							ChiTietDonDatHang ct = new ChiTietDonDatHang(maDDH, sp, soLuong, giaBan);
							if (chiTietDonDatHang_DAO.themChiTietDDH(ct)) System.out.println("ok"); 
						}
						xoaDuLieuCuaBang(tbl_gioHang);
						txt_tienKhachTra.setText("");
						lbl_thienthua.setText("");
						lbl_kqtongthanhtoan.setText("");
						cmb_khuyenmai.setSelectedIndex(0);
						ManHinhDatHang.docLaiDuLieuCuaBang();
						JOptionPane.showMessageDialog(null, "Đặt hàng thành công!");
						khachHang = khachHang_dao.getKHMaKH("KHACHLE");
						lbl_kqTenKH.setText("Khách lẻ");
						List<SanPham> dsSP = sanPham_dao.getDsSanPham();
						capNhatDuLieuCuaBang(dsSP);
					}
					
				}
			}
		});
		
		btn_thanhtoan = new JButton("Thanh toán");
		btn_thanhtoan.setBackground(new Color(255, 127, 80));
		btn_thanhtoan.setBounds((int)(whd*0.5), (int)(hhd*0.7), (int)(whd*0.4), 30);
		pnl_hd.add(btn_thanhtoan);
		
		btn_troLai = new JButton("Trở lại");
		btn_troLai.setBackground(new Color(255, 127, 80));
		btn_troLai.setBounds(0, (int)(hhd*0.7), (int)(whd*0.4), 30);
		pnl_hd.add(btn_troLai);
		
		JLabel lbl_kqTenKH_1 = new JLabel("Khách lẻ");
		lbl_kqTenKH_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_kqTenKH_1.setBounds(110, 0, 190, 20);
		pnl_hd.add(lbl_kqTenKH_1);
		
		JCheckBox ckb_inHoaDon = new JCheckBox("Xuất hóa đơn");
		ckb_inHoaDon.setBounds(180, 183, 120, 20);
		pnl_hd.add(ckb_inHoaDon);
		lbl_kqTenKH_1.setVisible(false);
		
		btn_troLai.setVisible(false);
		
		btn_troLai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				lbl_kqTenKH.validate();
				lbl_kqTenKH.repaint();
				khachHang = khachHang_dao.getKHMaKH("KHACHLE");
				ManHinhChinh.pn_body.removeAll();
				ManHinhChinh.pn_body.add(ManHinhChinh.mh_dathang);
				ManHinhChinh.pn_body.validate();
				ManHinhChinh.pn_body.repaint();
			}
		});
		
		//thêm sự kiện click chuột cho button thanh toán
		btn_thanhtoan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//nếu là màn hình lập hóa đơn từ màn hình đặt hàng chuyển qua
				//sau khi thanh toán thành công thì tắt đi và chuyển về màn hình đặt hàng
				if (isDH && !maDDH.equals("")) {
					if (kiemTraDuLieuThanhToan()) {
						LocalDateTime ngayLap = LocalDateTime.now();
						String maCtkm = txt_khuyenMai.getText();//(String) cmb_khuyenmai.getSelectedItem();
						//maCtkm = maCtkm.equals("Không có")?"MACDINH":maCtkm;
						ChuongTrinhKhuyenMai ctkm = ctkm_dao.getCTKM(maCtkm);
						if (ctkm==null) ctkm=ctkm_dao.getCTKM("MACDINH");
						double tongTienHD = dinhDangTien(lbl_kqtongthanhtoan.getText());
						double tienKhachTra =  Double.parseDouble(txt_tienKhachTra.getText());
						String maHD = HoaDon_DAO.taoMaHoaDon();
						HoaDon hd = new HoaDon(maHD, ngayLap, nhanVien, khachHang, ctkm, tongTienHD, tienKhachTra);
						
						if (hoaDon_DAO.themHD(hd)) {
							for (int row = 0; row < tbl_gioHang.getRowCount(); row++) {
								String maSP = (String) model_giohang.getValueAt(row, 1);
								SanPham sp = sanPham_dao.getSanPham(maSP);
								int soLuong = (int) model_giohang.getValueAt(row, 3);
								double giaBan = dinhDangTien((String) model_giohang.getValueAt(row, 4));
								ChiTietHoaDon ct = new ChiTietHoaDon(maHD, sp, soLuong, giaBan);
								if (chiTietHoaDon_DAO.themChiTietHD(ct)) System.out.println("ok"); 
							}
							//String t = lbl_kqtongthanhtoan.getText();
							//ManHinhChinh.thread = new SendSMS("Nhân viên "+nhanVien.getTenNV()+" vừa lập hóa đơn có trị giá "+t);
							//ManHinhChinh.thread.start();
							if (khachHang.getEmail()!=null) {
								String mess = "Cảm ơn quý khách đã sử dụng dịch vụ của AM STORE \n"
										+ "Trạng thái đơn hàng\n"
										+ "-----------------------------------------------------------\n"
										+ "Thành công\n"
										+ "-----------------------------------------------------------\n"
										+ "Mã hoá đơn                  "+hd.getMaHD()+"\n"
										+ "Số tiền                           "+hd.getTongTienHD()+"\n"
										+ "Tên khách hàng              "+ khachHang.getTenKH()+"\n"
										+ "------------------------------------------------------------";
								SendEmailWithThread emailThread = new SendEmailWithThread(khachHang.getEmail(), mess);
								emailThread.start();
							}
							xoaDuLieuCuaBang(tbl_gioHang);
							txt_tienKhachTra.setText("");
							lbl_thienthua.setText("");
							lbl_kqtongthanhtoan.setText("");
							//cmb_khuyenmai.setSelectedIndex(0);
							txt_khuyenMai.setText("");
							JOptionPane.showMessageDialog(null, "Thanh toán thành công!");
							khachHang = khachHang_dao.getKHMaKH("KHACHLE");
							donDatHang_DAO.capNhatDDH(maDDH, "Đã thanh toán");
							if (ckb_inHoaDon.isSelected()) {
								Report.xuatHoaDonPDF("sMaHD", maHD, "E:\\hoadon.pdf", false);
							}
							ManHinhDatHang.docLaiDuLieuCuaBang();
							ManHinhTimKiemHoaDon.resetData();
							ManHinhChinh.pn_body.removeAll();
							ManHinhChinh.pn_body.add(ManHinhChinh.mh_dathang);
							ManHinhChinh.pn_body.validate();
							ManHinhChinh.pn_body.repaint();
						}						
					}
				}
				else {
					if (kiemTraDuLieuThanhToan()) {
						LocalDateTime ngayLap = LocalDateTime.now();
						String maCtkm = txt_khuyenMai.getText();//(String) cmb_khuyenmai.getSelectedItem();
						//maCtkm = maCtkm.equals("Không có")?"MACDINH":maCtkm;
						ChuongTrinhKhuyenMai ctkm = ctkm_dao.getCTKM(maCtkm);
						if (ctkm==null) ctkm=ctkm_dao.getCTKM("MACDINH");
						double tongTienHD = dinhDangTien(lbl_kqtongthanhtoan.getText());
						double tienKhachTra =  Double.parseDouble(txt_tienKhachTra.getText());
						String maHD = HoaDon_DAO.taoMaHoaDon();
						HoaDon hd = new HoaDon(maHD, ngayLap, nhanVien, khachHang, ctkm, tongTienHD, tienKhachTra);
						
						if (hoaDon_DAO.themHD(hd)) {
							for (int row = 0; row < tbl_gioHang.getRowCount(); row++) {
								String maSP = (String) model_giohang.getValueAt(row, 1);
								SanPham sp = sanPham_dao.getSanPham(maSP);
								int soLuong = (int) model_giohang.getValueAt(row, 3);
								double giaBan = dinhDangTien((String) model_giohang.getValueAt(row, 4));
								ChiTietHoaDon ct = new ChiTietHoaDon(maHD, sp, soLuong, giaBan);
								if (chiTietHoaDon_DAO.themChiTietHD(ct)) sanPham_dao.capNhatSoLuongTon(maSP, soLuong); 
							}
							
							//String t = lbl_kqtongthanhtoan.getText();
							//ManHinhChinh.thread = new SendSMS("Cám ơn bạn đã mua hàng tại AM Store! Bạn đã thanh toán hóa đơn có trị giá "+t);
							//ManHinhChinh.thread.start();
							
							if (khachHang.getEmail()!=null) {
								String mess = "Cảm ơn quý khách đã sử dụng dịch vụ của AM STORE \n"
										+ "Trạng thái đơn hàng\n"
										+ "-----------------------------------------------------------\n"
										+ "Thành công\n"
										+ "-----------------------------------------------------------\n"
										+ "Mã hoá đơn                  "+hd.getMaHD()+"\n"
										+ "Số tiền                           "+hd.getTongTienHD()+"\n"
										+ "Tên khách hàng              "+ khachHang.getTenKH()+"\n"
										+ "------------------------------------------------------------";
								SendEmailWithThread emailThread = new SendEmailWithThread(khachHang.getEmail(), mess);
								emailThread.start();
							}
							
							xoaDuLieuCuaBang(tbl_gioHang);
							lbl_kqTongtien.setText("0 VND");
							txt_tienKhachTra.setText("");
							lbl_thienthua.setText("");
							lbl_kqtongthanhtoan.setText("0 VND");
							//cmb_khuyenmai.setSelectedIndex(0);
							txt_khuyenMai.setText("");
							List<SanPham> dsSP = sanPham_dao.getDsSanPham();
							capNhatDuLieuCuaBang(dsSP);
							khachHang = khachHang_dao.getKHMaKH("KHACHLE");
							ManHinhTimKiemHoaDon.resetData();
							JOptionPane.showMessageDialog(null, "Thanh toán thành công!");
							if (ckb_inHoaDon.isSelected()) {
								Report.xuatHoaDonPDF("sMaHD", maHD, "E:\\hoadon.pdf", false);
							}
						}
						
					}
				}
				
			}
		});
		
		
		//thêm sự kiện click chuột cho button thêm khách hàng mới
		//hiển thị form nhập thông tin khách hàng
		btn_themKH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				ManHinhChinh.form.setVisible(true);
			}
		});
		//
		
		//scan kh
		qrCodeScanner = new QRCodeScanner();
		QRCodeScanner.webcam.close();

		btnQR = new JButton("QR");
		btnQR.setBounds((int)(whd*0.5)+5, 5, (int)(whd*0.12)+5, 20);
		pn_KH.add(btnQR);
		btnQR.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        JDialog qrDialog = new JDialog();
		        qrDialog.setTitle("Cửa sổ QR");
		        qrDialog.setSize(500, 600);
		        qrDialog.setLocationRelativeTo(null);
		        qrDialog.setModal(true);
		        qrDialog.getContentPane().add(qrCodeScanner);
		        qrDialog.setVisible(true);
		        //QRCodeScanner.webcam.open();
		    }
		});
		QRCodeScanner.stopButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (QRCodeScanner.result != null) {
					String maKhachHang = QRCodeScanner.result.getText(); 
					khachHang = khachHang_dao.getKHMaKH(maKhachHang);
					if (khachHang!=null) {
						lbl_kqTenKH.setText(khachHang.getTenKH());
						txt_timkiemKH.setForeground(new Color(192, 192, 192));
						txt_timkiemKH.setText("Nhập số điện thoại khách hàng ...");
					} 
				}
				else {
					//JOptionPane.showMessageDialog(null,"Không tìm thấy khách hàng!");
					khachHang = khachHang_dao.getKHMaKH("KHACHLE");
					lbl_kqTenKH.setText(khachHang.getTenKH());
					txt_timkiemKH.setForeground(new Color(192, 192, 192));
					txt_timkiemKH.setText("Nhập số điện thoại khách hàng ...");
				}
				
				
				
			}
		});

		
		JPanel pn_DSGioHang = new JPanel();
		pn_DSGioHang.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Gi\u1ECF h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_DSGioHang.setBackground(new Color(255, 255, 255));
		pn_DSGioHang.setBounds(10, (int)(h*0.6), (int)(w*0.7), (int)(h*0.4));
		add(pn_DSGioHang);
		
		JScrollPane scr_giohang = new JScrollPane();
		scr_giohang.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		scr_giohang.getVerticalScrollBar().setPreferredSize(new Dimension(8,300));
		model_giohang = new DefaultTableModel(
	            new Object [][] {
	    
	            },
	            new String [] {
	                "Hình ảnh","Mã", "Tên", "Số lượng", "Giá bán","Thành tiền",""
	            }
	        ) {
	            boolean[] canEdit = new boolean [] {
	                false, false, false, false, false, false, true
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	        };
		tbl_gioHang = new JTable(model_giohang);
		tbl_gioHang.setBackground(Color.white);

		
		tbl_dssp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = tbl_dssp.getSelectedRow();
				spn_soluong.setModel(new SpinnerNumberModel(1,1,(int) tbl_dssp.getValueAt(row, 5),1));
			}
		});
		
		
		
		tbl_gioHang.setRowHeight(80);
		tbl_gioHang.setSelectionBackground(new java.awt.Color(65, 105, 225));
		
		tbl_gioHang.getColumnModel().getColumn(0).setMinWidth(50);
		tbl_gioHang.getColumnModel().getColumn(1).setMinWidth(30);
		tbl_gioHang.getColumnModel().getColumn(2).setMinWidth(170);
		tbl_gioHang.getColumnModel().getColumn(3).setMinWidth(40);
		tbl_gioHang.getColumnModel().getColumn(4).setMinWidth(80);
		tbl_gioHang.getColumnModel().getColumn(5).setMinWidth(90);
		tbl_gioHang.getColumnModel().getColumn(6).setMinWidth(110);
		
	    head_render.setBackground(new Color(135, 205, 230));
	    tbl_gioHang.getTableHeader().setDefaultRenderer(head_render);
		
		scr_giohang.setViewportView(tbl_gioHang);
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
        
        
        event = new TableActionEvent() {
        	/**
        	 * khi click vào dấu cộng thì tăng số lượng sản phẩm tại row được chọn
        	 * cập nhật lại các kết quả tính toán tiền
        	 * @param row
        	 */
            @Override
            public void onAdd(int row) {
                System.out.println("Edit row : " + row);
                int soluong = (int) model_giohang.getValueAt(row, 3);
                SanPham s = sanPham_dao.getSanPham((String)model_giohang.getValueAt(row, 1));
                if (soluong<s.getSoLuongTon()) {
                	soluong+=1;
                	model_giohang.setValueAt(soluong, row, 3);
                	double gia = dinhDangTien((String) model_giohang.getValueAt(row, 4));
    				model_giohang.setValueAt(df.format((double)(soluong*gia)), row, 5);
                    lbl_kqTongtien.setText(df.format(tinhTongTienHD()));
                    lbl_kqtongthanhtoan.setText(df.format(tinhTongThanhToan()));
                    thayDoiGiaTriTienThua();
                    rowselect=row;
                }
            }
            /**
        	 * khi click vào dấu X thì xóa sản phẩm tại row được chọn
        	 * cập nhật lại các kết quả tính toán tiền
        	 * @param row
        	 */
            @Override
            public void onDelete(int row) {
                if (tbl_gioHang.isEditing()) {
                    tbl_gioHang.getCellEditor().stopCellEditing();
                }
                DefaultTableModel model = (DefaultTableModel) tbl_gioHang.getModel();
                model.removeRow(row);
                lbl_kqTongtien.setText(df.format(tinhTongTienHD()));
                lbl_kqtongthanhtoan.setText(df.format(tinhTongThanhToan()));
                thayDoiGiaTriTienThua();
            }
            /**
        	 * khi click vào dấu trừ thì giảm số lượng sản phẩm tại row được chọn
        	 * cập nhật lại các kết quả tính toán tiền
        	 * @param row
        	 */
            @Override
            public void onMinus(int row) {
                System.out.println("View row : " + row);
                int soluong = (int) model_giohang.getValueAt(row, 3);
                if (soluong>1) {
                	soluong-=1;
                	model_giohang.setValueAt(soluong, row, 3);
                	double gia = dinhDangTien((String) model_giohang.getValueAt(row, 4));
    				model_giohang.setValueAt(df.format((double)(soluong*gia)), row, 5);
                    lbl_kqTongtien.setText(df.format(tinhTongTienHD()));
                    lbl_kqtongthanhtoan.setText(df.format(tinhTongThanhToan()));
                    thayDoiGiaTriTienThua();
                }
				rowselect=row;
            }
        };
        tbl_gioHang.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRender());
        tbl_gioHang.getColumnModel().getColumn(6).setCellEditor(new TableActionCellEditor(event));
        tbl_gioHang.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                setHorizontalAlignment(SwingConstants.CENTER);          
                
                Component com = super.getTableCellRendererComponent(tbl_gioHang, o, bln, bln1, i, i1);
                
                if (o == null) {
                	Icon image = new ImageIcon(ManHinhLapHoaDon.class.getResource("/images/null.jpg"));
            		Image ima = ((ImageIcon) image).getImage();
            		Image newimg = ima.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            		image = new ImageIcon(newimg);
            		JLabel label = new JLabel(image);
                	label.setHorizontalAlignment(JLabel.CENTER);
            		label.setOpaque(true);
                	label.setBackground(com.getBackground());
                	return label;
                } 
                else {
                	if (o instanceof Icon) {
                		Icon image = (ImageIcon)o;
                		Image ima = ((ImageIcon) image).getImage(); 
                		Image newimg = ima.getScaledInstance(70, 70,Image.SCALE_SMOOTH); 
                		image = new ImageIcon(newimg); 
                		JLabel label = new JLabel(image);
                		label.setHorizontalAlignment(JLabel.CENTER);
                		label.setOpaque(true);
                		label.setBackground(com.getBackground());
                		return label;
                	} 
                	else {
                		Icon image = new ImageIcon(ManHinhLapHoaDon.class.getResource("/images/null.jpg"));
                		Image ima = ((ImageIcon) image).getImage();
                		Image newimg = ima.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                		image = new ImageIcon(newimg);
                		JLabel label = new JLabel(image);
                    	label.setHorizontalAlignment(JLabel.CENTER);
                		label.setOpaque(true);
                    	label.setBackground(com.getBackground());
                    	return label;
                	}
                }
                
            }
        });
        
        //nếu từ màn hình đặt hàng chuyển sang thì cập nhật danh sách sản phẩm trong giỏ hàng và tên khách hàng
        //ẩn phần tìm kiếm và thêm khách hàng mới
        //ẩn nút đặt hàng
        //hiện nút trở lại để quay về màn hình đặt hàng
        if (isDH && !maDDH.equals("")) {
        	btn_dathang.setVisible(false);
        	btn_troLai.setVisible(true);
        	pn_KH.setVisible(false);
        	pnl_hd.setBounds(15, 50, whd-30, hhd-60);
        	DonDatHang ddh = donDatHang_DAO.getDDH(maDDH);
        	khachHang = khachHang_dao.getKHMaKH(ddh.getKhachHang().getMaKH());
        	lbl_kqTenKH.setVisible(false);
        	lbl_kqTenKH_1.setVisible(true);
        	lbl_kqTenKH_1.setText(khachHang.getTenKH());
        	ds_ctddh = chiTietDonDatHang_DAO.getDsChiTietDDH(maDDH);
        	for (ChiTietDonDatHang ct : ds_ctddh) {
        		ImageIcon im = new ImageIcon(ct.getSanPham().getHinhAnh());
				Object data[] = {im,ct.getSanPham().getMaSP(),ct.getSanPham().getTenSP(),ct.getSoLuong(),df.format(ct.getGiaBan()),df.format((int)ct.getSoLuong()*ct.getGiaBan())};
				model_giohang.addRow(data);
				lbl_kqTongtien.setText(df.format(tinhTongTienHD()));
				lbl_kqtongthanhtoan.setText(df.format(tinhTongThanhToan()));
				thayDoiGiaTriTienThua();
			}
        }
   
        ManHinhChinh.btnVN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblTitleHeader.setText("LẬP HÓA ĐƠN");
				((TitledBorder) pn_timKiem.getBorder()).setTitle("Tìm kiếm sản phẩm");
				pn_timKiem.repaint();
				((TitledBorder) pn_DSGioHang.getBorder()).setTitle("Vỏ hàng");
				pn_DSGioHang.repaint();
				lblMa.setText("Mã sp:");
				lblTen.setText("Tên sp:");
				lbl_tenKH.setText("Khách hàng:");
				lbl_Tongtien.setText("Tổng tiền:");
				lbl_khuyenmai.setText("Mã khuyến mãi:");
				lbl_tongthanhtoan.setText("Tổng thanh toán:");
				lbl_tienkhachtra.setText("Tiền khách trả:");
				lbl_thienthua.setText("Tiền thừa trả khách:");
				ckb_inHoaDon.setText("Xuất hoá đơn");
				btn_dathang.setText("Đặt hàng");
				btn_thanhtoan.setText("Thanh toán");
				btnThem.setText("Thêm");
				lblSoluong.setText("Số lượng:");
				lblLoai.setText("Loại:");
				lblKichCo.setText("Kích cỡ:");
				lblMauSac.setText("Màu:");
				lblChatLieu.setText("Chất liệu:");
				btnSearch.setText("Tìm");
				String[] newColumns_dssp = {"Hình ảnh", "Mã", "Tên", "Loại", "Đơn giá", "Số lượng", "Chất liệu", "Màu sắc", "Kích cỡ"};
				model_dssp.setColumnIdentifiers(newColumns_dssp);
			
				String[] newColumn_gioHang = {"Hình ảnh", "Mã", "Tên", "Số lượng", "Giá bán", "Thành tiền", ""};
				model_giohang.setColumnIdentifiers(newColumn_gioHang);
				xuLyAnh();
				FormNhapThongTinKhachHangMoi.lblNewLabel.setText("NHẬP THÔNG TIN KHÁCH HÀNG");
				FormNhapThongTinKhachHangMoi.lblNewLabel_1_1.setText("Họ tên:");
				FormNhapThongTinKhachHangMoi.lblNewLabel_1_1_1.setText("Số điện thoại:");
				FormNhapThongTinKhachHangMoi.lblNewLabel_1_1_1_1.setText("Địa chỉ:");
				FormNhapThongTinKhachHangMoi.lblNewLabel_1_1_2.setText("Giới tính:");
				FormNhapThongTinKhachHangMoi.btnThemKHMoi.setText("Thêm");
				//txt_tkMaSp.setText("Nhập mã sản phẩm...");
				//txt_tkTenSp.setText("Nhập tên sản phẩm...");
				xuLyNut();
			}
		});
		
		ManHinhChinh.btnEN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblTitleHeader.setText("INVOICE");
				((TitledBorder) pn_timKiem.getBorder()).setTitle("Search for products");
				pn_timKiem.repaint();
				((TitledBorder) pn_DSGioHang.getBorder()).setTitle("Cart");
				pn_DSGioHang.repaint();
				lblMa.setText("Id:");
				lblTen.setText("Name:");
				lbl_tenKH.setText("Customer:");
				lbl_Tongtien.setText("Total amount:");
				lbl_khuyenmai.setText("Coupon:");
				lbl_tongthanhtoan.setText("Total payment:");
				lbl_tienkhachtra.setText("Guest pay:");
				lbl_thienthua.setText("Money returned:");
				ckb_inHoaDon.setText("Invoice");
				btn_dathang.setText("Order");
				btn_thanhtoan.setText("Purchase");
				btnThem.setText("Add");
				lblSoluong.setText("Quantity:");
				lblLoai.setText("Type:");
				lblKichCo.setText("Size:");
				lblMauSac.setText("Color:");
				lblChatLieu.setText("Material:");
				btnSearch.setText("Find");
				
				String[] newColumns_dssp = {"Image", "Id", "Name", "Type", "Unit price", "Quantity", "Material", "Color", "Size"};
				model_dssp.setColumnIdentifiers(newColumns_dssp);
				
				String[] newColumn_gioHang = {"Image", "Id", "Name", "Quantity", "Unit price", "Money", ""};
				model_giohang.setColumnIdentifiers(newColumn_gioHang);
				xuLyAnh();
				if (ctkm==null) {
					lbl_ptkm.setText("Promotion 0%");
					lbl_kqtongthanhtoan.setText(df.format(tinhTongThanhToan()));
				} 
				else {
					lbl_ptkm.setText("Promotion "+ctkm.getPhanTramKhuyenMai()+"%");
					lbl_kqtongthanhtoan.setText(df.format(tinhTongThanhToan()));
				}
				FormNhapThongTinKhachHangMoi.lblNewLabel.setText("ENTER CUSTOMER INFORMATION");
				FormNhapThongTinKhachHangMoi.lblNewLabel_1_1.setText("Full name:");
				FormNhapThongTinKhachHangMoi.lblNewLabel_1_1_1.setText("Phone number:");
				FormNhapThongTinKhachHangMoi.lblNewLabel_1_1_1_1.setText("Address:");
				FormNhapThongTinKhachHangMoi.lblNewLabel_1_1_2.setText("Gender:");
				FormNhapThongTinKhachHangMoi.btnThemKHMoi.setText("Add");
				//txt_tkMaSp.setText("Enter product code...");
				//txt_tkTenSp.setText("Enter product name...");
				xuLyNut();
			}
		});
		
	}
	
	/**
	 * tính tổng tiền hóa đơn dựa trên danh sách sản phẩm trong giỏ hàng
	 * @return tổng tiền hóa đơn
	 */
	private double tinhTongTienHD() {
		double sum=0;
		for (int i = 0; i < tbl_gioHang.getRowCount(); i++) {
			sum+=dinhDangTien((String)model_giohang.getValueAt(i, 5));
		}
		return sum;
	}
	
	/**
	 * chuyển chuỗi 100,000VND thành số thực 100000.0
	 * @param s
	 * @return số thực
	 */
	public double dinhDangTien(String s) {
		s=s.replace(" VND", "");
		s=s.replace(",", "");
		return Double.parseDouble(s);
	}
	
	/**
	 * lấy dữ liệu từ csdl để cập nhật combo box chương trình khuyến mãi
	 * @return mảng chuỗi tên chương trình khuyến mãi
	 */
	public static String[] capNhatCmbMaCTKM() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		list.add("Không có");
		for (ChuongTrinhKhuyenMai ct : ctkm_dao.getDsCTKM()) {
			if (ct.getTrangThai().equalsIgnoreCase("Đang diễn ra")) list.add(ct.getMaKM());
		}
		s = list.toArray(new String[0]);
		return s;
	}
	
	/**
	 * lấy dữ liệu từ csdl để cập nhật combo box loại
	 * @return mảng chuỗi tên loại
	 */
	private String[] capNhatCmbLoai() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		list.add("Tất cả");
		for (LoaiSanPham ct : loaiSanPham_DAO.getDsLoaiSP()) {
			list.add(ct.getTenLoai());
		}
		s = list.toArray(new String[0]);
		return s;
	}
	
	/**
	 * lấy dữ liệu từ csdl để cập nhật combo box màu sắc
	 * @return mảng chuỗi tên màu sắc
	 */
	private String[] capNhatCmbMauSac() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		list.add("Tất cả");
		for (MauSac ct : mauSac_DAO.getDsMauSac()) {
			list.add(ct.getTenMauSac());
		}
		s = list.toArray(new String[0]);
		return s;
	}
	
	/**
	 * lấy dữ liệu từ csdl để cập nhật combo box kích cỡ
	 * @return mảng chuỗi tên kích cỡ
	 */
	private String[] capNhatCmbKichCo() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		list.add("Tất cả");
		for (KichCo ct : kichCo_DAO.getDsKichCo()) {
			list.add(ct.getTenKichCo());
		}
		s = list.toArray(new String[0]);
		return s;
	}
	
	/**
	 * lấy dữ liệu từ csdl để cập nhật combo box chất liệu
	 * @return mảng chuỗi tên chất liệu
	 */
	private String[] capNhatCmbChatLieu() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		list.add("Tất cả");
		for (ChatLieu cl : chatLieu_DAO.getDsChatLieu()) {
			list.add(cl.getTenChatLieu());
		}
		s = list.toArray(new String[0]);
		return s;
	}
	
	/**
	 * tính tổng tiền thanh toán
	 * @return tổng tiền hóa đơn - tổng tiền hóa đơn * phần trăm khuyến mãi
	 */
	private double tinhTongThanhToan() {
		if (ctkm==null) return tinhTongTienHD();
		return tinhTongTienHD()*((100-ctkm.getPhanTramKhuyenMai())/100);
	}
	
	/**
	 * tính tiền thừa khi nhập tiền khách trả
	 * @return tiền khách trả - tổng tiền thanh toán
	 */
	private double tinhTienThua() {
		if (txt_tienKhachTra.getText().isEmpty()) return 0;
		String tv = GeneratorID.tiengVietFull();
		String m = txt_tienKhachTra.getText().replaceAll(tv+"|\\s", "");
		if (m.equals("")) return 0;
		return Double.parseDouble(m)-tinhTongThanhToan();
	}
	
	/**
	 * thay đổi gía trị của txt tiền thừa khi nhập tiền khách trả
	 */
	private void thayDoiGiaTriTienThua() {
		double tienThua = tinhTienThua();
		if (tienThua<0) lbl_kqtienthua.setForeground(Color.red);
		else lbl_kqtienthua.setForeground(Color.black);
		lbl_kqtienthua.setText(df.format(tienThua));
	}

	
	/**
	 * xóa hết dữ liệu trên bảng
	 * @param t bảng cần xóa dữ liệu
	 */
	private void xoaDuLieuCuaBang(JTable t) {
		DefaultTableModel dm = (DefaultTableModel) t.getModel();
		dm.getDataVector().removeAllElements();
		t.revalidate();
	}
	
	/**
	 * cập nhật dữ liệu cho bảng danh sách sản phẩm
	 * @param ds là danh sách sản phẩm
	 */
	private void capNhatDuLieuCuaBang(List<SanPham> ds) {
		xoaDuLieuCuaBang(tbl_dssp);
		for (SanPham sp : ds) {
			ImageIcon im = new ImageIcon(sp.getHinhAnh());
			Object data[] = {im, sp.getMaSP(), sp.getTenSP(), sp.getLoaiSP().getTenLoai(), df.format(sp.getGiaBan()), sp.getSoLuongTon(), sp.getChatLieu().getTenChatLieu(), sp.getMauSac().getTenMauSac(), sp.getKichCo().getTenKichCo()};
			model_dssp.addRow(data);
		}
	}
	
	/**
	 * kiểm tra dữ liệu khi thanh toán
	 * @return
	 */
	private boolean kiemTraDuLieuThanhToan() {
		if (model_giohang.getRowCount()==0) {
			JOptionPane.showMessageDialog(null, "Giỏ hàng trống!");
			return false;
		}
		if (txt_tienKhachTra.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Nhập tiền khách trả!");
			txt_tienKhachTra.selectAll();
			txt_tienKhachTra.requestFocus();
			return false;
		} 
		else if (!txt_tienKhachTra.getText().trim().matches("\\d+\\.?\\d*")) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập số thực dương!");
			txt_tienKhachTra.selectAll();
			txt_tienKhachTra.requestFocus();
			return false;
		}
		else if (Double.parseDouble(txt_tienKhachTra.getText())<dinhDangTien(lbl_kqtongthanhtoan.getText())) {
			JOptionPane.showMessageDialog(null, "Tiền khách trả còn thiếu!");
			txt_tienKhachTra.selectAll();
			txt_tienKhachTra.requestFocus();
			return false;
		}
		return true;
	}
	
	/**
	 * kiểm tra dữ liệu khi đặt hàng
	 * @return
	 */
	private boolean kiemTraDuLieuDatHang() {
		if (model_giohang.getRowCount()==0) {
			JOptionPane.showMessageDialog(null, "Giỏ hàng trống!");
			return false;
		}
		if (khachHang.getMaKH().equals("KHACHLE")) {
			JOptionPane.showMessageDialog(null, "Cần thông tin khách hàng để đặt hàng!");
			return false;
		}
		return true;
	}
	private void xuLyNut() {
		tbl_gioHang.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRender());
        tbl_gioHang.getColumnModel().getColumn(6).setCellEditor(new TableActionCellEditor(event));
	}
	private void xuLyAnh() {
		tbl_dssp.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                setHorizontalAlignment(SwingConstants.CENTER);          
                
                Component com = super.getTableCellRendererComponent(tbl_gioHang, o, bln, bln1, i, i1);
                
                if (o == null) {
                	Icon image = new ImageIcon(ManHinhLapHoaDon.class.getResource("/images/null.jpg"));
            		Image ima = ((ImageIcon) image).getImage();
            		Image newimg = ima.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            		image = new ImageIcon(newimg);
            		JLabel label = new JLabel(image);
                	label.setHorizontalAlignment(JLabel.CENTER);
            		label.setOpaque(true);
                	label.setBackground(com.getBackground());
                	return label;
                } 
                else {
                	if (o instanceof Icon) {
                		Icon image = (ImageIcon)o;
                		Image ima = ((ImageIcon) image).getImage(); 
                		Image newimg = ima.getScaledInstance(70, 70,Image.SCALE_SMOOTH); 
                		image = new ImageIcon(newimg); 
                		JLabel label = new JLabel(image);
                		label.setHorizontalAlignment(JLabel.CENTER);
                		label.setOpaque(true);
                		label.setBackground(com.getBackground());
                		return label;
                	} 
                	else {
                		Icon image = new ImageIcon(ManHinhLapHoaDon.class.getResource("/images/null.jpg"));
                		Image ima = ((ImageIcon) image).getImage();
                		Image newimg = ima.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                		image = new ImageIcon(newimg);
                		JLabel label = new JLabel(image);
                    	label.setHorizontalAlignment(JLabel.CENTER);
                		label.setOpaque(true);
                    	label.setBackground(com.getBackground());
                    	return label;
                	}
                }
                
            }
        });
		 tbl_gioHang.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
	            @Override
	            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
	                setHorizontalAlignment(SwingConstants.CENTER);          
	                
	                Component com = super.getTableCellRendererComponent(tbl_gioHang, o, bln, bln1, i, i1);
	                
	                if (o == null) {
	                	Icon image = new ImageIcon(ManHinhLapHoaDon.class.getResource("/images/null.jpg"));
	            		Image ima = ((ImageIcon) image).getImage();
	            		Image newimg = ima.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
	            		image = new ImageIcon(newimg);
	            		JLabel label = new JLabel(image);
	                	label.setHorizontalAlignment(JLabel.CENTER);
	            		label.setOpaque(true);
	                	label.setBackground(com.getBackground());
	                	return label;
	                } 
	                else {
	                	if (o instanceof Icon) {
	                		Icon image = (ImageIcon)o;
	                		Image ima = ((ImageIcon) image).getImage(); 
	                		Image newimg = ima.getScaledInstance(70, 70,Image.SCALE_SMOOTH); 
	                		image = new ImageIcon(newimg); 
	                		JLabel label = new JLabel(image);
	                		label.setHorizontalAlignment(JLabel.CENTER);
	                		label.setOpaque(true);
	                		label.setBackground(com.getBackground());
	                		return label;
	                	} 
	                	else {
	                		Icon image = new ImageIcon(ManHinhLapHoaDon.class.getResource("/images/null.jpg"));
	                		Image ima = ((ImageIcon) image).getImage();
	                		Image newimg = ima.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
	                		image = new ImageIcon(newimg);
	                		JLabel label = new JLabel(image);
	                    	label.setHorizontalAlignment(JLabel.CENTER);
	                		label.setOpaque(true);
	                    	label.setBackground(com.getBackground());
	                    	return label;
	                	}
	                }
	                
	            }
	        });
	}
}
