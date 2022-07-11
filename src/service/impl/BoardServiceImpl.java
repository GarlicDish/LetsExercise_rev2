package service.impl;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import common.JDBCTemplate;
import dao.face.boardDAO;
import dao.face.boardListDAO;
import dao.impl.boardDAOImpl;
import dao.impl.boardListDAOImpl;
import dto.board.BoardDto;
import dto.board.SearchDto;
import dto.board.UploadFile;
import dto.board.commentDto;
import dto.member.MemberDto;
import service.face.BoardService;

public class BoardServiceImpl implements BoardService{
	
	boardDAO boarddao = new boardDAOImpl();
	boardListDAO boarlistddao = new boardListDAOImpl();
	
	
	
	//req를 받아서 boarddto에 어느정도 값을 채울것입니다.
	public BoardDto getParam( HttpServletRequest req ) {
		
		BoardDto boardDto = new BoardDto();
		
		boardDto.setBoardtitle( req.getParameter("boardtitle") );
		boardDto.setBoardcontent(req.getParameter("boardcontent"));
		boardDto.setBoardcode(Integer.parseInt(req.getParameter("code")));
		boardDto.setBoardcategory(req.getParameter("boardcategory"));
		boardDto.setBoardstate(req.getParameter("boardstate"));

		
		return boardDto;
		
		
		
	
	}; //여기서 boardDto에서 title과 boardcontent가 Dto에 저장됬습니다.
	
	
	//받은 boardDto를 db에 삽입할것입니다.
	public BoardDto insert(BoardDto boardDto,MemberDto memberdto) {
		
		
		//DB연결 객체연결합
		Connection conn = JDBCTemplate.getConnection();
		
		int next = boarddao.getboardnumber(conn);
		System.out.println("next가 정상적으로 들어왔나요? "+ next);
		boardDto.setBoardnumber( next );
		
		String date = boarddao.getDate(conn);
		System.out.println("date가 정상적으로 들어왔나요? "+ date);
		boardDto.setBoarddate(date);
		
		int result = boarddao.write(conn,boardDto,memberdto); //db에 저장합니다.
		System.out.println(result);
		
		
		if( result > 0 ) {              //DB삽입 성공 이거안해주면 값안들어옵니다.!!
			JDBCTemplate.commit(conn);
			return boardDto;
			
		} else {                        //DB삽입 실패
			JDBCTemplate.rollback(conn);
			return null;
			
		}

		
	}; //insert끝입니다. 이제 db에 데이터도 저장완료됬습니다.
	
	
	
	//getlist를 받아옵니다.
	 public ArrayList<BoardDto> getlist(int pagenumber,int pagesize,int boardcode) {
		
		//DB연결 객체연결합
		Connection conn = JDBCTemplate.getConnection();
		
		return (ArrayList<BoardDto>)boarlistddao.getList(conn, pagenumber, pagesize,boardcode);
		
		
	};
	
	//게시글 전체 개수를 받아옵니다.
	 public int getcount(int BOARDCODE) {
		
		//DB연결 객체연결합
		Connection conn = JDBCTemplate.getConnection();
		
		return boarlistddao.getcount(conn,BOARDCODE);
		
		
	};
	
	
	//게시글정보를 받아옵니다.
	public BoardDto getBoardDto(int boardnumber) {
			
			//DB연결 객체연결합
			Connection conn = JDBCTemplate.getConnection();

			return boarlistddao.getBoardDto(conn,boardnumber);
		
		};
		
		
	//게시글을 삭제합니다.
	public int boardDtodelete(int boardnumber) {
			
		//DB연결 객체연결합
		Connection conn = JDBCTemplate.getConnection();
		
		
		int result = boarlistddao.deleteBoardDto(conn, boardnumber);

		

		if( result > 0 ) {              //DB삽입 성공 이거안해주면 값안들어옵니다.!!
			JDBCTemplate.commit(conn);
			return result;
			
		} else {                        //DB삽입 실패
			JDBCTemplate.rollback(conn);
			return -1;
			
		}
	
						
		};	
		
		
		//게시물 수정하기
		public int updateBoardDto(BoardDto boarddto) {
			
			
			//DB연결 객체연결합
			Connection conn = JDBCTemplate.getConnection();
			
			int result = boarlistddao.updateBoardDto(conn, boarddto);
			
			
			if( result > 0 ) {              //DB삽입 성공 이거안해주면 값안들어옵니다.!!
				JDBCTemplate.commit(conn);
				return result;
				
			} else {                        //DB삽입 실패
				JDBCTemplate.rollback(conn);
				return -1;
				
			}

		};
		
		
		
