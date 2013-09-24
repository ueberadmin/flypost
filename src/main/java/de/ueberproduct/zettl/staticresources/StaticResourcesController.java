package de.ueberproduct.zettl.staticresources;

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

import de.ueberproduct.zettl.utils.StreamUtils;

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
	
	@RequestMapping(value="/tinymce/{name}.js")
	public void getTinymceJs(@PathVariable("name") String name, HttpServletResponse response) throws IOException {
		get("de/ueberproduct/flypost/tinymce/"+name+".js", response);
	}
	
	@RequestMapping(value="/tinymce/themes/{theme}/{name}.js")
	public void getTinymceTheme(@PathVariable("theme") String theme, @PathVariable("name") String name, HttpServletResponse response) throws IOException {
		get("de/ueberproduct/flypost/tinymce/themes/"+theme+"/"+name+".js", response);
	}
	
	@RequestMapping(value="/tinymce/plugins/{plugin}/{name}.js")
	public void getTinymcePlugin(@PathVariable("plugin") String plugin, @PathVariable("name") String name, HttpServletResponse response) throws IOException {
		get("de/ueberproduct/flypost/tinymce/plugins/"+plugin+"/"+name+".js", response);
	}
	
	@RequestMapping(value="/tinymce/skins/{skin}/{name}.css")
	public void getTinymceSkin(@PathVariable("skin") String skin, @PathVariable("name") String name, HttpServletResponse response) throws IOException {
		get("de/ueberproduct/flypost/tinymce/skins/"+skin+"/"+name+".css", response);
	}
	
	@RequestMapping(value="/tinymce/skins/{skin}/fonts/{font}.{type}")
	public void getTinymceFont(@PathVariable("skin") String skin, @PathVariable("font") String font, @PathVariable("type") String type, HttpServletResponse response) throws IOException {
		get("de/ueberproduct/flypost/tinymce/skins/"+skin+"/fonts/"+font+"."+type, response);
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
