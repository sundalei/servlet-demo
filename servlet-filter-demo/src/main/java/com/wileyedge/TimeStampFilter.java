package com.wileyedge;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/info")
public class TimeStampFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("TimeStampFilter doFilter begin.");
		
		if (request instanceof HttpServletRequest) {
			HttpServletRequest req = (HttpServletRequest) request;
			MyHttpRequestWrapper requestWrapper = new MyHttpRequestWrapper(req);
	        String remote_addr = request.getRemoteAddr();
	        requestWrapper.addHeader("remote_addr", remote_addr);
	        chain.doFilter(requestWrapper, response);
		}
		
		System.out.println("TimeStampFilter doFilter end.");
	}

}
