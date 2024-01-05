package entities;

import java.time.LocalDateTime;

public class NhanVien {
	private String maNV;
	private String tenNV;
	private String sdt;
	private boolean gioiTinh;
	private LocalDateTime ngaySinh;
	private String chucVu;
	private String caLamViec;
	private String diaChi;
	private String email;
	private String cccd;
	private TaiKhoan taiKhoan;
	private double phuCap;
	private double heSoLuong;
	private double luongCoBan;
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}
	public void setCaLamViec(String caLamViec) {
		this.caLamViec = caLamViec;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public NhanVien(String maNV, String tenNV, String sdt, boolean gioiTinh, LocalDateTime ngaySinh, String chucVu, String caLamViec,
			String diaChi, String email, String cccd, TaiKhoan taiKhoan, double phuCap, double heSoLuong, double luongCoBan) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.sdt = sdt;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.chucVu = chucVu;
		this.caLamViec = caLamViec;
		this.diaChi = diaChi;
		this.email = email;
		this.cccd = cccd;
		this.taiKhoan = taiKhoan;
		this.heSoLuong=heSoLuong;
		this.luongCoBan=luongCoBan;
		this.phuCap = phuCap;
	}
	
	public NhanVien(String maNV, String tenNV) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
	}
	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMaNV() {
		return maNV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public String getSdt() {
		return sdt;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public String getChucVu() {
		return chucVu;
	}
	public String getCaLamViec() {
		return caLamViec;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public String getEmail() {
		return email;
	}
	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	public LocalDateTime getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(LocalDateTime ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public double getPhuCap() {
		return phuCap;
	}
	public void setPhuCap(double phuCap) {
		this.phuCap = phuCap;
	}
	
	public double getHeSoLuong() {
		return heSoLuong;
	}
	public void setHeSoLuong(double heSoLuong) {
		this.heSoLuong = heSoLuong;
	}
	public double getLuongCoBan() {
		return luongCoBan;
	}
	public void setLuongCoBan(double luongCoBan) {
		this.luongCoBan = luongCoBan;
	}
	public double tinhLuong() {
		return luongCoBan*heSoLuong+phuCap;
	}
	public String getCccd() {
		return cccd;
	}
	public void setCccd(String cccd) {
		this.cccd = cccd;
	}
	
	
}
