package service.impl;

import dao.face.JoinDao;
import dao.impl.JoinDaoImpl;
import dto.member.MemberDto;
import service.face.JoinIDCheckService;

public class JoinIDCheckServiceImpl implements JoinIDCheckService{

	@Override
	public int idCheck(MemberDto memberDto) {
		
		String userID = memberDto.getUserID();
		int idCheck = 0;
		
		JoinDao joinDao = new JoinDaoImpl();
		
		if (!joinDao.idCheck(userID) && joinDao.isokayID(userID))
			idCheck = 1;
		
		System.out.println((joinDao.idCheck(userID) && joinDao.isokayID(userID)));
		System.out.println("idCheck : " + idCheck);

		return idCheck;
		
	}

}
