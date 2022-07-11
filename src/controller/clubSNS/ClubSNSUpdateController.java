package controller.clubSNS;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.club.ClubSNSDto;
import service.face.ClubSNSService;
import service.impl.ClubSNSServiceImpl;

@WebServlet("/club/sns/update")
public class ClubSNSUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ClubSNSService clubSNSService = new ClubSNSServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/club/sns/update [GET]");

		ClubSNSDto clubSNSGet = clubSNSService.view(clubSNSService.getSNSno(req));

		req.setAttribute("clubSNS", clubSNSGet);
		System.out.println("/////ClubSNSUpdateController doGet() - clubSNSGet 값 출력 : " + clubSNSGet);

		req.getRequestDispatcher("/WEB-INF/views/club/clubSNSUpdate.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/club/sns/update [POST]");

		clubSNSService.update(req, resp);

		resp.sendRedirect("/club/sns/list?clubnumber=" + (int) req.getSession().getAttribute("clubnumber"));

	}

}