		//게시글 조회수를 높입니다.
		public int boardviewsup(int boardnumber) {
			
			//DB연결 객체연결합
			Connection conn = JDBCTemplate.getConnection();
			
			int result = boarlistddao.updateBoardviews(conn, boardnumber);
			
			
			if( result > 0 ) {              //DB삽입 성공 이거안해주면 값안들어옵니다.!!
				JDBCTemplate.commit(conn);
				return result;
				
			} else {                        //DB삽입 실패
				JDBCTemplate.rollback(conn);
				return -1;
				
			}
			
		};
		
		
		public int boardcommentinsert(int boardnumber,int boardcode,String commentcontent,String nickname) {
			
			
			
			commentDto commentdto = new commentDto();
			commentdto.setBoardcode(boardcode);
			commentdto.setBoardnumber(boardnumber);
			commentdto.setCommentText(commentcontent);
			commentdto.setNickname(nickname);
			
			//DB연결 객체연결합
			Connection conn = JDBCTemplate.getConnection();
			
			int result = boarlistddao.updateBoardviews(conn, commentdto);
			
			
			if( result > 0 ) {              //DB삽입 성공 이거안해주면 값안들어옵니다.!!
				JDBCTemplate.commit(conn);
				return result;
				
			} else {                        //DB삽입 실패
				JDBCTemplate.rollback(conn);
				return -1;
				
			}
			
		};
		
		
		public ArrayList<commentDto> getcommentDto(int boardnumber) {
			
			
			//DB연결 객체연결합
			Connection conn = JDBCTemplate.getConnection();
			
			return boarlistddao.getcommentDto(conn,boardnumber);
			
			
		};

		
		public int deletecomment(int commentnumber) {
			
			
			//DB연결 객체연결합
			Connection conn = JDBCTemplate.getConnection();
			
			int result =  boarlistddao.commentdelete(conn,commentnumber);
			
			if( result > 0 ) {              //DB삽입 성공 이거안해주면 값안들어옵니다.!!
				JDBCTemplate.commit(conn);
				return result;
				
			} else {                        //DB삽입 실패
				JDBCTemplate.rollback(conn);
				return -1;
				
			}
			
			
		};
		
		
		//코맨트 수정합니다.
		public int updatecomment(int commentnumber,String commenttext) {
			
			Connection conn = JDBCTemplate.getConnection();
			//commentDto commentdto = boarlistddao.getcommentDto1(conn,commentnumber);
			int result =  boarlistddao.commentupdate(conn,commentnumber,commenttext);
			
			if( result > 0 ) {              //DB삽입 성공 이거안해주면 값안들어옵니다.!!
				JDBCTemplate.commit(conn);
				return result;
				
			} else {                        //DB삽입 실패
				JDBCTemplate.rollback(conn);
				return -1;
				
			}
			
			
			
		};
		
		
		
