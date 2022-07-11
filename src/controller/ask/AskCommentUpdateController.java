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

@WebServlet("/ask/commentupdate")
public class AskCommentUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	AskCommentWriteService askCommentWriteService = new AskCommentWriteServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("ask/commentupdate [GET]");

		AskComment ac = new AskComment();
		ac.setCommentNumber(Integer.parseInt(req.getParameter("commentnumber")));

		System.out.println("boardnumber가 들어간 ac 내용 : " + ac);
		ac = askCommentWriteService.getACdata(ac);

		System.out.println("Update 할 ac 내용 : " + ac);
		req.setAttribute("ac", ac);

		req.getRequestDispatcher("/WEB-INF/views/asklist/askcommentupdate.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("ask/commentupdate [POST]");

		askCommentWriteService.updateComment(req);

		resp.sendRedirect("/ask/view?boardno=" + req.getParameter("boardno"));
	}
}
