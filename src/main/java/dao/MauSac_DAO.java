package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entities.MauSac;
import my_Interfaces.I_MauSac;

public class MauSac_DAO implements I_MauSac{

	@Override
	public List<MauSac> getDsMauSac() {
		List<MauSac> ds = new ArrayList<MauSac>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from MauSac";
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getNString("maMauSac");
				String ten = rs.getNString("tenMauSac");
				MauSac l = new MauSac(ma, ten);
				ds.add(l);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}

	@Override
	public boolean themMauSac(MauSac ms) {
		int n=0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "INSERT INTO MauSac ([maMauSac], [tenMauSac]) VALUES	(?, ?)";

		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, ms.getMaMauSac());
			statement.setNString(2, ms.getTenMauSac());
			
			n=statement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;

	}
	public static String getMaMauSac(String tenMauSac) {
	    String maMauSac = null;
	    ConnectDB.getInstance();
	    Connection con = ConnectDB.getInstance().getConnection();
	    PreparedStatement statement = null;
	    ResultSet rs = null;
	    String sql = "SELECT maMauSac FROM MauSac WHERE tenMauSac = ?";
	    try {
	        statement = con.prepareStatement(sql);
	        statement.setString(1, tenMauSac);
	        rs = statement.executeQuery();
	        if (rs.next()) {
	            maMauSac = rs.getString("maMauSac");
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
	    return maMauSac;
	}
	public static String taoMaMauSac() {
		int stt=0;
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String sql = "select stt = count(*) from MauSac";    
        try {
        	Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) stt= rs.getInt("stt")+1;
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "MS"+String.format("%03d", stt);
    }
}
