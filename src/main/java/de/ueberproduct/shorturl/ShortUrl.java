package de.ueberproduct.shorturl;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "short_urls")
public class ShortUrl {
	
	public static final String PROP_URL = "url";
	public static final String PROP_KEY = "key";
	
	public static final String URL_PATTERN = "/z/{"+PROP_KEY+"}";
	
	public static String getFor(String key) {
		return "/z/"+key;
	}
	
	
	
	@Id
	private String key;
	
	@Indexed(unique=false)
	private String url;
	
	ShortUrl() {
	}
	
	public ShortUrl(String key, String url) {
		this.key = key;
		this.url = url;
	}
	
	public String getKey() {
		return key;
	}
	
	public String getUrl() {
		return url;
	}

}
