package entities;

public class NhaCungCap {
	private String maNCC;
	private String tenNCC;
	private String sdtNCC;
	private String diaChiNCC;
	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}
	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}
	public void setSdtNCC(String sdtNCC) {
		this.sdtNCC = sdtNCC;
	}
	public void setDiaChiNCC(String diaChiNCC) {
		this.diaChiNCC = diaChiNCC;
	}
	public String getMaNCC() {
		return maNCC;
	}
	public String getTenNCC() {
		return tenNCC;
	}
	public String getSdtNCC() {
		return sdtNCC;
	}
	public String getDiaChiNCC() {
		return diaChiNCC;
	}
	public NhaCungCap() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NhaCungCap(String maNCC, String tenNCC, String sdtNCC, String diaChiNCC) {
		super();
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		this.sdtNCC = sdtNCC;
		this.diaChiNCC = diaChiNCC;
	}
	public NhaCungCap(String maNCC, String tenNCC) {
		super();
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
	}
	
	
	
}
