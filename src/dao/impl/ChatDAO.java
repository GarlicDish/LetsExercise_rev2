package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.chat.ChatDTO;

public class ChatDAO {
	
	DataSource dataSource;
	
	//DB연결 생성자
	public ChatDAO() {
		try {
			// JNDI를 통해 커넥션 획득 - dataSource에 저장
			InitialContext initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/UserChat");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//채팅 내용 불러오기
	public ArrayList<ChatDTO> getChatListByID(String fromID,String toID, String chatID) {
		
		//채팅방 내용을 담을 ArrayList - ChatDTO타입만 허용
		ArrayList<ChatDTO> chatList = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 1대1 채팅방 내용 불러오기
		String SQL = "SELECT *" + 
					 "  FROM CHAT" + 
					 " WHERE ((fromID = ? AND toID = ?) OR (fromID = ? AND toID = ?))" + 
				 	 "   AND chatID > ?" + 
					 " ORDER BY chatTime"
					 ;
		try {
			// context.xml에 있는 오라클 주소에 접근(컨넥션) 
			conn = dataSource.getConnection();
			
			// SQL문장을 데이터베이스에 입력한뒤 결과 반환
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			pstmt.setString(3, toID);
			pstmt.setString(4, fromID);
			pstmt.setInt(5, Integer.parseInt(chatID));
			rs = pstmt.executeQuery();
			
			// ArrayList를 생성하여 주소 저장 - ChatDTO타입의 데이터만 저장 가능
			chatList = new ArrayList<ChatDTO>();
			
			// 채팅 개수만큼 반복
			while(rs.next()) {

				// ChatDTO 객체 생성
				ChatDTO chat = new ChatDTO();
				
				// " ", <, >, \n 처리
				chat.setChatID(rs.getInt("chatID"));
				chat.setFromID(rs.getString("fromID").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt").replaceAll("\n","<br>" ));
				chat.setToID(rs.getString("toID").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt").replaceAll("\n","<br>" ));
				chat.setChatContent(rs.getString("chatContent").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt").replaceAll("\n","<br>" ));
				
				// 시간만 추출
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11, 13));
				String timeType = "오전";
				if(chatTime >= 12) {
					timeType = "오후";
					chatTime -= 12;
				}
				
				// (날짜 + 오전(오후) + 시간) 출력 
				chat.setChatTime(rs.getString("chatTime").substring(0, 11) + " " + timeType + " " + chatTime + ":" + rs.getString("chatTime").substring(14, 16) + "");
				
				// chatList에 chat정보 추가
				chatList.add(chat);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return chatList; // 채팅 리스트 반환 
	}
	
	// 위에 getChatListByID와 같은 기능이지만 불러오는 채팅수 제한이 있음
	public ArrayList<ChatDTO> getChatListByRecent(String fromID,String toID, int number) {

		//채팅방 내용을 담을 ArrayList - ChatDTO타입만 허용
		ArrayList<ChatDTO> chatList = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 1대1 채팅방 내용을 number만큼 불러오기 불러오기
		String SQL = "SELECT * " + 
				   	 "  FROM CHAT" + 
					 " WHERE ((fromID = ? AND toID = ?) OR (fromID = ? AND toID = ?))" + 
					 "   AND chatID > (SELECT MAX(chatID) - ?" + 
					 "                   FROM CHAT" + 
					 "                  WHERE (fromID = ? AND toID = ?) OR (fromID = ? AND toID = ?))" + 
					 " ORDER BY chatTime"  
					 ;
		try {
			// context.xml에 있는 오라클 주소에 접근(컨넥션) 
			conn = dataSource.getConnection();
			
			// SQL문장을 데이터베이스에 입력한뒤 결과 반환
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			pstmt.setString(3, toID);
			pstmt.setString(4, fromID);
			pstmt.setInt(5, number); //보여줄 채팅 수
			pstmt.setString(6, fromID);
			pstmt.setString(7, toID);
			pstmt.setString(8, toID);
			pstmt.setString(9, fromID);
			rs = pstmt.executeQuery();
			
			// ArrayList를 생성하여 주소 저장 - ChatDTO타입의 데이터만 저장 가능
			chatList= new ArrayList<ChatDTO>();
			
			// 채팅 개수만큼 반복
			while(rs.next()) {
				
				//ChatDTO객체 생성
				ChatDTO chat = new ChatDTO();
				
				// " ", <, >, \n 처리
				chat.setChatID(rs.getInt("chatID"));
				chat.setFromID(rs.getString("fromID").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt").replaceAll("\n","<br>" ));
				chat.setToID(rs.getString("toID").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt").replaceAll("\n","<br>" ));
				chat.setChatContent(rs.getString("chatContent").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt").replaceAll("\n","<br>" ));
				
				// 시간만 추출
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11, 13));
				String timeType = "오전";
				if(chatTime >= 12) {
					timeType = "오후";
					chatTime -= 12;
				}
				
				// (날짜 + 오전(오후) + 시간) 출력 
				chat.setChatTime(rs.getString("chatTime").substring(0, 11) + " " + timeType + " " + chatTime + ":" + rs.getString("chatTime").substring(14, 16) + "");
				
				// chatList에 chat정보 추가
				chatList.add(chat);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return chatList; // 채팅 리스트 반환 
	}
	
	// 채팅 보내기
	public int submit(String fromID,String toID, String chatContent) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//전달받은 fromID, toID, chatContent 저장
		String SQL = " INSERT" + 
					 "   INTO CHAT" + 
					 " VALUES (chat_seq.nextval, ?, ?, ?, sysdate, 0)" 
					 ;
		
		try {
			// context.xml에 있는 오라클 주소에 접근(컨넥션) 
			conn = dataSource.getConnection();
			
			// SQL문장을 데이터베이스에 입력한뒤 결과 반환
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			pstmt.setString(3, chatContent);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1; // 데이터베이스 오류
	}
	
	// 읽음 표시 처리
	public int readChat(String fromID, String toID) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 메시지읽음 속성값 1로 변경
		String SQL = "UPDATE CHAT" + 
					 "   SET chatRead = 1" + 
					 " WHERE (fromID = ? AND toID  = ?)";
		
		try {
			// context.xml에 있는 오라클 주소에 접근(컨넥션) 
			conn = dataSource.getConnection();
			
			// SQL문장을 데이터베이스에 입력한뒤 결과 반환
			pstmt = conn.prepareStatement(SQL);

			//fromID에 저장된 유저가 메세지를 읽었을때 적용되는 메세지 
			// -> fromID한테 보낸 메세지를 읽음처리 함
			pstmt.setString(1, toID);
			pstmt.setString(2, fromID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return -1; //데이터베이스 오류
	}
	
	// 전체 메세지중 안읽은 메세지 수
	public int getAllUnreadChat(String userID) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 메세지읽음 속성값이 0 인 chatID의 개수
		String SQL = "SELECT COUNT(chatID)" + 
					 "  FROM CHAT" + 
					 " WHERE toID = ? AND chatRead = 0";
		try {
			// context.xml에 있는 오라클 주소에 접근(컨넥션) 
			conn = dataSource.getConnection();
			
			// SQL문장을 데이터베이스에 입력한뒤 결과 반환
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			
			// 안읽은 메세지가 있을시 채팅 개수 반환
			if(rs.next()) {
				return rs.getInt("COUNT(chatID)");
			}
			// 안읽은 메세지가 없으면 0 반환
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return -1; //데이터베이스 오류
	}
	
	//채팅방 메세지중 안읽은 메세지 수
	public int getUnreadChat(String fromID, String toID) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 채팅방 내 메세지중 안읽은 메세지 수 출력
		String SQL = "SELECT COUNT(chatID)" + 
					 "  FROM CHAT" + 
					 " WHERE fromID = ? AND toID = ? AND chatRead = 0";
		try {
			// context.xml에 있는 오라클 주소에 접근(컨넥션) 
			conn = dataSource.getConnection();
			
			// SQL문장을 데이터베이스에 입력한뒤 결과 반환
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			rs = pstmt.executeQuery();
		
			// 안읽은 메세지가 있을시 채팅 개수 반환
			if(rs.next()) {
				return rs.getInt("COUNT(chatID)");
			}
			// 안읽은 메세지가 없으면 0 반환
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return -1; //데이터베이스 오류
	}
	
	//채팅방 리스트 불러오기
	public ArrayList<ChatDTO> getBox(String userID) {
		
		//채팅방 내용을 담을 ArrayList - ChatDTO타입만 허용
		ArrayList<ChatDTO> chatList = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 채팅방마다 가장 최근에온 메세지 불러오기
		String SQL = "WITH lastest AS (" + 
				"    SELECT chatid FROM (" + 
				"        SELECT chatid, rank() OVER ( PARTITION BY userid ORDER BY chatid DESC ) rank" + 
				"        FROM (" + 
				"            SELECT chatid, toid userid" + 
				"            FROM chat" + 
				"            WHERE fromid = ? AND toid != ?" + 
				"            UNION" + 
				"            SELECT chatid, fromid userid" + 
				"            FROM chat" + 
				"            WHERE fromid != ? AND toid = ?" + 
				"        ) roomlist" + 
				"    ) R" + 
				"    WHERE rank = 1" + 
				" )" + 
				" SELECT C.* FROM chat C, lastest L" + 
				" WHERE C.chatid = L.chatid" + 
				" ORDER BY C.chatid DESC";
		try {
			// context.xml에 있는 오라클 주소에 접근(컨넥션) 
			conn = dataSource.getConnection();
			
			// SQL문장을 데이터베이스에 입력한뒤 결과 반환
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setString(2, userID);
			pstmt.setString(3, userID);
			pstmt.setString(4, userID);
			rs = pstmt.executeQuery();
			
			// ArrayList를 생성하여 주소 저장 - ChatDTO타입의 데이터만 저장 가능
			chatList = new ArrayList<ChatDTO>();
			
			// 채팅방 개수만큼 반복
			while(rs.next()) {
				
				//ChatDTO객체 생성
				ChatDTO chat = new ChatDTO();
				
				// " ", <, >, \n 처리
				chat.setChatID(rs.getInt("chatID"));
				chat.setFromID(rs.getString("fromID").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt").replaceAll("\n","<br>" ));
				chat.setToID(rs.getString("toID").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt").replaceAll("\n","<br>" ));
				chat.setChatContent(rs.getString("chatContent").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt").replaceAll("\n","<br>" ));
				
				// 시간만 추출
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11, 13));
				String timeType = "오전";
				if(chatTime >= 12) {
					timeType = "오후";
					chatTime -= 12;
				}
				
				// (날짜 + 오전(오후) + 시간) 출력 
				chat.setChatTime(rs.getString("chatTime").substring(0, 11) + " " + timeType + " " + chatTime + ":" + rs.getString("chatTime").substring(14, 16) + "");
				
				// chatList에 chat정보 추가
				chatList.add(chat);
			}
			System.out.println(chatList.size());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return chatList; // 채팅 리스트 반환 
	}
	
}
