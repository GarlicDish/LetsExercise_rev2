package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.QnaReply;
import service.face.AdminQnaReplyService;
import service.impl.AdminQnaReplyServiceImpl;

@WebServlet("/admin/qna/reply/delete")
public class AdminQnaReplyDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	AdminQnaReplyService adminQnaReplyService = new AdminQnaReplyServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/qna/reply/delete [GET]");

		QnaReply csr = new QnaReply();
		csr.setQnareplyno(Integer.parseInt(req.getParameter("QnaReply")));

		adminQnaReplyService.delete(csr);

		resp.sendRedirect("/admin/qna/view?boardno=" + req.getParameter("boardno"));
	}
}
