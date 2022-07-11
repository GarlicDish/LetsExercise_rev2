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

@WebServlet("/ask/commentwrite")
public class AskCommentWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	AskCommentWriteService askCommentWriteService = new AskCommentWriteServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/ask/commentwrite [POST]");

		AskComment ac = new AskComment();
		ac = askCommentWriteService.getCommentNumber(ac);
		ac.setUserNumber((int) req.getSession().getAttribute("userno"));

		ac.setBoardNumber(Integer.parseInt(req.getParameter("boardno")));
		ac.setCommentText(req.getParameter("commenttext"));
//		ac.setNickname( req.getSession().getAttribute("nickname"));

		System.out.println("[AskCommentWriteController] doGet() - ac 값 : " + ac);

		askCommentWriteService.write(ac);

		resp.sendRedirect("/ask/view?boardno=" + ac.getBoardNumber());
	}
}
