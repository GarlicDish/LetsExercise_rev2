package controller.club;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.MyClubService;
import service.impl.MyClubServiceImpl;

@WebServlet("/myClub/withdraw")
public class MyClubWithdrawalController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MyClubService myClubService = new MyClubServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		myClubService.withdraw(req);

		resp.sendRedirect("/myClub/list");

	}

}
