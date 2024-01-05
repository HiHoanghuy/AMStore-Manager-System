package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connectDB.ConnectDB;
import entities.HoaDon;
import entities.HoaDonTra;
import entities.KhachHang;
import entities.NhanVien;

public class HoaDonTra_DAO {
	public static String taoMaHoaDon(String maDT) {
		int stt = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "select stt = count(*) from HoaDonTra where maDT like ?";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, maDT + "%");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				stt = rs.getInt("stt");
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return maDT + "-DT" + String.format("%05d", stt + 1);
	}

	public boolean themHDT(HoaDonTra hd) {
		int n = 0;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "insert into HoaDonTra values (?, ?, ?, ?, ?,?)";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, hd.getMaDT());
			statement.setNString(2, hd.getNhanVien().getMaNV());
			if (hd.getKhachHang() == null) {
				statement.setNull(3, Types.NVARCHAR);
			} else {
				statement.setNString(3, hd.getKhachHang().getMaKH());
			}
			statement.setDouble(4, hd.getTongTienTra());
			statement.setNString(5, dtf.format(hd.getNgayTra()));
			statement.setNString(6, hd.getMaDT().substring(0, 17));
			n = statement.executeUpdate();
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			// JOptionPane.showMessageDialog(null, "Mỗi hóa đơn chỉ được đổi trả 1 lần!");
			e.printStackTrace();
		}
		return n > 0;
	}

	public List<HoaDonTra> getDsHoaDonTra() {
		// TODO Auto-generated method stub
		NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
		KhachHang_DAO khachHang_DAO = new KhachHang_DAO();
		List<HoaDonTra> ds = new ArrayList<HoaDonTra>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from HoaDonTra order by ngayTra desc";
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maDT = rs.getNString("maDT");
				String maKH = rs.getNString("maKH");
				KhachHang kh = khachHang_DAO.getKHMaKH(maKH);
				String maNV = rs.getNString("maNV");
				NhanVien nv = nhanVien_DAO.getNhanVien(maNV);
				LocalDateTime ngayTra = rs.getTimestamp("ngayTra").toLocalDateTime();
				double tongTienTra = rs.getDouble("tongTienTra");
				String maHD = rs.getNString("maHD");
				HoaDon_DAO hoaDon_dao = new HoaDon_DAO();
				HoaDon hd = hoaDon_dao.getHD(maHD);
				HoaDonTra hdt = new HoaDonTra(maDT, ngayTra, nv, kh, tongTienTra, hd);
				ds.add(hdt);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}

	public List<HoaDonTra> timKiemHDT(String tmaHD, String tsdtKH, String ttenKH, String ttenNV, String tngayTra,
			String tmaDT) {
		System.out.println(tmaHD);
		System.out.println(tsdtKH);
		System.out.println(ttenKH);
		System.out.println(ttenNV);
		System.out.println(tngayTra);
		System.out.println(tmaDT);
		NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
		KhachHang_DAO khachHang_DAO = new KhachHang_DAO();
		List<HoaDonTra> ds = new ArrayList<HoaDonTra>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "select hdt.maDT, nv.maNV, kh.maKH, hdt.tongTienTra, hdt.ngayTra, hd.maHD from HoaDon hd \n"
				+ "join NhanVien nv on hd.maNV = nv.maNV \n" + "join KhachHang kh on hd.maKH = kh.maKH\n"
				+ "join HoaDonTra hdt on hdt.maHD = hd.maHD\n"
				+ "where hdt.maDT like ? and hd.maHD like ? and nv.tenNV like ? and kh.sdtKH like ? and kh.tenKH like ? \n"
				+ "and CONVERT(nvarchar(20),ngayTra,120) like ?";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, "%" + tmaDT + "%");
			statement.setNString(2, "%" + tmaHD + "%");
			statement.setNString(3, "%" + ttenNV + "%");
			statement.setNString(4, "%" + tsdtKH + "%");
			statement.setNString(5, "%" + ttenKH + "%");
			statement.setNString(6, tngayTra + "%");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maHD = rs.getNString("maHD");
				String maKH = rs.getNString("maKH");
				KhachHang kh = khachHang_DAO.getKHMaKH(maKH);
				String maNV = rs.getNString("maNV");
				String maDT = rs.getNString("maDT");
				NhanVien nv = nhanVien_DAO.getNhanVien(maNV);
				LocalDateTime ngayTra = rs.getTimestamp("ngayTra").toLocalDateTime();
				double tongTienTra = rs.getDouble("tongTienTra");
				HoaDon_DAO hoaDon_dao = new HoaDon_DAO();
				HoaDon hd = hoaDon_dao.getHD(maHD);
				HoaDonTra hdt = new HoaDonTra(maDT, ngayTra, nv, kh, tongTienTra, hd);
				ds.add(hdt);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}
}
