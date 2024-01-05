package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entities.ChuongTrinhKhuyenMai;
import my_Interfaces.I_ChuongTrinhKhuyenMai;

public class ChuongTrinhKhuyenMai_DAO implements I_ChuongTrinhKhuyenMai{

	@Override
	public List<ChuongTrinhKhuyenMai> getDsCTKM() {
		// TODO Auto-generated method stub
		List<ChuongTrinhKhuyenMai> dsCTKM = new ArrayList<ChuongTrinhKhuyenMai>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from ChuongTrinhKhuyenMai where maKM != 'MACDINH'";
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maKM = rs.getNString("maKM");
				double phanTramKhuyenMai = rs.getDouble("phanTramKhuyenMai");
				LocalDateTime ngayBatDau = rs.getTimestamp("ngayBatDau").toLocalDateTime(); 
				LocalDateTime ngayKetThuc =  rs.getTimestamp("ngayKetThuc").toLocalDateTime();  
				String trangThai = rs.getNString("trangThai");
				ChuongTrinhKhuyenMai ctkm = new ChuongTrinhKhuyenMai(maKM, phanTramKhuyenMai, ngayBatDau, ngayKetThuc, trangThai);
				dsCTKM.add(ctkm);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsCTKM;
	}

	@Override
	public ChuongTrinhKhuyenMai getCTKM(String maKM) {
		// TODO Auto-generated method stub
		ChuongTrinhKhuyenMai ctkm = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "select * from ChuongTrinhKhuyenMai where maKM=?";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, maKM);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				double phanTramKhuyenMai = rs.getDouble("phanTramKhuyenMai");
				LocalDateTime ngayBatDau = rs.getTimestamp("ngayBatDau").toLocalDateTime(); 
				LocalDateTime ngayKetThuc =  rs.getTimestamp("ngayKetThuc").toLocalDateTime();  
				String trangThai = rs.getNString("trangThai");
				ctkm = new ChuongTrinhKhuyenMai(maKM, phanTramKhuyenMai, ngayBatDau, ngayKetThuc, trangThai);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ctkm;
	}

	@Override
	public boolean themCTKM(ChuongTrinhKhuyenMai ctkm) {
		int n = 0;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "INSERT INTO ChuongTrinhKhuyenMai([maKM],phanTramKhuyenMai,ngayBatDau,ngayKetThuc,trangThai) VALUES (?,?,?,?,?)\n";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, ctkm.getMaKM());
			statement.setDouble(2, ctkm.getPhanTramKhuyenMai());
			statement.setNString(3, dtf.format(ctkm.getNgayBatDau()));
			statement.setNString(4, dtf.format(ctkm.getNgayKetThuc()));
			statement.setNString(5, ctkm.getTrangThai());
			n = statement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return n>0;
	}

	@Override
	public boolean capNhatCTKM(String maKM, ChuongTrinhKhuyenMai ctkmNew) {
		int n=0;
		ConnectDB.getInstance();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		System.out.println(dtf.format(ctkmNew.getNgayBatDau()));
		System.out.println(dtf.format(ctkmNew.getNgayKetThuc()));
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "\n"
				+ "Update ChuongTrinhKhuyenMai set phanTramKhuyenMai = ? , ngayBatDau = ?, ngayKetThuc = ? ,trangThai = ? \n"
				+ "Where maKM = ?";
		try {
			statement = con.prepareStatement(sql);
			statement.setDouble(1, ctkmNew.getPhanTramKhuyenMai());
			statement.setNString(2, dtf.format(ctkmNew.getNgayBatDau()));
			statement.setNString(3, dtf.format(ctkmNew.getNgayKetThuc()));
			statement.setNString(4, ctkmNew.getTrangThai());
			statement.setNString(5, maKM);
			n= statement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}
	
	public List<ChuongTrinhKhuyenMai> timKiem(String ma,double phanTram,String ngayBatDau,String ngayKetThuc, String trangThai) {
		List<ChuongTrinhKhuyenMai> ds = new ArrayList<ChuongTrinhKhuyenMai>();
		//NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "declare @km int,@ma nvarchar(50),@trangThai nvarchar(50),@ngaybd nvarchar(50),@ngaykt nvarchar(50)\n"
				+ "select @ma= ?,@km = ?,@ngaybd = ?,@ngaykt = ?,@trangThai = ?\n"
				+ "if @ma != '%'\n"
				+ "begin\n"
				+ "select * from ChuongTrinhKhuyenMai\n"
				+ "where maKM like @ma\n"
				+ "end\n"
				+ "else if @km != 0\n"
				+ "begin\n"
				+ "select * from ChuongTrinhKhuyenMai\n"
				+ "where phanTramKhuyenMai = @km\n"
				+ "end\n"
				+ "else if @ngaybd != '2023-01-01%'\n"
				+ "begin\n"
				+ "select * from ChuongTrinhKhuyenMai\n"
				+ "where CONVERT(nvarchar(50),ngayBatDau,120) like @ngaybd\n"
				+ "end\n"
				+ "else if @ngaykt != '2023-12-01%'\n"
				+ "begin\n"
				+ "select * from ChuongTrinhKhuyenMai\n"
				+ "where CONVERT(nvarchar(50),ngayKetThuc,120) like @ngaykt\n"
				+ "end\n"
				+ "else\n"
				+ "begin\n"
				+ "select * from ChuongTrinhKhuyenMai\n"
				+ "where trangThai = @trangThai\n"
				+ "end";

		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, ma);
			statement.setDouble(2, phanTram);
			statement.setNString(3,ngayBatDau+"%");
			statement.setNString(4,ngayKetThuc+"%");
			statement.setNString(5,	trangThai);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				
				String maKM = rs.getNString("maKM");
				double phanTram1 = rs.getDouble("phanTramKhuyenMai");
				LocalDateTime ngay1 = rs.getTimestamp("ngayBatDau").toLocalDateTime();
				LocalDateTime ngay2 = rs.getTimestamp("ngayKetThuc").toLocalDateTime();
				String trangThai1 = rs.getNString("trangThai");
				ChuongTrinhKhuyenMai ctkm = new ChuongTrinhKhuyenMai(maKM, phanTram1, ngay1, ngay2, trangThai1);
				ds.add(ctkm);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}
}
