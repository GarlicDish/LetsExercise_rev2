package service.face;

public interface AdminLoginService {

	/**
	 * 로그인 창에서 id와 비밀번호를 체크한다. 성공시 메인페이지로 리다이렉트한다. 실패시 재확인하라는 메시지를 띄운다.
	 * 
	 * @param userid 유저 아이디
	 * @param userpw 유저 패스워드
	 * @return 성공(1) 또는 실패(0)
	 */
	public int idpwCheck(String userid, String userpw);

}
