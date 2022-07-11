package service.face;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.BoardDto;
import dto.board.UploadFile;
import dto.board.commentDto;
import dto.member.MemberDto;





public interface BoardService {
	
	
	//req를 받아서 boarddto에 어느정도 값을 채울것입니다.
	public BoardDto getParam( HttpServletRequest req );
	
	//받은 boardDto를 db에 삽입할것입니다.
	public BoardDto insert(BoardDto boardDto,MemberDto memberdto);
	
	//getlist를 받아옵니다.
	public ArrayList<BoardDto> getlist(int pagenumber,int pagesize,int boardcode);
	
	//게시글 전체 개수를 받아옵니다.
	public int getcount(int BOARDCODE);
	
	//게시글정보를 받아옵니다.
	public BoardDto getBoardDto(int boardnumber);
	
	//게시글을 삭제합니다.
	public int boardDtodelete(int boardnumber);
	
	//게시물 수정하기
	public int updateBoardDto(BoardDto boarddto);
	
	//게시글 조회수를 높입니다.
	public int boardviewsup(int boardnumber);
	
	//게시글 댓글을 저장할겁니다.
	public int boardcommentinsert(int boardnumber,int boardcode,String commentcontent,String username);
	
	//게시글코맨트 dto 여러개를 받아옵니다.
	public ArrayList<commentDto> getcommentDto(int boardnumber);
	
	//코맨트 삭제합니다.
	public int deletecomment(int commentnumber);
	
	//코맨트 수정합니다.
	public int updatecomment(int commentnumber,String commenttext);
	
	//게시글을 검색 찾습니다.
	public ArrayList<BoardDto> searchboard(HttpServletRequest request,int pagenumber,int PAGESIZE);
	
	//게시글 검색갯수를 찾습니다.
	public int getcountsearch(HttpServletRequest request);
	
	//파일업로드
	public int fileupload(HttpServletRequest req, HttpServletResponse resp);
	
	//파일업로드 찾기
	public UploadFile getfileinfo(int Boardnumber);
	
	//파일 삭제하기
	public int deletefile(int Boardnumber);
	
	//파일 업데이트하기
	public int updatefileinfo(HttpServletRequest request, HttpServletResponse response);
	
	
	
}
