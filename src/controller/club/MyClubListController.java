package controller.club;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.club.User;
import service.face.MyClubService;
import service.impl.MyClubServiceImpl;
import util.Paging;

@WebServlet("/myClub/list")
public class MyClubListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MyClubService myClubService = new MyClubServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/MyClub/List [GET]");

		Paging paging = myClubService.getPaging(req);

		System.out.println("getPagign() : " + paging);
		User user = new User();

		user.setUserno((int) req.getSession().getAttribute("userno"));

		user = myClubService.getLoginUserInfoByUserno(user);
		System.out.println("user 정보 전체 출력 : " + user);

		List<Map<String, Object>> mcList = myClubService.getMyList(user, paging);

		System.out.println(mcList);
		System.out.println(mcList.size());
		req.setAttribute("mcList", mcList);
		req.setAttribute("paging", paging);
		req.getRequestDispatcher("/WEB-INF/views/myClub/myClubList.jsp").forward(req, resp);
	}

}
