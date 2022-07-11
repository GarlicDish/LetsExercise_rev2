package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import service.face.ClubSNSService;
import service.impl.ClubSNSServiceImpl;

@WebFilter("/club/sns/*")
public class ClubFilter implements Filter {

	ClubSNSService clubSNSService = new ClubSNSServiceImpl();

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("ClubFilter - doFilter()");

		// 멤버 확인 플래그
		Boolean isMember = false;

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		System.out.println("[필터] req의 userno 속성 값 : " + session.getAttribute("userno"));
		System.out.println("[필터] req의 clubnumber 속성 값 : " + req.getParameter("clubnumber"));

		if (clubSNSService.checkMember(req)) {
			session.setAttribute("member", true);
		}
		System.out.println("////세션의 member에 저장된 값 : " + session.getAttribute("member"));

		// 멤버 여부 확인
		if (session.getAttribute("member") != null) {
			isMember = true;
		} else {
			isMember = false;
		}

		if (isMember) {
			chain.doFilter(request, response);
		} else {
			// 멤버가 아니므로, 내 동호회 목록으로 이동
			req.getRequestDispatcher("/myClub/list").forward(request, response);
		}

		System.out.println("---ClubFilter 끗---");
	}

}
