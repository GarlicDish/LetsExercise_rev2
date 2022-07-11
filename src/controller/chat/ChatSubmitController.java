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

@WebServlet("/ChatSubmitServlet")
public class ChatSubmitController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ChatService chatService = new ChatServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("ChatSubmit실행[POST]");
		
		// 전달된 파라미터를 UTF-8로 인코딩
		req.setCharacterEncoding("UTF-8");
		
		// HTML이 UTF-8형식이라는 것을 브라우저에 알림
		resp.setContentType("text/html;charset=UTF-8");
		
		// 전달된 파라미터를 새로운 변수에 저장 
		String fromID = req.getParameter("fromID");
		String toID = req.getParameter("toID");
		String chatContent = req.getParameter("chatContent");
		
		// 변수에 저장된 값들이 NULL이거나 공백일시 스트림에 0 출력
		if(fromID == null || fromID.equals("") 
			|| toID == null || toID.equals("")
			|| chatContent == null || chatContent.equals("")) {
			resp.getWriter().write("0");
		// 변수에 값이 저장되어 있으면 submitService메소드 출력
		} else {
			fromID = URLDecoder.decode(fromID, "UTF-8");
			toID = URLDecoder.decode(toID, "UTF-8");
			chatContent = URLDecoder.decode(chatContent, "UTF-8");
//			resp.getWriter().write(new ChatDAO().submit(fromID, toID, chatContent) + "");
			resp.getWriter().write(chatService.submitService(fromID, toID, chatContent) + "");
		}
	}
}
