package entities;

import java.time.LocalDateTime;

public class DonDatHang {
	private String maDDH;
	private KhachHang khachHang;
	private LocalDateTime ngayLap;
	private NhanVien nhanVien;
	private double tongTienDDH;
	private double tienKhachTra;
	private String tinhTrangThanhToan;

	public DonDatHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DonDatHang(String maDDH, KhachHang khachHang, LocalDateTime ngayLap, NhanVien nhanVien,double tongTienDDH, double tienKhachTra, String tinhTrangThanhToan) {
		super();
		this.maDDH = maDDH;
		this.khachHang = khachHang;
		this.ngayLap = ngayLap;
		this.nhanVien = nhanVien;
		this.tongTienDDH = tongTienDDH;
		this.tienKhachTra = tienKhachTra;
		this.tinhTrangThanhToan = tinhTrangThanhToan;
	}
	
	
	
	public String getMaDDH() {
		return maDDH;
	}
	public void setMaDDH(String maDDH) {
		this.maDDH = maDDH;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
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
	public String getTinhTrangThanhToan() {
		return tinhTrangThanhToan;
	}
	public void setTinhTrangThanhToan(String tinhTrangThanhToan) {
		this.tinhTrangThanhToan = tinhTrangThanhToan;
	}
	
	
	public double getTongTienDDH() {
		return tongTienDDH;
	}
	public void setTongTienDDH(double tongTienDDH) {
		this.tongTienDDH = tongTienDDH;
	}
	public double getTienKhachTra() {
		return tienKhachTra;
	}
	public void setTienKhachTra(double tienKhachTra) {
		this.tienKhachTra = tienKhachTra;
	}
	@Override
	public String toString() {
		return "DonDatHang [maDDH=" + maDDH + ", khachHang=" + khachHang + ", ngayLap=" + ngayLap + ", nhanVien="
				+ nhanVien + ", tinhTrangThanhToan=" + tinhTrangThanhToan + "]";
	}
	
	
}
