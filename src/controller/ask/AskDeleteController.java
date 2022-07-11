package controller.ask;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Ask;
import service.face.AskService;
import service.impl.AskServiceImpl;



@WebServlet("/ask/delete")
public class AskDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AskService askService = new AskServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//boardno 가져옴
		Ask ask = askService.getBoardno(req);
		
		//삭제하는 메소드 실행
		askService.delete(ask);
		
		//삭제 후 문의내역 화면으로 이동됨
		resp.sendRedirect("/ask/list");	

	}
	
}
