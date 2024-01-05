package entities;

public class MauSac {
	private String maMauSac;
	private String tenMauSac;
	public MauSac(String maMauSac, String tenMauSac) {
		this.maMauSac = maMauSac;
		this.tenMauSac = tenMauSac;
	}
	public MauSac(String maMauSac) {
		this.maMauSac = maMauSac;
	}
	public String getMaMauSac() {
		return maMauSac;
	}
	public void setMaMauSac(String maMauSac) {
		this.maMauSac = maMauSac;
	}
	public String getTenMauSac() {
		return tenMauSac;
	}
	public void setTenMauSac(String tenMauSac) {
		this.tenMauSac = tenMauSac;
	}
	
	
}
