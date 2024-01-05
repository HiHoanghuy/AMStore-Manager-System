package my_Interfaces;

import java.util.List;

import entities.ChatLieu;

public interface I_ChatLieu {
	public List<ChatLieu> getDsChatLieu();
	public boolean themChatLieu(ChatLieu cl);
}
