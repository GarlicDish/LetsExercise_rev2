package service.face;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dto.club.User;
import util.Paging;

public interface MyClubService {
	/**
	 * 회원이 가입한 동호회 목록 조회
	 * 
	 * @param paging
	 * 
	 * @return 조회된 로그인한 회원이 가입한 동호회 목록
	 */
	public List<Map<String, Object>> getMyList(User user, Paging paging);

	/**
	 * userid 랑 pw 로 데이터 가져오기
	 * 
	 * @param user
	 * @return 정보 담긴 user 객체
	 */
	public User info(User user);

	/**
	 * UserId로 userno 가져오기
	 * 
	 * @param user
	 */
	public User getLoginUserInfoByUserno(User user);

	/**
	 * 해당 동호회 삭제(정지)
	 * 
	 * @param req - clubnumver가 담긴 club 객체
	 */
	public void deactivateClub(HttpServletRequest req);

	public Paging getPaging(HttpServletRequest req);

	/**
	 * 회원 탈퇴
	 * 
	 * @param req
	 */
	public void withdraw(HttpServletRequest req);
}
