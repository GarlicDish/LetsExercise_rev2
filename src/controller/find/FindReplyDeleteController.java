package controller.find;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.FindReply;
import service.face.FindReplyService;
import service.impl.FindReplyServiceImpl;

@WebServlet("/find/reply/delete")
public class FindReplyDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	FindReplyService FindReplyService = new FindReplyServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/find/reply/delete [GET]");

		FindReply csr = new FindReply();
		csr.setFindreplyno(Integer.parseInt(req.getParameter("FindReply")));

		FindReplyService.delete(csr);

		resp.sendRedirect("/find/view?boardno=" + req.getParameter("boardno"));
	}
}
