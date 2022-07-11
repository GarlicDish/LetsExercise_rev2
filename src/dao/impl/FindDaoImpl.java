package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.FindDao;
import dto.board.Find;
import dto.board.FindFile;
import dto.board.FindReply;
import dto.board.Member;
import util.Paging;

public class FindDaoImpl implements FindDao {

	private PreparedStatement ps = null; // SQL수행 객체
	private ResultSet rs = null; // SQL조회 결과 객체

	@Override
	public List<Find> selectAll(Connection conn) {

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
		sql += " FROM find";
		sql += " ORDER BY boardno DESC";

		// 결과 저장할 List
		List<Find> findList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			rs = ps.executeQuery(); // SQL수행 및 결과집합 저장

			while (rs.next()) {
				Find b = new Find(); // 결과값 저장 객체

				// 결과값 한 행 처리
				b.setBoardno(rs.getInt("boardno"));
				b.setBoardcode(rs.getInt("boardcode"));
				b.setUserid(rs.getNString("userid"));
				b.setUserno(rs.getInt("userno"));
				b.setTitle(rs.getString("title"));
//				b.setContent( rs.getString("content") );
				b.setHit(rs.getInt("hit"));
				b.setWritedate(rs.getDate("writedate"));
				b.setIsdelete(rs.getString("isdelete"));
				System.out.println(b);
				// 리스트객체에 조회한 행 객체 저장
				findList.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 조회 결과 반환
		return findList;
	}

	@Override
	public int selectCntAll(Connection conn) {

		// SQL 작성
		String sql = "";
		sql += "SELECT count(*) cnt FROM find";

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
	public int updateHit(Connection conn, Find boardno) {

		String sql = "";
		sql += "UPDATE find";
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
	public Find selectBoardByBoardno(Connection conn, Find boardno) {

		// SQL 작성
		String sql = "";
		sql += "SELECT";
		sql += "	boardno";
		sql += "	, title";
		sql += "	, userid";
		sql += "    , userno";
		sql += "	, content";
		sql += "	, hit";
		sql += "	, writeDate";
		sql += " FROM find";
		sql += " WHERE boardno = ?";

		// 결과 저장할 DTO객체
		Find find = null;

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setInt(1, boardno.getBoardno());

			rs = ps.executeQuery(); // SQL수행 및 결과집합 저장

			while (rs.next()) {
				find = new Find(); // 결과값 저장 객체

				// 결과값 행 처리
				find.setBoardno(rs.getInt("boardno"));
//				notice.setBoardcode(rs.getInt("boardcode") );
				find.setUserid(rs.getNString("userid"));
				find.setUserno(rs.getInt("userno"));
				find.setTitle(rs.getString("title"));
				find.setContent(rs.getString("content"));
				find.setHit(rs.getInt("hit"));
				find.setWritedate(rs.getDate("writeDate"));
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
		return find;
	}

	@Override
	public String selectNickByUserno(Connection conn, Find viewFind) {

		// SQL 작성
		String sql = "";
		sql += "SELECT nickname FROM userinfo";
		sql += " WHERE usernumber = ?";

		// 결과 저장할 String 변수
		String usernick = null;

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setInt(1, viewFind.getUserno()); // 조회할 id 적용

			rs = ps.executeQuery(); // SQL 수행 및 결과집합 저장

			// 조회 결과 처리
			while (rs.next()) {
				usernick = rs.getString("nickname");
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
	public List<Find> selectAll(Connection conn, Paging paging) {
		// SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, B.* FROM (";
		sql += " 		SELECT";
		sql += "			boardno, title, userid";
		sql += "			, hit, writeDate";
		sql += "		FROM find";
		sql += "		ORDER BY boardno DESC";
		sql += " 	) B";
		sql += " ) BOARD";
		sql += " WHERE rnum BETWEEN ? AND ?";

		// 결과 저장할 List
		List<Find> findList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());

			rs = ps.executeQuery(); // SQL수행 및 결과집합 저장

			while (rs.next()) {
				Find b = new Find(); // 결과값 저장 객체

				// 결과값 한 행 처리
				b.setBoardno(rs.getInt("boardno"));
//						b.setBoardcode(rs.getInt("boardcode") );
				b.setUserid(rs.getNString("userid"));
//						b.setUserno(rs.getInt("userno") );
				b.setTitle(rs.getString("title"));
//						b.setContent( rs.getString("content") );
				b.setHit(rs.getInt("hit"));
				b.setWritedate(rs.getDate("writeDate"));
//						b.setIsdelete(rs.getString ("isdelete") );

				// 리스트객체에 조회한 행 객체 저장
				findList.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 조회 결과 반환
		return findList;
	}

	@Override
	public int insert(Connection conn, Find find) {
		// 다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "INSERT INTO find(BOARDNO, BOARDCODE, USERID, USERNO, TITLE, CONTENT, HIT, WRITEDATE, ISDELETE)";
		sql += " VALUES (?, 6, ?, ?, ?, ?, 0, sysdate, 'n')";

		int res = 0;

		try {
			// DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, find.getBoardno());
			ps.setString(2, find.getUserid());
			ps.setInt(3, find.getUserno());
			ps.setString(4, find.getTitle());
			ps.setString(5, find.getContent());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	@Override
	public int selectBoardno(Connection conn) {
		// SQL 작성
		String sql = "";
		sql += "SELECT find_seq.nextval FROM dual";

		// 다음 시퀀스 번호
		int nextBoardno = 0;

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			rs = ps.executeQuery(); // SQL 수행 및 결과집합 저장

			// 조회 결과 처리
			while (rs.next()) {
				nextBoardno = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 결과 반환
		return nextBoardno;
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

	@Override
	public FindFile selectFile(Connection conn, Find viewBoard) {
		// SQL 작성
		String sql = "";
		sql += "SELECT";
		sql += "	fileno";
		sql += "	, boardno";
		sql += "	, originname";
		sql += "	, storedname";
		sql += "	, filesize";
		sql += "	, write_date";
		sql += " FROM findfile";
		sql += " WHERE boardno = ?";

		// 결과 저장할 DTO객체
		FindFile findFile = null;

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setInt(1, viewBoard.getBoardno());

			rs = ps.executeQuery(); // SQL수행 및 결과집합 저장

			while (rs.next()) {
				findFile = new FindFile(); // 결과값 저장 객체

				// 결과값 행 처리
				findFile.setFileno(rs.getInt("fileno"));
				findFile.setBoardno(rs.getInt("boardno"));
				findFile.setOriginname(rs.getString("originname"));
				findFile.setStoredname(rs.getString("storedname"));
				findFile.setFilesize(rs.getInt("filesize"));
				findFile.setWriteDate(rs.getDate("write_date"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 조회 결과 반환
		return findFile;
	}

	@Override
	public int update(Connection conn, Find find) {
		String sql = "";
		sql += "UPDATE find";
		sql += " SET title = ?,";
		sql += " 	content = ?";
		sql += " WHERE boardno = ?";

		int res = -1;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, find.getTitle());
			ps.setString(2, find.getContent());
			ps.setInt(3, find.getBoardno());

			res = ps.executeUpdate();

			System.out.println(res);

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	@Override
	public int delete(Connection conn, Find find) {
		String sql = "";
		sql += "DELETE find";
		sql += " WHERE boardno = ?";

		int res = -1;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, find.getBoardno());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	@Override
	public int deleteFile(Connection conn, Find find) {
		String sql = "";
		sql += "DELETE findfile";
		sql += " WHERE boardno = ?";

		int res = -1;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, find.getBoardno());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	@Override
	public List<Map<String, Object>> getMapList(Connection conn, HttpServletRequest req) {

		String sql = "";
		sql += "select fr.member, fr.findboardno, fr.findreplyno , fr.content, fr.writedate, fr.updatedate,mb.USERID, mb.NICKNAME";
		sql += " from findreply fr, USERinfo mb";
		sql += " where mb.USERNUMBER = fr.member (+) and fr.findboardno= ?";
		sql += " order by WRITEDATE ASC ";

		List<Map<String, Object>> gotlist = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(req.getParameter("boardno")));
			System.out.println(Integer.parseInt(req.getParameter("boardno")));
			rs = ps.executeQuery();

			while (rs.next()) {
				Map<String, Object> map = new HashMap<>();

				FindReply fr = new FindReply();
				Member mb = new Member();

				fr.setFindreplyno(rs.getInt("findreplyno"));
				fr.setFindboardno(rs.getInt("findboardno"));
				fr.setContent(rs.getString("content"));
				fr.setMember(rs.getInt("member"));
				fr.setWritedate(rs.getDate("writedate"));
				fr.setUpdatedate(rs.getDate("updatedate"));
				System.out.println(fr);
				mb.setUserid(rs.getString("USERID"));
				mb.setUsernick(rs.getString("NICKNAME"));
				mb.setUserno(rs.getInt("member"));

				map.put("fr", fr);
				map.put("mb", mb);

				gotlist.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return gotlist;
	}

}
