package service.face;

public interface ChatIDService {
	
	/**
	 * 아이디값 비교 체크
	 * 
	 * @param userID
	 * @return 존재하는 회원 0, 없는회원 1 , 데이터베이스 오류발생시 -1
	 */
	public String RegisterCheckService(String userID);
}
