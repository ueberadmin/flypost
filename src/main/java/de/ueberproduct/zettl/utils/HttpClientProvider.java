package de.ueberproduct.zettl.utils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.springframework.stereotype.Component;

@Component
public class HttpClientProvider {
	
	private HttpClient httpClient;
	
	@PostConstruct
	public void init() {
		ClientConnectionManager cm = new PoolingClientConnectionManager();
		DefaultHttpClient hc = new DefaultHttpClient(cm);
		
		this.httpClient = hc;
	}
	
	@PreDestroy
	public void destroy() {
		httpClient.getConnectionManager().shutdown();
	}
	
	public HttpClient getHttpClient() {
		return httpClient;
	}

}