		//게시글을 검색합니다.
		public ArrayList<BoardDto> searchboard(HttpServletRequest request,int pagenumber,int PAGESIZE) {
			
			int boardcode = Integer.parseInt(request.getParameter("boardcode"));
			
			SearchDto searchdto = new SearchDto();
			
			searchdto.setSearchField(request.getParameter("searchField"));
			searchdto.setSearchText(request.getParameter("searchText"));
			searchdto.setBoardcode(boardcode);
			
			Connection conn = JDBCTemplate.getConnection();
			
			ArrayList<BoardDto> list = boarlistddao.searchboard(conn,searchdto,pagenumber,PAGESIZE);
			
		
			
			return list;
			
		};
		
		
		//게시글 검색갯수를 구합니다.
		public int getcountsearch(HttpServletRequest request) {
			
			int boardcode = Integer.parseInt(request.getParameter("boardcode"));
			
			SearchDto searchdto = new SearchDto();
			
			searchdto.setSearchField(request.getParameter("searchField"));
			searchdto.setSearchText(request.getParameter("searchText"));
			Connection conn = JDBCTemplate.getConnection();
			
			int result = boarlistddao.getcountsearch(conn,searchdto,boardcode);
			
			return result;
			
		};
		
		
		//파일업로드
		public int fileupload(HttpServletRequest request, HttpServletResponse response)  {
			
			
			//세션 얻기
			HttpSession session = request.getSession(false); //세션이 있으면 굳이 생성안한다.
			
			MemberDto memberdto = (MemberDto) session.getAttribute("memberDto"); //멤버dto를 얻는다.
			
			
			
			
			//파일 업로드 형식 검증
			//	-> multipart/form-data 인코딩 검증
			if( !ServletFileUpload.isMultipartContent(request) ) {
				
				request.setAttribute("msg", "form태그의 enctype속성이 잘못되었습니다");
				
				request.setAttribute("alertMsg", "경고메시지!");
				request.setAttribute("redirectURL", "/file/list");
				
				try {
					request.getRequestDispatcher("/WEB-INF/views/file/error.jsp").forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//요청-응답 처리 중단시키기
				return -1;
			}
			
//			System.out.println("[TEST] - forward코드 실행 이후");
			
			
			//--- COS라이브러리를 이용한 파일업로드 처리 ---
			
			
			//MultipartRequest 생성자의 매개변수 준비
			
			//1. 요청 정보 객체
			//	req
			
			//2. 업로드 파일 저장 위치
			String saveDirectory = request.getServletContext().getRealPath("upload");
			System.out.println(saveDirectory);
			//3. 업로드 용량 제한 크기
			int maxPostSize = 10 * 1024 * 1024; //10MB
			
			//4. 인코딩
			String encoding = "UTF-8";
			
			//5. 중복된 파일이름을 처리하는 정책
			FileRenamePolicy policy = new DefaultFileRenamePolicy();

			//**DefaultFileRenamePolicy의 정책
			//	-> 중복된 파일이름이 있을 경우 파일이름의 마지막에 번호를 붙인다
			//	-> 자동으로 1부터 1씩 증가하면서 부여된다
			
			
			//MultipartRequest 객체 생성 - 파일 업로드
			MultipartRequest mul = null;
			
			try {
				
				mul = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
				
			} catch (IOException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			
			
			//원본 파일 이름
			String origin = mul.getOriginalFileName("upfile");
			System.out.println("너가 널이니? = "+ origin);
			//저장된 파일 이름
			String stored = mul.getFilesystemName("upfile");
			System.out.println("너가 널이니? = "+ stored);
			
			
			
			
			BoardDto boardDto = new BoardDto();
			
			
			if(origin == null) {
				
				//전달파라미터 처리하기
				boardDto = new BoardDto();
		
				boardDto.setBoardtitle( mul.getParameter("boardtitle") );
				boardDto.setBoardcontent(mul.getParameter("boardcontent"));
				boardDto.setBoardcode(Integer.parseInt(mul.getParameter("Boardcode")));
				boardDto.setBoardcategory(mul.getParameter("boardcategory"));
				boardDto.setBoardstate(mul.getParameter("boardstate"));
				

				
				
				//업로드된 파일 정보를 DB에 삽입하기
				//전달파라미터 board DB삽입하기
				Connection conn = JDBCTemplate.getConnection();
				
				int next = boarddao.getboardnumber(conn);
				System.out.println("next가 정상적으로 들어왔나요? "+ next);
				boardDto.setBoardnumber( next );
				
				String date = boarddao.getDate(conn);
				System.out.println("date가 정상적으로 들어왔나요? "+ date);
				boardDto.setBoarddate(date);
				
				int result = boarddao.write(conn,boardDto,memberdto);
				
				if( result > 0 ) {
					JDBCTemplate.commit(conn);
				} else {
					JDBCTemplate.rollback(conn);
				}
				
				
			} else {
				
				
				//파일 정보 DTO
				UploadFile up = new UploadFile();
				
				up.setOriginName(origin);
				up.setStoredName(stored);
				
				//전달파라미터 처리하기
				boardDto = new BoardDto();
		
				boardDto.setBoardtitle( mul.getParameter("boardtitle") );
				boardDto.setBoardcontent(mul.getParameter("boardcontent"));
				boardDto.setBoardcode(Integer.parseInt(mul.getParameter("Boardcode")));
				boardDto.setBoardcategory(mul.getParameter("boardcategory"));
				boardDto.setBoardstate(mul.getParameter("boardstate"));
				

				
				
				//업로드된 파일 정보를 DB에 삽입하기
				//전달파라미터 board DB삽입하기
				Connection conn = JDBCTemplate.getConnection();
				
				int next = boarddao.getboardnumber(conn);
				System.out.println("next가 정상적으로 들어왔나요? "+ next);
				boardDto.setBoardnumber( next );
				
				String date = boarddao.getDate(conn);
				System.out.println("date가 정상적으로 들어왔나요? "+ date);
				boardDto.setBoarddate(date);
				
				int result = boarddao.write(conn,boardDto,memberdto);
				
				if( result > 0 ) {
					JDBCTemplate.commit(conn);
				} else {
					JDBCTemplate.rollback(conn);
				}
				
				
				
				
				//업로드된 파일 정보를 DB에 삽입하기
				result = boarlistddao.fileinsert(conn, up,boardDto,memberdto);
				if( result > 0 ) {
					JDBCTemplate.commit(conn);
				} else {
					JDBCTemplate.rollback(conn);
				}

			}
			
		
			return boardDto.getBoardcode();
			
		} //cosFileupload() end
			
		public UploadFile getfileinfo(int Boardnumber) {
			
			
			
			Connection conn = JDBCTemplate.getConnection();

			return boarlistddao.getuploadfile(conn,Boardnumber);
		
			
			
			
			
		};
		
		
		//파일 삭제하기
		public int deletefile(int Boardnumber) {;
			//DB연결 객체연결합
				Connection conn = JDBCTemplate.getConnection();
				
				
				int result = boarlistddao.deletefile(conn, Boardnumber);

				

				if( result > 0 ) {              //DB삽입 성공 이거안해주면 값안들어옵니다.!!
					JDBCTemplate.commit(conn);
					return result;
					
				} else {                        //DB삽입 실패
					JDBCTemplate.rollback(conn);
					return -1;
					
				}
			
		};
		
		
		
		
		
		
		
		//파일 수정하기
		public int updatefileinfo(HttpServletRequest request, HttpServletResponse response) {
			
			//세션 얻기
			HttpSession session = request.getSession(false); //세션이 있으면 굳이 생성안한다.
			MemberDto memberdto = (MemberDto) session.getAttribute("memberDto"); //멤버dto를 얻는다.
			
		
			
			
			//파일 업로드 형식 검증
			//	-> multipart/form-data 인코딩 검증
			if( !ServletFileUpload.isMultipartContent(request) ) {
				
				request.setAttribute("msg", "form태그의 enctype속성이 잘못되었습니다");
				
				request.setAttribute("alertMsg", "경고메시지!");
				request.setAttribute("redirectURL", "/file/list");
				
				try {
					request.getRequestDispatcher("/WEB-INF/views/file/error.jsp").forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//요청-응답 처리 중단시키기
				return -1;
			}
			
//			System.out.println("[TEST] - forward코드 실행 이후");
			
			
			//--- COS라이브러리를 이용한 파일업로드 처리 ---
			
			
			//MultipartRequest 생성자의 매개변수 준비
			
			//1. 요청 정보 객체
			//	req
			
			//2. 업로드 파일 저장 위치
			String saveDirectory = request.getServletContext().getRealPath("upload");
			System.out.println(saveDirectory);
			//3. 업로드 용량 제한 크기
			int maxPostSize = 10 * 1024 * 1024; //10MB
			
			//4. 인코딩
			String encoding = "UTF-8";
			
			//5. 중복된 파일이름을 처리하는 정책
			FileRenamePolicy policy = new DefaultFileRenamePolicy();

			//**DefaultFileRenamePolicy의 정책
			//	-> 중복된 파일이름이 있을 경우 파일이름의 마지막에 번호를 붙인다
			//	-> 자동으로 1부터 1씩 증가하면서 부여된다
			
			
			//MultipartRequest 객체 생성 - 파일 업로드
			MultipartRequest mul = null;
			
			try {
				
				mul = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
				
			} catch (IOException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			
			
			
			BoardDto boardDto = new BoardDto();
			
			boardDto.setBoardtitle( mul.getParameter("boardtitle") );
			boardDto.setBoardcontent(mul.getParameter("boardcontent"));
			boardDto.setBoardnumber(Integer.parseInt(mul.getParameter("boardnumber")));
			boardDto.setBoardcode(Integer.parseInt(mul.getParameter("Boardcode")));
			boardDto.setBoardcategory(mul.getParameter("boardcategory"));
			boardDto.setBoardstate(mul.getParameter("boardstate"));
			

			//업로드된 파일 정보를 DB에 삽입하기
			//전달파라미터 board DB삽입하기
			Connection conn = JDBCTemplate.getConnection();
			
			//원조 file안바꾸고 내용만 바꿀시에
			int result = boarlistddao.updateBoardDto(conn,boardDto);
			
			if( result > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}

			//원본 파일 이름
			String origin = mul.getOriginalFileName("upfile");

			
			//저장된 파일 이름
			String stored = mul.getFilesystemName("upfile");


			int a = updatefilehandler(origin, mul.getParameter("orijindelete"));

		
			if(a == 1) {
				
				//파일 정보 DTO
				UploadFile up = new UploadFile();
				
				up.setOriginName(origin);
				up.setStoredName(stored);
				
				result = boarlistddao.fileinsert(conn, up,boardDto,memberdto);
				
				if( result > 0 ) {
					JDBCTemplate.commit(conn);
				} else {
					JDBCTemplate.rollback(conn);
				}
				
				return boardDto.getBoardcode();
		
			} else if( a == 2 ){
				
				
				
				//원조 file제거
				result = boarlistddao.deletefile(conn,Integer.parseInt(request.getParameter("boardnumber")));
				
				if( result > 0 ) {
					JDBCTemplate.commit(conn);
				} else {
					JDBCTemplate.rollback(conn);
				}

				return boardDto.getBoardcode();
	
				
			}  else if(a == 3) {
				
				
				
				//원조 file제거
				result = boarlistddao.deletefile(conn,Integer.parseInt(request.getParameter("boardnumber")));
				
				if( result > 0 ) {
					JDBCTemplate.commit(conn);
				} else {
					JDBCTemplate.rollback(conn);
				}
				
				//파일 정보 DTO
				UploadFile up = new UploadFile();
				
				up.setOriginName(origin);
				up.setStoredName(stored);
				
				result = boarlistddao.fileinsert(conn, up,boardDto,memberdto);
				
				if( result > 0 ) {
					JDBCTemplate.commit(conn);
				} else {
					JDBCTemplate.rollback(conn);
				}

			}
			
		
			return boardDto.getBoardcode();
			
			
			
		};
		
		
		
		private int updatefilehandler(String origin,String orijindelete) {
			
			//insert로 간다.
			if(origin != null && orijindelete == null ) {
				System.out.println("3333 삭제후 업데이트");
				return 3;
				
			} else if(origin == null && orijindelete == null) {
				System.out.println("4444444암것도 안하기");
				return 4;
				
				
			} else if(origin == null && orijindelete.equals("tt")) {
				
				System.out.println("2222 삭제");
				return 2;
				
			} else if(origin != null && orijindelete.equals("xx") ) {
				
				System.out.println("1111 insert로 간다");
				return 1;
			}
			
			System.out.println("끝으로 왓으");
			return 4;
			
		};
		
};
