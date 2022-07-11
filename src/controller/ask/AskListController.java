package controller.ask;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Ask;
import service.face.AskService;
import service.impl.AskServiceImpl;
import util.Paging;

@WebServlet("/ask/list")
public class AskListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AskService askService = new AskServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 현재 페이징 객체 확인
		Paging paging = askService.getPaging(req);
		System.out.println("AskListController doGet() - " + paging);

		// 게시글 페이징 목록 조회
		List<Ask> askList = askService.getList(req, paging);

		// 조회 결과값 전달
		req.setAttribute("askList", askList);

		// 페이징 값 전달
		req.setAttribute("paging", paging);

		// 문의내역 첫 화면으로 이동됨
		req.getRequestDispatcher("/WEB-INF/views/asklist/asklist.jsp").forward(req, resp);

	}

}
