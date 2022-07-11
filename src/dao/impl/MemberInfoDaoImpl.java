package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.JDBCTemplate;
import dao.face.MemberInfoDao;
import dto.member.MemberDto;
import dto.member.PhotoDto;

public class MemberInfoDaoImpl implements MemberInfoDao {

	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public MemberDto selectUserInfo(Connection conn, String userid) {
		System.out.println("selectUserInfo():" + userid);

		MemberDto memberdto = null;

		String sql = "";
		sql += "SELECT";
		sql += "	userNumber,";
		sql += "	userid";
		sql += "	, userpw";
		sql += "	, nickname";
		sql += "	, email";
		sql += " FROM userinfo";
		sql += " WHERE userid = ?";


		try {
			// SQL수행 객체 생성
			ps = conn.prepareStatement(sql);
			ps.setNString(1, userid);

			rs = ps.executeQuery();

			// 조회 결과 처리
			memberdto = new MemberDto();


			while(rs.next()) {
				
				memberdto.setUserNumber(rs.getInt("userNumber"));
				memberdto.setUserID(rs.getString("userid"));
				memberdto.setUserPW(rs.getString("userpw"));
				memberdto.setNickname(rs.getString("nickname"));
				memberdto.setEmail(rs.getString("email"));

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);

		}
		System.out.println("view 실행 dao");
		// 최종 조회 결과 반환
		
		return memberdto;
	}
	
	@Override
	public MemberDto selectInfoByUserno(Connection conn, MemberDto usernumber) {


		MemberDto memberdto = null;
		
		String sql = "";
		sql += "SELECT";
		sql += "	userNumber";
		sql += "	, userid";
		sql += "	, userpw";
		sql += "	, nickname";
		sql += "	, email";
		sql += " FROM userinfo";
		sql += " WHERE userNumber = ?";
		
		
		MemberDto memberDto = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, usernumber.getUserNumber());
			
			rs = ps.executeQuery();
			

			while( rs.next() ) {

				memberdto = new MemberDto();
				
				memberdto.setUserNumber(rs.getInt("userNumber"));
				memberdto.setUserID(rs.getString("userid"));
				memberdto.setUserPW(rs.getString("userpw"));
				memberdto.setNickname(rs.getString("nickname"));
				memberdto.setEmail(rs.getString("email"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		
		return memberdto;
	}

	@Override
	public int infoupdate(Connection conn, MemberDto memberdto) {
		System.out.println("---"+memberdto);
		
		String sql = ""; 
		sql += "UPDATE userinfo SET userid = ?,"; 
		sql += " email = ?,";
		sql += " nickname = ?,"; 
		sql += " userpw = ?"; 
		sql += " WHERE usernumber = ?";

		int res = -1;

		try { 
			ps = conn.prepareStatement(sql);

			ps.setString(1, memberdto.getUserID()); 
			ps.setString(2, memberdto.getEmail());
			ps.setString(3, memberdto.getNickname()); 
			ps.setString(4, memberdto.getUserPW());
			ps.setInt(5, memberdto.getUserNumber());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace(); 
		} finally {
			JDBCTemplate.close(ps); 
		}

		System.out.println("updatec [POST] 실행 dao");
		
		return res; 
		}

	@Override
	public int deactivatedupdate(Connection conn, MemberDto memberdto) {
		String sql = "";
		sql += "UPDATE userinfo SET  deactivated = 1";
		sql += " WHERE usernumber = ?";
		System.out.println("dao: " + memberdto.getUserNumber());
		int res = -1;    
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1,memberdto.getUserNumber());
			
			
			res = ps.executeUpdate(); //결과값이 1아니면 0
			System.out.println(res);
			System.out.println(memberdto.getUserNumber());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		
		return res;
	}


	public int fileInsert(Connection conn, PhotoDto photoDto) {
		System.out.println("fileInsert()");
		String sql = "";
		sql += "INSERT INTO profilephoto(fileno, usernumber, originname, storedname )";
		sql += " VALUES ( profilephoto_seq.nextval, ?, ?, ? )";
		
		// 수행 결과 변수
		int res = 0;

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, photoDto.getUsernumber());
			ps.setString(2, photoDto.getOriginname());
			ps.setString(3, photoDto.getStoredname());

			res = ps.executeUpdate();
			System.out.println("res"+res);
			if(res>0)
			System.out.println("파일 업데이트 완료");

		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}
	
	public int fileUpdate(Connection conn, PhotoDto photoDto) {
		System.out.println("fileUpdate()");

		String sql = "";
		sql += "UPDATE profilephoto SET ";
		sql += "fileno= profilephoto_seq.nextval, ";
		sql += " originname=?, storedname=? ";
		sql += " where usernumber = ? ";
		System.out.println(" fileupdate dto" + photoDto);
		
		// 수행 결과 변수
		int res = 0;

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, photoDto.getOriginname());
			ps.setString(2, photoDto.getStoredname());
			ps.setInt(3, photoDto.getUsernumber());

			res = ps.executeUpdate();
			System.out.println("res"+res);
			if(res>0)
			System.out.println("파일 업데이트 완료");

		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}
			
	 public boolean isPhotoExist(PhotoDto photoDto) {
		 if(photoDto.getStoredname() != null) {
			 return true;
		 }else
			 return false;
		 
	 }

	@Override
	public void sessionUpdate(Connection conn, HttpServletRequest req, MemberDto memberDto, PhotoDto photoDto) {
		// TODO Auto-generated method stub
		String userid = memberDto.getUserID();
		HttpSession session = req.getSession();
		
		String sql = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemberDto logmem = new MemberDto();

		// sql문 실행하여 결과테이블 resultset 얻기
		sql += "SELECT * FROM USERINFO ";
		sql += "WHERE UserID = ? ";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userid);

			System.out.println("목표 " + userid);

			rs = ps.executeQuery();

			while (rs.next()) {

				logmem.setUserID(rs.getString("UserID"));
				logmem.setUserPW(rs.getString("USERPW"));
				logmem.setNickname(rs.getString("NICKNAME"));
				logmem.setEmail(rs.getString("email"));
				logmem.setUserNumber(rs.getInt("userNumber"));
				// 남자는 0, 여자는 1으로 처리
				if (rs.getString("gender") == "M") {
					logmem.setGender(0);
				} else if (rs.getString("gender") == "F") {
					logmem.setGender(1);
				}
			}
			

			session.setAttribute("memberDto", memberDto);
			session.setAttribute("photoDto", photoDto);
			
			session.setAttribute("login", true);
			session.setAttribute("loginID", memberDto.getUserID());
			session.setAttribute("loginPW", memberDto.getUserPW());
			session.setAttribute("email", memberDto.getEmail());
			session.setAttribute("userID", memberDto.getUserID());
			session.setAttribute("nickname", memberDto.getNickname());
			session.setAttribute("userno", memberDto.getUserNumber());
			session.setAttribute("usernumber", memberDto.getUserNumber());
			session.setAttribute("photo", photoDto.getStoredname());

			
			
			
	} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
	
	}	
}
