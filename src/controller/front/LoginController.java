package controller.front;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.member.MemberDto;
import dto.member.PhotoDto;
import service.face.LoginService;
import service.impl.LoginServiceImpl;

@WebServlet("/member/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/member/login[GET]");
		req.getRequestDispatcher("/WEB-INF/views/main/member/login.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("/member/login[POST]");

		// 요청 객체로부터 전달된 파라미터 받아오기
		String userid = req.getParameter("userID");
		String userpw = req.getParameter("userPW");
		System.out.println("userid, userpw: " + userid + "," + userpw);

		// 폼 입력 결과를 받아서 서비스메서드 실행
		LoginService loginService = new LoginServiceImpl();
		System.out.println(" loginService.idpwCheck()");
		int chk = loginService.idpwCheck(userid, userpw);
		System.out.println("ID, PW DB와 대조 결과: " + chk);

		// 유저 정보 DB로부터 로드 (닉네임, 프사 등)
		String nickname = loginService.loadUserInfo(userid).getNickname();
		MemberDto memberDto = loginService.loadUserInfo(userid);

		System.out.println("[LoginController] doGet() memberDto 값 : " + memberDto);
		PhotoDto photoDto = loginService.loadUserPhoto(memberDto);
		System.out.println("[LoginController] doGet() photoDto 값 : " + photoDto);

//		// 뷰 지정: 성공시 메인페이지로 리다이렉트, 실패시 실패 안내
		HttpSession session = req.getSession();
		
		if(userid == null || userid.equals("") || userpw == null || userpw.equals("")) {
			req.getSession().setAttribute("messageType", "오류 메시지");
			req.getSession().setAttribute("messageContent", "모든 내용을 입력해주세요.");
			resp.sendRedirect("/member/login");
			return;
		}
		int result = loginService.idpwCheck(userid, userpw);
		if(result == 1) {
			session.setAttribute("login", true);
			session.setAttribute("loginID", memberDto.getUserID());
			session.setAttribute("userID", memberDto.getUserID());
			session.setAttribute("nickname", memberDto.getNickname());
			session.setAttribute("userno", memberDto.getUserNumber());
			session.setAttribute("usernumber", memberDto.getUserNumber());
			session.setAttribute("email", memberDto.getEmail());
			session.setAttribute("photoDto", photoDto);
			req.getSession().setAttribute("messageType", "성공메시지");
			req.getSession().setAttribute("messageContent", "로그인에 성공했습니다.");
//			req.getRequestDispatcher("/WEB-INF/views/main/index.jsp").forward(req, resp);
			resp.sendRedirect("/");
//			resp.sendRedirect("index.jsp");
		} 
		else if(result == 2) {
			req.getSession().setAttribute("messageType", "오류메시지");
			req.getSession().setAttribute("messageContent", "비밀번호를 다시 확인하세요.");
			resp.sendRedirect("/member/login");
		}
		else if(result == 3) {
			req.getSession().setAttribute("messageType", "오류메시지");
			req.getSession().setAttribute("messageContent", "탈퇴한 회원 입니다.");
			resp.sendRedirect("/member/login");

		}
		else if(result == 0) {
			req.getSession().setAttribute("messageType", "오류메시지");
			req.getSession().setAttribute("messageContent", "아이디가 존재하지 않습니다.");
			resp.sendRedirect("/member/login");
			
		}
		else if(result == -1) {
			req.getSession().setAttribute("messageType", "오류메시지");
			req.getSession().setAttribute("messageContent", "데이터베이스 오류가 발생했습니다.");
			resp.sendRedirect("/member/login");
			
		}

		
		
//		if (chk == 1 && (memberDto.getDeActivated() == 0)) {
//			// 세션에 로그인 정보 저장
//
//			session.setAttribute("login", true);
//			session.setAttribute("loginID", memberDto.getUserID());
//			session.setAttribute("userID", memberDto.getUserID());
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