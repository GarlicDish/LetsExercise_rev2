package controller.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.QnaService;
import service.impl.QnaServiceImpl;

@WebServlet("/qna/write")
public class QnaWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private QnaService qnaService = new QnaServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 로그인 되어있지 않으면 리다이렉트
		if (req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/");
			return;
		}

		// VIEW 지정
		req.getRequestDispatcher("/WEB-INF/views/board/qna_write.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 작성글 삽입
		qnaService.write(req);

		// 목록으로 리다이렉션
		resp.sendRedirect("/qna/list");

	}

}
