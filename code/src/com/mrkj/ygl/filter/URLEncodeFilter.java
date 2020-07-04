package com.mrkj.ygl.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns={"/*"},filterName="URLEncodeFilter")
public class URLEncodeFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		fc.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig fc) throws ServletException {
		
	}

}
