package controller.work;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDto;
import service.face.WorkService;
import service.impl.WorkServiceImpl;
import util.Paging;

@WebServlet("/work/boardlist")
public class WorkBoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private WorkService workService = new WorkServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 전달파라미터에서 현재 페이징 객체 알아내기
		Paging paging = workService.getPaging(req);
		System.out.println("WorkBoardListController doGet() - " + paging);

		List<BoardDto> workList = workService.getList(req, paging);

		// 조회결과 MODEL값 전달
		req.setAttribute("workList", workList);

		System.out.println(workList);
		System.out.println(req.getSession().getAttribute("usernumber"));
		System.out.println(req.getSession().getAttribute("userno"));
		// 페이징 MODEL값 전달
		req.setAttribute("paging", paging);

		req.getRequestDispatcher("/WEB-INF/views/work/workboardlist.jsp").forward(req, resp);

	}

}
