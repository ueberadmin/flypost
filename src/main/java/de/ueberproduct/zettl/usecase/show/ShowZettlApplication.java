package de.ueberproduct.zettl.usecase.show;

import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import de.ueberproduct.zettl.domain.Geodata;
import de.ueberproduct.zettl.domain.Zettl;

@Component
public class ShowZettlApplication {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final static int MAX_HEADLINE_LENGTH = 20;
	
	@Resource
	private MongoOperations mongoOperations;
	
	public ViewModel find(String id) {
		return find(id, null);
	}
	
	public ViewModel find(String id, Set<String> tokens) {
		Zettl zettl = mongoOperations.findById(id, Zettl.class);
		Geodata geodata = zettl.getGeodata();
		if (geodata == null) {
			// Berlin
			geodata = new Geodata(52.5170365, 13.3888599);
		}
		
		Integer radius = 500;
		if (zettl.getRadius() != null) {
			try {
				radius = Integer.parseInt(zettl.getRadius());
			} catch (NumberFormatException e) {
				logger.debug("Radius {} of zettl {} is not a number", zettl.getRadius(), zettl.getId());
			}
		}
		
		String description = zettl.getDescription().trim();
		HeadlineAndDescription headlineAndDescription = getHeadline(description);
		ViewModel.Builder builder = new ViewModel.Builder().headline(headlineAndDescription.getHeadline())
									  					   .description(headlineAndDescription.getDescription())
									  					   .contactData(zettl.getEmailAddress())
									  					   .imageId(zettl.getImageId())
									  					   .street(zettl.getStreet())
									  					   .postcode(zettl.getPostCode())
									  					   .city(zettl.getCity())
									  					   .lat(Double.toString(geodata.getLatitude()))
									  					   .lon(Double.toString(geodata.getLongitude()))
									  					   .radius(radius);
		
		String token = zettl.getEditToken();
		if (tokens != null && token != null && tokens.contains(token)) {
			builder.editToken(token);
		}
		
		
		
		
		
		return builder.get();
	}
	
	private HeadlineAndDescription getHeadline(String description) {
		if (description.length() <= MAX_HEADLINE_LENGTH) {
			return new HeadlineAndDescription(description, "");
		}
		
		int firstLineBreak = description.indexOf("\n");
		if (firstLineBreak > 0 && firstLineBreak <= MAX_HEADLINE_LENGTH) {
			String headline = description.substring(0, firstLineBreak);
			String newDescription = description.length()>firstLineBreak?description.substring(firstLineBreak+1).trim():"";
			return new HeadlineAndDescription(headline, newDescription);
		}
		
		String[] words = description.split(" ");
		StringBuilder headline = new StringBuilder();
		StringBuilder newDescription = new StringBuilder();
		
		boolean headlineDone = false;
		for (int i=0; i<words.length; i++) {
			String nextWord = words[i];
			int currentLength = headline.length();
			int nextWordLength = nextWord.length();
			if (!headlineDone && ((currentLength + nextWordLength + 1) <= MAX_HEADLINE_LENGTH)) {
				headline.append(nextWord).append(" ");
			} else {
				headlineDone = true;
				newDescription.append(nextWord).append(" ");
			}
		}
		
		if (headline.length() > 0) {
			return new HeadlineAndDescription(headline.toString(), newDescription.toString());	
		}
		
		return new HeadlineAndDescription(description.substring(0, MAX_HEADLINE_LENGTH - 3)+"...", description);
	}
	
	
	
	private static class HeadlineAndDescription {
		private final String headline;
		private final String description;
		
		public HeadlineAndDescription(String headline, String description) {
			this.headline = trim(headline);
			this.description = description;
		}
		
		public String getDescription() {
			return description;
		}
		
		public String getHeadline() {
			return headline;
		}
		
		private String trim(String toTrim) {
			return toTrim.replaceAll("\n", " ").trim();
		}
	}

}
