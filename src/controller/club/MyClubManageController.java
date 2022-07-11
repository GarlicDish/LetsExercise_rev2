package controller.club;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.club.ClubDto;
import service.face.ClubService;
import service.impl.ClubServiceImpl;

@WebServlet("/myClub/manage")
public class MyClubManageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ClubService clubService = new ClubServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/myClub/manage [doGET]");

		ClubDto club = clubService.getClubInfoByClubnumber(req);

		List<Map<String, Object>> userMap = new ArrayList<>();

		userMap = clubService.getClubMemberData(club);

		System.out.println("[MyClubManageController]에서 가져온 club 정보 객체 : " + club);
		System.out.println("[MyClubManageController]에서 가져온 userMap 정보 객체 : " + userMap);

		req.setAttribute("club", club);
		req.setAttribute("userMap", userMap);

		req.getRequestDispatcher("/WEB-INF/views/myClub/manage.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/myClub/manage [DOPOST]");

		clubService.updateClub(req);

		resp.sendRedirect("/myClub/manage?clubnumber=" + Integer.parseInt(req.getParameter("clubnumber")));
	}
}
