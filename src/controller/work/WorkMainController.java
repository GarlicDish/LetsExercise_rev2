package controller.work;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDto;
import dto.commentDto;
import service.face.WorkCommentService;
import service.face.WorkService;
import service.impl.WorkCommentServiceImpl;
import service.impl.WorkServiceImpl;

@WebServlet("/work/main")
public class WorkMainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private WorkService workService = new WorkServiceImpl();
	private WorkCommentService commentService = new WorkCommentServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/work/main [GET]");

		// req.getSession().setAttribute("usernumber", 1);
		// HttpSession session = req.getSession();

		// MemberDto memberdto = (MemberDto)session.getAttribute("memberDto");

		// 게시글 전체 조회
		List<BoardDto> workList = workService.getList(req);

		// 조회결과 MODEL값 전달
		req.setAttribute("workList", workList);

		// 댓글 전체 조회
		List<commentDto> commentList = commentService.getList(req);

		// 조회결과 MODEL값 전달
		req.setAttribute("commentList", commentList);

		req.getRequestDispatcher("/WEB-INF/views/work/workmain.jsp").forward(req, resp);
	}
}
