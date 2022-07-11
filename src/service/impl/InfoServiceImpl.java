package service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.InfoDao;
import dao.impl.InfoDaoImpl;
import dto.MemberDto;
import service.face.InfoService;



public class InfoServiceImpl implements InfoService {


	private InfoDao infoDao = new InfoDaoImpl();
	
	@Override
	public MemberDto getselectUserInfo(String userid) {
		Connection conn = JDBCTemplate.getConnection();
		
		System.out.println("view 실행service");
		return infoDao.selectUserInfo( conn, userid );
	}
	
	@Override
	public MemberDto getUserno(HttpServletRequest req) {
		
		//전달파라미터 boardno를 저장할 DTO객체 생성
		MemberDto usernumber = new MemberDto(); //userid
		
		/* userno.getUserid(); */
		String param = req.getParameter("usernumber");
		if( param != null && !"".equals( param ) ) {
			usernumber.setUserNumber( Integer.parseInt(param) );
		} else {
			System.out.println("[WARN] BoardService getBoardno() - boardno값이 null이거나 비어있음");
		}
		
		return usernumber;
	}
	
	@Override
	public MemberDto view(MemberDto usernumber) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		//게시글 조회
		MemberDto memberdto = infoDao.selectInfoByUserno(conn, usernumber);
		
		return memberdto;
	}
	
	public void getupdate(MemberDto memberdto) { //session이 없어서 기능이 작동이안됨..
	
			Connection conn = JDBCTemplate.getConnection();
			System.out.println("업데이트서비스:" + memberdto.getUserNumber());
			memberdto.setUserNumber(10);
			
			
			if( infoDao.infoupdate(conn, memberdto) > 0 ) {
				JDBCTemplate.commit(conn);
				System.out.println("updatec service commit");
			} else {
				JDBCTemplate.rollback(conn);
				System.out.println("updatec service rollback");
			}
			System.out.println("updatec [POST] 실행service");
	}
	
	public void getdeactivatedelete(MemberDto memberdto) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		
		//int result = infoDao.deactivatedupdate(conn, info);
		System.out.println("서비스:" + memberdto.getUserNumber()); //session정보가 없어서 작동안함
		
		if( infoDao.deactivatedupdate(conn, memberdto) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	System.out.println("실행10");
						
		}

	
	
	
}	
//	@Override
//	public Info getParam(HttpServletRequest req) {
//		
//		Info param = new Info();
//		
//		param.setUserid(req.getParameter("userid"));
//		param.setEmail(req.getParameter("email"));
//		param.setNickname(req.getParameter("nickname"));
//		param.setPassword(req.getParameter("password"));
//		param.setPreference(req.getParameter("preference"));
//		param.setExercise(req.getParameter("exercise"));
//		
//		
//		return param;
//	}


//	@Override
//	public int getupdate(Info info) {
//		
//		
//		Connection conn = JDBCTemplate.getConnection();
//		
//		int result = infoDao.Infoupdate(conn, info);
//		
//		
//		if( result > 0 ) {              
//			JDBCTemplate.commit(conn);
//			return result;
//			
//		} else {                        
//			JDBCTemplate.rollback(conn);
//			return -1;
//			
//		}
//	}


	



