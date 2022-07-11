package service.impl;

import java.sql.Connection;

import common.JDBCTemplate;
import dao.face.AdminLoginDao;
import dao.impl.AdminLoginDaoImpl;
import service.face.AdminLoginService;

public class AdminLoginServiceImpl implements AdminLoginService {

	AdminLoginDao adminLoginDao = new AdminLoginDaoImpl();

	@Override
	public int idpwCheck(String adminid, String adminpw) {
		Connection conn = JDBCTemplate.getConnection();

		System.out.println("idpwCheck()");

		System.out.println("id, pw 데이터베이스와 대조");
//		return loginDao.idpwMatch(conn, userid, userpw);
		return adminLoginDao.idpwMatch(conn, adminid, adminpw);

	}

}
