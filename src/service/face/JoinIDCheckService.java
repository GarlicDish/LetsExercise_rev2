package service.face;

import dto.member.MemberDto;

public interface JoinIDCheckService {
/**
 * 아이디가 사용 가능한지 여부를 판별한다. 
 * 중복 여부와 유효성 검사를 시행한다. 
 * 
 * @param 아이디 정보를 담은 MemberDto
 * @return 사용 가능한 아이디이면 1, 사용 불가능하면 0 반환.
 */
	
	public int idCheck(MemberDto member);
}
