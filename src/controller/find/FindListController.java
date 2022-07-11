package controller.find;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Find;
import service.face.FindService;
import service.impl.FindServiceImpl;
import util.Paging;

@WebServlet("/find/list")
public class FindListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FindService findService = new FindServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 전달파라미터에서 현재 페이징 객체 알아내기
		Paging paging = findService.getPaging(req);
		System.out.println("findController doGet() - " + paging);

		// 게시글 전체 조회 - BoardService이용
		List<Find> findList = findService.getList();

		// 게시글 페이징 목록 조회 - BoardService이용
//		List<Find> findList = findService.getList( paging );

		// 조회결과 MODEL값 전달 - req.setAttribute
		req.setAttribute("findList", findList);

		// 페이징 MODEL값 전달
		req.setAttribute("paging", paging);

		// VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/board/find_list.jsp").forward(req, resp);

	}

}
