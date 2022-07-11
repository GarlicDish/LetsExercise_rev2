package controller.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.QnaReply;
import service.face.QnaReplyService;
import service.impl.QnaReplyServiceImpl;

@WebServlet("/qna/reply/delete")
public class QnaReplyDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	QnaReplyService QnaReplyService = new QnaReplyServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/qna/reply/delete [GET]");

		QnaReply csr = new QnaReply();
		csr.setQnareplyno(Integer.parseInt(req.getParameter("QnaReply")));

		QnaReplyService.delete(csr);

		resp.sendRedirect("/qna/view?boardno=" + req.getParameter("boardno"));
	}
}
