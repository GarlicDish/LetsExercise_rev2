package service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.AskCommentWriteDao;
import dao.impl.AskCommentWriteDaoImpl;
import dto.AskComment;
import service.face.AskCommentWriteService;

public class AskCommentWriteServiceImpl implements AskCommentWriteService {

	private AskCommentWriteDao askCommentWriteDao = new AskCommentWriteDaoImpl();

	@Override
	public AskComment getSNSno(HttpServletRequest req) {

		AskComment ac = new AskComment();

		String param = req.getParameter("boardnumber");
		if (param != null && "".equals(param)) {
			ac.setBoardNumber(Integer.parseInt(param));
			return ac;
		} else {
			return null;
		}

	}

	@Override
	public List<Map<String, Object>> selectComment(HttpServletRequest req) {

		Connection conn = JDBCTemplate.getConnection();

		return askCommentWriteDao.getMapList(conn, req);
	}

	@Override
	public void delete(AskComment ac) {

		Connection conn = JDBCTemplate.getConnection();

		System.out.println("/////askcommentimpl - delete  ac: " + ac);
		int res = askCommentWriteDao.delete(conn, ac);
		System.out.println(res);
		if (res > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}

	@Override
	public void write(AskComment ac) {

		Connection conn = JDBCTemplate.getConnection();

		if (askCommentWriteDao.write(conn, ac) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}

	@Override
	public AskComment getCommentNumber(AskComment ac) {

		Connection conn = JDBCTemplate.getConnection();

		return askCommentWriteDao.getAskCommentNumber(conn, ac);
	}

	@Override
	public AskComment getACdata(AskComment ac) {

		Connection conn = JDBCTemplate.getConnection();

		return askCommentWriteDao.getACdata(conn, ac);
	}

	@Override
	public void updateComment(HttpServletRequest req) {

		Connection conn = JDBCTemplate.getConnection();

		if (askCommentWriteDao.update(conn, req) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

	}

}
