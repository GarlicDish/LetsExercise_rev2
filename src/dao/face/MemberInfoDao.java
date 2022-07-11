package dao.face;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import dto.member.MemberDto;
import dto.member.PhotoDto;

public interface MemberInfoDao {

	/**
	 * 해당 USER 정보 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return 조회된 USER 정보 목록
	 */
	public MemberDto selectUserInfo(Connection conn, String userid);
	
	/**
	 * 
	 * @param conn
	 * @param infono
	 * @return
	 */
	public MemberDto selectInfoByUserno(Connection conn, MemberDto usernumber);
	
	/**
	 * 개인 정보 수정
	 * 
	 * @param conn - DB연결 객체
	 * @param info - 변경될 개인 정보 DTO객체
	 * @return int - UPDATE 수행 결과
	 */
	public int infoupdate(Connection conn, MemberDto memberdto);
	
	/**
	 * 개인 정보 삭제 - 비활성화 
	 * 
	 * @param conn
	 * @param userid
	 * @return
	 */
	public int deactivatedupdate(Connection conn,MemberDto memberdto);
	

	/**
	 * 프로필사진 DTO삽입
	 * 
	 * @param conn
	 * @param photoDto
	 * @return
	 */
	public int fileInsert(Connection conn, PhotoDto photoDto);
	
	
	/**
	 * 프로필사진 업데이트
	 * 
	 * @param conn
	 * @param photoDto
	 * @return
	 */
	public int fileUpdate(Connection conn, PhotoDto photoDto);


	/**
	 * 프로필사진 업데이트 내역 확인
	 * 
	 * @param photoDto
	 * @return
	 */
	 public boolean isPhotoExist(PhotoDto photoDto);
	 
	 /**
		 * 회원정보 업데이트후 세션을 업데이트한다. (cosFileupload에 이어 사용)
		 * @param DTO객체
		 *  */	
	public void sessionUpdate(Connection conn, HttpServletRequest req, MemberDto memberDto, PhotoDto photoDto);

	 
}
