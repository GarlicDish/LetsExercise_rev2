package service.impl;

import dao.face.JoinDao;
import dao.impl.JoinDaoImpl;
import dto.member.MemberDto;
import service.face.JoinNicknameCheckService;

public class JoinNicknameCheckServiceImpl implements JoinNicknameCheckService{

	@Override
	public int nicknameCheck(MemberDto memberDto) {
		
		String nickname = memberDto.getNickname();
		int nickCheck = 0;
		
		
		JoinDao joinDao = new JoinDaoImpl();

		if (!joinDao.nicknameCheck(nickname) && joinDao.isokaynick(nickname))
			nickCheck = 1;

		System.out.println(!joinDao.nicknameCheck(nickname) && joinDao.isokaynick(nickname));
		System.out.println("nicknameCheck : " + nickname);

		return nickCheck;
	}

}
