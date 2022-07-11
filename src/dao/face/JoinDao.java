package dao.face;

import java.sql.Connection;

import dto.member.MemberDto;
import dto.member.MyExerciseDto;

public interface JoinDao {
//
//	/**회원정보 테이블의 next_seq 반환
//	 * @param DB 연결 객체
//	 * @return 시퀀스의 nextval
//	 * */
//	public int getSqnForUserNo(Connection conn);
//	
//	
//	/**프로필사진 테이블의 next_seq 반환
//	 * @param DB 연결 객체
//	 * @return 시퀀스의 nextval
//	 * */
//	public int getSqnForPhotoNo(Connection conn);
//	
//	
	/**
	 * DTO객체를 전달받아 DB에 저장하는 함수
	 * 
	 * @param DB연결객체s
	 * @param 사용자로부터  입력받은 값이 들어있는 DTO객체 (기본정보)
	 * @return 성공(1), 실패(0)
	 */
	public boolean insert(Connection conn, MemberDto member);

	/**
	 * DTO객체를 전달받아 DB에 저장하는 함수
	 * 
	 * @param DB연결객체
	 * @param 사용자로부터 입력받은 운동경력값이 들어있는 DTO객체
	 * @return 성공(1), 실패(0)
	 */
	public boolean Exerciseinsert(Connection conn, MemberDto member, MyExerciseDto myExerciseDto);

	/**
	 * 가입화면에서 아이디를 체크한다. 기존 DB와의 일치여부,
	 * 
	 * @param member        유저 아이디 체크용 DTO
	 * @param myExerciseDto 입력받은 유저정보
	 * @return 성공(1) 또는 실패(0)
	 */
	public boolean idCheck(String userid);

	/**
	 * 가입화면에서 닉네임을 체크한다. 기존 DB와의 일치여부,
	 * 
	 * @param nickname 유저 닉네임
	 * @return 성공(1) 또는 실패(0)
	 */
	public boolean nicknameCheck(String nickname);

	// 유효성 검사

	/**
	 * 가입화면에서 패스워드를 체크한다. pw1 pw2 열치여부를 확인한다
	 * 
	 * @param 패스워드, 확인 패스워드
	 * @return 성공(1) 또는 실패(0)
	 */
	public boolean pwCheck(String userPW, String userPW2);

	/**
	 * 입력받은 닉네임 유효성 검사 숫자,영문자,한글 4~8자
	 * 
	 * @param 입력받은 닉네임
	 * @return 적합(true) 또는 부적합(false)
	 */
	public boolean isokaynick(String nickname);

	/**
	 * 입력받은 아이디 유효성 검사 숫자, 영문자, _, - 를 포함하는 4~12자
	 * 
	 * @param 아이디
	 * @return 적합(true) 또는 부적합(false)
	 */
	public boolean isokayID(String userID);

	/**
	 * 입력받은 패스워드 유효성 검사 숫자, 영문자, 특수문자 각 1개 이상을 포함하는 6~20자
	 * 
	 * @param 패스워드
	 * @return 적합(true) 또는 부적합(false)
	 */
	public boolean isokayPW(String userPW);

	/**
	 * 입력받은 이메일 유효성 검사
	 * 
	 * @param 이메일
	 * @return 적합(true) 또는 부적합(false)
	 */

	public boolean isokayEmail(String email);

	/**
	 * 회원ㄱ ㅏ입할 회원의 번호를 조회하여 반환
	 * 
	 * @param conn
	 * 
	 * @return
	 */
	public int getNextUserNo(Connection conn);

}
