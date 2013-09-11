package de.ueberproduct.zettl.utils;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {
	
	/**
	 * Get the request url including the context path.
	 * @param request
	 * @return
	 */
	public static String getRequestContext(HttpServletRequest request) {
		try {
			URL url = new URL(request.getRequestURL().toString());
			
			String protocol = url.getProtocol();
			int port = url.getPort();
			
			StringBuilder sb = new StringBuilder();
			sb.append(protocol).append("://");
			sb.append(request.getServerName());
			
			if (port > 0) {
				sb.append(":").append(port);
			}
			
			sb.append(request.getContextPath());
			
			return sb.toString();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}


}
