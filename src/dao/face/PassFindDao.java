package dao.face;

import java.sql.Connection;

public interface PassFindDao {

	//유저아이디가 있는 DTO를 불러온다
			//memberDto loadUserInfo 활용 : 서비스 서블릿에 존재

	
	
	/**
	 * 임시비밀번호를 업데이트하는 메서드이다.
	 * @param DB연결객체
	 * @param 본인확인된 유저아이디
	 * @param 임시발급된 비밀번호
	 * @return DB업데이트 반영여부 (0 실패 1 성공)
	 *  */
	public int updateTmpPass(Connection conn, String userid, String newPass);

	
}
