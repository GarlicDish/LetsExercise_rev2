package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import dao.face.boardListDAO;
import dto.board.BoardDto;
import dto.board.SearchDto;
import dto.board.UploadFile;
import dto.board.commentDto;
import dto.member.MemberDto;

public class boardListDAOImpl implements boardListDAO{

			PreparedStatement ps = null; //SQL수행 객체
			ResultSet rs = null; //조회결과 객체
			

	        //게시글 목록 List로 얻기
			public ArrayList<BoardDto> getList(Connection conn,int pagenumber,int pagesize,int boardcode){
				
				String sql = "SELECT * FROM " + 
						" (select rownum as ru,boardnumber,boardtitle,boarddate,boardcontent,views,usernumber,nickname,userid,boardcode,boardcategory,boardstate "+
						" from (SELECT boardnumber,boardtitle,boarddate,boardcontent,views " + 
						" ,b.usernumber,nickname,userid,boardcode,boardcategory,boardstate"+
						" FROM BOARD b inner join userinfo u on(b.usernumber =  u.usernumber) where boardcode = ?"+
						" ORDER BY boardnumber DESC)) " + 
						" WHERE Ru BETWEEN ? AND ? ";
				
				ArrayList<BoardDto> list = new ArrayList<>();
				
				try {
					
					ps = conn.prepareStatement(sql); //sql수행객체를 만든다.
					
					ps.setInt(1,boardcode);
					
					ps.setInt(2, (pagenumber-1)*pagesize+1);
					System.out.println(((pagenumber-1)*pagesize)+1);
					
					
					ps.setInt(3, pagenumber*pagesize );
					System.out.println(pagenumber*pagesize);
					
					
					rs = ps.executeQuery(); //수행결과를 저장한다.
					
					while(rs.next()) {
						
						BoardDto boarddto = new BoardDto();
					
						boarddto.setBoardnumber(rs.getInt(2));
						boarddto.setBoardtitle(rs.getString(3));
						boarddto.setBoarddate(rs.getString(4));
						boarddto.setBoardcontent(rs.getString(5));
						boarddto.setViews(rs.getInt(6));
						boarddto.setNickname(rs.getString(8));
						boarddto.setUserid(rs.getString(9));
						boarddto.setBoardcode(rs.getInt(10));
						boarddto.setBoardcategory(rs.getString(11));
						boarddto.setBoardstate(rs.getString(12));
						
						list.add(boarddto);
					}
					
				} catch (SQLException e) {
					
					e.printStackTrace();
					
				} finally {
					
					JDBCTemplate.close( rs );
					JDBCTemplate.close( ps ); 
					
				}

				return list;
		
			};
		
			
			
			
			
			
			
			
			
			
			
			//전체게시글 갯수얻기.
			public int getcount(Connection conn,int BOARDCODE) {
				
				String sql ="select count(*) from board where boardcode = ?";
				int result = 0;     //왜 여기서 이거 선언해준다음에 해야되는거지? -->연구필요.
				
				try {
					ps = conn.prepareStatement(sql);
					
					ps.setInt(1,BOARDCODE);
					
					rs = ps.executeQuery();
					
					rs.next(); //그냥 true인데 왜 있는지는 모르겟다. 그냥 넣어봄
					
					result = rs.getInt(1); // 칼럼에있는것을 int로 얻어온다.
					
				} catch (SQLException e) {
					
					e.printStackTrace();
					
				} finally {
					JDBCTemplate.close(rs);
					JDBCTemplate.close(ps);
				}
				
				
				return result;
				
			};
			
			
			
			
			
			
			
			
			
			//게시물 찾기
			public BoardDto getBoardDto(Connection conn,int boardnumber) {
				
				String sql ="select boardnumber,boardtitle,boarddate,boardcontent,views,usernumber,boardcode,boardcategory,boardstate from board where boardnumber = ? ";
				BoardDto boarddto = null;
				
				try {
					
					ps = conn.prepareStatement(sql);
					ps.setInt(1,boardnumber);
					rs = ps.executeQuery();
					
					rs.next(); //그냥 true인데 왜 있는지는 모르겟다. 그냥 넣어봄
					
					boarddto = new BoardDto();
					
					boarddto.setBoardnumber(rs.getInt(1));
					boarddto.setBoardtitle(rs.getString(2));
					boarddto.setBoarddate(rs.getString(3));
					boarddto.setBoardcontent(rs.getString(4));
					boarddto.setViews(rs.getInt(5));
					boarddto.setUsernumber(rs.getInt(6));
					boarddto.setBoardcode(rs.getInt(7));
					boarddto.setBoardcategory(rs.getString(8));
					boarddto.setBoardstate(rs.getString(9));

				} catch (SQLException e) {
					
					e.printStackTrace();
					
				} finally {
					JDBCTemplate.close(rs);
					JDBCTemplate.close(ps);
				}
				
				
				return boarddto;
				
				
			};
			
			
			
			
			//게시글 삭제하기
			public int deleteBoardDto(Connection conn,int boardnumber) {
				

				String sql ="delete from board where boardnumber = ?";
				int result = 0;     //왜 여기서 이거 선언해준다음에 해야되는거지? -->연구필요.
				
				try {
					ps = conn.prepareStatement(sql);
					
					ps.setInt(1,boardnumber);
					
					
					result = ps.executeUpdate();// 성공했다면 정상숫자반환.
					
				} catch (SQLException e) {
					
					e.printStackTrace();
					
				} finally {
					JDBCTemplate.close(rs);
					JDBCTemplate.close(ps);
				}
				
				
				return result;
					
				
			};
			
			
			
