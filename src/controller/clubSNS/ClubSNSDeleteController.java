package controller.clubSNS;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.ClubSNSService;
import service.impl.ClubSNSServiceImpl;

@WebServlet("/club/sns/delete")
public class ClubSNSDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ClubSNSService clubSNSService = new ClubSNSServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/club/sns/write [GET]");

		clubSNSService.delete(req);

		resp.sendRedirect("/club/sns/list?clubnumber=" + req.getParameter("clubnumber"));
	}

}
