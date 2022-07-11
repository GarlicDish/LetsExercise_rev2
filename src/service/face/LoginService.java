package service.face;

import dto.member.MemberDto;
import dto.member.PhotoDto;

public interface LoginService {

	/**
	 * 로그인 창에서 id와 비밀번호를 체크한다. 성공시 메인페이지로 리다이렉트한다. 실패시 재확인하라는 메시지를 띄운다.
	 * 
	 * @param userid 유저 아이디
	 * @param userpw 유저 패스워드
	 * @return 성공(1) 또는 실패(0)
	 */
	public int idpwCheck(String userid, String userpw);

	/**
	 * 유저 정보를 불러온다. DB에 저장된 row 1행 전체를 불러온다
	 * 
	 * @param userid 유저아이디
	 * @return 유저 DTO
	 */
	public MemberDto loadUserInfo(String userid);

	/**
	 * 유저의 사진 정보 가져오기
	 * 
	 * @param memberDto
	 * @return
	 */
	public PhotoDto loadUserPhoto(MemberDto memberDto);

}
