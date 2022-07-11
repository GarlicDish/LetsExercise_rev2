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

@WebServlet("/qna/reply/write")
public class QnaReplyWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	QnaReplyService qnaReplyService = new QnaReplyServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/find/reply/write [POST]");

		// 전달 파라미터에 대한 한글 인코딩
		req.setCharacterEncoding("UTF-8");

		QnaReply csr = new QnaReply();
		csr = qnaReplyService.getQnaReplyNumber(csr);

		csr.setMember(11);
		csr.setQnaboardno(Integer.parseInt(req.getParameter("qnaboardno")));
		csr.setContent(req.getParameter("content"));

		System.out.println("[FindReplyWriteController] doGet() - csr 값 : " + csr);

		qnaReplyService.write(csr);

		resp.sendRedirect("/qna/view?boardno=" + req.getParameter("qnaboardno"));
	}
}
