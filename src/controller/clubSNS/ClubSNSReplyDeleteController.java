package controller.clubSNS;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.club.ClubSNSReplyDto;
import service.face.ClubSNSReplyService;
import service.impl.ClubSNSReplyServiceImpl;

@WebServlet("/club/sns/reply/delete")
public class ClubSNSReplyDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ClubSNSReplyService clubSNSReplyService = new ClubSNSReplyServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/clubSNS/reply/delete [GET]");

		ClubSNSReplyDto csr = new ClubSNSReplyDto();
		csr.setClubSNSReplyNumber(Integer.parseInt(req.getParameter("clubsnsreplynumber")));

		clubSNSReplyService.delete(csr);

		resp.sendRedirect("/club/sns/view?clubsnsnumber=" + req.getParameter("clubsnsnumber") + "&clubnumber="
				+ req.getParameter("clubnumber"));
	}
}
