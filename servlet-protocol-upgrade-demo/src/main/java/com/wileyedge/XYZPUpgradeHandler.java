package com.wileyedge;

import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.WebConnection;

public class XYZPUpgradeHandler implements HttpUpgradeHandler {

	@Override
	public void init(WebConnection wc) {
		try {
			ServletInputStream input = wc.getInputStream();
			ServletOutputStream output = wc.getOutputStream();
			
			output.write("hello upgrade handler".getBytes());
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void destroy() {

	}

}
