package controller.front;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.club.CityDto;
import service.face.ClubService;
import service.face.JoinService;
import service.impl.ClubServiceImpl;
import service.impl.JoinServiceImpl;

@WebServlet("/member/join")
public class JoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ClubService clubService = new ClubServiceImpl();

	private JoinService joinService = new JoinServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/member/join[GET]");

		List<CityDto> cityList = clubService.getCityInfo();
		req.setAttribute("city", cityList);

		req.getRequestDispatcher("/WEB-INF/views/main/member/join.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		try {

			System.out.println("/member/join[POST]");

			req.setCharacterEncoding("UTF-8");

			int res = joinService.cosFileupload(req, resp);

			System.out.println("여기 도착해야함");
			// 성공, 실패시 리다이렉트 설정
			if (res == 1) {
				System.out.println("joinService: 성공");
				session.setAttribute("msg", "가입 성공, 로그인해주세요");
				resp.sendRedirect("/main");
			} else if (res == 0) {
				System.out.println("joinService: 실패");
			}

		} catch (Exception e) {
			req.setAttribute("msg", "가입 시도 실패, 다시 시도해주세요");
			req.getRequestDispatcher("/member/join").forward(req, resp);
		}

	}
}