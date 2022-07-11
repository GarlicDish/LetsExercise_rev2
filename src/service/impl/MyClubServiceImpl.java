package service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.MyClubDao;
import dao.impl.MyClubDaoImpl;
import dto.club.User;
import service.face.MyClubService;
import util.Paging;

public class MyClubServiceImpl implements MyClubService {

	MyClubDao myClubDao = new MyClubDaoImpl();

	@Override
	public List<Map<String, Object>> getMyList(User user, Paging paging) {
		Connection conn = JDBCTemplate.getConnection();

		return myClubDao.selectMyClub(conn, user, paging);

	}

	@Override
	public User info(User user) {
		Connection conn = JDBCTemplate.getConnection();

		return myClubDao.selectMemberByUserid(conn, user);
	}

	@Override
	public User getLoginUserInfoByUserno(User user) {

		Connection conn = JDBCTemplate.getConnection();

		return myClubDao.selectMemberByUserno(conn, user);
	}

	@Override
	public void deactivateClub(HttpServletRequest req) {

		Connection conn = JDBCTemplate.getConnection();

		if (myClubDao.deactivateClub(conn, req) > 0) {
			JDBCTemplate.commit(conn);
			System.out.println(req.getParameter("clubnumber") + "번 클럽 삭제 commit 완료");
		} else {
			JDBCTemplate.rollback(conn);
			System.out.println(req.getParameter("clubnumber") + "번 클럽 삭제 commit 실패");
		}

	}

	@Override
	public Paging getPaging(HttpServletRequest req) {

		String param = req.getParameter("curPage");

		int curPage = 0;

		if (param != null && param != "") {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNING] ClubSErvice getPaging() - curPage 값이 null 이거나, 비어있음");
		}

		// 총 게시글 수 조회하기
		int totalCount = myClubDao.selectCntAll(JDBCTemplate.getConnection(), req);

		// Paging 객체 생성 - 페이징 계산
		Paging paging = new Paging(totalCount, curPage);

		return paging;
	}

	@Override
	public void withdraw(HttpServletRequest req) {
		Connection conn = JDBCTemplate.getConnection();

		if (myClubDao.withdraw(conn, req) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}
}
