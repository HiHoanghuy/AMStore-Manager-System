package my_Interfaces;

import java.util.List;

import entities.ChiTietDonDatHang;

public interface I_ChiTietDonDatHang {
	public List<ChiTietDonDatHang> getDsChiTietDDH(String maDDH);
	public boolean themChiTietDDH(ChiTietDonDatHang ctddh);
	public boolean xoaChiTietDDH(String maDDH);
}
