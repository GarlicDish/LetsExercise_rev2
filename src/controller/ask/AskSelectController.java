package controller.ask;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.AskService;
import service.impl.AskServiceImpl;

@WebServlet("/ask/select")
public class AskSelectController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AskService askService = new AskServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/views/asklist/board.jsp").forward(req, resp);
	}

}
