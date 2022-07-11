package service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.ClubSNSReplyDao;
import dao.impl.ClubSNSReplyDaoImpl;
import dto.club.ClubSNSReplyDto;
import service.face.ClubSNSReplyService;

public class ClubSNSReplyServiceImpl implements ClubSNSReplyService {

	private ClubSNSReplyDao clubSNSReplyDao = new ClubSNSReplyDaoImpl();

	@Override
	public ClubSNSReplyDto getSNSno(HttpServletRequest req) {

		ClubSNSReplyDto csr = new ClubSNSReplyDto();

		String param = req.getParameter("clubsnsnumber");
		if (param != null && "".equals(param)) {
			csr.setClubSNSNumber(Integer.parseInt(param));
			return csr;
		} else {
			return null;
		}

	}

	@Override
	public List<Map<String, Object>> selectReply(HttpServletRequest req) {

		Connection conn = JDBCTemplate.getConnection();

		return clubSNSReplyDao.getMapList(conn, req);
	}

	@Override
	public void delete(ClubSNSReplyDto csr) {

		Connection conn = JDBCTemplate.getConnection();

		System.out.println("/////clubsnsreplyimpl - delete  csr: " + csr);
		int res = clubSNSReplyDao.delete(conn, csr);
		System.out.println(res);
		if (res > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}

	@Override
	public void write(ClubSNSReplyDto csr) {

		Connection conn = JDBCTemplate.getConnection();

		if (clubSNSReplyDao.write(conn, csr) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}

	@Override
	public ClubSNSReplyDto getClubSNSReplyNumber(ClubSNSReplyDto csr) {

		Connection conn = JDBCTemplate.getConnection();

		return clubSNSReplyDao.getClubSNSReplyNumber(conn, csr);
	}

	@Override
	public ClubSNSReplyDto getCSRdata(ClubSNSReplyDto csr) {

		Connection conn = JDBCTemplate.getConnection();

		return clubSNSReplyDao.getCSRdata(conn, csr);
	}

	@Override
	public void updateReply(HttpServletRequest req) {

		Connection conn = JDBCTemplate.getConnection();

		if (clubSNSReplyDao.update(conn, req) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

	}

}
