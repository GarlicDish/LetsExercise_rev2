package service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.WorkCommentDao;
import dao.impl.WorkCommentDaoImpl;
import dto.commentDto;
import service.face.WorkCommentService;
import util.Paging;

public class WorkCommentServiceImpl implements WorkCommentService {

	private WorkCommentDao commentDao = new WorkCommentDaoImpl();

	@Override
	public List<commentDto> getList(HttpServletRequest req) {

		// 게시글 전체 조회 결과 반환
		return commentDao.selectAll(req, JDBCTemplate.getConnection());

	}

	@Override
	public List<commentDto> getList(HttpServletRequest req, Paging paging) {

		// 페이징 적용해서 조회 결과 반환
		return commentDao.selectAll(req, JDBCTemplate.getConnection(), paging);

	}

	@Override
	public Paging getPaging(HttpServletRequest req) {

		// 전달파라미터 curPage 추출하기
		String param = req.getParameter("curPage");
		int curPage = 0;
		if (param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("CommentService getPaging() - curPage값이 null이거나 비어있음");
		}

		// 총 게시글 수 조회하기
		int totalCount = commentDao.selectCntAll(JDBCTemplate.getConnection());

		// Paging 객체 생성 - 페이징 계산
		Paging paging = new Paging(totalCount, curPage);

		return paging;
	}

}
