package dao.face;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dto.club.ClubDto;
import dto.club.ClubSNSAttachedPhoto;
import dto.club.ClubSNSDto;

public interface ClubSNSDao {

	/**
	 * club 번호로 club SNS 게시글 조회
	 * 
	 * @param conn - DB 연결 객체
	 * @param club - club number 정보가 담긴 club DTO 객체
	 * @return clubnumber가 일치하는 club SNS 게시글 전체
	 */
	List<ClubSNSDto> selectByClubNumber(Connection conn, ClubDto club);

	/**
	 * clubSNSno 반환
	 * 
	 * @param conn - DB 연결객체
	 * @return - clubSNSNumber_seq.nextval 반환
	 */
	int selectClubNo(Connection conn);

	/**
	 * clubSNS 게시글 정보 대입
	 * 
	 * @param conn
	 * @param clubSNS
	 * @return
	 */
	int insert(Connection conn, ClubSNSDto clubSNS);

	/**
	 * 파일 정보 입력
	 * 
	 * @param conn
	 * @param attachedPhoto
	 * @return
	 */
	int insertPhoto(Connection conn, ClubSNSAttachedPhoto attachedPhoto);

	/**
	 * ClubSNS로 부터 userno 추출하기
	 * 
	 * @param conn - DB 연결 객체
	 * @param i    - ClubSNS 객체
	 * @return usernumber 추출
	 */
	int getWriterNo(Connection conn, ClubSNSDto i);

	/**
	 * club 번호로 모든 게시글 및 관련 유저 정보 가져오기
	 * 
	 * @param club
	 * @return
	 */
	List<Map<String, Object>> getUserinfoAndProfileBySNSnumber(Connection conn, ClubDto club);

	/**
	 * DB에서 게시글 삭제
	 *
	 * @param conn - DB연결 객체
	 * @param req  - 게시글 정보를 담고 있는 req 객체
	 */
	void delete(Connection conn, HttpServletRequest req);

	/**
	 * sns번호를 통해 정보 담기
	 * 
	 * @param conn
	 * @param snSno
	 * @return
	 */
	ClubSNSDto selectSNSbySNSno(Connection conn, ClubSNSDto snSno);

	int checkMember(Connection conn, HttpServletRequest req);

	/**
	 * 수정된 게시글 업데이트
	 * 
	 * @param conn
	 * @param clubSNS
	 * @return
	 */
	int update(Connection conn, ClubSNSDto clubSNS);

	/**
	 * clubSNS 번호로 club 번호 가져오기
	 * 
	 * @param connection
	 * @param clubSNS
	 * @return
	 */
	ClubDto getClubNo(Connection connection, ClubSNSDto clubSNS);

	/**
	 * 다 가져오기
	 * 
	 * @param connection
	 * @param clubSNS
	 * @return
	 */
	Map<String, Object> getUserUserProfileClubSNSbyClubSNSno(Connection connection, ClubSNSDto clubSNS);

}
