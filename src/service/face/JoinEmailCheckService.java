package service.face;

import dto.member.MemberDto;

public interface JoinEmailCheckService {

	/** 사용 가능한 이메일인지 판별한다. 
	 * 이메일이 중복되었는지, 유효성 검사에 적합한지 확인한다. 
	 * 
	 * @param 이메일 값을 담은 MemberDto
	 * @return 사용 가능하면 1, 사용 불가면 0
	 * */
	public int emailCheckService(MemberDto memberDto);
	
}
