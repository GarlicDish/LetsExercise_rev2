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

@WebServlet("/find/reply/write")
public class FindReplyWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	FindReplyService FindReplyService = new FindReplyServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/find/reply/write [POST]");

		// 전달 파라미터에 대한 한글 인코딩
		req.setCharacterEncoding("UTF-8");

		FindReply csr = new FindReply();
		csr = FindReplyService.getFindReplyNumber(csr);

		csr.setMember((int) req.getSession().getAttribute("usernumber"));
		csr.setFindboardno(Integer.parseInt(req.getParameter("findboardno")));
		csr.setContent(req.getParameter("content"));

		System.out.println("[FindReplyWriteController] doGet() - csr 값 : " + csr);

		FindReplyService.write(csr);

		resp.sendRedirect("/find/view?boardno=" + req.getParameter("findboardno"));
	}
}
