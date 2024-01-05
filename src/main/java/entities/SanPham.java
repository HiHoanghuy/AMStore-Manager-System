package entities;

import java.time.LocalDateTime;


public class SanPham {
	private String maSP;
	private String tenSP;
	private double giaNhap;
	private double giaBan;
	private LoaiSanPham loaiSP; 
	private byte[] hinhAnh;
	private KichCo kichCo;
	private ChatLieu chatLieu;
	private MauSac mauSac;
	private int soLuongTon;
	private LocalDateTime ngayNhap;
	private String trangThai;
	private NhaCungCap nhaCungCap;
	
	public SanPham(String maSP, String tenSP, double giaNhap, double giaBan, LoaiSanPham loaiSP, byte[] hinhAnh, KichCo kichCo,
			ChatLieu chatLieu, MauSac mauSac, int soLuongTon, LocalDateTime ngayNhap, String trangThai,
			NhaCungCap nhaCungCap) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.giaNhap = giaNhap;
		this.giaBan = giaBan;
		this.loaiSP = loaiSP;
		this.hinhAnh = hinhAnh;
		this.kichCo = kichCo;
		this.chatLieu = chatLieu;
		this.mauSac = mauSac;
		this.soLuongTon = soLuongTon;
		this.ngayNhap = ngayNhap;
		this.trangThai = trangThai;
		this.nhaCungCap = nhaCungCap;
	}
	
	public SanPham(String maSP, String tenSP) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
	}

	public SanPham() {
		super();
	}
	public String getMaSP() {
		return maSP;
	}
	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}
	public String getTenSP() {
		return tenSP;
	}
	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}
	public double getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}
	public LoaiSanPham getLoaiSP() {
		return loaiSP;
	}
	public void setLoaiSP(LoaiSanPham loaiSP) {
		this.loaiSP = loaiSP;
	}
	public byte[] getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(byte[] hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	public KichCo getKichCo() {
		return kichCo;
	}
	public void setKichCo(KichCo kichCo) {
		this.kichCo = kichCo;
	}
	public ChatLieu getChatLieu() {
		return chatLieu;
	}
	public void setChatLieu(ChatLieu chatLieu) {
		this.chatLieu = chatLieu;
	}
	public MauSac getMauSac() {
		return mauSac;
	}
	public void setMauSac(MauSac mauSac) {
		this.mauSac = mauSac;
	}
	public int getSoLuongTon() {
		return soLuongTon;
	}
	public void setSoLuongTon(int soLuongTon) {
		this.soLuongTon = soLuongTon;
	}
	public LocalDateTime getNgayNhap() {
		return ngayNhap;
	}
	public void setNgayNhap(LocalDateTime ngayNhap) {
		this.ngayNhap = ngayNhap;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}
	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}
	public double getGiaNhap() {
		return giaNhap;
	}
	public void setGiaNhap(double giaNhap) {
		this.giaNhap = giaNhap;
	}

	
	
	
	
}
