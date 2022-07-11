package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.WorkDao;
import dto.BoardDto;
import dto.UploadFile;
import util.Paging;

public class WorkDaoImpl implements WorkDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public List<BoardDto> selectAll(HttpServletRequest req, Connection conn) {

		String sql = "";
		sql += " 		SELECT";
		sql += "			BOARDNO, TITLE";
		sql += "			, USERNO, HIT, WRITEDATE";
		sql += "		FROM find WHERE userno =?";
		sql += "		ORDER BY BOARDNO DESC";

		List<BoardDto> workList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (int) req.getSession().getAttribute("usernumber"));

			rs = ps.executeQuery();

			while (rs.next()) {
				BoardDto w = new BoardDto();

				w.setBoardnumber(rs.getInt("BOARDNO"));
				w.setBoardtitle(rs.getString("TITLE"));
				w.setUsernumber(rs.getInt("USERNO"));
				w.setViews(rs.getInt("HIT"));
				w.setBoarddate(rs.getDate("WRITEDATE"));

				workList.add(w);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return workList;
	}

	@Override
	public List<BoardDto> selectAll(HttpServletRequest req, Connection conn, Paging paging) {

		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, W.* FROM (";
		sql += " 		SELECT";
		sql += "			BOARDNO, TITLE";
		sql += "			, USERNO, HIT, WRITEDATE";
		sql += "		FROM find WHERE userno =?";
		sql += "		ORDER BY BOARDNO DESC";
		sql += " 	) W";
		sql += " ) WORK";
		sql += " WHERE rnum BETWEEN ? AND ?";

		List<BoardDto> workList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, (int) req.getSession().getAttribute("usernumber"));
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());

			rs = ps.executeQuery();

			while (rs.next()) {
				BoardDto w = new BoardDto();

				w.setBoardnumber(rs.getInt("BOARDNO"));
				w.setBoardtitle(rs.getString("TITLE"));
				w.setUsernumber(rs.getInt("USERNO"));
				w.setViews(rs.getInt("HIT"));
				w.setBoarddate(rs.getDate("WRITEDATE"));

				System.out.println(w);
				workList.add(w);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return workList;
	}

	@Override
	public int selectCntAll(Connection conn) {

		String sql = "";
		sql += "SELECT count(*) cnt FROM board";

		// 총 게시글 수
		int count = 0;

		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				count = rs.getInt("cnt");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return count;
	}

	@Override
	public int updateHit(Connection conn, BoardDto boardno) {

		String sql = "";
		sql += "UPDATE find";
		sql += " SET hit = hit + 1";
		sql += " WHERE boardno = ?";

		int res = 0;

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, boardno.getBoardnumber());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	@Override
	public BoardDto selectBoardByBoardnumber(Connection conn, BoardDto boardnumber) {

		String sql = "";
		sql += "SELECT";
		sql += "	boardno";
		sql += "	, title";
		sql += "	, userno";
		sql += "	, userid";
		sql += "	, CONTENT";
		sql += "	, hit";
		sql += "	, writedate";
		sql += " FROM find";
		sql += " WHERE boardno = ?";

		BoardDto work = new BoardDto();

		try {
			ps = conn.prepareStatement(sql);
			System.out.println(boardnumber.getBoardnumber());
			ps.setInt(1, boardnumber.getBoardnumber());
			rs = ps.executeQuery();

			while (rs.next()) {
				work.setBoardnumber(rs.getInt("boardno"));
				work.setBoardtitle(rs.getString("title"));
				work.setUsernumber(rs.getInt("userno"));
				work.setBoardcontent(rs.getString("CONTENT"));
				work.setViews(rs.getInt("hit"));
				work.setBoarddate(rs.getDate("writedate"));
				work.setUserid(rs.getString("userid"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return work;
	}

	@Override
	public int insert(Connection conn, BoardDto work) {

		String sql = "";
		sql += "INSERT INTO find(boardno, title, userno, CONTENT, hit,boardcode,writedate,userid, isdelete)";
		sql += " VALUES (?, ?, ?, ?, 0, 6, sysdate, ?, 0)";

		int res = 0;

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, work.getBoardnumber());
			ps.setString(2, work.getBoardtitle());
			ps.setInt(3, work.getUsernumber());
			ps.setString(4, work.getBoardcontent());
			ps.setString(5, work.getUserid());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return res;
	}

	@Override
	public String selectNickByUserid(Connection conn, BoardDto viewWork) {

		String sql = "";
		sql += "SELECT nickname FROM userinfo";
		sql += " WHERE userid = ?";

		String nickname = null;

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, viewWork.getUserid());

			rs = ps.executeQuery();

			while (rs.next()) {
				nickname = rs.getString("nickname");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return nickname;

	}

	@Override
	public int selectBoardnumber(Connection conn) {

		String sql = "";
		sql += "SELECT find_seq.nextval FROM dual";

		int nextBoardno = 0;

		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				nextBoardno = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return nextBoardno;
	}

	@Override
	public int insertFile(Connection conn, UploadFile workFile) {

		String sql = "";
		sql += "INSERT INTO fileinfo( FILENUMBER, BOARDNUMBER, ORIGINNAME, STOREDNAME)"; // 파일사이즈를 없앴음
		sql += " VALUES (seq_file.nextval, ?, ?, ?)";

		int res = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, workFile.getBoardnumber());
			ps.setString(2, workFile.getOriginName());
			ps.setString(3, workFile.getStoredName());
			// ps.setInt(4, workFile.getFileSize());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	@Override
	public UploadFile selectFile(BoardDto viewWork, Connection conn) { // BoardDto boardnumber

		String sql = "";
		sql += "SELECT";
		sql += "	fileno";
		sql += "	, boardno";
		sql += "	, storedname";
		sql += " FROM findfile";
		sql += " WHERE boardno = ?";

		UploadFile workFile = null;

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, viewWork.getBoardnumber());

			rs = ps.executeQuery();

			if (rs.next()) {
				workFile = new UploadFile();

				workFile.setFilenumber(rs.getInt("fileno"));
				workFile.setBoardnumber(rs.getInt("boardno"));
				workFile.setStoredName(rs.getString("storedname"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return workFile;
	}

	@Override
	public int update(Connection conn, BoardDto work) {

		String sql = "";
		sql += "UPDATE board";
		sql += " SET boardtitle = ?,";
		sql += " 	boardcontent = ?";
		sql += " WHERE boardnumber = ?";

		int res = -1;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, work.getBoardtitle());
			ps.setString(2, work.getBoardcontent());
			ps.setInt(3, work.getBoardnumber());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	@Override
	public int delete(Connection conn, BoardDto work) {

		String sql = "";
		sql += "DELETE board";
		sql += " WHERE boardnumber = ?";

		int res = -1;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, work.getBoardnumber());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	@Override
	public int deleteFile(Connection conn, BoardDto work) {

		String sql = "";
		sql += "DELETE UploadFile";
		sql += " WHERE boardnumber = ?";

		int res = -1;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, work.getBoardnumber());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

//	@Override
//	public List<Map<String, Object>> getMapList(Connection conn, HttpServletRequest req) {
//
//		String sql = "";
//		sql += "select wc.usernumber, wc.commentnumber, wc.boardnumber, wc.commenttext, wc.commentdate, ui.nickname, up.originname";
//		sql += " from userinfo ui, userprofile up, commenttable wc";
//		sql += " where wc.usernumber = ui.usernumber and wc.usernumber = up.usernumber (+)";
//		sql += " and wc.boardnumber = ?";
//		sql += " ORDER BY commentnumber asc";
//
//		List<Map<String, Object>> gotlist = new ArrayList<>();
//		try {
//			ps = conn.prepareStatement(sql);
//			ps.setInt(1, Integer.parseInt(req.getParameter("boardnumber")));
//
//			rs = ps.executeQuery();
//
//			while (rs.next()) {
//				Map<String, Object> map = new HashMap<>();
//				commentDto wc = new commentDto();
//				MemberDto memberdto = new MemberDto();
//				Userprofile up = new Userprofile();
//
//				wc.setCommentnumber(rs.getInt("commentnumber"));
//				wc.setBoardnumber(rs.getInt("boardnumber"));
//				wc.setUsernumber(rs.getInt("usernumber"));
//				wc.setCommentText(rs.getString("commenttext"));
//				wc.setCommentDate(rs.getDate("commentdate"));
//
//				memberdto.setNickname(rs.getString("nickname"));
//
//				up.setOriginname(rs.getString("originname"));
////				up.setUploadName(rs.getString("uploadname"));
//
//				map.put("wc", wc);
//				map.put("memberdto", memberdto);
//				map.put("up", up);
//
//				gotlist.add(map);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JDBCTemplate.close(rs);
//			JDBCTemplate.close(ps);
//		}
//
//		return gotlist;
//	}

}
