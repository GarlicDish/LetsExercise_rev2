package controller.clubSNS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.club.ClubSNSDto;
import service.face.ClubSNSReplyService;
import service.face.ClubSNSService;
import service.impl.ClubSNSReplyServiceImpl;
import service.impl.ClubSNSServiceImpl;

@WebServlet("/club/sns/view")
public class ClubSNSViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ClubSNSReplyService clubSNSReplyService = new ClubSNSReplyServiceImpl();
	ClubSNSService clubSNSService = new ClubSNSServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/club/sns/view [GET]");

		ClubSNSDto clubSNS = clubSNSService.getSNS(req);
		System.out.println("조회한 SNS 글 내용 : " + clubSNS);

		List<Map<String, Object>> lMapSR = new ArrayList<>();
		Map<String, Object> lMapSNS = new HashMap<>();

		lMapSNS = clubSNSService.getUserUserProfileClubSNSbyClubSNSno(clubSNS);
		lMapSR = clubSNSReplyService.selectReply(req);
		System.out.println("lMapSNS 객체 출력 - " + lMapSNS);
		System.out.println("lMapSR 객체 출력 - " + lMapSR);

		req.setAttribute("lMapSNS", lMapSNS);

		req.setAttribute("lMapSR", lMapSR);

		req.getRequestDispatcher("/WEB-INF/views/club/clubSNSView.jsp").forward(req, resp);
	}

}
