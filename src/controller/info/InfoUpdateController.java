package controller.info;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.JDBCTemplate;
import dao.face.LoginDao;
import dao.impl.LoginDaoImpl;
import dto.member.MemberDto;
import service.face.JoinService;
import service.face.MemberInfoService;
import service.face.MemberInfoUpdateService;
import service.impl.JoinServiceImpl;
import service.impl.MemberInfoServiceImpl;
import service.impl.MemberInfoUpdateServiceImpl;

@WebServlet("/info/update")
public class InfoUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberInfoService infoService = new MemberInfoServiceImpl();
	private JoinService joinService = new JoinServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/info/update [GET]");

		Connection conn	= JDBCTemplate.getConnection();
		
		HttpSession session = req.getSession();
		MemberDto memberdto = new MemberDto();
		
		String userid = (String)session.getAttribute("userID");
		
		LoginDao loginDao = new LoginDaoImpl();
		memberdto = loginDao.loadUserInfo(conn, userid);
		
		String userpw = (String)memberdto.getUserPW();

		req.setAttribute("memberdto", memberdto);
		req.setAttribute("userpw", userpw);

		String saveDirectory = req.getServletContext().getRealPath("upload");
		session.setAttribute("saveDirectory", saveDirectory);

		req.getRequestDispatcher("/WEB-INF/views/info/update.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/info/update [POST]");

		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
//		MemberDto memberDto = (MemberDto)session.getAttribute("memberdto");
//
//		PhotoDto photoDto = (PhotoDto)session.getAttribute("photoDto");

		MemberInfoUpdateService memberInfoUpdateService = new MemberInfoUpdateServiceImpl();
		memberInfoUpdateService.cosFileupload(req, resp);

		int res = 0;

		try {

			res = memberInfoUpdateService.cosFileupload(req, resp);

			System.out.println("여기 도착해야함");
			// 성공, 실패시 리다이렉트 설정
			if (res == 1) {
				System.out.println("joinService: 성공");

				resp.sendRedirect("/main");
			} else if (res == 0) {
				System.out.println("joinService: 실패");
			}

		} catch (Exception e) {
			resp.sendRedirect("/info/update");
		}

	}

}