			public int updateBoardDto(Connection conn,BoardDto boarddto) {
				

				String sql ="UPDATE board " + 
						"SET boardtitle = ? , boardcontent= ? ,boardcategory = ?, boardstate = ?" + 
						"where boardnumber = ?";
				
				int result = 0;     //왜 여기서 이거 선언해준다음에 해야되는거지? -->연구필요.
				
				try {
					ps = conn.prepareStatement(sql);
					
					ps.setString(1,boarddto.getBoardtitle());
					ps.setString(2,boarddto.getBoardcontent());
					ps.setString(3,boarddto.getBoardcategory());
					ps.setString(4,boarddto.getBoardstate());
					ps.setInt(5,boarddto.getBoardnumber());
					
					result = ps.executeUpdate();// 성공했다면 정상숫자반환.
					
				} catch (SQLException e) {
					
					e.printStackTrace();
					
				} finally {
					JDBCTemplate.close(rs);
					JDBCTemplate.close(ps);
				}
				
				
				return result;
				
				
				
			};
			
			
			//게시물 조회수 높이기
			public int updateBoardviews(Connection conn,int boardnumber) {
				
				String sql ="UPDATE board " + 
						"SET views = views + 1" + 
						"where boardnumber = ?";
				
				int result = 0;     //왜 여기서 이거 선언해준다음에 해야되는거지? -->연구필요.
				
				try {
					ps = conn.prepareStatement(sql);
					ps.setInt(1,boardnumber);
					result = ps.executeUpdate();// 성공했다면 정상숫자반환.
					
				} catch (SQLException e) {
					
					e.printStackTrace();
					
				} finally {
					JDBCTemplate.close(rs);
					JDBCTemplate.close(ps);
				}
				
				
				return result;
				
				
			};
			
			public int updateBoardviews(Connection conn,commentDto commentdto) {
				


				String sql = "INSERT INTO commenttable ( commentNumber,boardnumber, boardcode, nickname,commentText) VALUES (SEQ_comment.nextval,?,?,?,?)";
				

				int result = 0; //INSERT 수행 결과를 저장하는 변수이다.
				
				try {
					
					ps = conn.prepareStatement(sql); //수행객체를 만든다.
					
					ps.setInt(1, commentdto.getBoardnumber());
					ps.setInt(2, commentdto.getBoardcode());
					ps.setString(3, commentdto.getNickname());
					ps.setString(4, commentdto.getCommentText());
				
					
					
					result = ps.executeUpdate(); //insert를 수행한다.
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					JDBCTemplate.close(ps);
				}
				
				return result;
				
				
			};
			
			
			//게시물 코맨트 많이얻기
			public ArrayList<commentDto> getcommentDto(Connection conn,int boardnumber){
				
				String sql ="select commentNumber,boardnumber,boardcode,nickname,commentDate,commentText from commenttable where boardnumber = ? order by commentnumber desc ";
				
				
				ArrayList<commentDto> list = new ArrayList<>();
				
				try {
					
					ps = conn.prepareStatement(sql); //sql수행객체를 만든다.
					
					ps.setInt(1,boardnumber);
			
		
					rs = ps.executeQuery(); //수행결과를 저장한다.
					
					while(rs.next()) {
						
						commentDto commentdto = new commentDto();
					
						commentdto.setCommentnumber(rs.getInt(1));
						commentdto.setBoardnumber(rs.getInt(2));
						commentdto.setBoardcode(rs.getInt(3));
						commentdto.setNickname(rs.getString(4));
						commentdto.setCommentDate(rs.getString(5));
						commentdto.setCommentText(rs.getString(6));
						
						list.add(commentdto);
					}
					
				} catch (SQLException e) {
					
					e.printStackTrace();
					
				} finally {
					
					JDBCTemplate.close( rs );
					JDBCTemplate.close( ps ); 
					
				}

				return list;
				
				
				
			};
			
			
			//게시물 코맨트 삭제
			public int commentdelete(Connection conn,int commentnumber) {
				
				
				String sql ="delete from commenttable where commentnumber = ?";
				int result = 0;     //왜 여기서 이거 선언해준다음에 해야되는거지? -->연구필요.
				
				try {
					ps = conn.prepareStatement(sql);
					
					ps.setInt(1,commentnumber);
					
					
					result = ps.executeUpdate();// 성공했다면 정상숫자반환.
					
				} catch (SQLException e) {
					
					e.printStackTrace();
					
				} finally {
					JDBCTemplate.close(rs);
					JDBCTemplate.close(ps);
				}
				
				
				return result;
				

				
			};
			
