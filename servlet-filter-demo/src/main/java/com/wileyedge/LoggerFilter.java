package com.wileyedge;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/info")
public class LoggerFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("LoggerFilter doFilter begin.");
		
		if (request instanceof HttpServletRequest) {
			HttpServletRequest req = (HttpServletRequest) request;
			MyHttpRequestWrapper requestWrapper = new MyHttpRequestWrapper(req);
	        Long timestamp = new Date().getTime();
	        requestWrapper.addHeader("timestamp", timestamp.toString());
	        chain.doFilter(requestWrapper, response);
		}
		
		System.out.println("LoggerFilter doFilter end.");
	}

}
