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

@WebServlet("/club/sns/reply/update")
public class ClubSNSReplyUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ClubSNSReplyService clubSNSReplyService = new ClubSNSReplyServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/club/sns/reply/update [GET]");

		System.out.println("[ClubSNSReplyUpdateController] req의 clubnumber 파라미터: " + req.getParameter("clubnumber"));
		ClubSNSReplyDto csr = new ClubSNSReplyDto();
		csr.setClubSNSReplyNumber(Integer.parseInt(req.getParameter("clubsnsreplynumber")));

		System.out.println("clubsnsnumber가 들어간 csr 내용 : " + csr);
		csr = clubSNSReplyService.getCSRdata(csr);

		System.out.println("Update 할 csr 내용 : " + csr);
		req.setAttribute("csr", csr);

		req.getRequestDispatcher("/WEB-INF/views/club/clubSNSReplyUpdate.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/club/sns/reply/update [POST]");

		clubSNSReplyService.updateReply(req);

		resp.sendRedirect("/club/sns/view?clubsnsnumber=" + req.getParameter("clubsnsnumber") + "&clubnumber="
				+ req.getParameter("clubnumber"));
	}
}
