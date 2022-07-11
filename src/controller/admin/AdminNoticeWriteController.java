package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.AdminNoticeService;
import service.impl.AdminNoticeServiceImpl;

@WebServlet("/admin/notice/write")
public class AdminNoticeWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminNoticeService adminNoticeService = new AdminNoticeServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// VIEW 지정
		req.getRequestDispatcher("/WEB-INF/views/admin/admin_notice_write.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("title, content: " + req.getParameter("title") + " " + req.getParameter("content"));

		// 작성글 삽입
		adminNoticeService.write(req);

		// 목록으로 리다이렉션
		resp.sendRedirect("/admin/notice/list");

	}

}
