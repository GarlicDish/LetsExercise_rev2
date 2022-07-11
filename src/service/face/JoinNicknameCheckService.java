package service.face;

import dto.member.MemberDto;

public interface JoinNicknameCheckService {

	/**
	 * 닉네임이 사용가능한지 확인한다.
	 * 닉네임이 중복되지 않는지, 유효성검사를 통과하는지 확인한다.
	 * 
	 * @param 닉네임 정보를 담은 MemberDto
	 * @return 사용 가능한 닉네임이면 1, 사용 불가능하면 0 반환.*/
	
	
	public int nicknameCheck(MemberDto member);	
}
