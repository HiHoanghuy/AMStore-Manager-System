package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import custom.barChart.ThongKeBanChay;
import entities.ChiTietHoaDon;
import entities.SanPham;
import my_Interfaces.I_ChiTietHoaDon;

public class ChiTietHoaDon_DAO implements I_ChiTietHoaDon{
	private SanPham_DAO sanPham_DAO;

	@Override
	public ChiTietHoaDon get1ChiTietHD(String maHD,String masP) {
		// TODO Auto-generated method stub
		sanPham_DAO = new SanPham_DAO();
		ChiTietHoaDon ct = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "select * from ChiTietHoaDon where maHD = ? and maSP = ?";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, maHD);
			statement.setNString(2, masP);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maSP = rs.getNString("maSP");
				SanPham sp = sanPham_DAO.getSanPham(maSP);
				double giaBan = rs.getDouble("giaBan");
				int soLuong = rs.getInt("soLuong");
				ct = new ChiTietHoaDon(maHD, sp, soLuong, giaBan);
				return ct;
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ct;
	}
	
	@Override
	public List<ChiTietHoaDon> getChiTietHD(String maHD) {
		// TODO Auto-generated method stub
		sanPham_DAO = new SanPham_DAO();
		List<ChiTietHoaDon> ds = new ArrayList<ChiTietHoaDon>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "select * from ChiTietHoaDon where maHD = ?";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, maHD);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maSP = rs.getNString("maSP");
				SanPham sp = sanPham_DAO.getSanPham(maSP);
				double giaBan = rs.getDouble("giaBan");
				int soLuong = rs.getInt("soLuong");
				ChiTietHoaDon ct = new ChiTietHoaDon(maHD, sp, soLuong, giaBan);
				ds.add(ct);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}

	@Override
	public boolean themChiTietHD(ChiTietHoaDon cthd) {
		// TODO Auto-generated method stub
		int n=0;
		sanPham_DAO = new SanPham_DAO();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "insert into ChiTietHoaDon values (?, ?, ? ,?)";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, cthd.getMaHD());
			statement.setNString(2, cthd.getSanPham().getMaSP());
			statement.setDouble(3, cthd.getGiaBan());
			statement.setInt(4, cthd.getSoLuong());
			n = statement.executeUpdate();
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}
	public List<ChiTietHoaDon> getDSCTHD() {
		// TODO Auto-generated method stub
		sanPham_DAO = new SanPham_DAO();
		List<ChiTietHoaDon> ds = new ArrayList<ChiTietHoaDon>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "select * from ChiTietHoaDon";
		try {
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maHD = rs.getNString("maHD");
				String maSP = rs.getNString("maSP");
				SanPham sp = sanPham_DAO.getSanPham(maSP);
				double giaBan = rs.getDouble("giaBan");
				int soLuong = rs.getInt("soLuong");
				ChiTietHoaDon ct = new ChiTietHoaDon(maHD, sp, soLuong, giaBan);
				ds.add(ct);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}

	public List<ThongKeBanChay> getDSCTHDHomNay() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<ThongKeBanChay> dsbc = new ArrayList<ThongKeBanChay>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "declare @ngayhientai nvarchar(20)\r\n"
				+ "select @ngayhientai = CONVERT(nvarchar(10),GETDATE(),120) + '%'\r\n"
				+ "select top 5 SanPham.maSP,SanPham.tenSP, soLuong = sum(ChiTietHoaDon.soLuong) from ChiTietHoaDon \r\n"
				+ "join HoaDon on HoaDon.maHD = ChiTietHoaDon.maHD \r\n"
				+ "join SanPham on SanPham.maSP = ChiTietHoaDon.maSP \r\n"
				+ "where CONVERT(nvarchar(20),HoaDon.ngayLap,120) like @ngayhientai\r\n"
				+ "group by SanPham.maSP,SanPham.tenSP order by soLuong desc";
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maSP = rs.getNString("maSP");
				String tenSP = rs.getNString("tenSP");
				int soLuong = rs.getInt("soLuong");
				ThongKeBanChay tk = new ThongKeBanChay(maSP, tenSP, soLuong);
				dsbc.add(tk);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsbc;
	}
	
	public List<ThongKeBanChay> getDSCTHD7Ngay() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<ThongKeBanChay> dsbc = new ArrayList<ThongKeBanChay>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "declare @start datetime, @end datetime\n"
				+ "select @end =DATEDIFF(day,-1,GETDATE()) , @start = DATEDIFF(day,6,@end) \n"
				+ "select top 5 SanPham.maSP,SanPham.tenSP, soLuong = sum(ChiTietHoaDon.soLuong) from ChiTietHoaDon \n"
				+ "join HoaDon on HoaDon.maHD = ChiTietHoaDon.maHD \n"
				+ "join SanPham on SanPham.maSP = ChiTietHoaDon.maSP \n"
				+ "where CONVERT(nvarchar(20),HoaDon.ngayLap,120) >= @start AND CONVERT(nvarchar(20),HoaDon.ngayLap,120) <= @end\n"
				+ "group by SanPham.maSP,SanPham.tenSP order by soLuong desc";
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maSP = rs.getNString("maSP");
				String tenSP = rs.getNString("tenSP");
				int soLuong = rs.getInt("soLuong");
				ThongKeBanChay tk = new ThongKeBanChay(maSP, tenSP, soLuong);
				dsbc.add(tk);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsbc;
	}


	public List<ThongKeBanChay> getDSCTHD1Thang(int thang) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<ThongKeBanChay> dsbc = new ArrayList<ThongKeBanChay>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "declare @thang nvarchar(20),@start nvarchar(20),@end nvarchar(20)\n"
				+ "select @thang = ?\n"
				+ "select @start = '2023'+@thang+'00'+'%',@end = '2023'+@thang+'31'+'%'\n"
				+ "select top 5 SanPham.maSP,SanPham.tenSP, soLuong = sum(ChiTietHoaDon.soLuong) from ChiTietHoaDon \n"
				+ "join HoaDon on HoaDon.maHD = ChiTietHoaDon.maHD \n"
				+ "join SanPham on SanPham.maSP = ChiTietHoaDon.maSP \n"
				+ "where CONVERT(nvarchar(20),HoaDon.ngayLap,120) >= @start AND CONVERT(nvarchar(20),HoaDon.ngayLap,120) <= @end\n"
				+ "group by SanPham.maSP,SanPham.tenSP order by soLuong desc";
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, thang+"");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maSP = rs.getNString("maSP");
				String tenSP = rs.getNString("tenSP");
				int soLuong = rs.getInt("soLuong");
				ThongKeBanChay tk = new ThongKeBanChay(maSP, tenSP, soLuong);
				dsbc.add(tk);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsbc;
	}


	public List<ThongKeBanChay> getDSCTHD1Nam() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<ThongKeBanChay> dsbc = new ArrayList<ThongKeBanChay>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "declare @start datetime, @end datetime, @nam int\n"
				+ "select @nam = YEAR(GETDATE())\n"
				+ "if (@nam % 4 = 0 and @nam % 100 != 0) or (@nam % 400 = 0)\n"
				+ "begin\n"
				+ "	select @end =DATEDIFF(day,-1,GETDATE()) , @start = DATEDIFF(day,364,@end) \n"
				+ "end\n"
				+ "else\n"
				+ "begin\n"
				+ "	select @end =DATEDIFF(day,-1,GETDATE()) , @start = DATEDIFF(day,365,@end) \n"
				+ "end\n"
				+ "select top 5 SanPham.maSP,SanPham.tenSP, soLuong = sum(ChiTietHoaDon.soLuong) from ChiTietHoaDon \n"
				+ "join HoaDon on HoaDon.maHD = ChiTietHoaDon.maHD \n"
				+ "join SanPham on SanPham.maSP = ChiTietHoaDon.maSP \n"
				+ "where CONVERT(nvarchar(20),HoaDon.ngayLap,120) >= @start AND CONVERT(nvarchar(20),HoaDon.ngayLap,120) <= @end\n"
				+ "group by SanPham.maSP,SanPham.tenSP order by soLuong desc\n";
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maSP = rs.getNString("maSP");
				String tenSP = rs.getNString("tenSP");
				int soLuong = rs.getInt("soLuong");
				ThongKeBanChay tk = new ThongKeBanChay(maSP, tenSP, soLuong);
				dsbc.add(tk);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsbc;
	}
	
	public List<ThongKeBanChay> getDSCTHDTuyChinh(LocalDate ngaybatdau,LocalDate ngayketthuc) {
		List<ThongKeBanChay> dsbc = new ArrayList<ThongKeBanChay>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "declare @start nvarchar(20), @end nvarchar(20)\n"
				+ "select @start = ?,@end = ?\n"
				+ "select top 5 SanPham.maSP,SanPham.tenSP, soLuong = sum(ChiTietHoaDon.soLuong) from ChiTietHoaDon \n"
				+ "join HoaDon on HoaDon.maHD = ChiTietHoaDon.maHD \n"
				+ "join SanPham on SanPham.maSP = ChiTietHoaDon.maSP \n"
				+ "where CONVERT(nvarchar(20),HoaDon.ngayLap,120) >= @start and CONVERT(nvarchar(20),HoaDon.ngayLap,120) <= @end\n"
				+ "group by SanPham.maSP,SanPham.tenSP order by soLuong desc";
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, ngaybatdau+"%");
			statement.setNString(2, ngayketthuc+"%");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maSP = rs.getNString("maSP");
				String tenSP = rs.getNString("tenSP");
				int soLuong = rs.getInt("soLuong");
				ThongKeBanChay tk = new ThongKeBanChay(maSP, tenSP, soLuong);
				dsbc.add(tk);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsbc;
	}
	
	public void capNhatCTHD(ChiTietHoaDon cthd) {
		int n=0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "update ChiTietHoaDon set soLuong = ? where maHD=? and maSP = ?";
		try {
			statement = con.prepareStatement(sql);
			statement.setInt(1, cthd.getSoLuong());
			statement.setNString(2, cthd.getMaHD());
			statement.setNString(3, cthd.getSanPham().getMaSP());
			n= statement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
