package my_Interfaces;

import java.util.List;

import entities.NhanVien;

public interface I_NhanVien {
	public List<NhanVien> getDsNhanVien();
	public NhanVien getNhanVien(String maNV);
	public boolean themNhanVien(NhanVien nv);
	public boolean capNhatNhanVien(String maNV, NhanVien nvNew);
}
