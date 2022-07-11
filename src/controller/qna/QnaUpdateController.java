package controller.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.BoardFile;
import dto.board.Qna;
import service.face.QnaService;
import service.impl.QnaServiceImpl;

@WebServlet("/qna/update")
public class QnaUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private QnaService qnaService = new QnaServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 전달파라미터 얻기 - qnano
		Qna qnano = qnaService.getQnano(req);
		System.out.println(qnano);
		// boardno만 출력

		// 상세보기 결과 조회
		Qna updateQna = qnaService.view(qnano);

		// 조회결과 MODEL값 전달
		req.setAttribute("updateQna", updateQna);

		System.out.println(updateQna);
		// board 정보 전체 출력

		// 닉네임 전달
		req.setAttribute("writerNick", qnaService.getNick(updateQna));

		// 첨부파일 정보 조회
		BoardFile boardFile = qnaService.viewFile(updateQna);

		// 첨부파일 정보 MODEL값 전달
		req.setAttribute("boardFile", boardFile);
		System.out.println(boardFile);

		// VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/board/qna_update.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		qnaService.update(req);

		resp.sendRedirect("/qna/list");

	}
}
