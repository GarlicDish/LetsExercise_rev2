package controller.chat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.impl.UserDAO;
import service.face.ChatIDService;
import service.impl.ChatIDServiceImpl;

//아이디 체크 반환 서블릿
@WebServlet("/UserRegisterCheckServlet")
public class UserRegisterCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ChatIDService chatIDService = new ChatIDServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String userID = req.getParameter("userID");
		if(userID == null || userID.equals("")) resp.getWriter().write("-1");
		resp.getWriter().write(chatIDService.RegisterCheckService(userID) + "");
	}
}
