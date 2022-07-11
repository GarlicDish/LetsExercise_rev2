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
import dao.face.AskCommentWriteDao;
import dto.AskComment;
import dto.MemberDto;
import dto.Userprofile;

public class AskCommentWriteDaoImpl implements AskCommentWriteDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public List<Map<String, Object>> getMapList(Connection conn, HttpServletRequest req) {

		String sql = "";
		sql += "select ac.MEMBER, ac.qnareply_no, ac.qnaboardno, ac.CONTENT, ac.writedate, ui.userid, ui.nickname, up.originname";
		sql += " from userinfo ui, profilephoto up, qnareply ac";
		sql += " where ac.MEMBER = ui.usernumber and ac.MEMBER = up.usernumber (+)";
		sql += " and ac.qnaboardno = ?";
		sql += " ORDER BY qnareply_no asc";

		List<Map<String, Object>> gotlist = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			System.out.println("수행완료");
			System.out.println(req.getParameter("boardnumber"));
			ps.setInt(1, Integer.parseInt(req.getParameter("boardno")));

			rs = ps.executeQuery();
			System.out.println("HI1");
			while (rs.next()) {
				System.out.println("HI2");

				Map<String, Object> map = new HashMap<>();

				AskComment ac = new AskComment();
				MemberDto memberdto = new MemberDto();
				Userprofile up = new Userprofile();

				ac.setUserNumber(rs.getInt("MEMBER"));
				ac.setCommentNumber(rs.getInt("qnareply_no"));
				ac.setBoardNumber(rs.getInt("qnaboardno"));
				ac.setCommentText(rs.getString("CONTENT"));
				ac.setCommentDate(rs.getDate("writedate"));

				memberdto.setUserID(rs.getString("userid"));
				memberdto.setNickname(rs.getString("nickname"));

				up.setOriginname(rs.getString("originname"));

				map.put("ac", ac);
				map.put("memberdto", memberdto);
				// map.put("up", up);

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

	@Override
	public int delete(Connection conn, AskComment ac) {
		System.out.println("[delete DAO] 들어옴");
		System.out.println(ac);
		String sql = "";
		sql += "DELETE qnareply";
		sql += " WHERE qnareply_no = ?";

		int res = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, ac.getCommentNumber());
			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;

	}

	@Override
	public int write(Connection conn, AskComment ac) {

		String sql = "";
		sql += "INSERT INTO qnareply (qnareply_no, qnaboardno, MEMBER, CONTENT, writedate)";
		sql += " VALUES (?,?,?,?, sysdate)";

		int res = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, ac.getCommentNumber());
			ps.setInt(2, ac.getBoardNumber());
			ps.setInt(3, ac.getUserNumber());
			ps.setString(4, ac.getCommentText());

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
	public AskComment getAskCommentNumber(Connection conn, AskComment ac) {

		String sql = "";
		sql += "SELECT qnareply_seq.nextval acNo FROM DUAL";

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				ac.setCommentNumber(rs.getInt("acNo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return ac;
	}

	@Override
	public AskComment getACdata(Connection conn, AskComment ac) {

		String sql = "";
		sql += "SELECT qnareply_no, qnaboardno, MEMBER, CONTENT, writedate from qnareply";
		sql += " WHERE qnareply_no = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, ac.getCommentNumber());

			rs = ps.executeQuery();

			while (rs.next()) {
				ac.setBoardNumber(rs.getInt("qnaboardno"));
				ac.setUserNumber(rs.getInt("MEMBER"));
				ac.setCommentText(rs.getString("CONTENT"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return ac;
	}

	@Override
	public int update(Connection conn, HttpServletRequest req) {

		String sql = "";
		sql += "UPDATE qnareply SET CONTENT = ?";
		sql += " WHERE qnareply_no = ?";
		int res = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, req.getParameter("commenttext"));
			ps.setInt(2, Integer.parseInt(req.getParameter("commentnumber")));

			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;

	}

}
