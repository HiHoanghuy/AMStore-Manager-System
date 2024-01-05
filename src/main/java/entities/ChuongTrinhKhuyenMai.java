package entities;

import java.time.LocalDateTime;

public class ChuongTrinhKhuyenMai {
	private String maKM;
	private double phanTramKhuyenMai;
	private LocalDateTime ngayBatDau;
	private LocalDateTime ngayKetThuc;
	private String trangThai;
	public ChuongTrinhKhuyenMai(String maKM, double phanTramKhuyenMai, LocalDateTime ngayBatDau,
			LocalDateTime ngayKetThuc, String trangThai) {
		super();
		this.maKM = maKM;
		this.phanTramKhuyenMai = phanTramKhuyenMai;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.trangThai = setTrangThai();
	}
	public ChuongTrinhKhuyenMai() {
		super();
	}
	public String getMaKM() {
		return maKM;
	}
	public void setMaKM(String maKM) {
		this.maKM = maKM;
	}
	public double getPhanTramKhuyenMai() {
		return phanTramKhuyenMai;
	}
	public void setPhanTramKhuyenMai(double phanTramKhuyenMai) {
		this.phanTramKhuyenMai = phanTramKhuyenMai;
	}
	public LocalDateTime getNgayBatDau() {
		return ngayBatDau;
	}
	public void setNgayBatDau(LocalDateTime ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}
	public LocalDateTime getNgayKetThuc() {
		return ngayKetThuc;
	}
	public void setNgayKetThuc(LocalDateTime ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}
	public String getTrangThai() {
		return trangThai;
	}
	
	private String setTrangThai() {
		if (ngayBatDau.isAfter(LocalDateTime.now()) && ngayKetThuc.isAfter(LocalDateTime.now())) return "Chưa kích hoạt";
		else if (ngayBatDau.isBefore(LocalDateTime.now()) && ngayKetThuc.isBefore(LocalDateTime.now())) return "Hết hạn";
		else return "Đang diễn ra";
	}
}
