package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("EncodingFilter - doFilter()");
		System.out.println("===================================");

		// 요청 데이터 한글 인코딩 처리 : UTF-8
		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html; charset=UTF-8");
		// 요청 정보를 컨트롤러(서블릿) 쪽으로 전달한다 - 필터 체인
		chain.doFilter(request, response);

		System.out.println("===================================");
		System.out.println("---EncodingFilter 끗 ---");

	}

}
