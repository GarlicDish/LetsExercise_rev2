package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Qna;
import service.face.AdminQnaService;
import service.impl.AdminQnaServiceImpl;

@WebServlet("/admin/qna/delete")
public class AdminQnaDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// BoardService 객체 생성
	private AdminQnaService adminQnaService = new AdminQnaServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Qna qna = adminQnaService.getQnano(req);

		adminQnaService.delete(qna);

		// 목록으로 리다이렉트
		resp.sendRedirect("/admin/qna/list");

	}

}
