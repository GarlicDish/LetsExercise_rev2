package service.face;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.member.MemberDto;

public interface MemberInfoUpdateService {

	/**
	 * 회원정보를 업데이트한다. 
	 * @param 컨트롤러로부터 받은 응답객체
	 * @return 업데이트 성공시 1, 아닐시 0
	 * @throws IOException 
	 * @throws ServletException */
	public int cosFileupload(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

	

}
