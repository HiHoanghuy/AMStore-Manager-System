package entities;

public class ChiTietDonDatHang {
	private String maDDH;
	private SanPham sanPham;
	private int soLuong;
	private double giaBan;

	public ChiTietDonDatHang(String maDDH, SanPham sanPham, int soLuong, double giaBan) {
		super();
		this.maDDH = maDDH;
		this.sanPham = sanPham;
		this.soLuong = soLuong;
		this.giaBan =giaBan;
	}
	public String getMaDDH() {
		return maDDH;
	}
	public void setMaDDH(String maDDH) {
		this.maDDH = maDDH;
	}
	public SanPham getSanPham() {
		return sanPham;
	}
	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public double getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}
	
	
}
