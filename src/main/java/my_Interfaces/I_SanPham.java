package my_Interfaces;

import java.util.List;

import entities.SanPham;

public interface I_SanPham {
	public List<SanPham> getDsSanPham();
	public SanPham getSanPham(String maSP);
	public boolean themSanPham(SanPham sp);
	public boolean capNhatSanPham(String maSP, SanPham spNew);
	public boolean themSoLuongTon(String maSP, int soLuong);
}
