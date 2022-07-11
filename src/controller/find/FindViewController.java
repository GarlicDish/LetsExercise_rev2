package controller.find;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Find;
import dto.board.FindFile;
import service.face.FindService;
import service.impl.FindServiceImpl;

@WebServlet("/find/view")
public class FindViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 댓글

	private FindService findService = new FindServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/find/view [GET]");

		// 전달 파라미터에 대한 한글 인코딩
		req.setCharacterEncoding("UTF-8");

		// 전달파라미터 얻기 - findno
		Find findno = findService.getFindno(req);
		System.out.println(findno);

		// 상세보기 결과 조회
		Find viewFind = findService.view(findno);

		System.out.println("FindController viewFind - " + viewFind);

		// 조회결과 MODEL값 전달
		req.setAttribute("viewFind", viewFind);

		// 닉네임 전달
		req.setAttribute("writerNick", findService.getNick(viewFind));

		// 첨부파일 정보 조회
		FindFile findFile = findService.viewFile(viewFind);
		System.out.println("Findile findFile:" + findFile);

		// 첨부파일 정보 MODEL값 전달
		req.setAttribute("findFile", findFile);

		// 댓글리스트
		List<Map<String, Object>> findrp = new ArrayList<>();
		findrp = findService.selectReply(req);
		req.setAttribute("findrp", findrp);

		System.out.println(findrp);

		// VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/board/find_view.jsp").forward(req, resp);

	}

}
