package de.ueberproduct.flypost.images;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import de.ueberproduct.flypost.utils.StreamUtils;

@Controller
public class PNGController {
	private final Map<String, byte[]> cache = new ConcurrentHashMap<String, byte[]>();
	
	@RequestMapping("/icons/{name}.png")
	public void getPNG(@PathVariable("name") String name, HttpServletResponse response) throws IOException {
		byte[] bytes = cache.get(name);
		if (bytes == null) {
			bytes = loadPNG(name);
			cache.put(name, bytes);
		}
		InputStream in = new ByteArrayInputStream(bytes);
		OutputStream out = response.getOutputStream();
		StreamUtils.copy(in, out);
		
		in.close();
		out.flush();
	}


	private byte[] loadPNG(String name) throws IOException {
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("de/ueberproduct/flypost/images/"+name+".png");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		StreamUtils.copy(in, out);
		
		return out.toByteArray();
	}
	
	

}
