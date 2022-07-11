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

@WebServlet("/work/commentupdate")
public class WorkCommentUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	WorkCommentWriteService workCommentWriteService = new WorkCommentWriteServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("work/commentupdate [GET]");

		commentDto wc = new commentDto();
		wc.setCommentnumber(Integer.parseInt(req.getParameter("commentnumber")));
		wc.setBoardnumber(Integer.parseInt(req.getParameter("boardno")));
		System.out.println("boardnumber가 들어간 wc 내용 : " + wc);
		wc = workCommentWriteService.getWCdata(wc);

		System.out.println("Update 할 wc 내용 : " + wc);
		req.setAttribute("wc", wc);

		req.getRequestDispatcher("/WEB-INF/views/work/workcommentupdate.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("work/commentupdate [POST]");

		workCommentWriteService.updateComment(req);

		resp.sendRedirect("/work/view?boardno=" + req.getParameter("boardno"));
	}
}
