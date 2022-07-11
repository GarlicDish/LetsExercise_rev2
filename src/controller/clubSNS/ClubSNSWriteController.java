package controller.clubSNS;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.ClubSNSService;
import service.impl.ClubSNSServiceImpl;

@WebServlet("/club/sns/write")
public class ClubSNSWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ClubSNSService clubSNSService = new ClubSNSServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/club/sns/write [GET]");

		System.out.println("req객체의 clubnumber 값" + Integer.parseInt(req.getParameter("clubnumber")));

		req.getRequestDispatcher("/WEB-INF/views/club/clubSNSWrite.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/club/sns/write [POST]");

		clubSNSService.write(req, resp);
		System.out.println(req.getParameter("clubnumber"));
		resp.sendRedirect("/club/sns/list?clubnumber=" + Integer.parseInt(req.getParameter("clubnumber")));
	}
}
