package my_Interfaces;

import java.util.List;

import entities.NhaCungCap;

public interface I_NhaCungCap {
	public List<NhaCungCap> getDsNCC();
	public boolean themNCC(NhaCungCap ncc);
	public boolean capNhatNCC(String maNCC, NhaCungCap nccNew);
}
