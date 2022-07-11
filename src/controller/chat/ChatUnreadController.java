package controller.chat;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.ChatService;
import service.impl.ChatServiceImpl;

@WebServlet("/ChatUnreadServlet")
public class ChatUnreadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ChatService chatService = new ChatServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String userID = req.getParameter("userID");
		if (userID == null || userID.equals("")) {
			resp.getWriter().write("0");
		} else {
			userID = URLDecoder.decode(userID, "UTF-8");
			resp.getWriter().write(chatService.unreadService(userID) + "");
		}

	}
}
