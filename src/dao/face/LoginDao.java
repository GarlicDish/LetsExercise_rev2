package dao.face;

import java.sql.Connection;

import dto.member.MemberDto;
import dto.member.PhotoDto;

public interface LoginDao {

	/**
	 * 요청받은 아이디와 비밀번호에 해당하는 DB를 불러와서 빈 DTO객체에 저장한다.
	 *
	 * @param conn   DB연결객체
	 * @param userid 유저 아이디
	 * @return 정보가 담긴 DTO객체
	 */
	public MemberDto loadUserInfo(Connection conn, String userid);

	/**
	 * @param conn   DB연결객체
	 * @param member 로그인한 유저 정보 DTO객체
	 * @return DB와 대조한 결과: 존재(1) 존재하지 않음(0)
	 */
	public int idpwMatch(Connection conn, String userid, String userpw);

	/**
	 * 로그인한 유저의 포토 정보 불러오기
	 * 
	 * @param conn
	 * @param memberDto
	 * @return
	 */
	public PhotoDto loadPhotoInfo(Connection conn, MemberDto memberDto);

}
