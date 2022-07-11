package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.WorkCommentDao;
import dto.commentDto;
import util.Paging;

public class WorkCommentDaoImpl implements WorkCommentDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public List<commentDto> selectAll(HttpServletRequest req, Connection conn) {

		String sql = "";
		sql += "SELECT";
		sql += "	FINDREPLYNO";
		sql += "	, FINDBOARDNO";
		sql += "	, WRITEDATE";
		sql += "	, CONTENT";
		sql += "	, MEMBER";
		sql += " FROM findreply WHERE MEMBER=?";
		sql += " ORDER BY FINDREPLYNO DESC";

		List<commentDto> commentList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (int) req.getSession().getAttribute("usernumber"));
			rs = ps.executeQuery();

			while (rs.next()) {
				commentDto c = new commentDto();

				c.setCommentnumber(rs.getInt("FINDREPLYNO"));
				c.setBoardnumber(rs.getInt("FINDBOARDNO"));
				c.setCommentDate(rs.getDate("WRITEDATE"));
				c.setCommentText(rs.getString("CONTENT"));
				c.setUsernumber(rs.getInt("MEMBER"));

				commentList.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return commentList;
	}

	@Override
	public List<commentDto> selectAll(HttpServletRequest req, Connection conn, Paging paging) {

		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, C.* FROM (";
		sql += "SELECT";
		sql += "	FINDREPLYNO";
		sql += "	, FINDBOARDNO";
		sql += "	, WRITEDATE";
		sql += "	, CONTENT";
		sql += "	, MEMBER";
		sql += " FROM findreply WHERE MEMBER=?";
		sql += " 	) C";
		sql += " ) COMMENTTABLE";
		sql += " WHERE rnum BETWEEN ? AND ?";

		List<commentDto> commentList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (int) req.getSession().getAttribute("usernumber"));

			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());

			rs = ps.executeQuery();

			while (rs.next()) {
				commentDto c = new commentDto();

				c.setCommentnumber(rs.getInt("FINDREPLYNO"));
				c.setBoardnumber(rs.getInt("FINDBOARDNO"));
				c.setCommentDate(rs.getDate("WRITEDATE"));
				c.setCommentText(rs.getString("CONTENT"));
				c.setUsernumber(rs.getInt("MEMBER"));

				commentList.add(c);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return commentList;
	}

	@Override
	public int selectCntAll(Connection conn) {

		String sql = "";
		sql += "SELECT count(*) cnt FROM findreply";

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

}
