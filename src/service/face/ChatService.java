package service.face;

public interface ChatService {
	
	/**
	 * 보낸 메세지 연결된 db에 저장하기
	 * 
	 * @param fromID
	 * @param toID
	 * @param chatContent
	 * @return 메세지 보내기 성공시 1 , 데이터베이스 오류발생시 -1
	 */
	public String submitService(String fromID, String toID, String chatContent);
	
	/**
	 * 존재하는 채팅방리스트 보여주기
	 * 
	 * @param userID
	 * @return 존재하는 채팅방 리스트 
	 */
	public String getBox(String userID);
	
	/**
	 * userID 와 연관된 모든 메세지 중 안 읽은 메세지 수 보여주기
	 * 
	 * @param userID
	 * @return 안읽은 총 메세지 수 
	 */
	public String unreadService(String userID);
	
	
	/**
	 * 채팅 메세지 리스트 10개 보여주기
	 * 
	 * @param fromID
	 * @param toID
	 * @return 채팅 메세지 내용을 담은 StringBuffer 문자열
	 */
	public String getTen(String fromID, String toID);
	
	
	/**
	 * 채팅 메세지 리스트 전체 보여주기
	 * 
	 * @param fromID
	 * @param toID
	 * @param chatID
	 * @return 채팅 메세지 내용을 담은 StringBuffer 문자열
	 */
	public String getID(String fromID, String toID, String chatID);
	
	
}
