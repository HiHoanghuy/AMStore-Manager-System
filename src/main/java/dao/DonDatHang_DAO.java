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

import connectDB.ConnectDB;
import entities.ChiTietDonDatHang;
import entities.DonDatHang;
import entities.HoaDon;
import entities.KhachHang;
import entities.NhanVien;
import my_Interfaces.I_DonDatHang;

public class DonDatHang_DAO implements I_DonDatHang{

	@Override
	public List<DonDatHang> getDsDonDatHang() {
		// TODO Auto-generated method stub
		List<DonDatHang> dsDDH = new ArrayList<DonDatHang>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select maDDH,kh.maKH,tenKH,nv.maNV,nv.tenNV,ngayLap,tongTienHD,tienKhachTra,trangThai\r\n"
				+ "from DonDatHang ddh join KhachHang kh\r\n"
				+ "on ddh.maKH = kh.maKH join NhanVien nv\r\n"
				+ "on ddh.maNV = nv.maNV where trangThai = N'Chưa thanh toán'";
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maDDH = rs.getNString("maDDH");
				String maKH = rs.getNString("maKH");
				String tenKH = rs.getNString("tenKH");
				KhachHang kh = new KhachHang(maKH, tenKH);
				String maNV = rs.getNString("maNV");
				String tenNV = rs.getNString("tenNV");
				NhanVien nv = new NhanVien(maNV, tenNV);
				LocalDateTime ngayDat = rs.getTimestamp("ngayLap").toLocalDateTime();
				double tongTien = rs.getDouble("tongTienHD");
				double tienKhachTra = rs.getDouble("tienKhachTra");
				String trangThai = rs.getNString("trangThai");
				DonDatHang ddh = new DonDatHang(maDDH, kh, ngayDat, nv, tongTien, tienKhachTra, trangThai);
				dsDDH.add(ddh);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsDDH;
	}

	@Override
	public DonDatHang getDDH(String maDDH) {
		// TODO Auto-generated method stub
		NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
		KhachHang_DAO khachHang_DAO = new KhachHang_DAO();
		DonDatHang ddh = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "select * from DonDatHang\r\n"
				+ "where maDDH = ? ";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, maDDH);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String ma = rs.getNString("maDDH");
				String maKH = rs.getNString("maKH");
				KhachHang kh = khachHang_DAO.getKHMaKH(maKH);
				String maNV = rs.getNString("maNV");
				NhanVien nv = nhanVien_DAO.getNhanVien(maNV);
				LocalDateTime ngayDat = rs.getTimestamp("ngayLap").toLocalDateTime();
				double tongTien = rs.getDouble("tongTienHD");
				double tienKhachTra = rs.getDouble("tienKhachTra");
				String trangThai = rs.getNString("trangThai");
				ddh = new DonDatHang(ma, kh, ngayDat, nv, tongTien, tienKhachTra, trangThai);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ddh;
	}
	
	public boolean themDDH(DonDatHang ddh) {
		int n=0;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "insert into DonDatHang values (?, ?, ?, ?, ?, ?, ?)";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, ddh.getMaDDH());
			statement.setNString(2,ddh.getKhachHang().getMaKH());	
			statement.setNString(3, ddh.getNhanVien().getMaNV());
			statement.setNString(4, dtf.format(ddh.getNgayLap()));
			statement.setDouble(5, ddh.getTongTienDDH());
			statement.setDouble(6, ddh.getTienKhachTra());
			statement.setNString(7, ddh.getTinhTrangThanhToan());
			n = statement.executeUpdate();
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}
	
	public boolean capNhatDDH(String maDDH, String trangThai) {
		int n=0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "update DonDatHang set trangThai = ? where maDDH = ?";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, trangThai);
			statement.setNString(2, maDDH);
			n= statement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}
	
	public static String taoMaDonDatHang() {
		int stt=0;
		String id = "DDH-";
		int y =(LocalDate.now().getYear());
		String nam=(y+"");
		nam=nam.substring(2, 4);
		int m = LocalDate.now().getMonthValue();
		String thang= null;
		if (m<10) thang="0"+m;
		else thang=m+"";
		id=id+nam+"-"+thang+"%";
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "select stt = count(*) from DonDatHang where maDDH like ?";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				stt = rs.getInt("stt");
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return id.replace("%", "")+"-"+String.format("%05d", stt+1);
	}
	
	public List<DonDatHang> timKiemDDH(String maDDH, String sdtKH, String tenKH, String tenNV, String ngayLap) {
		List<DonDatHang> ds = new ArrayList<>();
		KhachHang_DAO khachHang_DAO = new KhachHang_DAO();
		NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "select * \r\n"
				+ "from DonDatHang ddh join KhachHang kh\r\n"
				+ "on ddh.maKH = kh.maKH join NhanVien nv\r\n"
				+ "on ddh.maNV = nv.maNV\r\n"
				+ "where maDDH like ? and kh.sdtKH like ? and kh.tenKH like ? and nv.tenNV like ? and CONVERT(nvarchar(20),ngayLap,120) like ? and trangThai = N'Chưa thanh toán'";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, "%"+maDDH+"%");
			statement.setNString(2, "%"+sdtKH+"%");
			statement.setNString(3, "%"+tenKH+"%");
			statement.setNString(4, "%"+tenNV+"%");
			statement.setNString(5, ngayLap+"%");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maDon = rs.getNString("maDDH");
				String maKH = rs.getNString("maKH");
				KhachHang kh = khachHang_DAO.getKHMaKH(maKH);
				String maNV = rs.getNString("maNV");
				NhanVien nv = nhanVien_DAO.getNhanVien(maNV);
				LocalDateTime ngay = rs.getTimestamp("ngayLap").toLocalDateTime();
				double tongTienHD = rs.getDouble("tongTienHD");
				double tienKhachTra = rs.getDouble("tienKhachTra");
				String trangThai = rs.getNString("trangThai");
				DonDatHang ddh = new DonDatHang(maDon, kh, ngay, nv, tongTienHD, tienKhachTra, trangThai);
				ds.add(ddh);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}
}
