package controller.ask;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Ask;
import dto.AskFile;
import service.face.AskService;
import service.impl.AskServiceImpl;

@WebServlet("/ask/update")
public class AskUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AskService askService = new AskServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// boardno 전달 파라미터 얻기
		Ask boardno = askService.getBoardno(req);

		// 상세보기 결과 조회
		Ask updateAsk = askService.view(boardno);

		// 조회 결과값 전달
		req.setAttribute("updateAsk", updateAsk);

		// 닉네임 전달
		req.setAttribute("writerNick", askService.getNick(updateAsk));

		// 첨부파일 정보 조회
		AskFile askFile = askService.viewFile(updateAsk);

		// 첨부파일 정보값 전달
		req.setAttribute("askFile", askFile);

		// 문의글 수정 화면으로 이동됨
		req.getRequestDispatcher("/WEB-INF/views/asklist/askupdate.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 수정하는 메소드 실행
		askService.update(req);

		// 삭제 후 문의내역 화면으로 이동됨
		resp.sendRedirect("/ask/list");

	}
}
