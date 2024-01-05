package my_Interfaces;

import java.util.List;

import entities.DonDatHang;

public interface I_DonDatHang {
	public List<DonDatHang> getDsDonDatHang();
	public DonDatHang getDDH(String maDDH);
}
