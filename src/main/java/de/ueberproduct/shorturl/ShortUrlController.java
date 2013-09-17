package de.ueberproduct.shorturl;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ShortUrlController {

	@Resource
	private ShortUrlService shortUrlService;
	
	
	@RequestMapping(value = ShortUrl.URL_PATTERN, method = RequestMethod.GET)
	public String resolve(@PathVariable(ShortUrl.PROP_KEY) String key) {
		
		String originalUrl = shortUrlService.findOriginalUrl(key);
		return "redirect:"+originalUrl;
		
	}

}
