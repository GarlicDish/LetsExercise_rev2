package controller.club;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.ClubService;
import service.impl.ClubServiceImpl;

@WebServlet("/member/delete")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClubService clubService = new ClubServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/member/delete [GET]");
		clubService.deleteMember(req);

		resp.sendRedirect("/myClub/manage?clubnumber=" + Integer.parseInt(req.getParameter("clubnumber")));
	}
}
