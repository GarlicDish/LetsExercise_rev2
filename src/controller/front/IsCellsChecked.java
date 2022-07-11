package controller.front;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.member.MemberDto;
import service.face.JoinCellsCheckService;
import service.impl.JoinCellsCheckServiceImpl;


@WebServlet("/check/othercells")
public class IsCellsChecked extends HttpServlet {
	private static final long serialVersionUID = 1L;
       @Override
    	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
       }

       @Override
    	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    	   req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
		
			PrintWriter out = resp.getWriter();
			
			MemberDto memberDto = new MemberDto();


			memberDto.setGender(Integer.parseInt(req.getParameter("gender")));			
			memberDto.setExercise(req.getParameter("exercise"));		
			
			JoinCellsCheckService joinCellsCheckService = new JoinCellsCheckServiceImpl();
			
			int res = joinCellsCheckService.isCellsChekced(memberDto);
			out.write(res+"");
					
//			String gender = req.getParameter("gender");
//			String exercise = req.getParameter("exercise");
			
			
			
			
       }
}