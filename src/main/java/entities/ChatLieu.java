package entities;

public class ChatLieu {
	private String maChatLieu;
	private String tenChatLieu;
	public ChatLieu(String maChatLieu, String tenChatLieu) {
		this.maChatLieu = maChatLieu;
		this.tenChatLieu = tenChatLieu;
	}
	public ChatLieu(String maChatLieu) {
		this.maChatLieu = maChatLieu;
	}
	public String getMaChatLieu() {
		return maChatLieu;
	}
	public void setMaChatLieu(String maChatLieu) {
		this.maChatLieu = maChatLieu;
	}
	public String getTenChatLieu() {
		return tenChatLieu;
	}
	public void setTenChatLieu(String tenChatLieu) {
		this.tenChatLieu = tenChatLieu;
	}
	
}
