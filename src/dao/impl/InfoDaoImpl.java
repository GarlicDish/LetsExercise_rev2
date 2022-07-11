package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import common.JDBCTemplate;
import dao.face.InfoDao;
import dto.MemberDto;

public class InfoDaoImpl implements InfoDao {

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
			  System.out.println("updatec [POST] 실행 dao");
		  
		  } catch (SQLException e) { e.printStackTrace(); 
		  } finally {
		  JDBCTemplate.close(ps); 
		  }
		  
		  return res; 
		  }

			/*
			 * public int infodelete(Connection conn,Info info) {
			 * 
			 * 
			 * String sql = ""; sql += "DELETE kh"; sql += " WHERE userno = ?"; int res =
			 * -1;
			 * 
			 * try { ps = conn.prepareStatement(sql);
			 * 
			 * ps.setInt(1,info.getUserno());
			 * 
			 * 
			 * res = ps.executeUpdate();
			 * 
			 * } catch (SQLException e) {
			 * 
			 * e.printStackTrace();
			 * 
			 * } finally { JDBCTemplate.close(ps); }
			 * 
			 * 
			 * return res;
			 * 
			 * 
			 * }
			 */

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


	  
			/*
			 * public Info getUserId(Connection conn,String userid) {
			 * 
			 * String sql
			 * ="select boardnumber,boardtitle,boarddate,boardcontent,views,userno from board where boardnumber = ? "
			 * ; Info info = null;
			 * 
			 * try {
			 * 
			 * ps = conn.prepareStatement(sql); ps.setInt(1,boardid); rs =
			 * ps.executeQuery();
			 * 
			 * rs.next(); //그냥 true인데 왜 있는지는 모르겟다. 그냥 넣어봄
			 * 
			 * boarddto = new BoardDto();
			 * 
			 * boarddto.setBoardId(rs.getInt(1)); boarddto.setTitle(rs.getString(2));
			 * boarddto.setReportingDate(rs.getString(3));
			 * boarddto.setBoardcontent(rs.getString(4)); boarddto.setViews(rs.getInt(5));
			 * boarddto.setId(rs.getInt(6));
			 * 
			 * } catch (SQLException e) {
			 * 
			 * e.printStackTrace();
			 * 
			 * } finally { JDBCTemplate.close(rs); JDBCTemplate.close(ps); }
			 * 
			 * 
			 * return boarddto;
			 * 
			 * 
			 * };
			 */
	 
}
