package controller.clubSNS;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.club.ClubDto;
import service.face.ClubSNSService;
import service.face.ClubService;
import service.impl.ClubSNSServiceImpl;
import service.impl.ClubServiceImpl;

@WebServlet("/club/sns/list")
public class ClubSNSListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ClubSNSService clubSNSService = new ClubSNSServiceImpl();
	ClubService clubService = new ClubServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/club/sns/list [GET]");

		HttpSession session = req.getSession();

		ClubDto club = clubService.getClubInfoByClubnumber(req);
		System.out.println(club);

		session.setAttribute("clubnumber", club.getClubNumber());

		System.out.println("session 에 저장된 클럽 번호 값 : " + session.getAttribute("clubnumber"));
		System.out.println("/club/sns/list 의 req session 의 userno 값 : " + session.getAttribute("userno"));
		System.out.println(req.getAttribute("userno"));

		List<Map<String, Object>> lMap = clubSNSService.listWithMap(club);

		if (lMap != null) {
			for (Map<String, Object> i : lMap) {
				System.out.println("(lMap 출력) " + i);
			}
		}

		req.setAttribute("lMap", lMap);
		req.setAttribute("clubname", club.getClubName());

		req.getRequestDispatcher("/WEB-INF/views/club/clubSNSList.jsp").forward(req, resp);
	}
}