			//게시물 코맨트 수정
			public int commentupdate(Connection conn,int commentnumber,String commenttext) {

				String sql ="UPDATE commenttable " + 
						"SET commenttext = ? " + 
						"where commentnumber = ?";
				
				int result = 0;     //왜 여기서 이거 선언해준다음에 해야되는거지? -->연구필요.
				
				try {
					ps = conn.prepareStatement(sql);
					
					ps.setString(1,commenttext);
					ps.setInt(2,commentnumber);
					
					
					result = ps.executeUpdate();// 성공했다면 정상숫자반환.
					
				} catch (SQLException e) {
					
					e.printStackTrace();
					
				} finally {
					JDBCTemplate.close(rs);
					JDBCTemplate.close(ps);
				}
				
				
				return result;
				
				
				
				
				
			};
			
			//게시물 코맨트 한개 얻기
			public commentDto getcommentDto1(Connection conn,int commentnumber) {
				
				String sql ="select * from board where commentnumber = ? ";
				commentDto commentdto = null;
				
				try {
					
					ps = conn.prepareStatement(sql);
					ps.setInt(1,commentnumber);
					rs = ps.executeQuery();
					
					rs.next(); //그냥 true인데 왜 있는지는 모르겟다. 그냥 넣어봄
					
					commentdto = new commentDto();
					
					commentdto.setCommentnumber(rs.getInt(1));
					commentdto.setBoardnumber(rs.getInt(2));
					commentdto.setBoardcode(rs.getInt(3));
					commentdto.setNickname(rs.getString(4));
					commentdto.setCommentDate(rs.getString(5));
					commentdto.setCommentText(rs.getString(6));

					
				} catch (SQLException e) {
					
					e.printStackTrace();
					
				} finally {
					JDBCTemplate.close(rs);
					JDBCTemplate.close(ps);
				}
				
				
				return commentdto;
				
				
				
				
			};
			
			//게시물 검색찾기
			public ArrayList<BoardDto> searchboard(Connection conn,SearchDto searchdto,int pagenumber,int PAGESIZE){
				
				ArrayList<BoardDto> list = new ArrayList<>();
    
	   
				String sql = "SELECT * FROM (select rownum as ru,boardnumber,boardtitle,boarddate,boardcontent,views,usernumber,nickname,userid,boardcode,boardcategory,boardstate ";
				sql += "from (SELECT boardnumber,boardtitle,boarddate,boardcontent,views ,b.usernumber,nickname,userid,boardcode,boardcategory,boardstate ";
				sql += "FROM BOARD b inner join userinfo u on(b.usernumber =  u.usernumber) where boardcode = ? ORDER BY boardnumber DESC) where "+(searchdto.getSearchField()).trim()+ " Like '%"+(searchdto.getSearchText()).trim()+"%')";
				sql += "WHERE Ru BETWEEN ? AND ?"; 
					   
				try {
					
					System.out.println("너가 범인 같은데? = " + searchdto.getSearchField());
					System.out.println("너가 범인 같은데? = " + searchdto.getSearchText());
					
					ps = conn.prepareStatement(sql); //sql수행객체를 만든다.
					ps.setInt(1,searchdto.getBoardcode());
					ps.setInt(2, (pagenumber-1)*PAGESIZE+1);
					ps.setInt(3, pagenumber*PAGESIZE );
					
					rs = ps.executeQuery(); //수행결과를 저장한다.
					
					while(rs.next()) {
						
						BoardDto boarddto = new BoardDto();
						
						boarddto.setBoardnumber(rs.getInt(2));
						boarddto.setBoardtitle(rs.getString(3));
						boarddto.setBoarddate(rs.getString(4));
						boarddto.setBoardcontent(rs.getString(5));
						boarddto.setViews(rs.getInt(6));
						boarddto.setUserid(rs.getString(7));
						boarddto.setNickname(rs.getString(8));
						boarddto.setBoardcode(rs.getInt(10));
						boarddto.setBoardcategory(rs.getString(11));
						boarddto.setBoardstate(rs.getString(12));
						
						list.add(boarddto);
					}
					
				} catch (SQLException e) {
					
					e.printStackTrace();
					
				} finally {
					
					JDBCTemplate.close( rs );
					JDBCTemplate.close( ps ); 
					
				}

				return list;
			};
				
			
			//게시물 검색찾은거 개수찾기
			public int getcountsearch(Connection conn,SearchDto searchdto,int boardcode) {
				
				String sql ="select count(*) from (SELECT boardnumber,boardtitle,boarddate,boardcontent,views ,b.usernumber,nickname,userid,boardcode,boardcategory,boardstate FROM BOARD b inner join userinfo u on (b.usernumber =  u.usernumber) where boardcode = ?) where "+(searchdto.getSearchField()).trim()+ " Like '%"+(searchdto.getSearchText()).trim()+"%'";
				
				int result = 0;
				
				try {
					
					ps = conn.prepareStatement(sql); //sql수행객체를 만든다.
					ps.setInt(1,boardcode);

					
					rs = ps.executeQuery(); //수행결과를 저장한다.
					
					while(rs.next()) {
						
					
						
						result = rs.getInt(1);
				
					}
					
				} catch (SQLException e) {
					
					e.printStackTrace();
					
				} finally {
					
					JDBCTemplate.close( rs );
					JDBCTemplate.close( ps ); 
					
				}

				return result;
				

			};
			
			
			
