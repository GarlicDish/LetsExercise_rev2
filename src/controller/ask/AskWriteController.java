package controller.ask;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.AskService;
import service.impl.AskServiceImpl;

@WebServlet("/ask/write")
public class AskWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AskService askService = new AskServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 문의글 작성화면
		req.getRequestDispatcher("/WEB-INF/views/asklist/askwrite.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 작성글 삽입
		askService.write(req);

		// 삽입 후 문의내역 화면으로 이동됨
		resp.sendRedirect("/ask/list");

	}

}
