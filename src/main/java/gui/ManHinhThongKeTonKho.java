package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import custom.CustomScrollBarUI;
import custom.barChart.ThongKeBanChay;
import custom.barChart.ThongKeTonKho;
import dao.ChiTietHoaDon_DAO;
import dao.LoaiSanPham_DAO;
import dao.SanPham_DAO;
import entities.LoaiSanPham;
import entities.SanPham;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.ScrollPaneConstants;


public class ManHinhThongKeTonKho extends JPanel{
	private JScrollPane scrDanhSach;
	public static JTable tbl_DanhSachSanPham;
	public static DefaultTableModel model_danhSachSanPham;


	private JComboBox<String> cmbTieuChi;

	private static SanPham_DAO sanPham_DAO;
	private LoaiSanPham_DAO loaiSanPham_DAO = new LoaiSanPham_DAO();
	public static List<ThongKeTonKho> danhSachThongKe;
	private BieuDoTonKho chart;
	private JPanel pnlBieuDo;
	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ManHinhThongKeTonKho() {
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


		sanPham_DAO = new SanPham_DAO();

		JPanel pnlThaoTacChinh = new JPanel();
		pnlThaoTacChinh.setBackground(new Color(255, 255, 255));
		pnlThaoTacChinh.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true),
				"Ti\u00EAu ch\u00ED th\u1ED1ng k\u00EA", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(65, 105, 225)));
		pnlThaoTacChinh.setBounds(10, 50, 1239, 80);
		add(pnlThaoTacChinh);
		pnlThaoTacChinh.setLayout(null);

		JPanel pnlThaoTac = new JPanel();
		pnlThaoTac.setLayout(null);
		pnlThaoTac.setBackground(new Color(255, 250, 240));
		pnlThaoTac.setBounds(10, 20, 1219, 50);
		pnlThaoTacChinh.add(pnlThaoTac);

		JLabel lblTieuChi = new JLabel("Tiêu chí:");
		lblTieuChi.setHorizontalAlignment(SwingConstants.LEFT);
		lblTieuChi.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTieuChi.setBounds(10, 10, 50, 30);
		pnlThaoTac.add(lblTieuChi);



		String[] loai = capNhatCmbLoai();
		cmbTieuChi = new JComboBox();
		cmbTieuChi.setModel(new DefaultComboBoxModel(loai));
		cmbTieuChi.setBackground(new Color(245, 222, 179));
		cmbTieuChi.setBounds(60, 10, 150, 30);
		pnlThaoTac.add(cmbTieuChi);
		
		JButton btnXuatBaoCao = new JButton("Xuất file *.xlxs");
		btnXuatBaoCao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnXuatBaoCao.setBounds(1069, 10, 140, 30);
		pnlThaoTac.add(btnXuatBaoCao);



		JPanel pnlDanhSachSanPham = new JPanel();
		pnlDanhSachSanPham.setBackground(new Color(255, 255, 255));
		pnlDanhSachSanPham.setBounds(10, 529, 1239, 325);
		add(pnlDanhSachSanPham);
		pnlDanhSachSanPham.setLayout(null);

		model_danhSachSanPham = new DefaultTableModel(new Object[][] {

		}, new String[] { "Mã Sản Phẩm","Tên Sản Phẩm","Số Lượng" }

				) {
			boolean[] canEdit = new boolean[] { false, false, false };

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

		scrDanhSach = new JScrollPane();
		scrDanhSach.setViewportView(tbl_DanhSachSanPham);
		scrDanhSach.setBounds(10, 30, 1219, 285);
		scrDanhSach.getViewport().setBackground(Color.white);
		pnlDanhSachSanPham.add(scrDanhSach);

		JLabel lblDanhSachSanPham = new JLabel("Danh sách sản phẩm");
		lblDanhSachSanPham.setHorizontalAlignment(SwingConstants.CENTER);
		lblDanhSachSanPham.setFont(new Font("Arial", Font.BOLD, 16));
		lblDanhSachSanPham.setForeground(new Color(65, 105, 225));
		lblDanhSachSanPham.setBounds(0, 0, 1239, 30);
		pnlDanhSachSanPham.add(lblDanhSachSanPham);

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

		JLabel lblTenManHinh = new JLabel("THỐNG KÊ TỒN KHO");
		lblTenManHinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenManHinh.setForeground(new Color(100, 149, 237));
		lblTenManHinh.setFont(new Font("Arial", Font.BOLD, 20));
		lblTenManHinh.setBounds(20, 0, 1209, 50);
		add(lblTenManHinh);

		JPanel pnlChart = new JPanel();
		pnlChart.setBackground(new Color(255, 255, 255));
		pnlChart.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(245, 222, 179)));
		pnlChart.setBounds(10, 133, 1239, 386);
		add(pnlChart);
		pnlChart.setLayout(null);

		JLabel lblBieuDoTonKho = new JLabel("Biểu đồ số lượng tồn kho");
		lblBieuDoTonKho.setBounds(0, 0, 1239, 30);
		lblBieuDoTonKho.setHorizontalAlignment(SwingConstants.CENTER);
		lblBieuDoTonKho.setForeground(new Color(65, 105, 225));
		lblBieuDoTonKho.setFont(new Font("Arial", Font.BOLD, 16));
		pnlChart.add(lblBieuDoTonKho);

		

		pnlBieuDo = new JPanel();
		pnlBieuDo.setBounds(10, 30, 1090, 309);
		pnlBieuDo.setLayout(null);

		danhSachThongKe = sanPham_DAO.getDSTonKho();
		

		JScrollPane scr_chart = new JScrollPane();
		scr_chart.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scr_chart.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scr_chart.setBounds(10, 30, 1219, 345);
		pnlChart.add(scr_chart);
		chart = new BieuDoTonKho();
		scr_chart.setViewportView(chart);
		scr_chart.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
		scr_chart.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		chart.ThongKeKho(danhSachThongKe);
		
		updateTable(danhSachThongKe);
		cmbTieuChi.getModel().addListDataListener(new ListDataListener() {

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
				String tieuChi = cmbTieuChi.getSelectedItem().toString();
				tieuChi = tieuChi.equalsIgnoreCase("")?"Tất cả":tieuChi;
				if(tieuChi.equalsIgnoreCase("Tất cả")) {
					chart = new BieuDoTonKho();
					scr_chart.revalidate();
					scr_chart.setViewportView(chart);
					chart.ThongKeKho(danhSachThongKe);
					updateTable(danhSachThongKe);
				}
				else {
					danhSachThongKe = sanPham_DAO.getDSTonKhoTheoTieuChi(tieuChi);
					scr_chart.revalidate();;
					chart = new BieuDoTonKho();
					scr_chart.setViewportView(chart);
					chart.ThongKeKho(danhSachThongKe);
					updateTable(danhSachThongKe);
				}
			}
		});
		btnXuatBaoCao.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	String tieuChi = cmbTieuChi.getSelectedItem().toString();
		    	tieuChi = tieuChi.equalsIgnoreCase("")?"Tất cả":tieuChi;
				xuatBaoCaoExcelTheoTieuChi(tieuChi);
		    }
		});
		ManHinhChinh.btnVN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblTenManHinh.setText("THỐNG KÊ TỒN KHO");
				pnlThaoTacChinh.setBorder(BorderFactory.createTitledBorder("Tiêu chí thống kê"));

				lblBieuDoTonKho.setText("Biểu đồ số lượng tồn kho");
				lblDanhSachSanPham.setText("Danh sách sản phẩm");
				
				lblTieuChi.setText("Tiêu chí");
				btnXuatBaoCao.setText("Xuất file *.xlxs");
				String[] newColumn = {"Mã Sản Phẩm","Tên Sản Phẩm","Số Lượng" };
				model_danhSachSanPham.setColumnIdentifiers(newColumn);
			}
		});
		
		ManHinhChinh.btnEN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblTenManHinh.setText("INVENTORY STATISTICS");
				pnlThaoTacChinh.setBorder(BorderFactory.createTitledBorder("Statistical criteria"));

				lblBieuDoTonKho.setText("Inventory quantity chart");
				lblDanhSachSanPham.setText("List of products");

				lblTieuChi.setText("Criteria");
				btnXuatBaoCao.setText("Export file *.xlxs");
				String[] newColumn = {"Product Id","Product Name","Quantity" };
				model_danhSachSanPham.setColumnIdentifiers(newColumn);

			}
		});
	}


	private void xoaTrangTable(JTable t) {
		DefaultTableModel dtm = (DefaultTableModel) t.getModel();
		dtm.getDataVector().removeAllElements();
	}
	private void updateTable(List<ThongKeTonKho> danhSach) {
		xoaTrangTable(tbl_DanhSachSanPham);
		tbl_DanhSachSanPham.revalidate();

		for (ThongKeTonKho tonKho : danhSach) {
			Object data[] = {tonKho.getMaSP(),tonKho.getTenSP(),tonKho.getTongSoLuong()};
			model_danhSachSanPham.addRow(data);
		}
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


	private void xuatBaoCaoExcelTheoTieuChi(String tieuChi) {
	    try {
	        JFileChooser fchViTri = new JFileChooser();
	        fchViTri.setDialogTitle("Chọn vị trí lưu file Excel");
	        int userSelection = fchViTri.showSaveDialog(null);

	        if (userSelection == JFileChooser.APPROVE_OPTION) {
	            String filePath = fchViTri.getSelectedFile().getAbsolutePath();
	            if (!filePath.endsWith(".xlsx")) {
	                filePath += ".xlsx";
	            }
	            
	            Workbook workbook = new XSSFWorkbook();
	            Sheet sheet = workbook.createSheet("Danh Sách Sản Phẩm");

	            Row headerRow = sheet.createRow(0);
	            String[] headers = {"Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng"};
	            for (int i = 0; i < headers.length; i++) {
	                Cell cell = headerRow.createCell(i);
	                cell.setCellValue(headers[i]);
	            }

	            List<ThongKeTonKho> danhSachThongKe = new ArrayList<>();
	            if (tieuChi.equalsIgnoreCase("Tất cả")) {
	                danhSachThongKe = sanPham_DAO.getDSTonKho();
	            } else {
	            	danhSachThongKe = sanPham_DAO.getDSTonKhoTheoTieuChi(tieuChi);
	            }

	            for (int i = 0; i < danhSachThongKe.size(); i++) {
	                Row dataRow = sheet.createRow(i + 1);
	                dataRow.createCell(0).setCellValue(danhSachThongKe.get(i).getMaSP());
	                dataRow.createCell(1).setCellValue(danhSachThongKe.get(i).getTenSP());
	                dataRow.createCell(2).setCellValue(danhSachThongKe.get(i).getTongSoLuong());
	            }

	            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
	                workbook.write(fileOut);
	            }

	            workbook.close();

	            JOptionPane.showMessageDialog(null, "Xuất báo cáo thành công!");
	        }
	    } catch (IOException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Lỗi khi xuất báo cáo: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	    }
	}
	

}



