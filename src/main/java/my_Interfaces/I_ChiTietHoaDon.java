package my_Interfaces;

import java.util.List;

import entities.ChiTietHoaDon;

public interface I_ChiTietHoaDon {
	public List<ChiTietHoaDon> getChiTietHD(String maHD);
	public boolean themChiTietHD(ChiTietHoaDon cthd);
	ChiTietHoaDon get1ChiTietHD(String maHD, String masP);
}
