package controller.find;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Find;
import service.face.FindService;
import service.impl.FindServiceImpl;

@WebServlet("/find/delete")
public class FindDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// BoardService 객체 생성
	private FindService findService = new FindServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Find find = findService.getFindno(req);

		findService.delete(find);

		// 목록으로 리다이렉트
		resp.sendRedirect("/find/list");

	}

}
