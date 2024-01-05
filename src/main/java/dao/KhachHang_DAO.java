package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entities.KhachHang;
import my_Interfaces.I_KhachHang;

public class KhachHang_DAO implements I_KhachHang{

	@Override
	public List<KhachHang> getDsKhachHang() {
		ArrayList<KhachHang> dskh=new ArrayList<KhachHang>();
		try {
			ConnectDB.getInstance();
			Connection con=ConnectDB.getInstance().getConnection();
			
			String sql="Select * from KhachHang";
			Statement statement=con.createStatement();
			
			ResultSet rs=statement.executeQuery(sql);
			while(rs.next()) {

				String maKH = rs.getNString(1);
				String tenKH = rs.getNString(2);
				boolean gioiTinh = rs.getBoolean(3);
				String diaChi = rs.getNString(4);
				String sdtKH = rs.getNString(5);
				String email = rs.getNString(6);
				KhachHang kh = new KhachHang(maKH, tenKH, sdtKH, gioiTinh, diaChi,email);

				dskh.add(kh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dskh;
	}

	@Override
	public KhachHang getKH(String sdt) {
		// TODO Auto-generated method stub
		KhachHang kh = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "select * from KhachHang where sdtKH = ?";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, sdt);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maKH = rs.getNString("maKH");
				String tenKH = rs.getNString("tenKH");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				String diaChi = rs.getNString("diaChi");
				String sdtKH = rs.getNString("sdtKH");
				String email = rs.getNString("email");
				kh = new KhachHang(maKH, tenKH, sdtKH, gioiTinh, diaChi,email);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kh;
	}

	@Override
	public boolean themKH(KhachHang kh) {
		// TODO Auto-generated method stub
		int n=0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "INSERT INTO KhachHang ([maKH], [tenKH],[sdtKH],[gioiTinh], [diaChi], [email]) VALUES	(?, ?,?,?,?,?)";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, kh.getMaKH());
			statement.setNString(2, kh.getTenKH());
			statement.setNString(3, kh.getSdtKH());
			int gt = kh.isGioiTinh()?1:0;  
			statement.setInt(4, gt);
			
			statement.setNString(5, kh.getDiaChi());
			statement.setNString(6, kh.getEmail());
			n=statement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}

	
	public boolean capNhatKhachHang(KhachHang kh) {
		Connection con = ConnectDB.getInstance().getConnection();
	    PreparedStatement sttm = null;
	    int n = 0;
	    try {
	        sttm = con.prepareStatement ("UPDATE KhachHang SET tenKH = ?, sdtKH = ?, gioiTinh = ?, diaChi = ?, email=? WHERE maKH = ?");
	        sttm.setString(1, kh.getTenKH());
	        sttm.setString(2, kh.getSdtKH());
	        int gt = kh.isGioiTinh() ? 1 : 0;  
	        sttm.setInt(3, gt);
	        sttm.setString(4, kh.getDiaChi());
	        sttm.setString(5, kh.getEmail());
	        sttm.setString(6, kh.getMaKH());
	        
	        n = sttm.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return n > 0;
	}

	
	public KhachHang getKHMaKH(String maKH) {
		// TODO Auto-generated method stub
		KhachHang kh = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "select * from KhachHang where maKH = ?";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, maKH);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				//String maKH = rs.getNString("maKH");
				String tenKH = rs.getNString("tenKH");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				String diaChi = rs.getNString("diaChi");
				String sdtKH = rs.getNString("sdtKH");
				String email = rs.getNString("email");
				kh = new KhachHang(maKH, tenKH, sdtKH, gioiTinh, diaChi,email);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kh;
	}
	
	public static String taoMaKhachHang() {
		int stt=0;
		String id = "KH-";
		int y =(LocalDate.now().getYear());
		String nam=(y+"");
		nam=nam.substring(2, 4);
		//int m = LocalDate.now().getMonthValue();
		//String thang= null;
		//if (m<10) thang="0"+m;
		//else thang=m+"";
		id=id+nam+"%";
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "select stt = count(*) from KhachHang where maKH like ?";
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

	@Override
	public boolean capNhatKhachHang(String maKH, KhachHang khNew) {
		// TODO Auto-generated method stub
		return false;
	}
}
