package service.face;

import javax.servlet.http.HttpServletRequest;

import dto.board.Member;

public interface MemberService {

	/**
	 * 로그인 정보 추출하기
	 * 
	 * @param req - 요청 정보 객체
	 * @return Member - 로그인 정보
	 */
	public Member getLoginMember(HttpServletRequest req);

	/**
	 * 로그인 인증 처리
	 * 
	 * @param member - 로그인 정보
	 * @return boolean - true:인증 성공함, false:인증 실패함
	 */
	public boolean login(Member member);

	/**
	 * 유저 정보 가져오기
	 * 
	 * @param member - 조회할 회원 아이디를 가진 객체 
	 * @return Member - 조회된 회원 정보
	 */
	public Member info(Member member);

	/**
	 * 회원가입 정보 추출하기
	 * 
	 * @param req - 요청 정보 객체
	 * @return Member - 전달파라미터를 추출하여 저장한 DTO객체
	 */
	public Member getJoinMember(HttpServletRequest req);

	/**
	 * 회원가입 처리
	 * 
	 * @param member - 회원가입 정보 객체
	 */
	public void join(Member member);

}

















