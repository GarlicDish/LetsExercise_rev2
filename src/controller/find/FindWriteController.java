package controller.find;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.FindService;
import service.impl.FindServiceImpl;

@WebServlet("/find/write")
public class FindWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FindService findService = new FindServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 로그인 되어있지 않으면 리다이렉트
		if (req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/");
			return;
		}
		// VIEW 지정
		req.getRequestDispatcher("/WEB-INF/views/board/find_write.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 작성글 삽입
		findService.write(req);

		// 목록으로 리다이렉션
		resp.sendRedirect("/find/list");

	}

}
