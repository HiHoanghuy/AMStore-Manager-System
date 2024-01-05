package entities;

public class ChiTietHoaDonTra {
	private String maDT;
	private SanPham sanPham;
	private int soLuong;
	private double giaBan;
	public ChiTietHoaDonTra() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChiTietHoaDonTra(String maDT, SanPham sanPham, int soLuong, double giaBan) {
		super();
		this.maDT = maDT;
		this.sanPham = sanPham;
		this.soLuong = soLuong;
		this.giaBan = giaBan;
	}
	public String getMaDT() {
		return maDT;
	}
	public void setMaDT(String maDT) {
		this.maDT = maDT;
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
	public double getThanhTien() {
		return this.giaBan*this.soLuong;
	}
}
