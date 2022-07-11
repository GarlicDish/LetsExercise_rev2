package service.face;

import dto.member.MemberDto;

public interface JoinPWUsableService {
	/**
	 * 패스워드가 사용 가능한지 여부를 판별한다. 
	 * 중복 여부와 유효성 검사를 시행한다. 
	 * 
	 * @param 패스워드 정보를 담은 MemberDto
	 * @return 사용 가능한 패스워드면 1, 사용 불가능하면 0 반환.
	 */
		
		public int pwCheck(MemberDto member);
}
