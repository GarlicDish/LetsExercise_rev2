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
import dao.face.WorkCommentWriteDao;
import dto.MemberDto;
import dto.Userprofile;
import dto.commentDto;

public class WorkCommentWriteDaoImpl implements WorkCommentWriteDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public List<Map<String, Object>> getMapList(Connection conn, HttpServletRequest req) {

		String sql = "";
		sql += "select wc.MEMBER, wc.findreplyno, wc.findboardno, wc.CONTENT, wc.writedate, ui.userid, ui.nickname, up.storedname";
		sql += " from userinfo ui, profilephoto up, findreply wc";
		sql += " where wc.MEMBER = ui.usernumber and wc.MEMBER = up.usernumber (+)";
		sql += " and wc.findboardno = ?";
		sql += " ORDER BY findreplyno asc";

		List<Map<String, Object>> gotlist = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			System.out.println("수행완료");
			System.out.println(req.getParameter("boardno"));
			ps.setInt(1, Integer.parseInt(req.getParameter("boardno")));
			// ps.setInt(1, 1);

			rs = ps.executeQuery();
			System.out.println("HI1");
			while (rs.next()) {
				System.out.println("HI2");
				Map<String, Object> map = new HashMap<>();

				commentDto wc = new commentDto();
				MemberDto memberdto = new MemberDto();
				Userprofile up = new Userprofile();

				wc.setCommentnumber(rs.getInt("findreplyno"));
				wc.setBoardnumber(rs.getInt("findboardno"));
				wc.setUsernumber(rs.getInt("MEMBER"));
				wc.setCommentText(rs.getString("CONTENT"));
				wc.setCommentDate(rs.getDate("writedate"));

				memberdto.setNickname(rs.getString("nickname"));
				memberdto.setUserID(rs.getString("userid"));

				up.setOriginname(rs.getString("storedname"));

				map.put("wc", wc);
				map.put("memberdto", memberdto);

				gotlist.add(map);
				System.out.println("map 값" + map);
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
	public int delete(Connection conn, commentDto wc) {
		System.out.println("[delete DAO] 들어옴");
		System.out.println(wc);
		String sql = "";
		sql += "DELETE findreply";
		sql += " WHERE findreplyno = ?";

		int res = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, wc.getCommentnumber());
			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;

	}

	@Override
	public int write(Connection conn, commentDto wc) {

		String sql = "";
		sql += "INSERT INTO findreply (findreplyno, findboardno, MEMBER, CONTENT, writedate)";
		sql += " VALUES (?,?,?,?, sysdate)";

		int res = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, wc.getCommentnumber());
			ps.setInt(2, wc.getBoardnumber());
			ps.setInt(3, wc.getUsernumber());
			ps.setString(4, wc.getCommentText());

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
	public commentDto getWorkCommentNumber(Connection conn, commentDto wc) {

		String sql = "";
		sql += "SELECT findreply_seq.nextval wcNo FROM DUAL";

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				wc.setCommentnumber(rs.getInt("wcNo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return wc;
	}

	@Override
	public commentDto getWCdata(Connection conn, commentDto wc) {

		String sql = "";
		sql += "SELECT findreplyno, findboardno, MEMBER, CONTENT, writedate from findreply";
		sql += " WHERE findreplyno = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, wc.getCommentnumber());

			rs = ps.executeQuery();

			while (rs.next()) {
				wc.setBoardnumber(rs.getInt("findboardno"));
				wc.setUsernumber(rs.getInt("MEMBER"));
				wc.setCommentText(rs.getString("CONTENT"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return wc;
	}

	@Override
	public int update(Connection conn, HttpServletRequest req) {

		String sql = "";
		sql += "UPDATE findreply SET content = ?";
		sql += " WHERE findreplyno = ?";
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
