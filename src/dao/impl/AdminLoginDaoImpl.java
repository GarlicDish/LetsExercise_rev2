package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import dao.face.AdminLoginDao;

public class AdminLoginDaoImpl implements AdminLoginDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public int idpwMatch(Connection conn, String adminid, String adminpw) {

		// 결과 저장을 위한 변수
		int chk = 0;
		String sql = "";

//		sql += "SELECT count(*) FROM userinfo";
		sql += "SELECT * FROM admininfo";
		sql += " WHERE adminid = ?";
//		sql += "AND userpw = ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, adminid);
//			ps.setString(2, userpw);
			rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getNString("adminpw").equals(adminpw)) { // 패스워드 비교
					return 1; // 로그인에 성공
				}
				return 2; // 비밀번호가 틀림
			} else {
				return 0; // 해당 사용자가 존재하지 않음
			}

//			while (rs.next()) {
//				chk = rs.getInt(1);
//				// 일치하는 데이터가 1개 있다면 chk는 1이다. 아닐경우 변화없이 0
//			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return -1; // 데이터 베이스 오류
//		return chk;
	}

}
