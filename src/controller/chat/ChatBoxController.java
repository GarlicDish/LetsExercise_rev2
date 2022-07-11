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


@WebServlet("/chat/box")
public class ChatBoxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ChatService chatService = new ChatServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 채팅방 페이지 보여주기
		req.getRequestDispatcher("/WEB-INF/views/chat/box.jsp").forward(req, resp);
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String userID = req.getParameter("userID");
		if(userID == null || userID.equals("")) {
			resp.getWriter().write("");
		} else {
			try {
				userID = URLDecoder.decode(userID, "UTF-8");
				System.out.println("[POST실행]");
				resp.getWriter().write(chatService.getBox(userID));
			} catch (Exception e) {
				resp.getWriter().write("");
			}
		}
			
	}
	
}
