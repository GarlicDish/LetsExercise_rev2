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
import dao.face.QnaDao;
import dto.board.BoardFile;
import dto.board.Member;
import dto.board.Qna;
import dto.board.QnaReply;
import util.Paging;

public class QnaDaoImpl implements QnaDao {

	private PreparedStatement ps = null; // SQL수행 객체
	private ResultSet rs = null; // SQL조회 결과 객체

	@Override
	public List<Qna> selectAll(Connection conn) {

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
		sql += " FROM qna";
		sql += " ORDER BY boardno DESC";

		// 결과 저장할 List
		List<Qna> qnaList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			rs = ps.executeQuery(); // SQL수행 및 결과집합 저장

			while (rs.next()) {
				Qna b = new Qna(); // 결과값 저장 객체

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
				qnaList.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 조회 결과 반환
		return qnaList;
	}

	@Override
	public int selectCntAll(Connection conn) {

		// SQL 작성
		String sql = "";
		sql += "SELECT count(*) cnt FROM qna";

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
	public int updateHit(Connection conn, Qna boardno) {

		String sql = "";
		sql += "UPDATE qna";
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
	public Qna selectBoardByBoardno(Connection conn, Qna boardno) {

		// SQL 작성
		String sql = "";
		sql += "SELECT";
		sql += "	boardno";
		sql += "    , userno";
		sql += "	, title";
		sql += "	, userid";
		sql += "	, content";
		sql += "	, hit";
		sql += "	, writeDate";
		sql += " FROM qna";
		sql += " WHERE boardno = ?";

		// 결과 저장할 DTO객체
		Qna qna = null;

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setInt(1, boardno.getBoardno());

			rs = ps.executeQuery(); // SQL수행 및 결과집합 저장

			while (rs.next()) {
				qna = new Qna(); // 결과값 저장 객체

				// 결과값 행 처리
				qna.setBoardno(rs.getInt("boardno"));
//				notice.setBoardcode(rs.getInt("boardcode") );
				qna.setUserid(rs.getNString("userid"));
				qna.setUserno(rs.getInt("userno"));
				qna.setTitle(rs.getString("title"));
				qna.setContent(rs.getString("content"));
				qna.setHit(rs.getInt("hit"));
				qna.setWritedate(rs.getDate("writeDate"));
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
		return qna;
	}

	@Override
	public String selectNickByUserid(Connection conn, Qna viewQna) {

		// SQL 작성
		String sql = "";
		sql += "SELECT nickname FROM userinfo";
		sql += " WHERE userid = ?";

		// 결과 저장할 String 변수
		String usernick = null;

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setString(1, viewQna.getUserid()); // 조회할 id 적용

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
	public List<Qna> selectAll(Connection conn, Paging paging) {
		// SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, B.* FROM (";
		sql += " 		SELECT";
		sql += "			boardno, title, userid";
		sql += "			, hit, writeDate";
		sql += "		FROM qna";
		sql += "		ORDER BY boardno DESC";
		sql += " 	) B";
		sql += " ) BOARD";
		sql += " WHERE rnum BETWEEN ? AND ?";

		// 결과 저장할 List
		List<Qna> qnaList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());

			rs = ps.executeQuery(); // SQL수행 및 결과집합 저장

			while (rs.next()) {
				Qna b = new Qna(); // 결과값 저장 객체

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
				qnaList.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		// 최종 조회 결과 반환
		return qnaList;
	}

	@Override
	public int insert(Connection conn, Qna qna) {
		// 다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "INSERT INTO qna(BOARDNO, BOARDCODE,USERID, USERNO, TITLE, CONTENT, HIT, WRITEDATE, ISDELETE)";
		sql += " VALUES (?, 5, ?, ?, ?, ?, 0, sysdate, 'n')";

		int res = 0;

		try {
			// DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qna.getBoardno());
			ps.setString(2, qna.getUserid());
			ps.setInt(3, qna.getUserno());
			ps.setString(4, qna.getTitle());
			ps.setString(5, qna.getContent());

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
		sql += "SELECT qna_seq.nextval FROM dual";

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
	public int insertFile(Connection conn, BoardFile boardFile) {
		String sql = "";
		sql += "INSERT INTO boardfile( FILENO, BOARDNO, ORIGINNAME, STOREDNAME, FILESIZE )";
		sql += " VALUES (boardfile_seq.nextval, ?, ?, ?, ?)";

		int res = 0;

		try {
			// DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardFile.getBoardno());
			ps.setString(2, boardFile.getOriginname());
			ps.setString(3, boardFile.getStoredname());
			ps.setInt(4, boardFile.getFilesize());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	@Override
	public BoardFile selectFile(Connection conn, Qna viewBoard) {
		// SQL 작성
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
		BoardFile boardFile = null;

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setInt(1, viewBoard.getBoardno());

			rs = ps.executeQuery(); // SQL수행 및 결과집합 저장

			while (rs.next()) {
				boardFile = new BoardFile(); // 결과값 저장 객체

				// 결과값 행 처리
				boardFile.setFileno(rs.getInt("fileno"));
				boardFile.setBoardno(rs.getInt("boardno"));
				boardFile.setOriginname(rs.getString("originname"));
				boardFile.setStoredname(rs.getString("storedname"));
				boardFile.setFilesize(rs.getInt("filesize"));
				boardFile.setWriteDate(rs.getDate("write_date"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 조회 결과 반환
		return boardFile;
	}

	@Override
	public int update(Connection conn, Qna qna) {
		String sql = "";
		sql += "UPDATE qna";
		sql += " SET title = ?,";
		sql += " 	content = ?";
		sql += " WHERE boardno = ?";

		int res = -1;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, qna.getTitle());
			ps.setString(2, qna.getContent());
			ps.setInt(3, qna.getBoardno());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	@Override
	public int delete(Connection conn, Qna qna) {
		String sql = "";
		sql += "DELETE qna";
		sql += " WHERE boardno = ?";

		int res = -1;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qna.getBoardno());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	@Override
	public int deleteFile(Connection conn, Qna qna) {
		String sql = "";
		sql += "DELETE boardfile";
		sql += " WHERE boardno = ?";

		int res = -1;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qna.getBoardno());

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
		sql += "select fr.member, fr.qnaboardno, fr.qnareply_no , fr.content, fr.writedate, fr.updatedate,mb.USERID, mb.NICKNAME";
		sql += " from qnareply fr, USERinfo mb";
		sql += " where mb.USERNUMBER = fr.member (+) and fr.qnaboardno= ?";
		sql += " order by WRITEDATE ASC ";

		List<Map<String, Object>> gotlist = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(req.getParameter("boardno")));
			System.out.println(Integer.parseInt(req.getParameter("boardno")));
			rs = ps.executeQuery();

			while (rs.next()) {
				Map<String, Object> map = new HashMap<>();

				QnaReply fr = new QnaReply();
				Member mb = new Member();

				fr.setQnareplyno(rs.getInt("qnareply_no"));
				fr.setQnaboardno(rs.getInt("qnaboardno"));
				fr.setContent(rs.getString("content"));
				fr.setMember(rs.getInt("member"));
				fr.setWritedate(rs.getDate("writedate"));
				fr.setUpdatedate(rs.getDate("updatedate"));

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
