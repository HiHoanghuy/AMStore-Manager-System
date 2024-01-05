package entities;

public class TaiKhoan {
	private String maNV;
	private String matKhau;
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public TaiKhoan(String maNV, String matKhau) {
		super();
		this.maNV = maNV;
		this.matKhau = matKhau;
	}
	public TaiKhoan() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMaNV() {
		return maNV;
	}
	public String getMatKhau() {
		return matKhau;
	}
	
	
	
}
