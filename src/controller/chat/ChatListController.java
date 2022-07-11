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

@WebServlet("/ChatListServlet")
public class ChatListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ChatService chatService = new ChatServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 전달된 파라미터를 UTF-8로 인코딩
		req.setCharacterEncoding("UTF-8");

		// HTML이 UTF-8형식이라는 것을 브라우저에 알림
		resp.setContentType("text/html;charset=UTF-8");

		// 전달된 파라미터를 새로운 변수에 저장
		String fromID = req.getParameter("fromID");
		String toID = req.getParameter("toID");
		String listType = req.getParameter("listType");

		// 각 변수에 값이 null이거나 공백일때 공백 출력
		if (fromID == null || fromID.equals("") || toID == null || toID.equals("") || listType == null
				|| listType.equals("")) {
			resp.getWriter().write("");
		} else if (listType.equals("ten")) {
			System.out.println("채팅 내용 10개 불러오기[POST]");
			resp.getWriter()
					.write(chatService.getTen(URLDecoder.decode(fromID, "UTF-8"), URLDecoder.decode(toID, "UTF-8")));
		} else {
			try {
				System.out.println("채팅 내용 불러오기[POST]");
				resp.getWriter().write(chatService.getID(URLDecoder.decode(fromID, "UTF-8"),
						URLDecoder.decode(toID, "UTF-8"), URLDecoder.decode(listType, "UTF-8")));
			} catch (Exception e) {
				resp.getWriter().write("");
			}
		}
	}
}
