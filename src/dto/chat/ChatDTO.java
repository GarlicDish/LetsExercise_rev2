package dto.chat;

public class ChatDTO {
	
	private int chatID;			// 메세지 구분		
	private String fromID;		// 보낸사람
	private String toID;		// 받는사람
	private String chatContent;	// 채팅내용
	private String chatTime;	// 보낸시간
	
	
	public int getChatID() {
		return chatID;
	}
	public void setChatID(int chatID) {
		this.chatID = chatID;
	}
	public String getFromID() {
		return fromID;
	}
	public void setFromID(String fromID) {
		this.fromID = fromID;
	}
	public String getToID() {
		return toID;
	}
	public void setToID(String toID) {
		this.toID = toID;
	}
	public String getChatContent() {
		return chatContent;
	}
	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}
	public String getChatTime() {
		return chatTime;
	}
	public void setChatTime(String chatTime) {
		this.chatTime = chatTime;
	}
	
	
	
	
}
