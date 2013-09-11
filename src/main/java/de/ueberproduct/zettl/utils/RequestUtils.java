package de.ueberproduct.zettl.utils;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {
	private final static String HTTP = "http";
	private final static String HTTPS = "https";
	
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
			
			if (!isDefaultPort(protocol, port)) {
				sb.append(":").append(port);
			}
			
			sb.append(request.getContextPath());
			
			return sb.toString();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	private static boolean isDefaultPort(String protocol, int port) {
		if (protocol.equals(HTTP) && port == 80) {
			return true;
		}
		
		if (protocol.equals(HTTPS) && port == 443) {
			return true;
		}
		
		return false;
	}

}
