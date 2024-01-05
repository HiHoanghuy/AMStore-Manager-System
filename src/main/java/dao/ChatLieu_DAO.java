package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entities.ChatLieu;
import my_Interfaces.I_ChatLieu;

public class ChatLieu_DAO implements I_ChatLieu{

	@Override
	public List<ChatLieu> getDsChatLieu() {
		// TODO Auto-generated method stub
		List<ChatLieu> ds = new ArrayList<ChatLieu>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from ChatLieu";
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getNString("maChatLieu");
				String ten = rs.getNString("tenChatLieu");
				ChatLieu l = new ChatLieu(ma, ten);
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
	public boolean themChatLieu(ChatLieu cl) {
		int n=0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "INSERT INTO ChatLieu (maChatLieu, tenChatLieu) VALUES	(?, ?)";

		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, cl.getMaChatLieu());
			statement.setNString(2, cl.getTenChatLieu());
			
			n=statement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;

	}
	public static String getMaChatLieu(String tenChatLieu) {
	    String maChatLieu = null;
	    ConnectDB.getInstance();
	    Connection con = ConnectDB.getInstance().getConnection();
	    PreparedStatement statement = null;
	    ResultSet rs = null;
	    String sql = "SELECT maChatLieu FROM ChatLieu WHERE tenChatLieu = ?";
	    try {
	        statement = con.prepareStatement(sql);
	        statement.setString(1, tenChatLieu);
	        rs = statement.executeQuery();
	        if (rs.next()) {
	            maChatLieu = rs.getString("maChatLieu");
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
	    return maChatLieu;
	}
	public static String taoMaChatLieu() {
		int stt=0;
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String sql = "select stt = count(*) from ChatLieu";    
        try {
        	Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) stt= rs.getInt("stt")+1;
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "CL"+String.format("%03d", stt);
    }
}
