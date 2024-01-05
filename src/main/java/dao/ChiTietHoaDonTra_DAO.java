package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connectDB.ConnectDB;
import entities.ChiTietHoaDon;
import entities.ChiTietHoaDonTra;
import entities.SanPham;

public class ChiTietHoaDonTra_DAO {
	private SanPham_DAO sanPham_DAO;
	public ChiTietHoaDonTra get1ChiTietHD(String maDT,String masP) {
		// TODO Auto-generated method stub
		sanPham_DAO = new SanPham_DAO();
		ChiTietHoaDonTra ct = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "select * from ChiTietHoaDonTra where maDT = ? and maSP = ?";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, maDT);
			statement.setNString(2, masP);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maSP = rs.getNString("maSP");
				SanPham sp = sanPham_DAO.getSanPham(maSP);
				double giaBan = rs.getDouble("giaBan");
				int soLuong = rs.getInt("soLuong");
				ct = new ChiTietHoaDonTra(maDT, sp, soLuong, giaBan);
				return ct;
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ct;
	}
	
	public boolean themChiTietHDT(ChiTietHoaDonTra cthd) {
		// TODO Auto-generated method stub
		int n=0;
		sanPham_DAO = new SanPham_DAO();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "insert into ChiTietHoaDonTra values (?, ?, ? ,?)";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, cthd.getMaDT());
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
}
