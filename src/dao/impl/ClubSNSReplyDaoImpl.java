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
import dao.face.ClubSNSReplyDao;
import dto.club.ClubSNSReplyDto;
import dto.club.User;
import dto.club.UserProfile;

public class ClubSNSReplyDaoImpl implements ClubSNSReplyDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public List<Map<String, Object>> getMapList(Connection conn, HttpServletRequest req) {

		String sql = "";
		sql += "select csr.writer, csr.ClubSNSReplyNumber, csr.ClubSNSNumber, csr.replycontent, csr.postingdate, ui.nickname, up.originname, up.storedname";
		sql += " from userinfo ui, profilephoto up, clubsnsreply csr";
		sql += " where csr.writer = ui.usernumber and csr.writer = up.usernumber (+)";
		sql += " and csr.clubsnsnumber = ?";
		sql += " ORDER BY clubsnsreplynumber asc";

		List<Map<String, Object>> gotlist = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(req.getParameter("clubsnsnumber")));

			rs = ps.executeQuery();

			while (rs.next()) {
				Map<String, Object> map = new HashMap<>();
				ClubSNSReplyDto csr = new ClubSNSReplyDto();
				User user = new User();
				UserProfile up = new UserProfile();

				csr.setClubSNSReplyNumber(rs.getInt("ClubSNSReplyNumber"));
				csr.setClubSNSNumber(rs.getInt("ClubSNSNumber"));
				csr.setWriter(rs.getInt("Writer"));
				csr.setReplyContent(rs.getString("ReplyContent"));
				csr.setPostingDate(rs.getDate("PostingDate"));

				user.setUsername(rs.getString("nickname"));

				up.setOriginname(rs.getString("originname"));
				up.setUploadname(rs.getString("storedname"));

				map.put("csr", csr);
				map.put("user", user);
				map.put("up", up);

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
	public int delete(Connection conn, ClubSNSReplyDto csr) {
		System.out.println("[delete DAO] 들어옴");
		System.out.println(csr);
		String sql = "";
		sql += "DELETE CLUBSNSREPLY";
		sql += " WHERE CLUBSNSREPLYNUMBER = ?";

		int res = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, csr.getClubSNSReplyNumber());
			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;

	}

	@Override
	public int write(Connection conn, ClubSNSReplyDto csr) {

		String sql = "";
		sql += "INSERT INTO clubsnsreply (ClubSNSReplyNumber, ClubSNSNumber, Writer, ReplyContent, PostingDate)";
		sql += " VALUES (?,?,?,?, sysdate)";

		int res = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, csr.getClubSNSReplyNumber());
			ps.setInt(2, csr.getClubSNSNumber());
			ps.setInt(3, csr.getWriter());
			ps.setString(4, csr.getReplyContent());

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
	public ClubSNSReplyDto getClubSNSReplyNumber(Connection conn, ClubSNSReplyDto csr) {

		String sql = "";
		sql += "SELECT ClubSNSReplynumber_seq.nextval csrNo FROM DUAL";

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				csr.setClubSNSReplyNumber(rs.getInt("csrNo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return csr;
	}

	@Override
	public ClubSNSReplyDto getCSRdata(Connection conn, ClubSNSReplyDto csr) {

		String sql = "";
		sql += "SELECT clubsnsreplynumber, clubsnsnumber, writer, replycontent, postingdate from clubsnsreply";
		sql += " WHERE clubsnsreplynumber = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, csr.getClubSNSReplyNumber());

			rs = ps.executeQuery();

			while (rs.next()) {
				csr.setClubSNSNumber(rs.getInt("clubsnsnumber"));
				csr.setWriter(rs.getInt("writer"));
				csr.setReplyContent(rs.getString("replycontent"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return csr;
	}

	@Override
	public int update(Connection conn, HttpServletRequest req) {

		String sql = "";
		sql += "UPDATE clubsnsreply SET replyCONTENT = ?";
		sql += " WHERE clubsnsreplynumber = ?";
		int res = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, req.getParameter("replycontent"));
			ps.setInt(2, Integer.parseInt(req.getParameter("clubsnsnreplynumber")));

			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;

	}

}
