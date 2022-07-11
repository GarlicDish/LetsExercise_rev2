package controller.club;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.club.CityDto;
import dto.club.ClubDto;
import dto.club.ExerciseDto;
import service.face.ClubService;
import service.impl.ClubServiceImpl;

@WebServlet("/myClub/create")
public class MyClubCreateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ClubService clubService = new ClubServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/myClub/create [GET]");

		List<CityDto> cityList = clubService.getCityInfo();
		req.setAttribute("city", cityList);

		List<ExerciseDto> eXList = clubService.getExerciseInfo();

		req.setAttribute("exercise", eXList);

		req.getRequestDispatcher("/WEB-INF/views/myClub/createClub.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/myClub/create [POST]");

		ClubDto club = clubService.getClubData(req);
		System.out.println("전달된 club 정보 : " + club);

		ClubDto result = clubService.createClub(club);
		System.out.println("dB에 입력된 club 정보 : " + result);

		clubService.addMember(club, req);
		req.setAttribute("result", result);

		// View 지정 후, 응답하기 - forward
		resp.sendRedirect("/myClub/list");
	}
}
