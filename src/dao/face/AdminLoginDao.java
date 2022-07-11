package dao.face;

import java.sql.Connection;

public interface AdminLoginDao {

	/**
	 * @param conn   DB연결객체
	 * @param member 로그인한 유저 정보 DTO객체
	 * @return DB와 대조한 결과: 존재(1) 존재하지 않음(0)
	 */
	public int idpwMatch(Connection conn, String adminid, String adminpw);

}
