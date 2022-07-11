package dao.face;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dto.club.CityDto;
import dto.club.ClubDto;
import dto.club.ClubMemberListDto;
import dto.club.ExerciseDto;
import dto.club.GuDto;
import dto.club.User;
import dto.club.UserProfile;
import util.Paging;

public interface ClubDao {

	/**
	 * clubnumber_seq 다음 값 불러오기
	 * 
	 * @param conn DB연결 객체
	 * @return 다음 ClubNo 값
	 */
	public int selectNextClubNo(Connection conn);

	/**
	 * clubnumber가 포함된 Club 객체를 데이터베이스에 전송
	 * 
	 * @param conn DB연결 객체
	 * @param club cluno이 포함된 Club 객체
	 * @return 데이터 삽입 결과 true(1) - 성공 false(0) - 실패
	 */
	public int insertNextClub(Connection conn, ClubDto club);

	/**
	 * 활성화된 동호회 정보 전체 조회
	 * 
	 * @param conn   DB 연결 객체
	 * @param paging
	 * @return 조회된 동호회 정보 목록
	 */
	public List<Map<String, Object>> selectActivate(Connection conn, Paging paging);

	/**
	 * 회원 id 및 pw 를 이용해 로그인 인증
	 * 
	 * @param connection - DB 연결 객체
	 * @param loginUser  - 로그인 정보를 담고 있는 User 객체
	 * @return 1 - 인증 성공, 0 - 인증 실패
	 */
	public int selectUserByUserIdPw(Connection connection, User loginUser);

	/**
	 * userno를 이용하여 회원 정보 조회
	 * 
	 * @param conn   - DB 연결 객체
	 * @param userno - 입력된 user번호
	 * @return - user dto 객체
	 */
	public User selectUserInfobyUserno(Connection conn, int userno);

	/**
	 * userno을 이용해 프로필 사진 가져오기
	 * 
	 * @param conn
	 * @param userNo
	 * @return
	 */
	public UserProfile getUserProfilebyUserno(Connection conn, int userNo);

	/**
	 * 동호회 수 파악
	 * 
	 * @param connection
	 * @return
	 */
	public int selectCntAll(Connection connection);

	/**
	 * City 테이블 전체 정보 반환
	 * 
	 * @param connection
	 * @return
	 */
	public List<CityDto> selectAllCity(Connection connection);

	/**
	 * gu 테이블 전체 정보 반환
	 * 
	 * @param connection
	 * @return
	 */
	public List<GuDto> selectAllGu(Connection connection);

	/**
	 * d운동 테이블 전체 정보 반환
	 * 
	 * @param connection
	 * @return
	 */
	public List<ExerciseDto> selectAllExercise(Connection connection);

	public List<GuDto> selectGuByCityNumber(Connection connection, CityDto city);

	/**
	 * 추가하기
	 * 
	 * @param connection
	 * @param club
	 * @param req
	 */
	public int insertClubMemberList(Connection connection, ClubDto club, HttpServletRequest req);

	/**
	 * req 객체의 clubnumber 파라미터로 클럽 전체 정보 가져오기
	 * 
	 * @param req
	 * @return
	 */
	public ClubDto selectClub(Connection conn, HttpServletRequest req);

	/**
	 * club 객체의 clubnumber를 이용해 member 리스트와 그 멤버들 정보 가져오기
	 * 
	 * @param connection
	 * @param club
	 * @return
	 */
	public List<Map<String, Object>> selectMember(Connection connection, ClubDto club);

	/**
	 * 회원 멤버리스트에서 삭제
	 * 
	 * @param conn
	 * @param req
	 * @return
	 */
	public int deleteMember(Connection conn, HttpServletRequest req);

	/**
	 * 동호회 정보 업데이트
	 * 
	 * @param conn
	 * @param req
	 * @return
	 */
	public int updateClub(Connection conn, HttpServletRequest req);

	/**
	 * 회원 가입 및 확인
	 * 
	 * @param conn
	 * @param req
	 * @return
	 */
	public int clubJoin(Connection conn, HttpServletRequest req);

	/**
	 * approve
	 * 
	 * @param conn
	 * @param cml
	 * @return
	 */
	public int memberApprove(Connection conn, ClubMemberListDto cml);

}
