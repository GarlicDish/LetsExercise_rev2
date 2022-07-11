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

@WebServlet("/find/reply/update")
public class FindReplyUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	FindReplyService FindReplyService = new FindReplyServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/find/reply/update [GET]");

		System.out.println("[FindReplyUpdateController] req의 clubnumber 파라미터: " + req.getParameter("clubnumber"));
		FindReply csr = new FindReply();
		csr.setFindreplyno(Integer.parseInt(req.getParameter("FindReply")));

		System.out.println("findreplyno가 들어간 csr 내용 : " + csr);
		csr = FindReplyService.getFrdata(csr);

		System.out.println("Update 할 csr 내용 : " + csr);
		req.setAttribute("csr", csr);

		req.getRequestDispatcher("/WEB-INF/views/board/findreply_update.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/find/reply/update [POST]");

		FindReplyService.updateReply(req);

		resp.sendRedirect("/find/view?boardno=" + req.getParameter("boardno"));
	}
}
