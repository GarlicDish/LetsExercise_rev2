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

@WebServlet("/club/sns/reply/write")
public class ClubSNSReplyWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ClubSNSReplyService clubSNSReplyService = new ClubSNSReplyServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/club/sns/reply/write [POST]");

		ClubSNSReplyDto csr = new ClubSNSReplyDto();
		csr = clubSNSReplyService.getClubSNSReplyNumber(csr);

		csr.setClubSNSNumber(Integer.parseInt(req.getParameter("clubsnsnumber")));
		csr.setReplyContent(req.getParameter("replyContent"));
		csr.setWriter((int) req.getSession().getAttribute("userno"));

		System.out.println("[ClubSNSReplyWriteController] doGet() - csr 값 : " + csr);

		clubSNSReplyService.write(csr);

		resp.sendRedirect("/club/sns/view?clubsnsnumber=" + csr.getClubSNSNumber() + "&clubnumber="
				+ req.getParameter("clubnumber"));
	}
}
