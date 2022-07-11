package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.AdminNoticeDao;
import dto.board.FindFile;
import dto.board.Notice;
import util.Paging;

public class AdminNoticeDaoImpl implements AdminNoticeDao {

	private PreparedStatement ps = null; // SQL수행 객체
	private ResultSet rs = null; // SQL조회 결과 객체

	@Override
	public List<Notice> selectAll(Connection conn) {

		// SQL 작성
		String sql = "";
		sql += "SELECT";
		sql += "	boardno";
		sql += "	,boardcode";
		sql += "	, userid";
		sql += "	, userno";
		sql += "	, title";
//		sql += "	, content";
		sql += "	, hit";
		sql += "	, writeDate";
		sql += "	, isdelete";
		sql += " FROM notice";
		sql += " ORDER BY boardno DESC";

		// 결과 저장할 List
		List<Notice> noticeList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			rs = ps.executeQuery(); // SQL수행 및 결과집합 저장

			while (rs.next()) {
				Notice b = new Notice(); // 결과값 저장 객체

				// 결과값 한 행 처리
				b.setBoardno(rs.getInt("boardno"));
				b.setBoardcode(rs.getInt("boardcode"));
				b.setUserid(rs.getNString("userid"));
				b.setUserno(rs.getInt("userno"));
				b.setTitle(rs.getString("title"));
//				b.setContent(rs.getString("content"));
				b.setHit(rs.getInt("hit"));
				b.setWriteDate(rs.getDate("writeDate"));
				b.setIsdelete(rs.getString("isdelete"));

				// 리스트객체에 조회한 행 객체 저장
				noticeList.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 조회 결과 반환
		return noticeList;
	}

	@Override
	public int selectCntAll(Connection conn) {

		// SQL 작성
		String sql = "";
		sql += "SELECT count(*) cnt FROM notice";

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
	public int updateHit(Connection conn, Notice boardno) {

		String sql = "";
		sql += "UPDATE notice";
		sql += " SET hit = hit + 1";
		sql += " WHERE boardno = ?";

		int res = 0;

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, boardno.getBoardno());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	@Override
	public Notice selectBoardByBoardno(Connection conn, Notice boardno) {

		// SQL 작성
		String sql = "";
		sql += "SELECT";
		sql += "	boardno";
		sql += "	, title";
		sql += "	, userid";
		sql += "	, content";
		sql += "	, hit";
		sql += "	, writeDate";
		sql += " FROM notice";
		sql += " WHERE boardno = ?";

		// 결과 저장할 DTO객체
		Notice notice = null;

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setInt(1, boardno.getBoardno());

			rs = ps.executeQuery(); // SQL수행 및 결과집합 저장

			while (rs.next()) {
				notice = new Notice(); // 결과값 저장 객체

				// 결과값 행 처리
				notice.setBoardno(rs.getInt("boardno"));
//				notice.setBoardcode(rs.getInt("boardcode") );
				notice.setUserid(rs.getNString("userid"));
//				notice.setUserno(rs.getInt("userno") );
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setHit(rs.getInt("hit"));
				notice.setWriteDate(rs.getDate("writeDate"));
//				notice.setIsdelete(rs.getString ("isdelete") );
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 조회 결과 반환
		return notice;
	}

	@Override
	public String selectNickByUserid(Connection conn, Notice viewNotice) {

		// SQL 작성
		String sql = "";
		sql += "SELECT nickname FROM userinfo";
		sql += " WHERE userid = ?";

		// 결과 저장할 String 변수
		String usernick = null;

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setString(1, viewNotice.getUserid()); // 조회할 id 적용

			rs = ps.executeQuery(); // SQL 수행 및 결과집합 저장

			// 조회 결과 처리
			while (rs.next()) {
				usernick = rs.getString("usernick");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 결과 반환
		return usernick;

	}

	@Override
	public List<Notice> selectAll(Connection conn, Paging paging) {
		// SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, B.* FROM (";
		sql += " 		SELECT";
		sql += "			boardno, title, userid";
		sql += "			, hit, writeDate";
		sql += "		FROM notice";
		sql += "		ORDER BY boardno DESC";
		sql += " 	) B";
		sql += " ) BOARD";
		sql += " WHERE rnum BETWEEN ? AND ?";

		// 결과 저장할 List
		List<Notice> noticeList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());

			rs = ps.executeQuery(); // SQL수행 및 결과집합 저장

			while (rs.next()) {
				Notice b = new Notice(); // 결과값 저장 객체

				// 결과값 한 행 처리
				b.setBoardno(rs.getInt("boardno"));
				b.setBoardcode(rs.getInt("boardcode"));
				b.setUserid(rs.getNString("userid"));
				b.setUserno(rs.getInt("usernumber"));
				b.setTitle(rs.getString("title"));
//						b.setContent( rs.getString("content") );
				b.setHit(rs.getInt("hit"));
				b.setWriteDate(rs.getDate("writeDate"));
				b.setIsdelete(rs.getString("isdelete"));

				// 리스트객체에 조회한 행 객체 저장
				noticeList.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 조회 결과 반환
		return noticeList;
	}

	@Override
	public int insert(Connection conn, Notice notice) {
		// 다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "INSERT INTO notice(BOARDNO, TITLE, USERNO, USERID, CONTENT, HIT, BOARDCODE, WRITEDATE, isdelete)";
		sql += " VALUES (notice_seq.nextval, ?, 1, ?, ?, 0, 4, SYSDATE, 'N')";

		int res = 0;

		try {
			// DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, notice.getTitle());
			ps.setString(2, "관리자");
			ps.setString(3, notice.getContent());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	@Override
	public String selectNickByUserno(Connection conn, Notice viewNotice) {
		// SQL 작성
		String sql = "";
		sql += "SELECT nickname FROM userinfo";
		sql += " WHERE usernumber = ?";

		// 결과 저장할 String 변수
		String usernick = null;

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setInt(1, viewNotice.getUserno()); // 조회할 id 적용

			rs = ps.executeQuery(); // SQL 수행 및 결과집합 저장

			// 조회 결과 처리
			while (rs.next()) {
				usernick = rs.getString("usernick");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 결과 반환
		return usernick;

	}

	@Override
	public int selectBoardno(Connection conn) {
		String sql = "";
		sql += "SELECT notice_seq.nextval next from dual";

		int res = 0;

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getInt("next");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return res;

	}

	@Override
	public int insertFile(Connection conn, FindFile findFile) {
		String sql = "";
		sql += "INSERT INTO findfile( FILENO, BOARDNO, ORIGINNAME, STOREDNAME, FILESIZE )";
		sql += " VALUES (boardfile_seq.nextval, ?, ?, ?, ?)";

		int res = 0;

		try {
			// DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, findFile.getBoardno());
			ps.setString(2, findFile.getOriginname());
			ps.setString(3, findFile.getStoredname());
			ps.setInt(4, findFile.getFilesize());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

}
