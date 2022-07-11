package controller.ask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Ask;
import dto.AskFile;
import service.face.AskCommentWriteService;
import service.face.AskService;
import service.impl.AskCommentWriteServiceImpl;
import service.impl.AskServiceImpl;

@WebServlet("/ask/view")
public class AskViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AskService askService = new AskServiceImpl();
	private AskCommentWriteService askCommentWriteService = new AskCommentWriteServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/ask/view [GET]");

		// 전달파라미터 얻기
		Ask boardno = askService.getBoardno(req);

		// 상세보기 결과 조회
		Ask viewAsk = askService.view(boardno);

		// 조회 결과값 전달
		req.setAttribute("viewAsk", viewAsk);
		System.out.println(viewAsk);
		// 닉네임 전달
		req.setAttribute("writerNick", askService.getNick(viewAsk));

		// 첨부파일 정보 조회
		AskFile askFile = askService.viewFile(viewAsk);

		// 첨부파일 정보값 전달
		req.setAttribute("askFile", askFile);

		// 댓글
		List<Map<String, Object>> lMapSR = new ArrayList<>();

		lMapSR = askCommentWriteService.selectComment(req);
		System.out.println("lMapSR값: " + lMapSR);
		req.setAttribute("lMapSR", lMapSR);

		// 문의글 상세보기 화면으로 이동됨
		req.getRequestDispatcher("/WEB-INF/views/asklist/askview.jsp").forward(req, resp);

	}

}
