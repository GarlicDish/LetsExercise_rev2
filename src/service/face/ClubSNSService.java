package service.face;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.club.ClubDto;
import dto.club.ClubSNSDto;

public interface ClubSNSService {

	/**
	 * 동호회 번호가 담긴 DTO 객체 반환
	 * 
	 * @param req - 전달받은 파라미터
	 * @return 동호회 번호가 담긴 DTO 객체
	 */
	ClubDto getClubnumber(HttpServletRequest req);

	/**
	 * club number로 SNS 게시글 전부 불러오기
	 * 
	 * @param club - 전달받은 동호회 번호가 담긴 club DTO 객체
	 * @return - 해당 동호회의 모든 SNS 게시글
	 */
	List<ClubSNSDto> selectSNSBySNSno(ClubDto club);

	/**
	 * 글쓰기 및 첨부 사진 업로드
	 * 
	 * @param req
	 * @param resp
	 */
	void write(HttpServletRequest req, HttpServletResponse resp);

	/**
	 * 조회한 ClubSNSList 를 활용하여, 회원 정보, 회원 프로필 불러오기
	 * 
	 * @param club - 회원 번호를 갖고 있는 csList
	 * @return 회원 이름 및 사진 정보를 갖고 있는 List<Map<String, Object>>
	 */
	List<Map<String, Object>> listWithMap(ClubDto club);

	/**
	 * 선택한 게시글 삭제
	 * 
	 * @param req
	 */
	void delete(HttpServletRequest req);

	/**
	 * clubSNS 게시글 번호가 담긴 객체
	 * 
	 * @param req
	 * @return
	 */
	ClubSNSDto getSNSno(HttpServletRequest req);

	/**
	 * snsno 로 해당 정보 전부 불러오기
	 * 
	 * @param snSno
	 * @return
	 */
	ClubSNSDto view(ClubSNSDto snSno);

	/**
	 * 게시글 수정
	 * 
	 * @param req
	 * @param resp
	 */
	void update(HttpServletRequest req, HttpServletResponse resp);

	/**
	 * 멤버 여부 확인
	 * 
	 * @param req
	 * @return
	 */
	boolean checkMember(HttpServletRequest req);

	/**
	 * clubsnsnumber 가 담긴 req 객체를 이용해, 해당 clubsns 전체 객체 가져오기
	 * 
	 * @param req
	 * @return
	 */
	ClubSNSDto getSNS(HttpServletRequest req);

	/**
	 * snsnumber가 담긴 객체로, clubno 가져오기
	 * 
	 * @param clubSNS
	 * @return
	 */
	ClubDto getClubnumber(ClubSNSDto clubSNS);

	/**
	 * clubsnsnumber 가지고 글쓰니 정보 가져오기
	 * 
	 * @param clubSNS
	 * @return
	 */
	Map<String, Object> getUserUserProfileClubSNSbyClubSNSno(ClubSNSDto clubSNS);

}
