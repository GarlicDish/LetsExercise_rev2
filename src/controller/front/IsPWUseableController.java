package controller.front;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.face.JoinDao;
import dao.impl.JoinDaoImpl;
import dto.member.MemberDto;
import service.face.JoinPWUsableService;
import service.impl.JoinPWUsableServiceImpl;

/**
 * Servlet implementation class IsIdOverlappedController
 */
@WebServlet("/check/useablepw")
public class IsPWUseableController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		}
	
		@Override
			protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				req.setCharacterEncoding("UTF-8");
				resp.setCharacterEncoding("UTF-8");
				
				MemberDto memberDto = new MemberDto();
				memberDto.setUserPW(req.getParameter("pw1"));
				
				PrintWriter out = resp.getWriter();
				
				JoinPWUsableService joinPWUsableService = new JoinPWUsableServiceImpl();
				int pwCheck = joinPWUsableService.pwCheck(memberDto);
				
				
				out.write(pwCheck+"");
			}
	
}
