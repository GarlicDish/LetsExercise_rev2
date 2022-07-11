package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.AskDao;
import dto.Ask;
import dto.AskFile;
import util.Paging;

public class AskDaoImpl implements AskDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public List<Ask> selectAll(HttpServletRequest req, Connection conn, Paging paging) {

		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, A.* FROM (";
		sql += " 		SELECT";
		sql += "			boardno, title, userid";
		sql += "			, hit, writedate";
		sql += "		FROM qna WHERE userid= ?";
		sql += "		ORDER BY boardno DESC";
		sql += " 	) A";
		sql += " ) ASK";
		sql += " WHERE rnum BETWEEN ? AND ?";

		// 결과값 저장할 List 객체
		List<Ask> askList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);

			ps.setNString(1, (String) req.getSession().getAttribute("userID"));
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());

			rs = ps.executeQuery();

			while (rs.next()) {
				Ask a = new Ask();

				a.setBoardNumber(rs.getInt("boardno"));
				a.setTitle(rs.getString("title"));
				a.setUserID(rs.getString("userid"));
				a.setHit(rs.getInt("hit"));
				a.setWrite_date(rs.getDate("writedate"));

				askList.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return askList;
	}

	@Override
	public int selectCntAll(Connection conn) {

		String sql = "";
		sql += "SELECT count(*) cnt FROM qna";

		// 총 문의글 수
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
	public int updateHit(Connection conn, Ask boardno) {

		String sql = "";
		sql += "UPDATE qna";
		sql += " SET hit = hit + 1";
		sql += " WHERE boardno = ?";

		int res = 0;

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, boardno.getBoardNumber());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	@Override
	public Ask selectBoardByBoardno(Connection conn, Ask boardno) {

		String sql = "";
		sql += "SELECT";
		sql += "	boardno";
		sql += "	, title";
		sql += "	, userid";
		sql += "	, content";
		sql += "	, hit";
		sql += "	, writedate";
		sql += " FROM qna";
		sql += " WHERE boardno = ?";

		// 결과 저장할 DTO객체
		Ask ask = null;

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, boardno.getBoardNumber());

			rs = ps.executeQuery();

			while (rs.next()) {
				ask = new Ask();

				ask.setBoardNumber(rs.getInt("boardno"));
				ask.setTitle(rs.getString("title"));
				ask.setUserID(rs.getString("userid"));
				ask.setContent(rs.getString("content"));
				ask.setHit(rs.getInt("hit"));
				ask.setWrite_date(rs.getDate("writedate"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return ask;
	}

	@Override
	public int insert(HttpServletRequest req, Connection conn, Ask ask) {

		String sql = "";
		sql += "INSERT INTO qna(boardno, BOARDCODE, TITLE, USERID, CONTENT, HIT, userno, writedate, isdelete)";
		sql += " VALUES (?, 5, ?, ?, ?, 0, ?, sysdate, 'n')";

		int res = 0;

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, ask.getBoardNumber());
			ps.setString(2, ask.getTitle());
			ps.setString(3, ask.getUserID());
			ps.setString(4, ask.getContent());
			ps.setInt(5, (int) req.getSession().getAttribute("userno"));

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	@Override
	public String selectNickByUserid(Connection conn, Ask viewAsk) {

		String sql = "";
		sql += "SELECT nickname FROM userinfo";
		sql += " WHERE userid = ?";

		// 결과 저장할 변수
		String nickname = null;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, viewAsk.getUserID());

			rs = ps.executeQuery();

			// 조회 결과 처리
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
	public int selectBoardno(Connection conn) {

		String sql = "";
		sql += "SELECT qna_seq.nextval FROM dual";

		// 다음 시퀀스 번호
		int nextBoardno = 0;

		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			// 조회 결과 처리
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
	public int update(Connection conn, Ask ask) {

		String sql = "";
		sql += "UPDATE qna";
		sql += " SET title = ?,";
		sql += " 	content = ?";
		sql += " WHERE boardno = ?";

		int res = -1;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, ask.getTitle());
			ps.setString(2, ask.getContent());
			ps.setInt(3, ask.getBoardNumber());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	@Override
	public int delete(Connection conn, Ask ask) {

		String sql = "";
		sql += "DELETE qna";
		sql += " WHERE boardno = ?";

		int res = -1;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, ask.getBoardNumber());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	@Override
	public int insertFile(Connection conn, AskFile askFile) {
		String sql = "";
		sql += "INSERT INTO boardfile( fileno, boardno, ORIGINNAME, STOREDNAME, FILESIZE )";
		sql += " VALUES (askfile_seq.nextval, ?, ?, ?, ?)";

		int res = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, askFile.getBoardNumber());
			ps.setString(2, askFile.getOriginname());
			ps.setString(3, askFile.getStoredname());
			ps.setInt(4, askFile.getFileSize());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	@Override
	public AskFile selectFile(Connection conn, Ask viewAsk) {

		String sql = "";
		sql += "SELECT";
		sql += "	fileno";
		sql += "	, boardno";
		sql += "	, originname";
		sql += "	, storedname";
		sql += "	, filesize";
		sql += "	, write_date";
		sql += " FROM boardfile";
		sql += " WHERE boardno = ?";

		// 결과 저장할 DTO객체
		AskFile askFile = null;

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, viewAsk.getBoardNumber());

			rs = ps.executeQuery();

			while (rs.next()) {
				askFile = new AskFile();

				// 결과값 행 처리
				askFile.setFileNumber(rs.getInt("fileno"));
				askFile.setBoardNumber(rs.getInt("boardno"));
				askFile.setOriginname(rs.getString("originname"));
				askFile.setStoredname(rs.getString("storedname"));
				askFile.setFileSize(rs.getInt("filesize"));
				askFile.setWriteDate(rs.getDate("write_date"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return askFile;
	}

	@Override
	public int deleteFile(Connection conn, Ask ask) {

		String sql = "";
		sql += "DELETE boardfile";
		sql += " WHERE boardno = ?";

		int res = -1;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, ask.getBoardNumber());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

}