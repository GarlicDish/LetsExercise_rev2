package controller.front;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.member.PhotoDto;
import service.face.JoinService;
import service.impl.JoinServiceImpl;

/**
 * Servlet implementation class PhotoUpController
 */
@WebServlet("/file/upload")
public class PhotoUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private JoinService joinService = new JoinServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/file/list [GET]");
		
		List<PhotoDto> list = joinService.list();

		for( PhotoDto photo : list ) {
			System.out.println(photo);
		}
		req.setAttribute("list", list);
		
		req.getRequestDispatcher("/WEB-INF/views/file/list.jsp").forward(req, resp);
		
	}

	
}