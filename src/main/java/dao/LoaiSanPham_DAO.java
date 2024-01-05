package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entities.LoaiSanPham;
import my_Interfaces.I_LoaiSanPham;

public class LoaiSanPham_DAO implements I_LoaiSanPham{

	@Override
	public List<LoaiSanPham> getDsLoaiSP() {
		// TODO Auto-generated method stub
		List<LoaiSanPham> ds = new ArrayList<LoaiSanPham>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from LoaiSanPham";
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getNString("maLoai");
				String ten = rs.getNString("tenLoai");
				LoaiSanPham l = new LoaiSanPham(ma, ten);
				ds.add(l);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}
	
	public static String getMaLoaiSanPham(String tenLoai) {
	    String maLoai = null;
	    ConnectDB.getInstance();
	    Connection con = ConnectDB.getInstance().getConnection();
	    PreparedStatement statement = null;
	    ResultSet rs = null;
	    String sql = "SELECT maLoai FROM LoaiSanPham WHERE tenLoai = ?";
	    try {
	        statement = con.prepareStatement(sql);
	        statement.setString(1, tenLoai);
	        rs = statement.executeQuery();
	        if (rs.next()) {
	            maLoai = rs.getString("maLoai");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (statement != null) {
	                statement.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return maLoai;
	}
	public static String taoMaLoai() {
		int stt=0;
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String sql = "select stt = count(*) from LoaiSanPham";    
        try {
        	Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) stt= rs.getInt("stt")+1;
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "L"+String.format("%03d", stt);
    }
	public boolean themLoai(LoaiSanPham loai) {
		int n=0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "INSERT INTO [LoaiSanPham] ([maLoai], [tenLoai]) VALUES(?, ?)";

		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, loai.getMaLoai());
			statement.setNString(2, loai.getTenLoai());
			
			n=statement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}
}
