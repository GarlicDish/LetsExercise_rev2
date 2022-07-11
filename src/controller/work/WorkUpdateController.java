package controller.work;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDto;
import dto.UploadFile;
import service.face.WorkService;
import service.impl.WorkServiceImpl;

@WebServlet("/work/update")
public class WorkUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private WorkService workService = new WorkServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 전달파라미터 얻기
		BoardDto boardnumber = workService.getBoardno(req);

		// 상세보기 결과 조회
		BoardDto updateWork = workService.view(boardnumber);

		// 조회결과 MODEL값 전달
		req.setAttribute("updateWork", updateWork);

		// 닉네임 전달
		req.setAttribute("writerNick", workService.getNick(updateWork));

		// 첨부파일 정보 조회
		UploadFile workFile = workService.viewFile(updateWork);

		// 첨부파일 정보 MODEL값 전달
		req.setAttribute("workFile", workFile);

		req.getRequestDispatcher("/WEB-INF/views/work/workupdate.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		workService.update(req);

		resp.sendRedirect("/work/boardlist");

	}
}
