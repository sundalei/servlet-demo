package com.wileyedge;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/asyncioservlet"}, asyncSupported = true)
public class AsyncIOServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		final AsyncContext acontext = req.startAsync();
		final ServletInputStream input = req.getInputStream();
		
		input.setReadListener(new ReadListener() {
			
			byte[] buffer = new byte[4 * 1024];
			StringBuilder sbuilder = new StringBuilder();
			
			@Override
			public void onError(Throwable t) {
				
			}
			
			@Override
			public void onDataAvailable() throws IOException {
				
				try {
					do {
						int length = input.read(buffer);
						sbuilder.append(new String(buffer, 0, length));
					} while(input.isReady());
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			
			@Override
			public void onAllDataRead() throws IOException {
				try {
					acontext.getResponse().getWriter().write(sbuilder.toString());
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				acontext.complete();
			}
		});
	}
}
