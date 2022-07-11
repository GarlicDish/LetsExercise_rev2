package service.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.MemberDao;
import dao.impl.MemberDaoImpl;
import dto.board.Member;
import service.face.MemberService;

public class MemberServiceImpl implements MemberService {

	private MemberDao memberDao = new MemberDaoImpl();
	
	@Override
	public Member getLoginMember(HttpServletRequest req) {
		
		Member member = new Member();
		
		member.setUserid( req.getParameter("userid") );
		member.setUserpw( req.getParameter("userpw") );
		
		return member;
	}
	
	@Override
	public boolean login(Member member) {
		
		//로그인 인증 성공
		if( memberDao.selectCntMemberByUseridUserpw(JDBCTemplate.getConnection(), member) > 0 ) {
			return true;
		}
		
		//로그인 인증 실패
		return false;
	}

	@Override
	public Member info(Member member) {
		
		return memberDao.selectMemberByUserid(JDBCTemplate.getConnection(), member);
		
	}
	
	@Override
	public Member getJoinMember(HttpServletRequest req) {
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		Member member = new Member();
		
		member.setUserid( req.getParameter("userid") );
		member.setUserpw( req.getParameter("userpw") );
		member.setUsernick( req.getParameter("usernick") );
		
		return member;
	}
	
	@Override
	public void join(Member member) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		if( memberDao.insert(conn, member) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}
	
}


















