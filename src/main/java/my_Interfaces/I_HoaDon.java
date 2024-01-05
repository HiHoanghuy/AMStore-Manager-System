package my_Interfaces;

import java.util.List;

import entities.HoaDon;

public interface I_HoaDon {
	public List<HoaDon> getDsHoaDon();
	public HoaDon getHD(String maHD);
}
