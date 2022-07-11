package controller.admin;

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
import service.face.AdminQnaService;
import service.impl.AdminQnaServiceImpl;

@WebServlet("/admin/qna/view")
public class AdminQnaViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminQnaService adminQnaService = new AdminQnaServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/qna/view [GET]");

		// 전달파라미터 얻기 - qnano
		Qna qnano = adminQnaService.getQnano(req);
		System.out.println(qnano);

		// 상세보기 결과 조회
		Qna viewQna = adminQnaService.view(qnano);

		System.out.println("QnaController viewQna - " + viewQna);

		// 조회결과 MODEL값 전달
		req.setAttribute("viewQna", viewQna);

		// 닉네임 전달
		req.setAttribute("writerNick", adminQnaService.getNick(viewQna));

		// 첨부파일 정보 조회
		BoardFile boardFile = adminQnaService.viewFile(viewQna);
		System.out.println("BoardFile boardFile:" + boardFile);

		// 첨부파일 정보 MODEL값 전달
		req.setAttribute("boardFile", boardFile);

		// 댓글리스트
		List<Map<String, Object>> qnarp = new ArrayList<>();
		qnarp = adminQnaService.selectReply(req);
		req.setAttribute("qnarp", qnarp);

		System.out.println("QNA의 댓글 리스트 : " + qnarp);

		// VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/admin/admin_qna_view.jsp").forward(req, resp);

	}

}
