package controller.work;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.commentDto;
import service.face.WorkCommentService;
import service.impl.WorkCommentServiceImpl;
import util.Paging;

@WebServlet("/work/commentlist")
public class WorkCommentListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private WorkCommentService commentService = new WorkCommentServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 전달파라미터에서 현재 페이징 객체 알아내기
		Paging paging = commentService.getPaging(req);
		System.out.println("WorkController doGet() - " + paging);

		List<commentDto> commentList = commentService.getList(req, paging);

		// 조회결과 MODEL값 전달 - req.setAttribute
		req.setAttribute("commentList", commentList);

		// 페이징 MODEL값 전달
		req.setAttribute("paging", paging);

		req.getRequestDispatcher("/WEB-INF/views/work/workcommentlist.jsp").forward(req, resp);

	}

}
