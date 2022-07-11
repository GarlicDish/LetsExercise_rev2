package dao.face;

import java.sql.Connection;

import dto.board.BoardDto;
import dto.member.MemberDto;



public interface boardDAO {
	
	//게시글 작성시간얻기
	public String getDate(Connection conn);
	
	//게시글ID 얻기
	public int getboardnumber(Connection conn);

	//이제 얻은정보들을 db게시판정보에 넣는다.
	public int write(Connection conn,BoardDto boardDto,MemberDto memberdto);

}
