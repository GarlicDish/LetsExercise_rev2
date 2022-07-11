package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCheckFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("LoginFilter - doFilter()");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

//		세션 내에 "login" attribute가 true인지 확인한다. 
		boolean isLogged;

		try {
			isLogged = (boolean) session.getAttribute("login");
		} catch (NullPointerException e) {
			isLogged = false;
		}
		System.out.println("isLogged " + isLogged);
		if (isLogged != true) {
			req.getRequestDispatcher("/WEB-INF/views/main/member/login.jsp").forward(request, response);
		} else {
			chain.doFilter(request, response);
		}

	}

}
