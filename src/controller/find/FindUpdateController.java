package controller.find;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Find;
import dto.board.FindFile;
import service.face.FindService;
import service.impl.FindServiceImpl;

@WebServlet("/find/update")
public class FindUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FindService findService = new FindServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 전달 파라미터에 대한 한글 인코딩
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		// 전달파라미터 얻기 - findno
		Find findno = findService.getFindno(req);
		System.out.println(findno);
		// boardno만 출력

		// 상세보기 결과 조회
		Find updateFind = findService.view(findno);

		// 조회결과 MODEL값 전달
		req.setAttribute("updateFind", updateFind);

		System.out.println(updateFind);
		// board 정보 전체 출력

		// 닉네임 전달
		req.setAttribute("writerNick", findService.getNick(updateFind));

		// 첨부파일 정보 조회
		FindFile findFile = findService.viewFile(updateFind);

		// 첨부파일 정보 MODEL값 전달
		req.setAttribute("findFile", findFile);
		System.out.println(findFile);

		// VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/board/find_update.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 전달 파라미터에 대한 한글 인코딩
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		findService.update(req);

		resp.sendRedirect("/find/list");

	}
}
