package entities;

public class KhachHang {
	private String maKH;
	private String tenKH;
	private String sdtKH;
	private boolean gioiTinh;
	private String diaChi;
	private String email;
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}
	public void setSdtKH(String sdtKH) {
		this.sdtKH = sdtKH;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getMaKH() {
		return maKH;
	}
	public String getTenKH() {
		return tenKH;
	}
	public String getSdtKH() {
		return sdtKH;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}

	
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	
	public KhachHang(String maKH, String tenKH) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
	}
	public KhachHang(String maKH, String tenKH, String sdtKH, boolean gioiTinh, String diaChi, String email) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.sdtKH = sdtKH;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	
	
	
	
}
