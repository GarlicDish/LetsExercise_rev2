package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Notice;
import service.face.AdminNoticeService;
import service.impl.AdminNoticeServiceImpl;

@WebServlet("/admin/notice/view")
public class AdminNoticeViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminNoticeService adminNoticeService = new AdminNoticeServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/notice/view [GET]");

		// 전달파라미터 얻기 - Noticedno
		Notice noticeno = adminNoticeService.getNoticeno(req);

		System.out.println(noticeno);

		// 상세보기 결과 조회
		Notice viewNotice = adminNoticeService.view(noticeno);

		System.out.println("BoardController viewBoard - " + viewNotice);

		// 조회결과 MODEL값 전달
		req.setAttribute("viewNotice", viewNotice);

		// VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/admin/admin_notice_view.jsp").forward(req, resp);

	}

}
