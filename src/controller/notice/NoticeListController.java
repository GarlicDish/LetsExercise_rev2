package controller.notice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Notice;
import service.face.NoticeService;
import service.impl.NoticeServiceImpl;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private NoticeService noticeService = new NoticeServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 게시글 전체 조회 - BoardService이용
		List<Notice> noticeList = noticeService.getList();

		// 조회결과 MODEL값 전달 - req.setAttribute
		req.setAttribute("noticeList", noticeList);

		// VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/board/notice_list.jsp").forward(req, resp);

	}

}
