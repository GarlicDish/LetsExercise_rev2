package service.impl;

import java.sql.Connection;

import common.JDBCTemplate;
import dao.face.LoginDao;
import dao.impl.LoginDaoImpl;
import dto.member.MemberDto;
import dto.member.PhotoDto;
import service.face.LoginService;

public class LoginServiceImpl implements LoginService {

	LoginDao loginDao = new LoginDaoImpl();

	@Override
	public int idpwCheck(String userid, String userpw) {
		Connection conn = JDBCTemplate.getConnection();

		System.out.println("idpwCheck()");

		System.out.println("id, pw 데이터베이스와 대조");
//		return loginDao.idpwMatch(conn, userid, userpw);
		return loginDao.idpwMatch(conn, userid, userpw);

	}

	@Override
	public MemberDto loadUserInfo(String userid) {
		Connection conn = JDBCTemplate.getConnection();

		MemberDto member = loginDao.loadUserInfo(conn, userid);
//		MemberDto member =loginDao.loadUserInfo(conn, "dmswk108");
		System.out.println("[LoginServiceImpl] loadUserInfo() - memberDto 정보 : " + member.toString());

		return member;
	}

	@Override
	public PhotoDto loadUserPhoto(MemberDto memberDto) {
		Connection conn = JDBCTemplate.getConnection();

		return loginDao.loadPhotoInfo(conn, memberDto);
	}

}
