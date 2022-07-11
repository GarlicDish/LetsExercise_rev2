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

@WebServlet("/admin/qna/reply/update")
public class AdminQnaReplyUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	AdminQnaReplyService adminQnaReplyService = new AdminQnaReplyServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/qna/reply/update [GET]");

		System.out.println("[QnaReplyUpdateController] req의 clubnumber 파라미터: " + req.getParameter("clubnumber"));
		QnaReply csr = new QnaReply();
		csr.setQnareplyno(Integer.parseInt(req.getParameter("QnaReply")));

		System.out.println("findreplyno가 들어간 csr 내용 : " + csr);
		csr = adminQnaReplyService.getQrdata(csr);

		System.out.println("Update 할 csr 내용 : " + csr);
		req.setAttribute("csr", csr);

		req.getRequestDispatcher("/WEB-INF/views/admin/admin_qnareply_update.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/qna/reply/update [POST]");

		adminQnaReplyService.updateReply(req);

		resp.sendRedirect("/admin/qna/view?boardno=" + req.getParameter("boardno"));
	}
}
