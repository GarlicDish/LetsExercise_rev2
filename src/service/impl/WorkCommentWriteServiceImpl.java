package service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.WorkCommentWriteDao;
import dao.impl.WorkCommentWriteDaoImpl;
import dto.commentDto;
import service.face.WorkCommentWriteService;

public class WorkCommentWriteServiceImpl implements WorkCommentWriteService {

	private WorkCommentWriteDao workCommentWriteDao = new WorkCommentWriteDaoImpl();

	@Override
	public commentDto getBoardno(HttpServletRequest req) {

		commentDto wc = new commentDto();

		String param = req.getParameter("boardnumber");
		if (param != null && "".equals(param)) {
			wc.setBoardnumber(Integer.parseInt(param));
			return wc;
		} else {
			return null;
		}

	}

	@Override
	public List<Map<String, Object>> selectComment(HttpServletRequest req) {
		Connection conn = JDBCTemplate.getConnection();

		return workCommentWriteDao.getMapList(conn, req);
	}

	@Override
	public void delete(commentDto wc) {

		Connection conn = JDBCTemplate.getConnection();

		System.out.println("/////workimpl - delete  wc: " + wc);
		int res = workCommentWriteDao.delete(conn, wc);
		System.out.println(res);
		if (res > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}

	@Override
	public void write(commentDto wc) {

		Connection conn = JDBCTemplate.getConnection();

		if (workCommentWriteDao.write(conn, wc) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}

	@Override
	public commentDto getCommentNumber(commentDto wc) {

		Connection conn = JDBCTemplate.getConnection();

		return workCommentWriteDao.getWorkCommentNumber(conn, wc);
	}

	@Override
	public commentDto getWCdata(commentDto wc) {

		Connection conn = JDBCTemplate.getConnection();

		return workCommentWriteDao.getWCdata(conn, wc);
	}

	@Override
	public void updateComment(HttpServletRequest req) {

		Connection conn = JDBCTemplate.getConnection();

		if (workCommentWriteDao.update(conn, req) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

	}

}
