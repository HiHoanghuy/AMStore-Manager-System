package entities;

public class ChiTietHoaDon {
	private String maHD;
	private SanPham sanPham;
	private int soLuong;
	private double giaBan;
	public ChiTietHoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public ChiTietHoaDon(String maHD, SanPham sanPham, int soLuong, double giaBan) {
		super();
		this.maHD = maHD;
		this.sanPham = sanPham;
		this.soLuong = soLuong;
		this.giaBan = giaBan;
	}



	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
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

	public double getThanhTien() {
		return giaBan*soLuong;
	}



	public double getGiaBan() {
		return giaBan;
	}



	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}
	
	
}
