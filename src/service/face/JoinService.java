package service.face;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.member.MemberDto;
import dto.member.PhotoDto;

public interface JoinService {
	
	
	
	/**
	 * 입력 정보를 통해 생성된 DTO를 DB에 저장한다. 
	 * (회원가입 처리)
	 * @param DTO객체
	 * @result 저장 결과 (1: 성공, 0: 실패)*/
	public int join(MemberDto member);
	

	
	
	/**
	 * COS라이브러리를 이용하여 파일 업로드를 처리한다
	 * 전달파라미터(폼필드)도 함께 처리할 수 있다
	 * 
	 * @param req 요청 정보 객체
	 * @param resp 응답 정보 객체
	 * @return 0 가입실패 1 가입성공
	 */
	public int cosFileupload(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException;
	

	
	
	/**
	 * 업로드된 파일의 정보를 전체 조회한다
	 * 
	 * @return 조회된 전체 파일 목록
	 */
	public List<PhotoDto> list();
	
	

}
	
	
	
	
	
	

