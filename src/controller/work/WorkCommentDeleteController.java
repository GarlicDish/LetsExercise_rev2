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

@WebServlet("/work/commentdelete")
public class WorkCommentDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	WorkCommentWriteService workCommentWriteService = new WorkCommentWriteServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/work/commentdelete [GET]");

		commentDto wc = new commentDto();
		wc.setCommentnumber(Integer.parseInt(req.getParameter("commentnumber")));

		workCommentWriteService.delete(wc);

		resp.sendRedirect("/work/view?boardno=" + req.getParameter("boardno"));
	}
}
