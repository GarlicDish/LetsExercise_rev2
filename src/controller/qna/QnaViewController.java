package controller.qna;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.BoardFile;
import dto.board.Qna;
import service.face.QnaService;
import service.impl.QnaServiceImpl;

@WebServlet("/qna/view")
public class QnaViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private QnaService qnaService = new QnaServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/qna/view [GET]");

		// 전달파라미터 얻기 - qnano
		Qna qnano = qnaService.getQnano(req);
		System.out.println(qnano);

		// 상세보기 결과 조회
		Qna viewQna = qnaService.view(qnano);

		System.out.println("QnaController viewQna - " + viewQna);

		// 조회결과 MODEL값 전달
		req.setAttribute("viewQna", viewQna);

		// 닉네임 전달
		req.setAttribute("writerNick", qnaService.getNick(viewQna));

		// 첨부파일 정보 조회
		BoardFile boardFile = qnaService.viewFile(viewQna);
		System.out.println("BoardFile boardFile:" + boardFile);

		// 첨부파일 정보 MODEL값 전달
		req.setAttribute("boardFile", boardFile);

		// 댓글리스트
		List<Map<String, Object>> qnarp = new ArrayList<>();
		qnarp = qnaService.selectReply(req);
		req.setAttribute("qnarp", qnarp);

		System.out.println("QNA의 댓글 리스트 : " + qnarp);

		// VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/board/qna_view.jsp").forward(req, resp);

	}

}
