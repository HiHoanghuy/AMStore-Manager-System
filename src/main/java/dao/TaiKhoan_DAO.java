package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connectDB.ConnectDB;
import entities.TaiKhoan;
import my_Interfaces.I_TaiKhoan;

public class TaiKhoan_DAO implements I_TaiKhoan{

	@Override
	public TaiKhoan getTaiKhoan(String maNV) {
		// TODO Auto-generated method stub
		TaiKhoan tk = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "select * from TaiKhoan where maNV = ?";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, maNV);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String matKhau = rs.getNString("matKhau");
				tk = new TaiKhoan(maNV, matKhau);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return tk;
	}
	
	public boolean themTaiKhoan(TaiKhoan tk) {
		int n=0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "insert into TaiKhoan values (?, ?)";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, tk.getMaNV());
			statement.setNString(2, tk.getMatKhau());
			n = statement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}
	
	public boolean capNhatTaiKhoan(String maNV, TaiKhoan tk) {
		int n=0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "update TaiKhoan set matKhau=? where maNV = ?";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, tk.getMatKhau());
			statement.setNString(2, maNV);
			n=  statement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}
}
