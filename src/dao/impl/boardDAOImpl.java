package dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import dao.face.boardDAO;
import dto.board.BoardDto;
import dto.member.MemberDto;


public class boardDAOImpl implements boardDAO{
	
	
	PreparedStatement ps = null; //SQL수행 객체
	ResultSet rs = null; //조회결과 객체
	
	@Override//게시글 작성시간얻기
	public String getDate(Connection conn) {
		
		String sql = "SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD') FROM DUAL"; //현재시간 얻어오기
		
		
		try {
			
			ps = conn.prepareStatement(sql); //sql수행객체를 만든다.
			rs = ps.executeQuery(); //수행결과를 저장한다.
			
			if(rs.next()) {
				
				return rs.getNString(1); //1번 칼럼에 있는걸 가져온다.
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			JDBCTemplate.close( rs );
			JDBCTemplate.close( ps ); 
			
		}
		
		return "";
		
	};
	
	//sequence를 얻어온것입니다.
	public int getboardnumber(Connection conn) {
		
		String sql = "";
		sql += "SELECT member_seq.nextval AS next FROM dual";
		
		int next = 0; //결과를 저장할 변수
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			rs.next(); //그냥 true인데 왜 있는지는 모르겟다. 그냥 넣어봄
			
			next = rs.getInt("next"); //next라는 칼럼에있는것을 int로 얻어온다.
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return next; //next에 seq가 있으니 이값을 반환해버리는거임.
		
	};
	
	//여기서 시퀸스를 각 증가시켜서 순차적으로 Id값을 얻는다. 아직 insert한거아님


	@Override
	public int write(Connection conn,BoardDto boardDto,MemberDto memberdto) {
		
		
		String sql = "INSERT INTO board ( boardnumber, boardtitle, boardcontent,boarddate,usernumber,views,boardcode,boardcategory,boardstate) VALUES (?,?,?,?,?,?,?,?,?)";
		

		int result = 0; //INSERT 수행 결과를 저장하는 변수이다.
		
		try {
			
			ps = conn.prepareStatement(sql); //수행객체를 만든다.
			
			ps.setInt(1, boardDto.getBoardnumber());
			ps.setString(2, boardDto.getBoardtitle());
			ps.setString(3, boardDto.getBoardcontent());
			ps.setString(4, boardDto.getBoarddate());   //각줄마다 ? 순서대로 값을 넣습니다.
			ps.setInt(5, memberdto.getUserNumber());
			ps.setInt(6, 0);
			ps.setInt(7, boardDto.getBoardcode());
			ps.setString(8, boardDto.getBoardcategory());
			ps.setString(9, boardDto.getBoardstate());
			
			
			result = ps.executeUpdate(); //insert를 수행한다.
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return result;
		
	};

}
