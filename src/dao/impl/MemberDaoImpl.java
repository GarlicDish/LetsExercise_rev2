package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import dao.face.MemberDao;
import dto.board.Member;

public class MemberDaoImpl implements MemberDao {

	private PreparedStatement ps = null; // SQL수행 객체
	private ResultSet rs = null; // SQL조회 결과 객체

	@Override
	public int selectCntMemberByUseridUserpw(Connection conn, Member member) {

		String sql = "";
		sql += "SELECT count(*) FROM member";
		sql += " WHERE userid = ?";
		sql += "	AND userpw = ?";

		int cnt = 0;

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, member.getUserid());
			ps.setString(2, member.getUserpw());

			rs = ps.executeQuery();

			while (rs.next()) {
				cnt = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return cnt;
	}

	@Override
	public Member selectMemberByUserid(Connection conn, Member member) {

		String sql = "";
		sql += "SELECT userid, userpw, usernick";
		sql += " FROM member";
		sql += " WHERE userid = ?";

		// 조회 결과를 저장할 DTO
		Member result = null;

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, member.getUserid());

			rs = ps.executeQuery();

			while (rs.next()) {
				result = new Member();

				result.setUserid(rs.getString("userid"));
				result.setUserpw(rs.getString("userpw"));
				result.setUsernick(rs.getString("usernick"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return result;

	}

	@Override
	public int insert(Connection conn, Member member) {

		String sql = "";
		sql += "INSERT INTO member ( userid, userpw, usernick )";
		sql += " VALUES ( ?, ?, ? )";

		int res = 0;

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, member.getUserid());
			ps.setString(2, member.getUserpw());
			ps.setString(3, member.getUsernick());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

}
