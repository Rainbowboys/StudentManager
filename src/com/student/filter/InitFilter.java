package com.student.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InitFilter implements Filter {
	private Set<String> paths = new HashSet<>();

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String path = request.getServletPath();
		System.out.println(path);
		String username=(String) request.getSession().getAttribute("studentBean");
		if(username!=null){
			chain.doFilter(request, response);
		}else {
			if (path.startsWith("/static") || paths.contains(path)) {
				chain.doFilter(request, response);
			} else {
				response.sendRedirect(request.getContextPath()+"/student/studentlogin.jsp?status=2");
			}
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("begin-----filter");
		paths.add("/student/studentlogin.jsp");
		paths.add("/student/StudentServlet");
	}

}
