package service.impl;

import dto.member.MemberDto;
import service.face.JoinCellsCheckService;

public class JoinCellsCheckServiceImpl implements JoinCellsCheckService {

	@Override
	public int isCellsChekced(MemberDto member) {
		
		int res = 0;
		
		int gender = member.getGender();
		String exercise = member.getExercise();
		
		if( gender == 0  ) {
			res = 1;
		} else if (exercise.equals(null) || Integer.parseInt(exercise)==0)  {
			res = 2;
		} else {
			res = 0;
		}
		return res;
	}

}
