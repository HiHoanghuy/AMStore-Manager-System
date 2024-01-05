package custom.barChart;

public class ThongKeTonKho {
	private String maSP;
	private String tenSP;
	private int tongSoLuong;
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
		return tongSoLuong;
	}
	public void setTongSoLuong(int tongSoLuong) {
		this.tongSoLuong = tongSoLuong;
	}
	public ThongKeTonKho(String maSP, String tenSP, int tongSoLuong) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.tongSoLuong = tongSoLuong;
	}
	public ThongKeTonKho(String tenSP, int tongSoLuong) {
		super();
		this.tenSP = tenSP;
		this.tongSoLuong = tongSoLuong;
	}
	public ThongKeTonKho() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ThongKeTonKho [maSP=" + maSP + ", tenSP=" + tenSP + ", tongSoLuong=" + tongSoLuong + "]";
	}

	
}
