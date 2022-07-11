package dao.face;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dto.board.BoardDto;
import dto.board.SearchDto;
import dto.board.UploadFile;
import dto.board.commentDto;
import dto.member.MemberDto;

public interface boardListDAO {
	

	    //게시글 목록 List로 얻기
		public List<BoardDto> getList(Connection conn,int pageNumber,int pagesize,int boardcode);
		
		//전체게시물 갯수 얻기
		public int getcount(Connection conn,int BOARDCODE);
		
		//게시물 찾기
		public BoardDto getBoardDto(Connection conn,int boardnumber);
	
		//게시물 삭제하기
		public int deleteBoardDto(Connection conn,int boardnumber);
		
		//게시물 수정하기
		public int updateBoardDto(Connection conn,BoardDto boarddto);
		
		//게시물 조회수 높이기
		public int updateBoardviews(Connection conn,int boardnumber);
		
		//게시물 댓글 삽입하기
		public int updateBoardviews(Connection conn,commentDto commentdto);
		
		//게시물 코맨트 많이얻기
		public ArrayList<commentDto> getcommentDto(Connection conn,int boardnumber);
		
		//게시물 코맨트 한개 얻기
		public commentDto getcommentDto1(Connection conn,int commentnumber);
		
		//게시물 코맨트 삭제
		public int commentdelete(Connection conn,int commentnumber);
		
		//게시물 코멘트 수정
		public int commentupdate(Connection conn,int commentnumber,String commenttext);
		
		//게시물 검색찾기
		public ArrayList<BoardDto> searchboard(Connection conn,SearchDto searchdto,int pagenumber,int PAGESIZE);
		
		//게시물 검색찾은거 개수찾기
		public int getcountsearch(Connection conn,SearchDto searchdto,int boardcode);
		
		//유저닉네임 찾기
		public String getnickname(Connection conn,int userid);
		
		//게시물 file db 삽입하기
		public int fileinsert(Connection conn,UploadFile up,BoardDto boardDto,MemberDto memberdto);
		
		//게시물 file db에서 찾기
		public UploadFile getuploadfile(Connection conn,int Boardnumber);
		
		//게시물 file db에서 제거
		public int deletefile(Connection conn,int Boardnumber);
		

		
		
}
