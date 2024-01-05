package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.fasterxml.jackson.databind.ser.std.SerializableSerializer;

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
import entities.HoaDon;
import entities.KhachHang;
import entities.KichCo;
import entities.LoaiSanPham;
import entities.MauSac;
import entities.NhaCungCap;
import entities.SanPham;
import qrCode.QRScannerProduct;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.Icon;

public class ManHinhCapNhatSanPham extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JButton btnThemSanPham;
	private JButton btnXoaTrangSanPham;
	private JButton btnSuaSanPham;
	private JButton btnThemAnhSanPham;
	private JScrollPane scrDanhSachSanPham;
	private JTable tbl_DanhSachSanPham;
	private static DefaultTableModel model_DanhSachSanPham;

	private JLabel txtMaSanPham;

	private static DecimalFormat df;
	private static DateTimeFormatter dtf;

	private JComboBox cmbTim_LoaiSanPham;
	private JLabel lblTim_LoaiSanPham;
	private JLabel lblTim_MauSacSanPham;
	private JComboBox cmbTim_MauSacSanPham;
	private JComboBox cmbTim_KichCoSanPham;
	private JLabel lblTim_ChatLieuSanPham;
	private JComboBox cmbTim_ChatLieuSanPham;
	private JButton btnTim_SanPham;
	private JTextField txtTenSanPham;
	private JTextField txtGiaNhapSanPham;
	private JLabel lblHinh;
	private JComboBox cmbMauSacSanPham;
	private JComboBox cmbLoaiSanPham;
	private JComboBox cmbKichCoSanPham;
	private JComboBox cmbChatLieuSanPham;
	public static JComboBox cmbNhaCungCap;
	private JComboBox cmbTinhTrangSanPham;
	private UtilDateModel model_date;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl dpi_SanPham;
	private JTextField txtGiaBanSanPham;
	private JTextField txtSoLuongSanPham;
	private static NhaCungCap_DAO nhaCungCap_DAO = new NhaCungCap_DAO();
	private static SanPham_DAO sanPham_DAO = new SanPham_DAO();
	private LoaiSanPham_DAO loaiSanPham_DAO = new LoaiSanPham_DAO();
	private MauSac_DAO mauSac_DAO = new MauSac_DAO();
	private KichCo_DAO kichCo_DAO = new KichCo_DAO();
	private ChatLieu_DAO chatLieu_DAO = new ChatLieu_DAO();
	private ArrayList<SanPham> danhSachSanPham = new ArrayList<SanPham>();
	private JFileChooser fileChooser;
	private String filePath;
	private byte[] hinhAnh;
	private JTextField txtTim_MaSanPham;
	private JTextField txtTim_TenSanPham;
	private JLabel lblTim_KichCoSanPham;

	private JButton btnQR;
	private QRScannerProduct qrScannerProduct;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManHinhCapNhatSanPham frame = new ManHinhCapNhatSanPham();
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
	public ManHinhCapNhatSanPham() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setSize(1259, 864);

		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		fileChooser = new JFileChooser("./HinhAnhSanPham/");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp");
		fileChooser.setFileFilter(filter);

		df = new DecimalFormat("#,###");
		dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		JPanel pnlManHinhThaoTac = new JPanel();
		pnlManHinhThaoTac.setBackground(new Color(255, 255, 255));
		pnlManHinhThaoTac.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true),
				"Cập nhật thông tin sản phẩm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pnlManHinhThaoTac.setBounds(10, 50, 1239, 300);
		add(pnlManHinhThaoTac);
		pnlManHinhThaoTac.setLayout(null);

		JPanel pnlThaoTac = new JPanel();
		pnlThaoTac.setLayout(null);
		pnlThaoTac.setBackground(new Color(255, 250, 240));
		pnlThaoTac.setBounds(10, 60, 1219, 230);
		pnlManHinhThaoTac.add(pnlThaoTac);

		JLabel lblMaSanPham = new JLabel("Mã sản phẩm:");
		lblMaSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaSanPham.setFont(new Font("Arial", Font.PLAIN, 11));
		lblMaSanPham.setBounds(10, 15, 80, 20);
		pnlThaoTac.add(lblMaSanPham);

		txtMaSanPham = new JLabel("");
		txtMaSanPham.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		txtMaSanPham.setBounds(90, 15, 160, 20);
		pnlThaoTac.add(txtMaSanPham);

		JLabel lblTenSanPham = new JLabel("Tên sản phẩm:");
		lblTenSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenSanPham.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTenSanPham.setBounds(311, 15, 80, 20);
		pnlThaoTac.add(lblTenSanPham);

		JLabel lblMauSacSanPham = new JLabel("Màu sắc:");
		lblMauSacSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		lblMauSacSanPham.setFont(new Font("Arial", Font.PLAIN, 11));
		lblMauSacSanPham.setBounds(518, 83, 60, 20);
		pnlThaoTac.add(lblMauSacSanPham);

		JLabel lblNgayNhapSanPham = new JLabel("Ngày nhập:");
		lblNgayNhapSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgayNhapSanPham.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNgayNhapSanPham.setBounds(10, 120, 80, 20);
		pnlThaoTac.add(lblNgayNhapSanPham);

		btnSuaSanPham = new JButton("Sửa");
		btnSuaSanPham.setBounds(437, 188, 100, 30);
		pnlThaoTac.add(btnSuaSanPham);
		btnSuaSanPham.setFont(new Font("Arial", Font.BOLD, 14));
		btnSuaSanPham.setBackground(new Color(244, 164, 96));

		txtTenSanPham = new JTextField();
		txtTenSanPham.setBackground(new Color(255, 250, 240));
		txtTenSanPham.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtTenSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		txtTenSanPham.setBounds(391, 15, 317, 20);
		pnlThaoTac.add(txtTenSanPham);
		txtTenSanPham.setColumns(10);

		model_date = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(model_date, p);
		model_date.setValue(new Date(100, 00, 01));
		dpi_SanPham = new JDatePickerImpl(datePanel, new custom.DateLabelFormatter());
		dpi_SanPham.setBackground(new Color(255, 255, 255));
		dpi_SanPham.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		dpi_SanPham.setBounds(90, 117, 160, 27);
		dpi_SanPham.getJDateInstantPanel().setShowYearButtons(true);
		dpi_SanPham.getJFormattedTextField().setText("2023-01-01");
		dpi_SanPham.setButtonFocusable(false);
		// datePicker.getModel().setDate(2000, 1, 1);
		pnlThaoTac.add(dpi_SanPham);

		String[] mauSac = capNhatCmbMauSac();
		cmbMauSacSanPham = new JComboBox();
		cmbMauSacSanPham.setModel(new DefaultComboBoxModel(mauSac));
		cmbMauSacSanPham.setBackground(new Color(245, 222, 179));
		cmbMauSacSanPham.setBounds(578, 82, 130, 22);
		pnlThaoTac.add(cmbMauSacSanPham);

		JLabel lblNhaCungCap = new JLabel("Nhà cung cấp:");
		lblNhaCungCap.setHorizontalAlignment(SwingConstants.LEFT);
		lblNhaCungCap.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNhaCungCap.setBounds(10, 50, 80, 20);
		pnlThaoTac.add(lblNhaCungCap);

		JLabel lblLoaiSanPham = new JLabel("Loại sản phẩm:");
		lblLoaiSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		lblLoaiSanPham.setFont(new Font("Arial", Font.PLAIN, 11));
		lblLoaiSanPham.setBounds(776, 84, 80, 20);
		pnlThaoTac.add(lblLoaiSanPham);

		String[] loai = capNhatCmbLoai();
		cmbLoaiSanPham = new JComboBox();
		cmbLoaiSanPham.setModel(new DefaultComboBoxModel(loai));
		cmbLoaiSanPham.setBackground(new Color(245, 222, 179));
		cmbLoaiSanPham.setBounds(866, 83, 100, 22);
		pnlThaoTac.add(cmbLoaiSanPham);

		JLabel lblSoLuongSanPham = new JLabel("Số lượng:");
		lblSoLuongSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		lblSoLuongSanPham.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSoLuongSanPham.setBounds(776, 15, 60, 20);
		pnlThaoTac.add(lblSoLuongSanPham);

		JLabel lblGiaNhapSanPham = new JLabel("Giá nhập:");
		lblGiaNhapSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		lblGiaNhapSanPham.setFont(new Font("Arial", Font.PLAIN, 11));
		lblGiaNhapSanPham.setBounds(518, 45, 60, 20);
		pnlThaoTac.add(lblGiaNhapSanPham);

		txtGiaNhapSanPham = new JTextField();
		txtGiaNhapSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		txtGiaNhapSanPham.setColumns(10);
		txtGiaNhapSanPham.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtGiaNhapSanPham.setBackground(new Color(255, 250, 240));
		txtGiaNhapSanPham.setBounds(578, 45, 130, 20);
		pnlThaoTac.add(txtGiaNhapSanPham);

		btnXoaTrangSanPham = new JButton("Xóa trắng");
		btnXoaTrangSanPham.setFont(new Font("Arial", Font.BOLD, 14));
		btnXoaTrangSanPham.setBackground(new Color(255, 0, 0));
		btnXoaTrangSanPham.setBounds(547, 188, 100, 30);
		pnlThaoTac.add(btnXoaTrangSanPham);

		btnThemSanPham = new JButton("Thêm");
		btnThemSanPham.setBounds(324, 188, 100, 30);
		pnlThaoTac.add(btnThemSanPham);
		btnThemSanPham.setFont(new Font("Arial", Font.BOLD, 14));
		btnThemSanPham.setBackground(new Color(0, 128, 0));

		JLabel lblGiaBanSanPham = new JLabel("Giá bán:");
		lblGiaBanSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		lblGiaBanSanPham.setFont(new Font("Arial", Font.PLAIN, 11));
		lblGiaBanSanPham.setBounds(311, 50, 80, 20);
		pnlThaoTac.add(lblGiaBanSanPham);

		txtGiaBanSanPham = new JTextField();
		txtGiaBanSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		txtGiaBanSanPham.setColumns(10);
		txtGiaBanSanPham.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtGiaBanSanPham.setBackground(new Color(255, 250, 240));
		txtGiaBanSanPham.setBounds(392, 45, 116, 20);
		pnlThaoTac.add(txtGiaBanSanPham);

		String[] chatlieu = capNhatCmbChatLieu();
		cmbChatLieuSanPham = new JComboBox();
		cmbChatLieuSanPham.setModel(new DefaultComboBoxModel(chatlieu));
		cmbChatLieuSanPham.setBackground(new Color(245, 222, 179));
		cmbChatLieuSanPham.setBounds(91, 84, 159, 22);
		pnlThaoTac.add(cmbChatLieuSanPham);

		JLabel lblChatLieuSanPham = new JLabel("Chất liệu:");
		lblChatLieuSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		lblChatLieuSanPham.setFont(new Font("Arial", Font.PLAIN, 11));
		lblChatLieuSanPham.setBounds(10, 85, 80, 20);
		pnlThaoTac.add(lblChatLieuSanPham);

		String[] kc = capNhatCmbKichCo();
		cmbKichCoSanPham = new JComboBox();
		cmbKichCoSanPham.setModel(new DefaultComboBoxModel(kc));
		cmbKichCoSanPham.setBackground(new Color(245, 222, 179));
		cmbKichCoSanPham.setBounds(367, 82, 96, 22);
		pnlThaoTac.add(cmbKichCoSanPham);

		JLabel lblKichCoSanPham = new JLabel("Kích cỡ:");
		lblKichCoSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		lblKichCoSanPham.setFont(new Font("Arial", Font.PLAIN, 11));
		lblKichCoSanPham.setBounds(311, 85, 80, 20);
		pnlThaoTac.add(lblKichCoSanPham);

		txtSoLuongSanPham = new JTextField();
		txtSoLuongSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		txtSoLuongSanPham.setColumns(10);
		txtSoLuongSanPham.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtSoLuongSanPham.setBackground(new Color(255, 250, 240));
		txtSoLuongSanPham.setBounds(836, 15, 100, 20);
		pnlThaoTac.add(txtSoLuongSanPham);

		JLabel lblTinhTrangSanPham = new JLabel("Tình trạng:");
		lblTinhTrangSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		lblTinhTrangSanPham.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTinhTrangSanPham.setBounds(776, 50, 60, 20);
		pnlThaoTac.add(lblTinhTrangSanPham);

		String[] ncc = capNhatCmbNhaCungCap();
		cmbNhaCungCap = new JComboBox();
		cmbNhaCungCap.setBackground(new Color(255, 255, 255));
		cmbNhaCungCap.setModel(new DefaultComboBoxModel(ncc));
		cmbNhaCungCap.setBounds(90, 49, 160, 22);
		pnlThaoTac.add(cmbNhaCungCap);

		btnThemAnhSanPham = new JButton("Thêm ảnh");
		btnThemAnhSanPham.setBounds(1079, 180, 100, 20);
		pnlThaoTac.add(btnThemAnhSanPham);

		// hình
		Icon image = new ImageIcon(ManHinhCapNhatSanPham.class.getResource("/images/null.jpg"));
		Image ima = ((ImageIcon) image).getImage();
		Image newimg = ima.getScaledInstance(140, 140, Image.SCALE_SMOOTH);

		image = new ImageIcon(newimg);
		lblHinh = new JLabel(image);
		lblHinh.setBounds(1049, 10, 160, 160);
		pnlThaoTac.add(lblHinh);
		lblHinh.setHorizontalAlignment(JLabel.CENTER);
		lblHinh.setOpaque(true);

		cmbTinhTrangSanPham = new JComboBox();
		cmbTinhTrangSanPham.setBackground(new Color(255, 255, 255));
		cmbTinhTrangSanPham
				.setModel(new DefaultComboBoxModel(new String[] { "Đang kinh doanh", "Hết hàng", "Ngừng kinh doanh" }));
		cmbTinhTrangSanPham.setBounds(836, 50, 130, 22);
		pnlThaoTac.add(cmbTinhTrangSanPham);

		JButton btn_themCL = new JButton("+");
		btn_themCL.setBounds(257, 84, 40, 22);
		pnlThaoTac.add(btn_themCL);
		btn_themCL.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = new JDialog();
				dialog.setTitle("Thêm Chất Liệu");
				dialog.setSize(300, 150);

				GroupLayout layout = new GroupLayout(dialog.getContentPane());
				dialog.setLayout(layout);

				JLabel lblMaChatLieu = new JLabel("Mã chất liệu:");
				JTextField txtMaChatLieu = new JTextField();
				txtMaChatLieu.setEnabled(false);

				JLabel lblTenChatLieu = new JLabel("Tên chất liệu:");
				JTextField txtTenChatLieu = new JTextField();

				JButton btnDong = new JButton("Đóng");
				btnDong.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dialog.dispose();
					}
				});
				JButton btnLuuChatLieu = new JButton("Lưu");
				btnLuuChatLieu.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String maChatLieu = chatLieu_DAO.taoMaChatLieu();// txtMaChatLieu.getText();
						String tenChatLieu = txtTenChatLieu.getText();
						ChatLieu chatLieu = new ChatLieu(maChatLieu, tenChatLieu);
						if (chatLieu_DAO.themChatLieu(chatLieu)) {
							String[] chatLieu1 = capNhatCmbChatLieu();
							cmbChatLieuSanPham.setModel(new DefaultComboBoxModel(chatLieu1));
							JOptionPane.showMessageDialog(null, "Thêm chất liệu mới thành công");
							dialog.dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Lỗi thêm!");
						}
					}

				});
				layout.setAutoCreateGaps(true);
				layout.setAutoCreateContainerGaps(true);

				GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
				hGroup.addGroup(layout.createParallelGroup().addComponent(lblMaChatLieu).addComponent(lblTenChatLieu)
						.addComponent(btnDong));
				hGroup.addGroup(layout.createParallelGroup().addComponent(txtMaChatLieu).addComponent(txtTenChatLieu)
						.addComponent(btnLuuChatLieu));
				layout.setHorizontalGroup(hGroup);

				GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
				vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblMaChatLieu)
						.addComponent(txtMaChatLieu));
				vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblTenChatLieu)
						.addComponent(txtTenChatLieu));
				vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(btnDong)
						.addComponent(btnLuuChatLieu));
				layout.setVerticalGroup(vGroup);

				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
			}
		});

		JButton btn_themKC = new JButton("+");
		btn_themKC.setBounds(468, 83, 40, 22);
		pnlThaoTac.add(btn_themKC);
		btn_themKC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JDialog dialog = new JDialog();
				dialog.setTitle("Thêm Kích Cỡ");
				dialog.setSize(300, 150);

				GroupLayout layout = new GroupLayout(dialog.getContentPane());
				dialog.setLayout(layout);

				JLabel lblMaKichCo = new JLabel("Mã kích cỡ:");
				JTextField txtMaKichCo = new JTextField();
				txtMaKichCo.setEnabled(false);

				JLabel lblTenKichCo = new JLabel("Tên kích cỡ:");
				JTextField txtTenKichCo = new JTextField();

				JButton btnDong = new JButton("Đóng");
				btnDong.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dialog.dispose();
					}
				});
				JButton btnLuuKichCo = new JButton("Lưu");
				btnLuuKichCo.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String maKichCo = kichCo_DAO.taoMaKichCo();// txtMaKichCo.getText();
						String tenKichCo = txtTenKichCo.getText();
						KichCo kichCo1 = new KichCo(maKichCo, tenKichCo);

						if (kichCo_DAO.themKichCo(kichCo1)) {
							String[] kc = capNhatCmbKichCo();
							cmbKichCoSanPham.setModel(new DefaultComboBoxModel(kc));
							JOptionPane.showMessageDialog(null, "Thêm kích cỡ mới thành công");
							dialog.dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Lỗi thêm!");
						}
					}
				});
				layout.setAutoCreateGaps(true);
				layout.setAutoCreateContainerGaps(true);

				GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
				hGroup.addGroup(layout.createParallelGroup().addComponent(lblMaKichCo).addComponent(lblTenKichCo)
						.addComponent(btnDong));
				hGroup.addGroup(layout.createParallelGroup().addComponent(txtMaKichCo).addComponent(txtTenKichCo)
						.addComponent(btnLuuKichCo));
				layout.setHorizontalGroup(hGroup);

				GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
				vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblMaKichCo)
						.addComponent(txtMaKichCo));
				vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblTenKichCo)
						.addComponent(txtTenKichCo));
				vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(btnDong)
						.addComponent(btnLuuKichCo));
				layout.setVerticalGroup(vGroup);

				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
			}
		});

		JButton btn_themMS = new JButton("+");
		btn_themMS.setBounds(713, 83, 40, 22);
		pnlThaoTac.add(btn_themMS);
		btn_themMS.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JDialog dialog = new JDialog();
				dialog.setTitle("Thêm Màu Sắc");
				dialog.setSize(300, 150);

				GroupLayout layout = new GroupLayout(dialog.getContentPane());
				dialog.setLayout(layout);

				JLabel lblMaMauSac = new JLabel("Mã màu sắc:");
				JTextField txtMaMauSac = new JTextField();
				txtMaMauSac.setEnabled(false);

				JLabel lblTenMauSac = new JLabel("Tên màu sắc:");
				JTextField txtTenMauSac = new JTextField();

				JButton btnDong = new JButton("Đóng");
				btnDong.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dialog.dispose();
					}
				});
				JButton btnLuuMauSac = new JButton("Lưu");
				btnLuuMauSac.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String maMauSac = mauSac_DAO.taoMaMauSac();// txtMaMauSac.getText();
						String tenMauSac = txtTenMauSac.getText();
						MauSac mauSac1 = new MauSac(maMauSac, tenMauSac);

						if (mauSac_DAO.themMauSac(mauSac1)) {
							String[] ms = capNhatCmbMauSac();
							cmbMauSacSanPham.setModel(new DefaultComboBoxModel(ms));

							JOptionPane.showMessageDialog(null, "Thêm màu sắc mới thành công");
							dialog.dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Lỗi thêm!");
						}
					}
				});
				layout.setAutoCreateGaps(true);
				layout.setAutoCreateContainerGaps(true);

				GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
				hGroup.addGroup(layout.createParallelGroup().addComponent(lblMaMauSac).addComponent(lblTenMauSac)
						.addComponent(btnDong));
				hGroup.addGroup(layout.createParallelGroup().addComponent(txtMaMauSac).addComponent(txtTenMauSac)
						.addComponent(btnLuuMauSac));
				layout.setHorizontalGroup(hGroup);

				GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
				vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblMaMauSac)
						.addComponent(txtMaMauSac));
				vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblTenMauSac)
						.addComponent(txtTenMauSac));
				vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(btnDong)
						.addComponent(btnLuuMauSac));
				layout.setVerticalGroup(vGroup);

				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
			}
		});

		JButton btn_themLoai = new JButton("+");
		btn_themLoai.setBounds(968, 82, 40, 22);
		pnlThaoTac.add(btn_themLoai);
		btn_themLoai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JDialog dialog = new JDialog();
				dialog.setTitle("Thêm Loại Sản Phẩm");
				dialog.setSize(300, 150);

				GroupLayout layout = new GroupLayout(dialog.getContentPane());
				dialog.setLayout(layout);

				JLabel lblMaLoai = new JLabel("Mã loại:");
				JTextField txtMaLoai = new JTextField();
				txtMaLoai.setEnabled(false);

				JLabel lblTenLoai = new JLabel("Tên loại:");
				JTextField txtTenLoai = new JTextField();

				JButton btnDong = new JButton("Đóng");
				btnDong.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dialog.dispose();
					}
				});
				JButton btnLuuLoai = new JButton("Lưu");
				btnLuuLoai.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String maLoai = loaiSanPham_DAO.taoMaLoai();// txtMaLoai.getText();
						String tenLoai = txtTenLoai.getText();
						LoaiSanPham loai1 = new LoaiSanPham(maLoai, tenLoai);

						if (loaiSanPham_DAO.themLoai(loai1)) {
							String[] loaisp = capNhatCmbLoai();
							cmbLoaiSanPham.setModel(new DefaultComboBoxModel(loaisp));
							JOptionPane.showMessageDialog(null, "Thêm loại sản phẩm mới thành công");
							dialog.dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Lỗi thêm!");
						}
					}
				});
				layout.setAutoCreateGaps(true);
				layout.setAutoCreateContainerGaps(true);

				GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
				hGroup.addGroup(layout.createParallelGroup().addComponent(lblMaLoai).addComponent(lblTenLoai)
						.addComponent(btnDong));
				hGroup.addGroup(layout.createParallelGroup().addComponent(txtMaLoai).addComponent(txtTenLoai)
						.addComponent(btnLuuLoai));
				layout.setHorizontalGroup(hGroup);

				GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
				vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblMaLoai)
						.addComponent(txtMaLoai));
				vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblTenLoai)
						.addComponent(txtTenLoai));
				vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(btnDong)
						.addComponent(btnLuuLoai));
				layout.setVerticalGroup(vGroup);

				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
			}
		});

		JLabel lblTim_MaSanPham = new JLabel("Mã :");
		lblTim_MaSanPham.setBounds(10, 19, 40, 30);
		pnlManHinhThaoTac.add(lblTim_MaSanPham);

		JPanel timKiem_1 = new JPanel();
		timKiem_1.setLayout(null);
		timKiem_1.setBorder(new RoundedCornerBorder());
		timKiem_1.setBackground(Color.WHITE);
		timKiem_1.setBounds(50, 19, 150, 30);
		pnlManHinhThaoTac.add(timKiem_1);

		txtTim_MaSanPham = new JTextField();
		txtTim_MaSanPham.setForeground(Color.GRAY);
		txtTim_MaSanPham.setEditable(false);
		txtTim_MaSanPham.setColumns(10);
		txtTim_MaSanPham.setBorder(null);
		txtTim_MaSanPham.setBackground(Color.WHITE);
		txtTim_MaSanPham.setBounds(10, 3, 130, 24);
		timKiem_1.add(txtTim_MaSanPham);
		txtTim_MaSanPham.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				txtTim_MaSanPham.setText("");
				txtTim_MaSanPham.setForeground(Color.BLACK);
				txtTim_MaSanPham.setEditable(true);
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
		JLabel lblTim_TenSanPham = new JLabel("Tên :");
		lblTim_TenSanPham.setBounds(210, 19, 50, 30);
		pnlManHinhThaoTac.add(lblTim_TenSanPham);

		JPanel timKiem = new JPanel();
		timKiem.setLayout(null);
		timKiem.setBorder(new RoundedCornerBorder());
		timKiem.setBackground(Color.WHITE);
		timKiem.setBounds(260, 19, 200, 30);
		pnlManHinhThaoTac.add(timKiem);

		txtTim_TenSanPham = new JTextField();
		txtTim_TenSanPham.setForeground(Color.GRAY);
		txtTim_TenSanPham.setEditable(false);
		txtTim_TenSanPham.setColumns(10);
		txtTim_TenSanPham.setBorder(null);
		txtTim_TenSanPham.setBackground(Color.WHITE);
		txtTim_TenSanPham.setBounds(10, 3, 180, 24);
		timKiem.add(txtTim_TenSanPham);
		txtTim_TenSanPham.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				txtTim_TenSanPham.setText("");
				txtTim_TenSanPham.setForeground(Color.BLACK);
				txtTim_TenSanPham.setEditable(true);
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
		cmbTim_LoaiSanPham = new JComboBox();
		String[] timLoai = capNhatCmbTimLoai();
		cmbTim_LoaiSanPham.setModel(new DefaultComboBoxModel(timLoai));
		cmbTim_LoaiSanPham.setBackground(Color.WHITE);
		cmbTim_LoaiSanPham.setBounds(500, 19, 101, 30);
		pnlManHinhThaoTac.add(cmbTim_LoaiSanPham);

		lblTim_LoaiSanPham = new JLabel("Loại:");
		lblTim_LoaiSanPham.setBounds(470, 19, 30, 30);
		pnlManHinhThaoTac.add(lblTim_LoaiSanPham);

		lblTim_MauSacSanPham = new JLabel("Màu sắc:");
		lblTim_MauSacSanPham.setBounds(611, 19, 50, 30);
		pnlManHinhThaoTac.add(lblTim_MauSacSanPham);

		cmbTim_MauSacSanPham = new JComboBox();
		String[] timMauSac = capNhatCmbTimMauSac();
		cmbTim_MauSacSanPham.setModel(new DefaultComboBoxModel(timMauSac));
		cmbTim_MauSacSanPham.setBackground(Color.WHITE);
		cmbTim_MauSacSanPham.setBounds(661, 19, 80, 30);
		pnlManHinhThaoTac.add(cmbTim_MauSacSanPham);

		lblTim_KichCoSanPham = new JLabel("Kích cỡ:");
		lblTim_KichCoSanPham.setBounds(751, 19, 50, 30);
		pnlManHinhThaoTac.add(lblTim_KichCoSanPham);

		cmbTim_KichCoSanPham = new JComboBox();
		String[] timKichCo = capNhatCmbTimKichCo();
		cmbTim_KichCoSanPham.setModel(new DefaultComboBoxModel(timKichCo));
		cmbTim_KichCoSanPham.setBackground(Color.WHITE);
		cmbTim_KichCoSanPham.setBounds(792, 19, 70, 30);
		pnlManHinhThaoTac.add(cmbTim_KichCoSanPham);

		lblTim_ChatLieuSanPham = new JLabel("Chất liệu:");
		lblTim_ChatLieuSanPham.setBounds(867, 19, 60, 30);
		pnlManHinhThaoTac.add(lblTim_ChatLieuSanPham);

		cmbTim_ChatLieuSanPham = new JComboBox();
		String[] timChatLieu = capNhatCmbTimChatLieu();
		cmbTim_ChatLieuSanPham.setModel(new DefaultComboBoxModel(timChatLieu));
		cmbTim_ChatLieuSanPham.setBackground(Color.WHITE);
		cmbTim_ChatLieuSanPham.setBounds(916, 19, 100, 30);
		pnlManHinhThaoTac.add(cmbTim_ChatLieuSanPham);

		btnTim_SanPham = new JButton("Tìm");
		btnTim_SanPham.setBackground(new Color(65, 105, 225));
		btnTim_SanPham.setBounds(1020, 19, 70, 30);
		pnlManHinhThaoTac.add(btnTim_SanPham);

		JPanel pnlDanhSachSanPham = new JPanel();
		pnlDanhSachSanPham.setBackground(new Color(255, 255, 255));
		pnlDanhSachSanPham.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true),
				"Danh sách sản phẩm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pnlDanhSachSanPham.setBounds(10, 350, 1239, 504);
		add(pnlDanhSachSanPham);
		pnlDanhSachSanPham.setLayout(null);

		model_DanhSachSanPham = new DefaultTableModel(new Object[][] {

		}, new String[] { "Mã sản phẩm", "Tên", "Giá nhập", "Giá bán", "Ngày nhập", "Loại", "Màu sắc", "Chất liệu",
				"Kích cỡ", "Nhà cung cấp", "Tồn kho", "Tình trạng" }

		) {
			boolean[] canEdit = new boolean[] { false, false, false, false, false, false, false, false, false, false,
					false, false };

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return canEdit[column];
			}
		};

		tbl_DanhSachSanPham = new JTable(model_DanhSachSanPham);
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
				if (JOptionPane.showConfirmDialog(null, "Nhấn OK để thoát khỏi chương trình.", "Thoat",
						JOptionPane.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION) == 0) {
					System.exit(0);
				}
			}
		});

		JLabel lblTenManHinh = new JLabel("CẬP NHẬT THÔNG TIN SẢN PHẨM");
		lblTenManHinh.setFont(new Font("Arial", Font.BOLD, 20));
		lblTenManHinh.setForeground(new Color(100, 149, 237));
		lblTenManHinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenManHinh.setBounds(20, 0, 1209, 50);
		add(lblTenManHinh);

		docDuLieu();
		btnThemSanPham.addActionListener(this);
		btnSuaSanPham.addActionListener(this);
		btnXoaTrangSanPham.addActionListener(this);
		btnThemAnhSanPham.addActionListener(this);
		btnTim_SanPham.addActionListener(this);
		tbl_DanhSachSanPham.addMouseListener(this);

		ManHinhChinh.btnVN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				lblTenManHinh.setText("CẬP NHẬT THÔNG TIN SẢN PHẨM");
				((TitledBorder) pnlManHinhThaoTac.getBorder()).setTitle("Cập nhật thông tin sản phẩm");
				pnlManHinhThaoTac.repaint();
				((TitledBorder) pnlDanhSachSanPham.getBorder()).setTitle("Danh sách sản phẩm");
				pnlDanhSachSanPham.repaint();
				lblTim_MaSanPham.setText("Mã :");
				lblTim_TenSanPham.setText("Tên :");
				lblTim_LoaiSanPham.setText("Loại");
				lblTim_MauSacSanPham.setText("Màu sắc");
				lblTim_KichCoSanPham.setText("Kích cỡ");
				lblTim_ChatLieuSanPham.setText("Chất liệu");
				btnTim_SanPham.setText("Tìm");
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
				btnThemAnhSanPham.setText("Thêm ảnh");
				btnThemSanPham.setText("Thêm");
				btnSuaSanPham.setText("Sửa");
				btnXoaTrangSanPham.setText("Xoá trắng");
				String[] newColumns_ds = { "Mã sản phẩm", "Tên", "Giá nhập", "Giá bán", "Ngày nhập", "Loại", "Màu sắc",
						"Chất liệu", "Kích cỡ", "Nhà cung cấp", "Tồn kho", "Tình trạng" };
				model_DanhSachSanPham.setColumnIdentifiers(newColumns_ds);

				cmbTinhTrangSanPham.setModel(
						new DefaultComboBoxModel<>(new String[] { "Đang kinh doanh", "Hết hàng", "Ngừng kinh doanh" }));
			}
		});

		ManHinhChinh.btnEN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblTenManHinh.setText("UPDATE PRODUCT INFORMATION");
				((TitledBorder) pnlManHinhThaoTac.getBorder()).setTitle("Update product information");
				pnlManHinhThaoTac.repaint();
				((TitledBorder) pnlDanhSachSanPham.getBorder()).setTitle("Product List");
				pnlDanhSachSanPham.repaint();

				lblTim_MaSanPham.setText("Id :");
				lblTim_TenSanPham.setText("Name :");
				lblTim_LoaiSanPham.setText("Type");
				lblTim_MauSacSanPham.setText("Color");
				lblTim_KichCoSanPham.setText("Size");
				lblTim_ChatLieuSanPham.setText("Material");
				btnTim_SanPham.setText("Find");
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
				btnThemAnhSanPham.setText("Add photo");
				btnThemSanPham.setText("Add");
				btnSuaSanPham.setText("Edit");
				btnXoaTrangSanPham.setText("Clear");
				String[] newColumns_ds = { "Product Id", "Name", "Import price", "Sales price", "Import date", "Type",
						"Color", "Material", "Size", "Vendor", "Inventory", "Condition" };
				model_DanhSachSanPham.setColumnIdentifiers(newColumns_ds);
				cmbTinhTrangSanPham.setModel(
						new DefaultComboBoxModel<>(new String[] { "In business", "Out of stock", "Stop business" }));
			}
		});
		btnQR = new JButton("QR");
		btnQR.setBounds(664, 188, 72, 30);
		pnlThaoTac.add(btnQR);
		qrScannerProduct = new QRScannerProduct();

		btnQR.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog qrDialog = new JDialog();
				qrDialog.setTitle("Cửa sổ QR");
				qrDialog.setSize(500, 600);
				qrDialog.setLocationRelativeTo(null);
				qrDialog.setModal(true);
				qrDialog.getContentPane().add(qrScannerProduct);
				qrDialog.setVisible(true);
			}
		});
		QRScannerProduct.stopButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (QRScannerProduct.result != null) {
					String product = QRScannerProduct.result.getText();
					String[] values = product.split(";");

					QRScannerProduct.ten = values[0];
					QRScannerProduct.soLuong = values[1];
					QRScannerProduct.nhaCungCap = values[2];
					QRScannerProduct.giaBan = values[3];
					QRScannerProduct.giaNhap = values[4];
					QRScannerProduct.chatLieu = values[5];
					QRScannerProduct.kichCo = values[6];
					QRScannerProduct.mauSac = values[7];
					QRScannerProduct.loaiSanPham = values[8];

					txtTenSanPham.setText(QRScannerProduct.ten);
					txtSoLuongSanPham.setText(QRScannerProduct.soLuong);
					cmbNhaCungCap.setSelectedItem(QRScannerProduct.nhaCungCap);
					txtGiaBanSanPham.setText(QRScannerProduct.giaBan);
					txtGiaNhapSanPham.setText(QRScannerProduct.giaNhap);
					// cmbChatLieuSanPham.setSelectedItem(QRScannerProduct.chatLieu);
					// cmbKichCoSanPham.setSelectedItem(QRScannerProduct.kichCo);
					// cmbMauSacSanPham.setSelectedItem(QRScannerProduct.mauSac);
					// cmbLoaiSanPham.setSelectedItem(QRScannerProduct.loaiSanPham);
					if (!containsValue(cmbNhaCungCap, QRScannerProduct.nhaCungCap)) {
						JOptionPane.showMessageDialog(null, "Nhà cung cấp không tồn tại. Hãy thêm nhà cung cấp mới!");
					} else {
						cmbNhaCungCap.setSelectedItem(QRScannerProduct.nhaCungCap);
					}
					
					if (containsValue(cmbChatLieuSanPham, QRScannerProduct.chatLieu)) {
						cmbChatLieuSanPham.setSelectedItem(QRScannerProduct.chatLieu);
					} else {
						int choice = showConfirmationDialog(null,
								"Chất liệu này không có trong hệ thống. Bạn có muốn thêm chất liệu mới?");
						if (choice == JOptionPane.YES_OPTION) {
							String maChatLieu = chatLieu_DAO.taoMaChatLieu();
							String tenChatLieu = QRScannerProduct.chatLieu;
							ChatLieu chatLieu = new ChatLieu(maChatLieu, tenChatLieu);
							if (chatLieu_DAO.themChatLieu(chatLieu)) {
								String[] chatLieu1 = capNhatCmbChatLieu();
								cmbChatLieuSanPham.setModel(new DefaultComboBoxModel(chatLieu1));
								JOptionPane.showMessageDialog(null, "Thêm chất liệu mới thành công");
								cmbChatLieuSanPham.setSelectedItem(QRScannerProduct.chatLieu);
							} else {
								JOptionPane.showMessageDialog(null, "Lỗi thêm!");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Chức năng đã bị hủy bỏ.");
						}
					}

					if (containsValue(cmbKichCoSanPham, QRScannerProduct.kichCo)) {
						cmbKichCoSanPham.setSelectedItem(QRScannerProduct.kichCo);
					} else {
						int choice = showConfirmationDialog(null,
								"Kích cỡ này không có trong hệ thống. Bạn có muốn thêm kích cỡ mới?");
						if (choice == JOptionPane.YES_OPTION) {
							String maKichCo = kichCo_DAO.taoMaKichCo();
							String tenKichCo = QRScannerProduct.kichCo;
							KichCo kichCo1 = new KichCo(maKichCo, tenKichCo);
							if (kichCo_DAO.themKichCo(kichCo1)) {
								String[] kc = capNhatCmbKichCo();
								cmbKichCoSanPham.setModel(new DefaultComboBoxModel(kc));
								JOptionPane.showMessageDialog(null, "Thêm kích cỡ mới thành công");
								cmbKichCoSanPham.setSelectedItem(QRScannerProduct.kichCo);
							} else {
								JOptionPane.showMessageDialog(null, "Lỗi thêm!");
							}

						} else {
							JOptionPane.showMessageDialog(null, "Chức năng đã bị hủy bỏ.");
						}
					}
					if (containsValue(cmbMauSacSanPham, QRScannerProduct.mauSac)) {
						cmbMauSacSanPham.setSelectedItem(QRScannerProduct.mauSac);
					} else {
						int choice1 = showConfirmationDialog(null,
								"Màu sắc này không có trong hệ thống. Bạn có muốn thêm chất liệu mới?");
						if (choice1 == JOptionPane.YES_OPTION) {
							String maMauSac = mauSac_DAO.taoMaMauSac();
							String tenMauSac = QRScannerProduct.mauSac;
							MauSac mauSac1 = new MauSac(maMauSac, tenMauSac);

							if (mauSac_DAO.themMauSac(mauSac1)) {
								String[] ms = capNhatCmbMauSac();
								cmbMauSacSanPham.setModel(new DefaultComboBoxModel(ms));
								JOptionPane.showMessageDialog(null, "Thêm màu sắc mới thành công");
								cmbMauSacSanPham.setSelectedItem(QRScannerProduct.mauSac);
							} else {
								JOptionPane.showMessageDialog(null, "Lỗi thêm!");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Chức năng đã bị hủy bỏ.");
						}

					}

					if (containsValue(cmbLoaiSanPham, QRScannerProduct.loaiSanPham)) {
						cmbLoaiSanPham.setSelectedItem(QRScannerProduct.loaiSanPham);
					} else {
						int choice2 = showConfirmationDialog(null,
								"Loại sản phẩm này không có trong hệ thống. Bạn có muốn thêm chất liệu mới?");
						if (choice2 == JOptionPane.YES_OPTION) {
							String maLoai = loaiSanPham_DAO.taoMaLoai();
							String tenLoai = QRScannerProduct.loaiSanPham;
							LoaiSanPham loai1 = new LoaiSanPham(maLoai, tenLoai);

							if (loaiSanPham_DAO.themLoai(loai1)) {
								String[] loaisp = capNhatCmbLoai();
								cmbLoaiSanPham.setModel(new DefaultComboBoxModel(loaisp));
								JOptionPane.showMessageDialog(null, "Thêm loại sản phẩm mới thành công");
								cmbLoaiSanPham.setSelectedItem(QRScannerProduct.loaiSanPham);
							} else {
								JOptionPane.showMessageDialog(null, "Lỗi thêm!");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Chức năng đã bị hủy bỏ.");
						}

					}
				}

				else {
					JOptionPane.showMessageDialog(null, "Lỗi!");
				}
			}

			private int showConfirmationDialog(Component parentComponent, String message) {
				return JOptionPane.showConfirmDialog(parentComponent, message, "Xác nhận", JOptionPane.YES_NO_OPTION);
			}
		});
	}

	private static boolean containsValue(JComboBox<String> comboBox, String value) {
		for (int i = 0; i < comboBox.getItemCount(); i++) {
			if (comboBox.getItemAt(i).equals(value)) {
				return true;
			}
		}
		return false;
	}

	private void xoaTrangTable(JTable t) {
		DefaultTableModel dm = (DefaultTableModel) t.getModel();
		dm.getDataVector().removeAllElements();
	}

	public void docDuLieu() {
		// TODO Auto-generated method stub
		df = new DecimalFormat("#,###");
		dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		xoaTrangTable(tbl_DanhSachSanPham);
		SanPham_DAO danhSach = new SanPham_DAO();
		List<SanPham> list = danhSach.getDsSanPham();
		for (SanPham sanPham : list) {
			String[] rowData = { sanPham.getMaSP(), sanPham.getTenSP(), Double.toString(sanPham.getGiaNhap()),
					Double.toString(sanPham.getGiaBan()), dtf.format(sanPham.getNgayNhap()).substring(0, 10),
					sanPham.getLoaiSP().getTenLoai(), sanPham.getMauSac().getTenMauSac(),
					sanPham.getChatLieu().getTenChatLieu(), sanPham.getKichCo().getTenKichCo(),
					sanPham.getNhaCungCap().getTenNCC(), Integer.toString(sanPham.getSoLuongTon()),
					sanPham.getTrangThai() };
			model_DanhSachSanPham.addRow(rowData);
		}
		tbl_DanhSachSanPham.setModel(model_DanhSachSanPham);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tbl_DanhSachSanPham.getSelectedRow();
		txtMaSanPham.setText(tbl_DanhSachSanPham.getValueAt(row, 0).toString());
		txtTenSanPham.setText(tbl_DanhSachSanPham.getValueAt(row, 1).toString());
		txtGiaNhapSanPham.setText(tbl_DanhSachSanPham.getValueAt(row, 2).toString());
		txtGiaBanSanPham.setText(tbl_DanhSachSanPham.getValueAt(row, 3).toString());
		String date = tbl_DanhSachSanPham.getValueAt(row, 4).toString().substring(0, 10);
		dpi_SanPham.getJFormattedTextField().setText(date);
		SanPham sanPham = sanPham_DAO.getSanPham(tbl_DanhSachSanPham.getValueAt(row, 0).toString());
		model_date.setValue(new Date(sanPham.getNgayNhap().getYear() - 1900, sanPham.getNgayNhap().getMonthValue() - 1,
				sanPham.getNgayNhap().getDayOfMonth()));
		Icon ic = new ImageIcon(sanPham.getHinhAnh());
		Image ima = ((ImageIcon) ic).getImage();
		Image newimg = ima.getScaledInstance(160, 160, Image.SCALE_SMOOTH);
		ic = new ImageIcon(newimg);
		lblHinh.setIcon(ic);
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
		if (o.equals(btnThemSanPham)) {
			if (validDataSanPham()) {
				String tenSanPham = txtTenSanPham.getText();
				double giaBan = Double.parseDouble(txtGiaBanSanPham.getText());
				double giaNhap = Double.parseDouble(txtGiaNhapSanPham.getText());
				String tenNhaCungCap = cmbNhaCungCap.getSelectedItem().toString();
				String maNhaCungCap = NhaCungCap_DAO.getMaNCC(tenNhaCungCap);
				NhaCungCap nhaCungCap = new NhaCungCap(maNhaCungCap, tenNhaCungCap);

				int soLuong = Integer.parseInt(txtSoLuongSanPham.getText());
				String tinhTrang = cmbTinhTrangSanPham.getSelectedItem().toString();

				Date selectedDate = (Date) dpi_SanPham.getModel().getValue();
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				String mydate = dateFormat.format(selectedDate);
				int nam = Integer.parseInt(mydate.substring(6, 10));
				int thang = Integer.parseInt(mydate.substring(3, 5));
				int ngay = Integer.parseInt(mydate.substring(0, 2));
				LocalDateTime ngayNhap = LocalDateTime.of(nam, thang, ngay, 0, 0);

				String tenloaiSanPham = cmbLoaiSanPham.getSelectedItem().toString();
				String maLoai = LoaiSanPham_DAO.getMaLoaiSanPham(tenloaiSanPham);
				LoaiSanPham loai = new LoaiSanPham(maLoai, tenloaiSanPham);

				String tenMauSac = cmbMauSacSanPham.getSelectedItem().toString();
				String maMauSac = MauSac_DAO.getMaMauSac(tenMauSac);
				MauSac mauSac = new MauSac(maMauSac, tenMauSac);

				String tenChatLieu = cmbChatLieuSanPham.getSelectedItem().toString();
				String maChatLieu = ChatLieu_DAO.getMaChatLieu(tenChatLieu);
				ChatLieu chatLieu = new ChatLieu(maChatLieu, tenChatLieu);

				String tenKichCo = cmbKichCoSanPham.getSelectedItem().toString();
				String maKichCo = KichCo_DAO.getMaKichCo(tenKichCo);
				KichCo kichCo = new KichCo(maKichCo, tenKichCo);

				String maSP = GeneratorID.generateIDSanPham(maLoai, maKichCo);

				byte[] hinhAnh = getImageFromLbl(lblHinh);
				SanPham sanPham = new SanPham(maSP, tenSanPham, giaNhap, giaBan, loai, hinhAnh, kichCo, chatLieu,
						mauSac, soLuong, ngayNhap, tinhTrang, nhaCungCap);
				if (sanPham_DAO.themSanPham(sanPham)) {
					model_DanhSachSanPham.getDataVector().removeAllElements();
					docDuLieu();
					xoaRong();
					ManHinhTimKiemSanPham.resetData();
					JOptionPane.showMessageDialog(null, "Thêm thành công");
				}
			}
		} else if (o.equals(btnSuaSanPham)) {
			int row = tbl_DanhSachSanPham.getSelectedRow();
			if (row >= 0) {
				String tenSanPham = txtTenSanPham.getText();
				double giaBan = Double.parseDouble(txtGiaBanSanPham.getText());
				double giaNhap = Double.parseDouble(txtGiaNhapSanPham.getText());
				String tenNhaCungCap = cmbNhaCungCap.getSelectedItem().toString();
				String maNhaCungCap = NhaCungCap_DAO.getMaNCC(tenNhaCungCap);
				NhaCungCap nhaCungCap = new NhaCungCap(maNhaCungCap, tenNhaCungCap);

				int soLuong = Integer.parseInt(txtSoLuongSanPham.getText());
				String tinhTrang = cmbTinhTrangSanPham.getSelectedItem().toString();

				Date selectedDate = (Date) dpi_SanPham.getModel().getValue();
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				String mydate = dateFormat.format(selectedDate);
				int nam = Integer.parseInt(mydate.substring(6, 10));
				int thang = Integer.parseInt(mydate.substring(3, 5));
				int ngay = Integer.parseInt(mydate.substring(0, 2));
				LocalDateTime ngayNhap = LocalDateTime.of(nam, thang, ngay, 0, 0);

				String tenloaiSanPham = cmbLoaiSanPham.getSelectedItem().toString();
				String maLoai = LoaiSanPham_DAO.getMaLoaiSanPham(tenloaiSanPham);
				LoaiSanPham loai = new LoaiSanPham(maLoai, tenloaiSanPham);

				String tenMauSac = cmbMauSacSanPham.getSelectedItem().toString();
				String maMauSac = MauSac_DAO.getMaMauSac(tenMauSac);
				MauSac mauSac = new MauSac(maMauSac, tenMauSac);

				String tenChatLieu = cmbChatLieuSanPham.getSelectedItem().toString();
				String maChatLieu = ChatLieu_DAO.getMaChatLieu(tenChatLieu);
				ChatLieu chatLieu = new ChatLieu(maChatLieu, tenChatLieu);

				String tenKichCo = cmbKichCoSanPham.getSelectedItem().toString();
				String maKichCo = KichCo_DAO.getMaKichCo(tenKichCo);
				KichCo kichCo = new KichCo(maKichCo, tenKichCo);

				String maSanPham = txtMaSanPham.getText();

				byte[] hinhAnh = getImageFromLbl(lblHinh);
				SanPham sanPham = new SanPham(maSanPham, tenSanPham, giaNhap, giaBan, loai, hinhAnh, kichCo, chatLieu,
						mauSac, soLuong, ngayNhap, tinhTrang, nhaCungCap);
				if (sanPham_DAO.capNhatSanPham(sanPham)) {
					model_DanhSachSanPham.setRowCount(0);
					docDuLieu();
					xoaRong();
					ManHinhTimKiemSanPham.resetData();
					JOptionPane.showMessageDialog(null, "Cập nhật thành công");
				}

			}
		} else if (o.equals(btnTim_SanPham)) {

			String maSanPham = txtTim_MaSanPham.getText();

			String tenSanPham = txtTim_TenSanPham.getText();
			String loai = cmbTim_LoaiSanPham.getSelectedItem().toString();
			loai = loai.equalsIgnoreCase("Tất cả") ? "" : loai;
			String mauSac = cmbTim_MauSacSanPham.getSelectedItem().toString();
			mauSac = mauSac.equalsIgnoreCase("Tất cả") ? "" : mauSac;
			String kichCo = cmbTim_KichCoSanPham.getSelectedItem().toString();
			kichCo = kichCo.equalsIgnoreCase("Tất cả") ? "" : kichCo;
			String chatLieu = cmbTim_ChatLieuSanPham.getSelectedItem().toString();
			chatLieu = chatLieu.equalsIgnoreCase("Tất cả") ? "" : chatLieu;

			DefaultTableModel model = (DefaultTableModel) tbl_DanhSachSanPham.getModel();
			TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
			tbl_DanhSachSanPham.setRowSorter(sorter);
			List<RowFilter<Object, Object>> filters = new ArrayList<>();
			filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(maSanPham), 0));
			filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(tenSanPham), 1));
			filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(loai), 5));
			filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(mauSac), 6));
			filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(chatLieu), 7));
			filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(kichCo), 8));

			RowFilter<Object, Object> af = RowFilter.andFilter(filters);
			sorter.setRowFilter(af);

		} else if (o.equals(btnXoaTrangSanPham)) {
			xoaRong();
		} else if (o.equals(btnThemAnhSanPham)) {
			hinhAnh = chooseImage(lblHinh);
		}
	}

	private void xoaRong() {
		txtTim_MaSanPham.setText("");
		txtTim_MaSanPham.setForeground(Color.GRAY);
		txtTim_TenSanPham.setText("");
		txtTim_TenSanPham.setForeground(Color.GRAY);
		txtMaSanPham.setText("");
		txtTenSanPham.setText("");
		txtGiaNhapSanPham.setText("");
		txtGiaBanSanPham.setText("");
		txtSoLuongSanPham.setText("");
		txtMaSanPham.setText("");
		cmbNhaCungCap.setSelectedIndex(0);
		cmbChatLieuSanPham.setSelectedIndex(0);
		cmbKichCoSanPham.setSelectedIndex(0);
		cmbMauSacSanPham.setSelectedIndex(0);
		cmbLoaiSanPham.setSelectedIndex(0);
		Icon image = new ImageIcon(ManHinhCapNhatSanPham.class.getResource("/images/null.jpg"));
		Image ima = ((ImageIcon) image).getImage();
		Image newimg = ima.getScaledInstance(140, 140, Image.SCALE_SMOOTH);
		image = new ImageIcon(newimg);
		lblHinh.setIcon(image);
		LocalDate ngay = LocalDate.now();
		dpi_SanPham.getJFormattedTextField().setText(dtf.format(ngay));
		model_date.setValue(new Date(ngay.getYear() - 1900, ngay.getMonthValue() - 1, ngay.getDayOfMonth()));
		hinhAnh = getImageFromLbl(lblHinh);
	}

	public static String[] capNhatCmbNhaCungCap() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		for (NhaCungCap nhaCungCap : nhaCungCap_DAO.getDsNCC()) {
			list.add(nhaCungCap.getTenNCC());
		}
		s = list.toArray(new String[0]);
		return s;
	}

	private String[] capNhatCmbLoai() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		for (LoaiSanPham loai : loaiSanPham_DAO.getDsLoaiSP()) {
			list.add(loai.getTenLoai());
		}
		s = list.toArray(new String[0]);
		return s;
	}

	private String[] capNhatCmbMauSac() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		for (MauSac mauSac : mauSac_DAO.getDsMauSac()) {
			list.add(mauSac.getTenMauSac());
		}
		s = list.toArray(new String[0]);
		return s;
	}

	private String[] capNhatCmbKichCo() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		for (KichCo kichCo : kichCo_DAO.getDsKichCo()) {
			list.add(kichCo.getTenKichCo());
		}
		s = list.toArray(new String[0]);
		return s;
	}

	private String[] capNhatCmbChatLieu() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		for (ChatLieu chatLieu : chatLieu_DAO.getDsChatLieu()) {
			list.add(chatLieu.getTenChatLieu());
		}
		s = list.toArray(new String[0]);
		return s;
	}

	public static String[] capNhatCmbTimNhaCungCap() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		list.add("Tất cả");
		for (NhaCungCap nhaCungCap : nhaCungCap_DAO.getDsNCC()) {
			list.add(nhaCungCap.getTenNCC());
		}
		s = list.toArray(new String[0]);
		return s;
	}

	private String[] capNhatCmbTimLoai() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		list.add("Tất cả");
		for (LoaiSanPham loai : loaiSanPham_DAO.getDsLoaiSP()) {
			list.add(loai.getTenLoai());
		}
		s = list.toArray(new String[0]);
		return s;
	}

	private String[] capNhatCmbTimMauSac() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		list.add("Tất cả");
		for (MauSac mauSac : mauSac_DAO.getDsMauSac()) {
			list.add(mauSac.getTenMauSac());
		}
		s = list.toArray(new String[0]);
		return s;
	}

	private String[] capNhatCmbTimKichCo() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		list.add("Tất cả");
		for (KichCo kichCo : kichCo_DAO.getDsKichCo()) {
			list.add(kichCo.getTenKichCo());
		}
		s = list.toArray(new String[0]);
		return s;
	}

	private String[] capNhatCmbTimChatLieu() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		list.add("Tất cả");
		for (ChatLieu chatLieu : chatLieu_DAO.getDsChatLieu()) {
			list.add(chatLieu.getTenChatLieu());
		}
		s = list.toArray(new String[0]);
		return s;
	}

	private byte[] getImageFromLbl(JLabel lable) {
		Icon icon = lable.getIcon();
		BufferedImage bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.createGraphics();
		icon.paintIcon(null, g, 0, 0);
		g.dispose();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(bi, "jpg", baos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return baos.toByteArray();
	}

	private byte[] chooseImage(JLabel lable) {
		String pathImage = null;
		int fileState = fileChooser.showSaveDialog(null);
		if (fileState == JFileChooser.APPROVE_OPTION) {
			File selectFile = fileChooser.getSelectedFile();
			pathImage = selectFile.getAbsolutePath();
			Icon myImage = null;

			if (pathImage != null) {
				myImage = new ImageIcon(pathImage);
				Image ima = ((ImageIcon) myImage).getImage();
				Image newimg = ima.getScaledInstance(160, 160, Image.SCALE_SMOOTH);
				myImage = new ImageIcon(newimg);
				lable.setIcon(myImage);
			}
			if (pathImage.equals(""))
				return getImageFromLbl(lblHinh);

		}
		return getBytesImg(pathImage);
	}

	private byte[] getBytesImg(String filePath) {
		if (filePath == null)
			return getImageFromLbl(lblHinh);
		File fnew = new File(filePath);
		BufferedImage originalImage = null;
		try {
			originalImage = ImageIO.read(fnew);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(originalImage, "jpg", baos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return baos.toByteArray();
	}

	public boolean validDataSanPham() {
		String tenSanPham = txtTenSanPham.getText().trim();
		String giaNhapSanPham = txtGiaNhapSanPham.getText().trim();
		String giaBanSanPham = txtGiaBanSanPham.getText();
		String soLuongSanPham = txtSoLuongSanPham.getText().trim();
		if (!(tenSanPham.length() > 0)) {
			JOptionPane.showMessageDialog(null, "Tên sản phẩm không được rỗng! ");
			return false;
		}

		if (!(giaNhapSanPham.length() > 0 && giaNhapSanPham.matches("^[0-9]+$"))) {
			JOptionPane.showMessageDialog(null, "Giá bán không hợp lệ! ");
			return false;
		}
		if (!(soLuongSanPham.length() > 0 && soLuongSanPham.matches("^[0-9]+$"))) {
			JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ! ");
			return false;
		}
		if (!(giaBanSanPham.length() > 0 && giaBanSanPham.matches("^[0-9]+$"))) {
			JOptionPane.showMessageDialog(null, "Giá nhập không hợp lệ! ");
			return false;
		}
		return true;
	}

	public static void updateDataForResetData(List<SanPham> danhSach) {
		// xoaTrangTable(tbl_DanhSachSanPham);

		df = new DecimalFormat("#,###");
		dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		List<SanPham> list = sanPham_DAO.getDsSanPham();
		for (SanPham sanPham : list) {
			String[] rowData = { sanPham.getMaSP(), sanPham.getTenSP(), Double.toString(sanPham.getGiaNhap()),
					Double.toString(sanPham.getGiaBan()), dtf.format(sanPham.getNgayNhap()).substring(0, 10),
					sanPham.getLoaiSP().getTenLoai(), sanPham.getMauSac().getTenMauSac(),
					sanPham.getChatLieu().getTenChatLieu(), sanPham.getKichCo().getTenKichCo(),
					sanPham.getNhaCungCap().getTenNCC(), Integer.toString(sanPham.getSoLuongTon()),
					sanPham.getTrangThai() };
			model_DanhSachSanPham.addRow(rowData);
		}

	}

	public static void resetData() {
		SanPham_DAO sanPham_DAO = new SanPham_DAO();
		List<SanPham> danhSach = sanPham_DAO.getDsSanPham();
		ManHinhTimKiemSanPham.updateDataForResetData(danhSach);
	}
}
