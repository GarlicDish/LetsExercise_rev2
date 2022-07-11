package controller.work;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDto;
import dto.UploadFile;
import service.face.WorkCommentWriteService;
import service.face.WorkService;
import service.impl.WorkCommentWriteServiceImpl;
import service.impl.WorkServiceImpl;

@WebServlet("/work/view")
public class WorkViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private WorkService workService = new WorkServiceImpl();
	private WorkCommentWriteService workCommentWriteService = new WorkCommentWriteServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/work/view [GET]");

		// 전달파라미터 얻기
		BoardDto boardno = workService.getBoardno(req);
		System.out.println("[WorkViewController] doGet() boardno : " + boardno);

		// 상세보기 결과 조회
		BoardDto viewWork = workService.view(boardno);
		System.out.println("viewWork 값 : " + viewWork);
		// 조회결과 MODEL값 전달
		req.setAttribute("viewWork", viewWork);

		// 닉네임 전달
		req.setAttribute("writerNick", workService.getNick(viewWork));

		// 첨부파일 정보 조회
		UploadFile workFile = workService.viewFile(viewWork);
		// 첨부파일 정보 MODEL값 전달
		req.setAttribute("workFile", workFile);

		// 댓글
		List<Map<String, Object>> lMapSR = new ArrayList<>();
		System.out.println("여기까지는 수행");

		lMapSR = workCommentWriteService.selectComment(req);
		System.out.println("lMapSR값: " + lMapSR);
		req.setAttribute("lMapSR", lMapSR);

		req.getRequestDispatcher("/WEB-INF/views/work/workview.jsp").forward(req, resp);

	}

}
