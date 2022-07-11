package dao.face;

import java.sql.Connection;


import dto.MemberDto;

public interface InfoDao {

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
	
	

	
	 /*
		 *
		 * 
		 * //게시물 찾기 public Info getUserId(Connection conn, String userid);
		 */

}
