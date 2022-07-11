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

@WebServlet("/qna/delete")
public class QnaDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//BoardService 객체 생성
	private QnaService qnaService = new QnaServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Qna qna = qnaService.getQnano(req);
		
		qnaService.delete(qna);
		
		//목록으로 리다이렉트
		resp.sendRedirect("/qna/list");	

	}
	
}
