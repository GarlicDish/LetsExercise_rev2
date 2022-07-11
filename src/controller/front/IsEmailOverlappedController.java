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
import service.face.JoinEmailCheckService;
import service.impl.JoinEmailCheckServiceImpl;

@WebServlet("/check/emailoverlap")
public class IsEmailOverlappedController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		MemberDto memberDto = new MemberDto();
		memberDto.setEmail(req.getParameter("email"));

		PrintWriter out = resp.getWriter();
		
		int emailCheck = 0;
		
		JoinEmailCheckService joinEmailCheckService = new JoinEmailCheckServiceImpl();
		emailCheck = joinEmailCheckService.emailCheckService(memberDto);
		
		
		out.write(emailCheck + "");
	}
}
