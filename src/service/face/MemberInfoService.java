package service.face;

import javax.servlet.http.HttpServletRequest;

import dto.member.MemberDto;



public interface MemberInfoService {
	
	/**
	 * 개인 정보 조회
	 * 
	 * @return 조회된 개인 정보 불러오기
	 */
	public MemberDto getselectUserInfo(String userid);
	
	/**
	 * 
	 * @param req
	 * @return
	 */
	public MemberDto getUserno(HttpServletRequest req);
	
	/**
	 * 
	 * @param userno
	 * @return
	 */
	public MemberDto view(MemberDto usernumber);
	
	/**
	 * 개인 정보 수정
	 * 
	 * @param req - 요청 정보 객체
	 */
	public void getupdate(MemberDto memberdto);

	/**
	 * 개인 정보 삭제 - 비활성화
	 * 
	 * @param userid
	 * @return
	 */
	public void getdeactivatedelete(MemberDto memberdto);
	
	
	
	
//	/**
//	 * 전달된 요청객체를 이용하여
//	 * 전달파라미터를 추출한다
//	 * 
//	 * @param req - 요청 정보 객체
//	 * @return 전달된 데이터로 개인정보를 변경하여 반환한다
//	 */
//	public Info getParam( HttpServletRequest req );

	/**
	 * 개인 정보 수정
	 * 
	 * @param info 수정될 개인 정보 객체
	 * @return 개인정보 수정 결과
	 */
//	public int getupdate(Info info);
	
}
