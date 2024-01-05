package entities;

import java.time.LocalDateTime;

public class HoaDon {
	private String maHD;
	private LocalDateTime ngayLap;
	private NhanVien nhanVien;
	private KhachHang khachHang;
	private ChuongTrinhKhuyenMai ctKhuyenMai;
	private double tongTienHD;
	private double tienKhachTra;
	public HoaDon(String maHD, LocalDateTime ngayLap, NhanVien nhanVien, KhachHang khachHang,
			  ChuongTrinhKhuyenMai ctKhuyenMai, double tongTienHD, double tienKhachTra) {
		super();
		this.maHD = maHD;
		this.ngayLap = ngayLap;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.ctKhuyenMai = ctKhuyenMai;
		this.tongTienHD = tongTienHD;
		this.tienKhachTra = tienKhachTra;
	}
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	public LocalDateTime getNgayLap() {
		return ngayLap;
	}
	public void setNgayLap(LocalDateTime ngayLap) {
		this.ngayLap = ngayLap;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public ChuongTrinhKhuyenMai getCtKhuyenMai() {
		return ctKhuyenMai;
	}
	public void setCtKhuyenMai(ChuongTrinhKhuyenMai ctKhuyenMai) {
		this.ctKhuyenMai = ctKhuyenMai;
	}
	public double getTongTienHD() {
		return tongTienHD;
	}
	public void setTongTienHD(double tongTienHD) {
		this.tongTienHD = tongTienHD;
	}
	public double getTienKhachTra() {
		return tienKhachTra;
	}
	public void setTienKhachTra(double tienKhachTra) {
		this.tienKhachTra = tienKhachTra;
	}
	
	
}
