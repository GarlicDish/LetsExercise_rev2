package service.face;

import dto.member.MemberDto;

public interface JoinCellsCheckService {

	/**입력 정보를 통해 셀들이 체크되었는지 확인한다. 
	 * @param 성별, 운동 입력정보
	 * @return 성별값이 없다면 1 
	 * 			운동값이 없다면 2, 
	 * 			모두 입력되어 있다면 0
	 * */
	
	public int isCellsChekced(MemberDto member);
	
}
