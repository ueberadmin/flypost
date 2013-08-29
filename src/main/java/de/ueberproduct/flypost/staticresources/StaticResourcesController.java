package de.ueberproduct.flypost.staticresources;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import de.ueberproduct.flypost.utils.StreamUtils;

@Controller
public class StaticResourcesController {
	private final Map<String, byte[]> cache = new ConcurrentHashMap<String, byte[]>();
	
	@RequestMapping(value="/icons/{name}.png")
	public void getPNG(@PathVariable("name") String name, HttpServletResponse response) throws IOException {
		get("de/ueberproduct/flypost/images/"+name+".png", response);
	}
	
	@RequestMapping(value="/css/{name}.css", produces="text/css")
	public void getCss(@PathVariable("name") String name, HttpServletResponse response) throws IOException {
		get("de/ueberproduct/flypost/css/"+name+".css", response);
		response.setContentType("text/css");
	}
	
	@RequestMapping(value="/js/{name}.js")
	public void getJs(@PathVariable("name") String name, HttpServletResponse response) throws IOException {
		get("de/ueberproduct/flypost/js/"+name+".js", response);
	}
	
	
	private void get(String resourceUrl, HttpServletResponse response) throws IOException {
		byte[] bytes = cache.get(resourceUrl);
		if (bytes == null) {
			bytes = load(resourceUrl);
			cache.put(resourceUrl, bytes);
		}
		InputStream in = new ByteArrayInputStream(bytes);
		OutputStream out = response.getOutputStream();
		StreamUtils.copy(in, out);
		in.close();
		out.flush();
	}


	private byte[] load(String url) throws IOException {
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(url);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		StreamUtils.copy(in, out);
		
		return out.toByteArray();
	}
	
	

}
