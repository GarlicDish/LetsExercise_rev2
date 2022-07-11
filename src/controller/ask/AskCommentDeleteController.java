package controller.ask;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.AskComment;
import service.face.AskCommentWriteService;
import service.impl.AskCommentWriteServiceImpl;

@WebServlet("/ask/commentdelete")
public class AskCommentDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	AskCommentWriteService askCommentWriteService = new AskCommentWriteServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/ask/commentdelete [GET]");

		AskComment ac = new AskComment();
		ac.setCommentNumber(Integer.parseInt(req.getParameter("commentnumber")));

		askCommentWriteService.delete(ac);

		resp.sendRedirect("/ask/view?boardno=" + req.getParameter("boardno"));
	}
}
