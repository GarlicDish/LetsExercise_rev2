package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.FindReplyDao;
import dto.board.FindReply;

public class FindReplyDaoImpl implements FindReplyDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public int delete(Connection conn, FindReply fr) {
		System.out.println("[delete DAO] 들어옴");
		System.out.println(fr);
		String sql = "";
		sql += "DELETE FindReply";
		sql += " WHERE FindReplyno = ?";

		int res = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, fr.getFindreplyno());
			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;

	}

	@Override
	public int write(Connection conn, FindReply fr) {

		String sql = "";
		sql += "INSERT INTO findreply (findboardno, findreplyno, member, content, writedate)";
		sql += " VALUES (?,?,?,?, sysdate)";

		int res = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, fr.getFindboardno());
			ps.setInt(2, fr.getFindreplyno());
			ps.setInt(3, fr.getMember());
			ps.setString(4, fr.getContent());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return res;
	}

	@Override
	public FindReply getFindReplyNumber(Connection conn, FindReply fr) {

		String sql = "";
		sql += "SELECT Findreply_seq.nextval csrNo FROM DUAL";

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				fr.setFindreplyno(rs.getInt("csrNo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return fr;
	}

	@Override
	public FindReply getFRdata(Connection conn, FindReply fr) {

		String sql = "";
		sql += "SELECT findreplyno,member,content,writedate,updatedate,findboardno from FindReply";
		sql += " WHERE FindReplyno = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, fr.getFindreplyno());

			rs = ps.executeQuery();

			while (rs.next()) {
				fr.setContent(rs.getString("content"));
				fr.setFindboardno(rs.getInt("findboardno"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return fr;
	}

	@Override
	public int update(Connection conn, HttpServletRequest req) {

		String sql = "";
		sql += "UPDATE FindReply SET CONTENT = ?";
		sql += " WHERE FindReplyno = ?";
		int res = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, req.getParameter("content"));
			ps.setInt(2, Integer.parseInt(req.getParameter("findreplyno")));

			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;

	}

}
