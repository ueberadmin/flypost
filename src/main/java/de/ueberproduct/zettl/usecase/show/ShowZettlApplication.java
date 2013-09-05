package de.ueberproduct.zettl.usecase.show;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import de.ueberproduct.zettl.domain.Zettl;

@Component
public class ShowZettlApplication {
	
	private final static int MAX_HEADLINE_LENGTH = 20;
	
	@Resource
	private MongoOperations mongoOperations;
	
	public ViewModel find(String id) {
		Zettl zettl = mongoOperations.findById(id, Zettl.class);
		
		String description = zettl.getDescription().trim();
		HeadlineAndDescription headlineAndDescription = getHeadline(description);
		return new ViewModel.Builder().headline(headlineAndDescription.getHeadline())
									  .description(headlineAndDescription.getDescription())
									  .contactData(zettl.getEmailAddress())
									  .imageId(zettl.getImageId())
									  .get();
	}
	
	private HeadlineAndDescription getHeadline(String description) {
		if (description.length() <= MAX_HEADLINE_LENGTH) {
			return new HeadlineAndDescription(description, "");
		}
		
		int firstLineBreak = description.indexOf("\n");
		if (firstLineBreak <= MAX_HEADLINE_LENGTH) {
			String headline = description.substring(0, firstLineBreak);
			String newDescription = description.length()>firstLineBreak?description.substring(firstLineBreak+1).trim():"";
			return new HeadlineAndDescription(headline, newDescription);
		}
		
		String[] words = description.split(" ");
		StringBuilder headline = new StringBuilder();
		StringBuilder newDescription = new StringBuilder();
		for (int i=0; i<words.length; i++) {
			String nextWord = words[i];
			int currentLength = headline.length();
			int nextWordLength = nextWord.length();
			if ((currentLength + nextWordLength + 1) <= MAX_HEADLINE_LENGTH) {
				headline.append(nextWord).append(" ");
			} else {
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
			this.headline = headline;
			this.description = description;
		}
		
		public String getDescription() {
			return description;
		}
		
		public String getHeadline() {
			return headline;
		}
	}

}
