package dao.face;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dto.club.User;
import util.Paging;

public interface MyClubDao {
	/**
	 * 회원이 가입한 동호회 전체 조회
	 * 
	 * @param conn   - DB 연결 객체
	 * @param paging
	 * @return 회원number와 동호회 목록, 각 동호회 회원 목록과 join 된 테이블 정보
	 */
	public List<Map<String, Object>> selectMyClub(Connection conn, User user, Paging paging);

	/**
	 * user id로 user 정보 불러오기
	 * 
	 * @param conn
	 * @param user
	 * @return
	 */
	public User selectMemberByUserid(Connection conn, User user);

	/**
	 * 클럽 정보 deactivation
	 * 
	 * @param conn - DB 연결 객체
	 * @param req  - clubnumber 정보를 담은 CLUB 객체
	 * @return
	 */
	public int deactivateClub(Connection conn, HttpServletRequest req);

	public int selectCntAll(Connection connection, HttpServletRequest req);

	public User selectMemberByUserno(Connection conn, User user);

	public int withdraw(Connection conn, HttpServletRequest req);
}
