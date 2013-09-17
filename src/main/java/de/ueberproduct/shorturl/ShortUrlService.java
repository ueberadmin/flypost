package de.ueberproduct.shorturl;

import java.util.Random;

import javax.annotation.Resource;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class ShortUrlService {
	private static final String CHARS = "abcdefghijklmnopqrstuvwxyz1234567890";
	
	@Resource
	private MongoOperations mongoOperations;
	
	private final Random random = new Random();
	
	
	public String getShortUrl(String originalUrl, String requestContext) {
		ShortUrl shortUrl = mongoOperations.findOne(new Query(Criteria.where(ShortUrl.PROP_URL).is(originalUrl)), ShortUrl.class);
		if (shortUrl == null) {
			shortUrl = createShortUrl(originalUrl);
		}
		StringBuilder sb = new StringBuilder(requestContext);
		
		sb.append(ShortUrl.getFor(shortUrl.getKey()));
		
		return sb.toString();
	}
	

	public String findOriginalUrl(String key) {
		ShortUrl shortUrl = mongoOperations.findById(key, ShortUrl.class);
		return shortUrl.getUrl();
	}
	
	private ShortUrl createShortUrl(String originalUrl) {
		while (true) {
			try {
				ShortUrl shortUrl = new ShortUrl(createShortUrl(), originalUrl);
				mongoOperations.insert(shortUrl);
				
				return shortUrl;
			} catch (DuplicateKeyException e) {
				// ignore
			}
		}
	}
	
	private String createShortUrl() {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<5; i++) {
			sb.append(CHARS.charAt(random.nextInt(CHARS.length())));
		}
		return sb.toString();
	}


}
