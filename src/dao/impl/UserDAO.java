package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserDAO {
	
	DataSource dataSource;
	
	//DB연결
	public UserDAO() {
		try {
			InitialContext initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/UserChat");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
//	
//	//유저 로그인 메소드
//	public int login(String userID, String userPassword) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String SQL = "SELECT * FROM CHATUSER WHERE userID = ?";
//		try {
//			conn = dataSource.getConnection();
//			pstmt = conn.prepareStatement(SQL);
//			pstmt.setNString(1, userID);
//			rs = pstmt.executeQuery();
//			if(rs.next()) {
//				if(rs.getNString("userPassword").equals(userPassword)) { // 패스워드 비교
//					return 1; // 로그인에 성공
//				}
//				return 2; // 비밀번호가 틀림
//			} else {
//				return 0; // 해당 사용자가 존재하지 않음
//			}
//		} catch (Exception e)  {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(rs != null) rs.close();
//				if(pstmt != null) pstmt.close();
//				if(conn != null) conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} 
//		}
//		return -1; //데이터베이스 오류
//		
//	}
	
	//아이디 체크
	public int registerCheck(String userID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM userinfo WHERE userID = ?";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setNString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next() || userID.equals("")) { //공백입력 처리
					return 0; // 이미 존재하는 회원
			} else {
				return 1; // 가입 가능한 회원 아이디
			}
		} catch (Exception e)  {
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
	
	//회원가입 처리
//	public int register(String userID, String userPassword, String userName, String userAge, String userGender, String userEmail, String userProfile) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		String SQL = "INSERT INTO CHATUSER VALUES (?, ?, ?, ?, ?, ?, ?)";
//		try {
//			conn = dataSource.getConnection();
//			pstmt = conn.prepareStatement(SQL);
//			pstmt.setString(1, userID);
//			pstmt.setString(2, userPassword);
//			pstmt.setString(3, userName);
//			pstmt.setInt(4, Integer.parseInt(userAge));
//			pstmt.setString(5, userGender);
//			pstmt.setString(6, userEmail);
//			pstmt.setString(7, userProfile);
//			return pstmt.executeUpdate();
//		} catch (Exception e)  {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(pstmt != null) pstmt.close();
//				if(conn != null) conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} 
//		}
//		return -1; //데이터베이스 오류
//		
//	}
	
	
}
