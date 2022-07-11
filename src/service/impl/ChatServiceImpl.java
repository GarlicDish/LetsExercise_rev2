package service.impl;

import java.util.ArrayList;

import dao.impl.ChatDAO;
import dto.chat.ChatDTO;
import service.face.ChatService;

public class ChatServiceImpl implements ChatService{
	
	ChatDAO chatDAO = new ChatDAO();
	
	@Override
	public String submitService(String fromID, String toID, String chatContent) {
		System.out.println("[" + fromID + " -> " + toID + "] 메세지 보내기 [메소드]");
		return chatDAO.submit(fromID, toID, chatContent) + "";
	}
	
	@Override
	public String getBox(String userID) {
		System.out.println("[getBox메소드 실행]");
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		ChatDAO chatDAO = new ChatDAO();
		ArrayList<ChatDTO> chatList = chatDAO.getBox(userID);
		if(chatList.size() == 0) {
			System.out.println("여기가 문제");
			return "";
		}
		for(int i = 0; i < chatList.size(); i++) { //채팅 리스트 역순처리
			String unread = "";
			if(userID.equals(chatList.get(i).getToID())) {
				unread = chatDAO.getUnreadChat(chatList.get(i).getFromID(), userID) + "";
				if(unread.equals("0")) unread = "";
			}
			result.append("[{\"value\": \"" + chatList.get(i).getFromID() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getToID() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatContent() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatTime() + "\"},");
			result.append("{\"value\": \"" + unread + "\"}]");
			if(i != chatList.size()-1) result.append(",");
		}
		result.append("], \"last\":\"" + chatList.get(chatList.size() - 1).getChatID() + "\"}");
		return result.toString();
	}

	
	@Override
	public String unreadService(String userID) {
		System.out.println("[" + userID + "] 안읽은 총 메세지 수 [메소드]");
		return chatDAO.getAllUnreadChat(userID) + "";
	}
	
	@Override
	public String getTen(String fromID, String toID) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		ChatDAO chatDAO = new ChatDAO();
		
		// chatList에 채팅 메세지 저장
		ArrayList<ChatDTO> chatList = chatDAO.getChatListByRecent(fromID, toID, 100);
		
		// 불러온 채팅이 없을떄 공백 출력
		if(chatList.size() == 0) return "";
		
		// 불러온 채팅 개수만큼 반복
		for(int i = 0; i < chatList.size(); i++) {
			result.append("[{\"value\": \""  + chatList.get(i).getFromID() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getToID() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatContent() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatTime() + "\"}]");
			if(i != chatList.size() -1) result.append(","); // 마지막 원소가 아니라면 ',' 추가
		}
		// last에 마지막 메세지 chatID 저장
		result.append("], \"last\":\"" + chatList.get(chatList.size() - 1).getChatID() + "\"}");
		
		// 메세지 읽음 기능
		chatDAO.readChat(fromID, toID); 
		
		//채팅 내용 문자열로 리턴
		return result.toString();
	}
	
	@Override
	public String getID(String fromID, String toID, String chatID) {

		System.out.println("["+fromID + " - " + toID + "]" + " 채팅내용 불러오기[메소드]" );
		
		//계속 바뀌는 채팅 내용을 저장하기 위해 StringBuffer 사용
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		ChatDAO chatDAO = new ChatDAO();
		
		// chatList에 채팅 메세지 저장
		ArrayList<ChatDTO> chatList = chatDAO.getChatListByID(fromID, toID, chatID);
		
		// 불러온 채팅이 없을떄 공백 출력
		if(chatList.size() == 0) return "";

		// 불러온 채팅 개수만큼 반복
		for(int i = 0; i < chatList.size(); i++) {
			result.append("[{\"value\": \"" + chatList.get(i).getFromID() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getToID() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatContent() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatTime() + "\"}]");
			if(i != chatList.size() -1) result.append(",");
		}		
		// last에 마지막 메세지 chatID 저장
		result.append("], \"last\":\"" + chatList.get(chatList.size() - 1).getChatID() + "\"}");
	
		// 메세지 읽음 기능
		chatDAO.readChat(fromID, toID);	

		//채팅 내용 문자열로 리턴
		return result.toString();
	}
	
	
}
