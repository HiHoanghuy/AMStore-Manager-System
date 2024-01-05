package custom.barChart;

public class ThongKeBanChay {
	private String maSP;
	private String tenSP;
	private int TongSoLuong;
	
	public ThongKeBanChay() {

	}
	public ThongKeBanChay(String maSP, String tenSP, int tongSoLuong) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		TongSoLuong = tongSoLuong;
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
	public int getTongSoLuong() {
		return TongSoLuong;
	}
	public void setTongSoLuong(int tongSoLuong) {
		TongSoLuong = tongSoLuong;
	}
	
}
