package controller.club;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.ClubService;
import service.impl.ClubServiceImpl;
import util.Paging;

@WebServlet("/club/list")
public class ClubListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ClubService clubService = new ClubServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/clubList/list [GET]");

		Paging paging = clubService.getPaging(req);
		System.out.println("getPagign() : " + paging);

		List<Map<String, Object>> list = clubService.getList(paging);

//		 Club 리스트 확인
//		for (Map<String, Object> d : list) {
//			System.out.println("===================list 출력하기" + d);
//		}

		req.setAttribute("list", list);
		req.setAttribute("paging", paging);

		req.getRequestDispatcher("/WEB-INF/views/club/clubList.jsp").forward(req, resp);
	}

}
