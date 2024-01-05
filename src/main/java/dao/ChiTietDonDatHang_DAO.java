package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entities.ChiTietDonDatHang;
import entities.KhachHang;
import entities.SanPham;
import my_Interfaces.I_ChiTietDonDatHang;

public class ChiTietDonDatHang_DAO implements I_ChiTietDonDatHang{
	private SanPham_DAO sanPham_DAO;
	
	@Override
	public List<ChiTietDonDatHang> getDsChiTietDDH(String maDDH) {
		// TODO Auto-generated method stub
		sanPham_DAO = new SanPham_DAO();
		List<ChiTietDonDatHang> dsCTDDH = new ArrayList<ChiTietDonDatHang>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement =null;
		String sql = "select maDDH,sp.maSP, sp.tenSP, ct.giaBan, soLuong\r\n"
				+ "from ChiTietDonDatHang ct join SanPham sp\r\n"
				+ "on ct.maSP = sp.maSP\r\n"
				+ "where maDDH = ?";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, maDDH);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maSP = rs.getNString("maSP");
				String tenSP = rs.getNString("tenSP");
				SanPham sp = sanPham_DAO.getSanPham(maSP);
				double giaBan = rs.getDouble("giaBan");
				int soLuong = rs.getInt("soLuong");
				ChiTietDonDatHang ct = new ChiTietDonDatHang(maDDH, sp, soLuong, giaBan);
				dsCTDDH.add(ct);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsCTDDH;
	}

	@Override
	public boolean themChiTietDDH(ChiTietDonDatHang ctddh) {
		int n=0;
		sanPham_DAO = new SanPham_DAO();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "insert into ChiTietDonDatHang values (?, ?, ? ,?)";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, ctddh.getMaDDH());
			statement.setNString(2, ctddh.getSanPham().getMaSP());
			statement.setDouble(3, ctddh.getGiaBan());
			statement.setInt(4, ctddh.getSoLuong());
			n = statement.executeUpdate();
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}

	@Override
	public boolean xoaChiTietDDH(String maDDH) {
		// TODO Auto-generated method stub
		return false;
	}

}
