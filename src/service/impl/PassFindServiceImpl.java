package service.impl;

import java.sql.Connection;
import java.util.UUID;

import common.JDBCTemplate;
import dao.face.JoinDao;
import dao.face.LoginDao;
import dao.face.PassFindDao;
import dao.impl.JoinDaoImpl;
import dao.impl.LoginDaoImpl;
import dao.impl.PassFindDaoImpl;
import dto.member.MemberDto;
import service.face.LoginService;
import service.face.PassFindService;

public class PassFindServiceImpl implements PassFindService{
	
	LoginDao loginDao = new LoginDaoImpl();
	LoginService loginService = new LoginServiceImpl();
	Connection conn = JDBCTemplate.getConnection();
	
	@Override
	public boolean isExsistUser(String userid, String email) {
		//유저아이디가 있는 DTO를 불러온다
		//memberDto loadUserInfo 활용
		MemberDto memberDto = loginService.loadUserInfo(userid);
		System.out.println(memberDto.toString());
		//해당 DTO의 이메일이 일치하는지 확인한다 
		boolean isCorrUser = memberDto.getEmail().equals(email);
		
		System.out.println("isExsistUser()"+isCorrUser);
		return isCorrUser;
	}
	
	@Override
	public String sendPass(String userid, String email) {
		
		System.out.println("sendPass()");
		PassFindDao passFindDao = new PassFindDaoImpl();
			
		//새로 비밀번호를 생성하는 함수 결과값을 받아서 테이블에 업데이트
			JoinDao joinDao = new JoinDaoImpl();
			String npass = createNewPass();
			
			boolean isokayPW = joinDao.isokayPW(npass);
			System.out.println("npass"+npass);
			
			//비밀번호 유효성 조건에 맞을때까지 비밀번호를 생성함 
			while(!	isokayPW) {
				npass = createNewPass();
				System.out.println("trypw"+npass);
			}
			System.out.println("newPwd"+npass);

			// 비밀번호 업데이트
			passFindDao.updateTmpPass(conn, userid, npass);
			
		//업데이트한 비밀번호를 불러옴

			MemberDto memberDto = loginService.loadUserInfo(userid);
			String newPwd = memberDto.getUserPW();
			System.out.println("newPwd"+newPwd);
			
			return newPwd;
			
	}

	
	// 비밀번호 생성 함수
	@Override
	public String createNewPass() {
		String nPass = UUID.randomUUID().toString().substring(0,8);
		return nPass;
	}

	// 이메일 전송 메서드

	
	
}
