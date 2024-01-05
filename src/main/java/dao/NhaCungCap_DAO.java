package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entities.NhaCungCap;
import my_Interfaces.I_NhaCungCap;

public class NhaCungCap_DAO implements I_NhaCungCap{

	@Override
	public List<NhaCungCap> getDsNCC() {
		// TODO Auto-generated method stub
		List<NhaCungCap> ds = new ArrayList<NhaCungCap>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from NhaCungCap";
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getNString("maNCC");
				String ten = rs.getNString("tenNCC");
				String sdt = rs.getNString("sdtNCC");
				String diachi = rs.getNString("diaChiNCC");
				NhaCungCap l = new NhaCungCap(ma, ten, sdt, diachi);
				ds.add(l);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}
	
	public NhaCungCap getNCC(String maNCC) {
		// TODO Auto-generated method stub
		NhaCungCap ncc = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "select * from NhaCungCap where maNCC=?";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, maNCC);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String ten = rs.getString("tenNCC");
				String sdt = rs.getString("sdtNCC");
				String diaChi = rs.getString("diaChiNCC");
				ncc = new NhaCungCap(maNCC,ten,sdt,diaChi);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ncc;
	}

	@Override
	public boolean themNCC(NhaCungCap ncc) {
		// TODO Auto-generated method stub
		int n = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "INSERT INTO NhaCungCap(maNCC,tenNCC,sdtNCC,diaChiNCC) VALUES (?, ?, ?, ?)";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, ncc.getMaNCC());
			statement.setNString(2, ncc.getTenNCC());
			statement.setNString(3,ncc.getSdtNCC());
			statement.setNString(4,ncc.getDiaChiNCC());
			n = statement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}

	@Override
	public boolean capNhatNCC(String maNCC, NhaCungCap nccNew) {
		int n=0;
		ConnectDB.getInstance();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "\n"
				+ "Update NhaCungCap set tenNCC = ? , sdtNCC = ?, diaChiNCC = ? \n"
				+ "Where maNCC = ?";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1,nccNew.getTenNCC());
			statement.setNString(2, nccNew.getSdtNCC());
			statement.setNString(3, nccNew.getDiaChiNCC());
			statement.setNString(4, maNCC);
			n= statement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}

	
	public static String taoMaNhaCungCap(String ten) {
		int stt=0;
        String id = "NCC-"+ten+"%";
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select stt = count(*) from NhaCungCap where maNCC like ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setNString(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) stt= rs.getInt("stt")+1;
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id.replace("%", "")+"-"+String.format("%05d", stt);
    }
	
	
	public static String getMaNCC(String tenNCC) {
	    String maNCC = null;
	    ConnectDB.getInstance();
	    Connection con = ConnectDB.getInstance().getConnection();
	    PreparedStatement statement = null;
	    ResultSet rs = null;
	    String sql = "SELECT maNCC FROM NhaCungCap WHERE tenNCC = ?";
	    try {
	        statement = con.prepareStatement(sql);
	        statement.setString(1, tenNCC);
	        rs = statement.executeQuery();
	        if (rs.next()) {
	        	maNCC = rs.getString("maNCC");
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
	    return maNCC;
	}
	
	public List<NhaCungCap> timKiem(String ma, String ten, String sdt, String diaChi){
		List<NhaCungCap> ds = new ArrayList<NhaCungCap>();
		//NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "select * from NhaCungCap\n"
				+ "where maNCC like ? and tenNCC like ?\n"
				+ "and sdtNCC like ? and diaChiNCC like ?";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, ma);
			statement.setNString(2, ten);
			statement.setNString(3, sdt);
			statement.setNString(4, diaChi);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maNCC = rs.getNString("maNCC");
				String tenNCC = rs.getNString("tenNCC");
				String sdtNCC = rs.getNString("sdtNCC");
				String diaChiNCC = rs.getNString("diaChiNCC");
				NhaCungCap ncc = new NhaCungCap(maNCC, tenNCC, sdtNCC, diaChiNCC);
				ds.add(ncc);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}

}
