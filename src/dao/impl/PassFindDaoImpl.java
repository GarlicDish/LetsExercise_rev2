package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import dao.face.PassFindDao;

public class PassFindDaoImpl implements PassFindDao {

	PreparedStatement ps = null;
	
	@Override
	public int updateTmpPass(Connection conn, String userid, String newPass) {

		String sql = "";
		sql += "UPDATE USERINFO SET USERPW = ?";
		sql += "WHERE USERID = ? ";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, newPass);
			ps.setString(2, userid);
			
			res = ps.executeUpdate();
			
			if(res>0) JDBCTemplate.commit(conn);
			else JDBCTemplate.rollback(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
		}
		
		
		return res;
	}

}
