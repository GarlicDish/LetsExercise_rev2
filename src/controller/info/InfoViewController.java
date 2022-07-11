package controller.info;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.member.MemberDto;
import service.face.MemberInfoService;
import service.impl.MemberInfoServiceImpl;

@WebServlet("/info/view")
public class InfoViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberInfoService infoService = new MemberInfoServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/info/view [GET]");

		
		HttpSession session = req.getSession();
		

		req.getParameter("userid");
		String userid = (String)session.getAttribute("userID");
		System.out.println("파라미터: " + userid);

		
//			System.out.println(info.getExercise() );

		req.getRequestDispatcher("/WEB-INF/views/info/view.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/info/view [POST]");

//					

	}

}
