package controller.work;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.WorkService;
import service.impl.WorkServiceImpl;

@WebServlet("/work/write")
public class WorkWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private WorkService workService = new WorkServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[WorkWriteController] - doGet");

		req.getRequestDispatcher("/WEB-INF/views/work/workwrite.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[WorkWriteController] - doPost");

		// 작성글 삽입
		workService.write(req);

		// 삽입 수행후 목록으로 리다이렉트
		resp.sendRedirect("/work/boardlist");

	}

}
