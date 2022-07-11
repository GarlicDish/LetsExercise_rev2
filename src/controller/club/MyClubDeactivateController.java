package controller.club;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.ClubService;
import service.face.MyClubService;
import service.impl.ClubServiceImpl;
import service.impl.MyClubServiceImpl;

@WebServlet("/myClub/deactivate")
public class MyClubDeactivateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MyClubService myClubService = new MyClubServiceImpl();
	ClubService clubService = new ClubServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/myClub/delete [GET]");

		myClubService.deactivateClub(req);

		resp.sendRedirect("/myClub/list");

	}
}
