package controller.work;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.commentDto;
import service.face.WorkCommentWriteService;
import service.impl.WorkCommentWriteServiceImpl;

@WebServlet("/work/commentwrite")
public class WorkCommentWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	WorkCommentWriteService workCommentWriteService = new WorkCommentWriteServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/work/commentwrite [POST]");

		commentDto wc = new commentDto();
		wc = workCommentWriteService.getCommentNumber(wc);

		wc.setBoardnumber(Integer.parseInt(req.getParameter("boardno")));
		wc.setCommentText(req.getParameter("commenttext"));
		wc.setUsernumber(1);

		System.out.println("[WorkCommentWriteController] doGet() - wc 값 : " + wc);

		workCommentWriteService.write(wc);

		resp.sendRedirect("/work/view?boardno=" + wc.getBoardnumber());
	}
}
