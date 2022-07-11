package controller.club;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.club.CityDto;
import dto.club.GuDto;
import service.face.ClubService;
import service.impl.ClubServiceImpl;

@WebServlet("/myClub/gu")
public class ClubGuController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ClubService clubService = new ClubServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/myClub/gu [GET]");

		CityDto city = clubService.getParamCity(req);

		List<GuDto> guList = clubService.getGuByCityNumber(city);
		System.out.println(guList);

		req.setAttribute("gu", guList);

		req.getRequestDispatcher("/WEB-INF/views/myClub/gu.jsp").forward(req, resp);
	}

}
