package controller.front;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.face.PassFindService;
import service.impl.PassFindServiceImpl;


@WebServlet("/findpass")
public class PassFindController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/member/findpass[GET]");
		req.getRequestDispatcher("/WEB-INF/views/main/member/findpassword.jsp").forward(req, resp);;
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/member/findpass[Post]");
		
		String userid = req.getParameter("userid");
		String email = req.getParameter("email");
		PassFindService passFindService = new PassFindServiceImpl();

		boolean isExistUser = passFindService.isExsistUser(userid, email);
		HttpSession session = req.getSession();
		
		// 존재하면 메세지 저장해서 세션에 표시. 불일치시 불일치 메시지만 저장.
		if(isExistUser) {
			String newPwd = passFindService.sendPass(userid, email);
			
			
			session.setAttribute("result", "success" );
			session.setAttribute("userid", userid );
			session.setAttribute("msg", newPwd );
			
		} else {
			session.setAttribute("result", "fail" );
			session.setAttribute("msg", "잘못된 정보를 입력하셨습니다." );
		}
		
		req.getRequestDispatcher("/WEB-INF/views/main/member/findpassResult.jsp").forward(req, resp);
		
	}
}