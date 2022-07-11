package service.impl;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.FindReplyDao;
import dao.impl.FindReplyDaoImpl;
import dto.board.FindReply;
import service.face.FindReplyService;

public class FindReplyServiceImpl implements FindReplyService {

	private FindReplyDao findReplyDao = new FindReplyDaoImpl();

	@Override
	public FindReply getFindno(HttpServletRequest req) {

		FindReply fr = new FindReply();

		String param = req.getParameter("clubFindnumber");
		if (param != null && "".equals(param)) {
			fr.setFindboardno(Integer.parseInt(param)); // dto에 내용 추가하기
			return fr;
		} else {
			return null;
		}

	}

	@Override
	public void delete(FindReply fr) {

		Connection conn = JDBCTemplate.getConnection();

		System.out.println("/////FindReplyimpl - delete  fr: " + fr);
		int res = findReplyDao.delete(conn, fr);
		System.out.println(res);
		if (res > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}

	@Override
	public void write(FindReply fr) {

		Connection conn = JDBCTemplate.getConnection();

		if (findReplyDao.write(conn, fr) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}

	@Override
	public FindReply getFindReplyNumber(FindReply fr) {

		Connection conn = JDBCTemplate.getConnection();

		return findReplyDao.getFindReplyNumber(conn, fr);
	}

	@Override
	public FindReply getFrdata(FindReply fr) {

		Connection conn = JDBCTemplate.getConnection();

		return findReplyDao.getFRdata(conn, fr);
	}

	@Override
	public void updateReply(HttpServletRequest req) {

		Connection conn = JDBCTemplate.getConnection();

		if (findReplyDao.update(conn, req) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

	}

}
