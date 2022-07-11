package service.impl;

import dao.face.JoinDao;
import dao.impl.JoinDaoImpl;
import dto.member.MemberDto;
import service.face.JoinEmailCheckService;

public class JoinEmailCheckServiceImpl implements JoinEmailCheckService{

	@Override
	public int emailCheckService(MemberDto memberDto) {
	
		JoinDao joinDao = new JoinDaoImpl();
		
		int emailCheck = 0;
		String email = memberDto.getEmail();
		
		
		if (joinDao.isokayEmail(email))
			emailCheck = 1;

		System.out.println(joinDao.isokayEmail(email));
		System.out.println("emailCheck : " + email);

		return emailCheck;
	}

}
