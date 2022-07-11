package service.impl;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.AdminQnaReplyDao;
import dao.impl.AdminQnaReplyDaoImpl;
import dto.board.QnaReply;
import service.face.AdminQnaReplyService;

public class AdminQnaReplyServiceImpl implements AdminQnaReplyService {

	private AdminQnaReplyDao adminQnaReplyDao = new AdminQnaReplyDaoImpl();

	@Override
	public QnaReply getQnano(HttpServletRequest req) {

		QnaReply qr = new QnaReply();

		String param = req.getParameter("clubQnanumber");
		if (param != null && "".equals(param)) {
			qr.setQnaboardno(Integer.parseInt(param)); // dto에 내용 추가하기
			return qr;
		} else {
			return null;
		}

	}

	@Override
	public void delete(QnaReply qr) {

		Connection conn = JDBCTemplate.getConnection();

		System.out.println("/////QnaReplyimpl - delete  qr: " + qr);
		int res = adminQnaReplyDao.delete(conn, qr);
		System.out.println(res);
		if (res > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}

	@Override
	public void write(QnaReply qr) {

		Connection conn = JDBCTemplate.getConnection();

		if (adminQnaReplyDao.write(conn, qr) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}

	@Override
	public QnaReply getQnaReplyNumber(QnaReply qr) {

		Connection conn = JDBCTemplate.getConnection();

		return adminQnaReplyDao.getQnaReplyNumber(conn, qr);
	}

	@Override
	public QnaReply getQrdata(QnaReply qr) {

		Connection conn = JDBCTemplate.getConnection();

		return adminQnaReplyDao.getQRdata(conn, qr);
	}

	@Override
	public void updateReply(HttpServletRequest req) {

		Connection conn = JDBCTemplate.getConnection();

		if (adminQnaReplyDao.update(conn, req) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

	}

}
