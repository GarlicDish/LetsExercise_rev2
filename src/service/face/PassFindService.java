package service.face;

public interface PassFindService {

	
	/** 기존유저가 올바른 정보를 입력했는지 확인한다.
	 * @param userid, email 입력받은 아이디, 이메일
	 * @return 정보 일치하면 1, 아니면 0
	 * */
	public boolean isExsistUser(String userid, String email);

	/**
	 * 새 비밀번호를 보낸다.
	 * @param userid, email 입력받은 아이디, 이메일
	 * @return 비밀번호
	 * */
	public String sendPass(String userid, String email);
	
	/**
	 * 새로운 비밀번호를 생성한다. 유효성 검사조건을 만족해야 한다.
	 * @return 비밀번호
	 * */
	public String createNewPass();
}
