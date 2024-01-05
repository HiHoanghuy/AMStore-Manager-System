package my_Interfaces;

import java.util.List;

import entities.ChuongTrinhKhuyenMai;

public interface I_ChuongTrinhKhuyenMai {
	public List<ChuongTrinhKhuyenMai> getDsCTKM();
	public ChuongTrinhKhuyenMai getCTKM(String maKM);
	public boolean themCTKM(ChuongTrinhKhuyenMai ctkm);
	public boolean capNhatCTKM(String maKM, ChuongTrinhKhuyenMai ctkmNew);
}
