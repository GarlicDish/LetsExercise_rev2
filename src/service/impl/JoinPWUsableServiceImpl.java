package service.impl;

import dao.face.JoinDao;
import dao.impl.JoinDaoImpl;
import dto.member.MemberDto;
import service.face.JoinPWUsableService;

public class JoinPWUsableServiceImpl implements JoinPWUsableService {

	@Override
	public int pwCheck(MemberDto memberDto) {
		JoinDao joinDao = new JoinDaoImpl();
		
		int pwCheck = 0;
		String userPW = memberDto.getUserPW();
		
		if (joinDao.isokayPW(userPW) ) 
			pwCheck = 1;
		System.out.println(joinDao.isokayPW(userPW));
		System.out.println("idCheck : "+pwCheck);
	
		return pwCheck;
	
	}

}
