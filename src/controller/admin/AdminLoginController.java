package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.AdminLoginService;
import service.impl.AdminLoginServiceImpl;

@WebServlet("/admin/login")
public class AdminLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/member/login[GET]");
		req.getRequestDispatcher("/WEB-INF/views/admin/adminMain.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("/member/login[POST]");

		// 요청 객체로부터 전달된 파라미터 받아오기
		String adminid = req.getParameter("adminid");
		String adminpw = req.getParameter("adminpw");
		System.out.println("adminid, adminpw: " + adminid + "," + adminpw);

		// 폼 입력 결과를 받아서 서비스메서드 실행
		AdminLoginService adminLoginService = new AdminLoginServiceImpl();
		System.out.println(" loginService.idpwCheck()");
//		int chk = loginService.idpwCheck(adminid, userpw);
//		System.out.println("ID, PW DB와 대조 결과: " + chk);

		if (adminid == null || adminid.equals("") || adminpw == null || adminpw.equals("")) {
			req.getSession().setAttribute("messageType", "오류 메시지");
			req.getSession().setAttribute("messageContent", "모든 내용을 입력해주세요.");
			resp.sendRedirect("/admin.jsp");
			return;
		}
		int result = adminLoginService.idpwCheck(adminid, adminpw);
		if (result == 1) {
//			req.getSession().setAttribute("messageType", "성공메시지");
//			req.getSession().setAttribute("messageContent", "관리자인증에 성공했습니다.");
//			req.getRequestDispatcher("/WEB-INF/views/main/index.jsp").forward(req, resp);
			resp.sendRedirect("/admin/login");
//			resp.sendRedirect("index.jsp");
		} else if (result == 2) {
			req.getSession().setAttribute("messageType", "오류메시지");
			req.getSession().setAttribute("messageContent", "관리자 인증에 실패했습니다1.");
			resp.sendRedirect("/admin.jsp");
		} else if (result == 0) {
			req.getSession().setAttribute("messageType", "오류메시지");
			req.getSession().setAttribute("messageContent", "관리자 인증에 실패했습니다2.");
			resp.sendRedirect("/admin.jsp");

		} else if (result == -1) {
			req.getSession().setAttribute("messageType", "오류메시지");
			req.getSession().setAttribute("messageContent", "데이터베이스 오류가 발생했습니다.");
			resp.sendRedirect("/admin.jsp");

		}

//		if (chk == 1 && (memberDto.getDeActivated() == 0)) {
//			// 세션에 로그인 정보 저장
//
//			session.setAttribute("login", true);
//			session.setAttribute("loginID", memberDto.getadminid());
//			session.setAttribute("adminid", memberDto.getadminid());
//			session.setAttribute("nickname", memberDto.getNickname());
//			session.setAttribute("userno", memberDto.getUserNumber());
//			session.setAttribute("usernumber", memberDto.getUserNumber());
//			session.setAttribute("photoDto", photoDto);
//
//			req.getRequestDispatcher("/WEB-INF/views/main/index.jsp").forward(req, resp);
//			System.out.println("LoginController[POST] 로그인 성공, 메인페이지로 이동합니다");
//
//		} else if (memberDto.getDeActivated() == 1) {
//
//			session.setAttribute("msg", "탈퇴한 회원입니다. 아이디를 확인해 주세요.");
//			resp.sendRedirect("/");
//			System.out.println("LoginController[POST] 로그인 시도 실패");
//
//		} else {
//
//			session.setAttribute("msg", "아이디와 비밀번호를 확인해주세요");
//			resp.sendRedirect("/");
//			System.out.println("LoginController[POST] 로그인 시도 실패");
//		}

	}

}