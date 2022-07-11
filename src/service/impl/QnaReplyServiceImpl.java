package service.impl;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.QnaReplyDao;
import dao.impl.QnaReplyDaoImpl;
import dto.board.QnaReply;
import service.face.QnaReplyService;

public class QnaReplyServiceImpl implements QnaReplyService {

	private QnaReplyDao qnaReplyDao = new QnaReplyDaoImpl();

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
		int res = qnaReplyDao.delete(conn, qr);
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

		if (qnaReplyDao.write(conn, qr) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}

	@Override
	public QnaReply getQnaReplyNumber(QnaReply qr) {

		Connection conn = JDBCTemplate.getConnection();

		return qnaReplyDao.getQnaReplyNumber(conn, qr);
	}

	@Override
	public QnaReply getQrdata(QnaReply qr) {

		Connection conn = JDBCTemplate.getConnection();

		return qnaReplyDao.getQRdata(conn, qr);
	}

	@Override
	public void updateReply(HttpServletRequest req) {

		Connection conn = JDBCTemplate.getConnection();

		if (qnaReplyDao.update(conn, req) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

	}

}
