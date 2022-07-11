package controller.work;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDto;
import service.face.WorkService;
import service.impl.WorkServiceImpl;


@WebServlet("/work/delete")
public class WorkDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private WorkService workService = new WorkServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		BoardDto work = workService.getBoardno(req);
		
		workService.delete(work);
		
		//게시글 삭제 수행 후 목록으로 리다이렉트
		resp.sendRedirect("/work/list");	

	}
	
}
