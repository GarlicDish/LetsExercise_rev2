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
import service.face.JoinIDCheckService;
import service.face.JoinNicknameCheckService;
import service.impl.JoinIDCheckServiceImpl;
import service.impl.JoinNicknameCheckServiceImpl;

@WebServlet("/check/nickoverlap")
public class IsNickOverlappedController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");


		PrintWriter out = resp.getWriter();
		
		int nickCheck = 0;

		MemberDto memberDto = new MemberDto();
		memberDto.setNickname(req.getParameter("nickname"));
		
		JoinNicknameCheckService joinNicknameCheckService = new JoinNicknameCheckServiceImpl();
		nickCheck = joinNicknameCheckService.nicknameCheck(memberDto);
	
		out.write(nickCheck + "");
	}

}
