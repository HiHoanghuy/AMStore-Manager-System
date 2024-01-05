package entities;

import java.time.LocalDateTime;

public class HoaDonTra {
	private String maDT;
	private LocalDateTime ngayTra;
	private NhanVien nhanVien;
	private KhachHang khachHang;
	private double tongTienTra;
	private HoaDon hoaDon;
	public HoaDonTra(String maDT, LocalDateTime ngayTra, NhanVien nhanVien, KhachHang khachHang, double tongTienTra,
			HoaDon hoaDon) {
		super();
		this.maDT = maDT;
		this.ngayTra = ngayTra;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.tongTienTra = tongTienTra;
		this.hoaDon = hoaDon;
	}
	public String getMaDT() {
		return maDT;
	}
	public void setMaDT(String maDT) {
		this.maDT = maDT;
	}
	public LocalDateTime getNgayTra() {
		return ngayTra;
	}
	public void setNgayTra(LocalDateTime ngayTra) {
		this.ngayTra = ngayTra;
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
	public double getTongTienTra() {
		return tongTienTra;
	}
	public void setTongTienTra(double tongTienTra) {
		this.tongTienTra = tongTienTra;
	}
	public HoaDon getHoaDon() {
		return hoaDon;
	}
	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}
}
