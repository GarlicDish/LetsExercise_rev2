package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.AdminQnaReplyDao;
import dto.board.QnaReply;

public class AdminQnaReplyDaoImpl implements AdminQnaReplyDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public int delete(Connection conn, QnaReply qr) {
		System.out.println("[delete DAO] 들어옴");
		System.out.println(qr);
		String sql = "";
		sql += "DELETE QnaReply";
		sql += " WHERE QnaReplyno = ?";

		int res = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qr.getQnareplyno());
			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;

	}

	@Override
	public int write(Connection conn, QnaReply qr) {

		String sql = "";
		sql += "INSERT INTO qnareply (qnaboardno, qnareplyno, member, content, writedate)";
		sql += " VALUES (?,?,?,?, sysdate)";

		int res = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qr.getQnaboardno());
			ps.setInt(2, qr.getQnareplyno());
			ps.setInt(3, qr.getMember());
			ps.setString(4, qr.getContent());

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
	public QnaReply getQnaReplyNumber(Connection conn, QnaReply qr) {

		String sql = "";
		sql += "SELECT Qnareply_seq.nextval csrNo FROM DUAL";

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				qr.setQnareplyno(rs.getInt("csrNo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return qr;
	}

	@Override
	public QnaReply getQRdata(Connection conn, QnaReply qr) {

		String sql = "";
		sql += "SELECT qnareplyno,member,content,writedate,updatedate,findboardno from QnaReply";
		sql += " WHERE QnaReplyno = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, qr.getQnareplyno());

			rs = ps.executeQuery();

			while (rs.next()) {
				qr.setContent(rs.getString("content"));
				qr.setQnaboardno(rs.getInt("qnaboardno"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return qr;
	}

	@Override
	public int update(Connection conn, HttpServletRequest req) {

		String sql = "";
		sql += "UPDATE QnaReply SET CONTENT = ?";
		sql += " WHERE QnaReplyno = ?";
		int res = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, req.getParameter("content"));
			ps.setInt(2, Integer.parseInt(req.getParameter("qnsreplyno")));

			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;

	}

}