			public String getnickname(Connection conn,int userid) {
				
				String sql ="select nickname from userinfo where usernumber = ? ";
				String nickname = "";
				
				try {
					
					ps = conn.prepareStatement(sql);
					ps.setInt(1,userid);
					rs = ps.executeQuery();
					
					rs.next(); //그냥 true인데 왜 있는지는 모르겟다. 그냥 넣어봄
					
				
					
					nickname = rs.getString(1);
				
					
				} catch (SQLException e) {
					
					e.printStackTrace();
					
				} finally {
					JDBCTemplate.close(rs);
					JDBCTemplate.close(ps);
				}
				
				
				return nickname;
				
				
				
				
			};
			
			
			
			public int fileinsert(Connection conn,UploadFile up,BoardDto boardDto,MemberDto memberdto) {
				
				String sql = "INSERT INTO fileinfo ( FileNumber,boardnumber, boardcode, UserNumber,FileName,storedName) VALUES (SEQ_file.nextval,?,?,?,?,?)";
				

				int result = 0; //INSERT 수행 결과를 저장하는 변수이다.
				
				try {
					
					ps = conn.prepareStatement(sql); //수행객체를 만든다.
					
					ps.setInt(1, boardDto.getBoardnumber());
					ps.setInt(2, boardDto.getBoardcode());
					ps.setInt(3, memberdto.getUserNumber());
					ps.setString(4, up.getOriginName());
					ps.setString(5, up.getStoredName());

				
					
					
					result = ps.executeUpdate(); //insert를 수행한다.
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					JDBCTemplate.close(ps);
				}
				
				return result;
				

			};
			
			
			public UploadFile getuploadfile(Connection conn,int Boardnumber) {
				
				String sql ="select * from fileinfo where boardnumber = ? ";
				UploadFile uploadfile = null;
				
				try {
					
					ps = conn.prepareStatement(sql);
					ps.setInt(1,Boardnumber);
					rs = ps.executeQuery();
					
					uploadfile = new UploadFile();
				
					if(rs.next()){

					uploadfile.setFilenumber(rs.getInt(1));
					uploadfile.setOriginName(rs.getString(5));
					uploadfile.setStoredName(rs.getString(6));
					
					};
			
				} catch (SQLException e) {
					
					e.printStackTrace();
					
				} finally {
					JDBCTemplate.close(rs);
					JDBCTemplate.close(ps);
				}
				
				
				return uploadfile;
				

				
			};
			
			
			public int deletefile(Connection conn,int Boardnumber) {
				

				String sql ="delete from fileinfo where boardnumber = ?";
				int result = 0;     //왜 여기서 이거 선언해준다음에 해야되는거지? -->연구필요.
				
				try {
					ps = conn.prepareStatement(sql);
					
					ps.setInt(1,Boardnumber);
					
					
					result = ps.executeUpdate();// 성공했다면 정상숫자반환.
					
				} catch (SQLException e) {
					
					e.printStackTrace();
					
				} finally {
					JDBCTemplate.close(rs);
					JDBCTemplate.close(ps);
				}
				
				
				return result;
					
				
				
				
				
			};
			
			
	

}
			

