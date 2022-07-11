package controller.info;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MemberDto;
import service.face.InfoService;
import service.impl.InfoServiceImpl;

@WebServlet("/info/delete")
public class InfoDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private InfoService infoService = new InfoServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/info/delete [GET]");

		req.getRequestDispatcher("/WEB-INF/views/info/delete.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/info/delete [POST]");

		// Int userno = req.getParameter("userno");
		MemberDto memberdto = infoService.getUserno(req);

		infoService.getdeactivatedelete(memberdto); // deactivate 1-비활성화 되고 userid가 null값이 됨

		System.out.println("실행11");

		resp.sendRedirect("/info/view"); // 홈페이지 메인으로 가게 다시 설정해야됨 지금은 메인화면이 없어서 여기로 설정함

	}

}
