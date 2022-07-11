package controller.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Notice;
import service.face.NoticeService;
import service.impl.NoticeServiceImpl;

@WebServlet("/notice/view")
public class NoticeViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private NoticeService noticeService = new NoticeServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/notice/view [GET]");

		// 전달파라미터 얻기 - Noticedno
		Notice noticeno = noticeService.getNoticeno(req);

		System.out.println(noticeno);

		// 상세보기 결과 조회
		Notice viewNotice = noticeService.view(noticeno);

		System.out.println("BoardController viewBoard - " + viewNotice);

		// 조회결과 MODEL값 전달
		req.setAttribute("viewNotice", viewNotice);

		// VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/board/notice_view.jsp").forward(req, resp);

	}

}
