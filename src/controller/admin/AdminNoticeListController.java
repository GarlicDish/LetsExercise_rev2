package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Notice;
import service.face.AdminNoticeService;
import service.impl.AdminNoticeServiceImpl;

@WebServlet("/admin/notice/list")
public class AdminNoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminNoticeService adminNoticeService = new AdminNoticeServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 게시글 전체 조회 - BoardService이용
		List<Notice> noticeList = adminNoticeService.getList();

		// 조회결과 MODEL값 전달 - req.setAttribute
		req.setAttribute("noticeList", noticeList);

		// VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/admin/admin_notice_list.jsp").forward(req, resp);

	}

}
