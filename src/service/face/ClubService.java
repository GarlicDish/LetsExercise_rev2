package service.face;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dto.club.CityDto;
import dto.club.ClubDto;
import dto.club.ExerciseDto;
import dto.club.GuDto;
import dto.club.User;
import util.Paging;

public interface ClubService {

	/**
	 * 전달된 파라미터를 기반으로 club 정보 추출 및 DTO 객체에 담기
	 * 
	 * @param req 요청 정보 객체
	 * @return 전달된 데이터가 담긴 Club DTO 객체
	 */
	public ClubDto getClubData(HttpServletRequest req);

	/**
	 * 전달된 데이터를 이용, 클럽 생성
	 * 
	 * @param club 클라이언트로 부터 전달된 club 정보 객체
	 * @return DB에 삽입된 (clubno포함) 클럽 정보
	 */
	public ClubDto createClub(ClubDto club);

	/**
	 * 동호회 목록 조회
	 * 
	 * @param paging
	 * 
	 * @return 활성화된 동호회 목록 전체
	 */
	public List<Map<String, Object>> getList(Paging paging);

	/**
	 * 전달 파라미터를 담은 User 객체 만들기
	 * 
	 * @param req - 요청 정보 객체
	 * @return id와 pw를 담은 User 객체
	 */
	public User getLoginUser(HttpServletRequest req);

	/**
	 * 로그인 한 유저가 dB에 있는지 여부 검사
	 * 
	 * @param loginUser - loginuser 객체
	 * @return true - 로그인 인증 성공, false - 로그인 인증 실패
	 */
	public boolean login(User loginUser);

	/**
	 * user 번호를 받아, user 모든 정보를 담은 객체 반환
	 * 
	 * @param userno - user 번호
	 * @return - user 객체
	 */
	public User getUserinfo(int userno);

	/**
	 * 동호회 페이지에 대한 paging 정보 가져오기
	 * 
	 * @param req
	 * @return 페이징 정보 객체 반환
	 */
	public Paging getPaging(HttpServletRequest req);

	/**
	 * 등록된 시 정보 반환
	 * 
	 * @return
	 */
	public List<CityDto> getCityInfo();

	/**
	 * 등록된 구 정보 반환
	 * 
	 * @return
	 */
	public List<GuDto> getGuInfo();

	/**
	 * 등록되어 있는 운동 정보 전체 반환
	 * 
	 * @return
	 */
	public List<ExerciseDto> getExerciseInfo();

	/**
	 * 입력받은 City 번호 전달 파라미터
	 * 
	 * @param req
	 * @return
	 */
	public CityDto getParamCity(HttpServletRequest req);

	/**
	 * 
	 * @param city
	 * @return
	 */
	public List<GuDto> getGuByCityNumber(CityDto city);

	/**
	 * 클럽 생성 후, 멤버리스트에 동호회장 넣기
	 * 
	 * @param club
	 * 
	 * @param req
	 */
	public void addMember(ClubDto club, HttpServletRequest req);

	/**
	 * Clubnumber 로 모든 클럽 정보 가져오기
	 * 
	 * @param req
	 */
	public ClubDto getClubInfoByClubnumber(HttpServletRequest req);

	/**
	 * club 정보로 회원 정보 불러오기
	 * 
	 * @param club
	 * @return
	 */
	public List<Map<String, Object>> getClubMemberData(ClubDto club);

	/**
	 * 회원 강제 탈퇴
	 * 
	 * @param req
	 */
	public void deleteMember(HttpServletRequest req);

	/**
	 * 동호회 정보 업데이트
	 * 
	 * @param req
	 */
	public void updateClub(HttpServletRequest req);

	/**
	 * 동호회 가입하기
	 * 
	 * @param req
	 */
	public void clubJoin(HttpServletRequest req);

	/**
	 * 전달 받은 회원 정보로 approve 상태를 1 로 변환
	 * 
	 * @param req
	 */
	public void approveMember(HttpServletRequest req);

}
