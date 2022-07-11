package service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.NoticeDao;
import dao.impl.NoticeDaoImpl;
import dto.board.Notice;
import service.face.NoticeService;
import util.Paging;

public class NoticeServiceImpl implements NoticeService {

	private NoticeDao noticeDao = new NoticeDaoImpl();

	@Override
	public List<Notice> getList() {

		// 게시글 전체 조회 결과 반환
		return noticeDao.selectAll(JDBCTemplate.getConnection());

	}

	@Override
	public List<Notice> getList(Paging paging) {

		// 페이징 적용해서 조회 결과 반환
		return noticeDao.selectAll(JDBCTemplate.getConnection(), paging);

	}

	@Override
	public Paging getPaging(HttpServletRequest req) {

		// 전달파라미터 curPage 추출하기
		String param = req.getParameter("curPage");
		int curPage = 0;
		if (param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARN] BoardService getPaging() - curPage값이 null이거나 비어있음");
		}

		// 총 게시글 수 조회하기
		int totalCount = noticeDao.selectCntAll(JDBCTemplate.getConnection());

		// Paging 객체 생성 - 페이징 계산
		Paging paging = new Paging(totalCount, curPage);

		return paging;
	}

	@Override
	public Notice getNoticeno(HttpServletRequest req) {

		// 전달파라미터 boardno를 저장할 DTO객체 생성
		Notice noticeno = new Notice();

		String param = req.getParameter("boardno");
		if (param != null && !"".equals(param)) {
			noticeno.setBoardno(Integer.parseInt(param));
		} else {
			System.out.println("[WARN] BoardService getBoardno() - boardno값이 null이거나 비어있음");
		}

		return noticeno;
	}

	@Override
	public Notice view(Notice noticeno) {

		Connection conn = JDBCTemplate.getConnection();

		// 조회수 증가
		if (noticeDao.updateHit(conn, noticeno) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		// 게시글 조회
		Notice notice = noticeDao.selectBoardByBoardno(conn, noticeno);

		return notice;
	}

	@Override
	public void write(HttpServletRequest req) {

		Notice notice = new Notice();

		notice.setTitle(req.getParameter("title"));
		notice.setContent(req.getParameter("content"));

		// 작성자id 처리
		notice.setUserid((String) req.getSession().getAttribute("userid"));

		if (notice.getTitle() == null || "".equals(notice.getTitle())) {
			notice.setTitle("(제목없음)");
		}

		Connection conn = JDBCTemplate.getConnection();
		if (noticeDao.insert(conn, notice) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

	}

	@Override
	public String getNick(Notice viewNotice) {
		return noticeDao.selectNickByUserno(JDBCTemplate.getConnection(), viewNotice);
	}

	@Override
	public Notice geNoticeno(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

}
