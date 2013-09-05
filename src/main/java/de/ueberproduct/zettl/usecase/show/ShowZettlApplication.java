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
		
		String description = zettl.getDescription();
		
		return new ViewModel.Builder().headline(getHeadline(description))
									  .description(description)
									  .contactData(zettl.getEmailAddress())
									  .get();
	}
	
	private String getHeadline(String description) {
		if (description.length() <= MAX_HEADLINE_LENGTH) {
			return description;
		}
		
		String[] words = description.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<words.length && sb.length()< MAX_HEADLINE_LENGTH; i++) {
			String nextWord = words[i];
			int currentLength = sb.length();
			int nextWordLength = nextWord.length();
			if ((currentLength + nextWordLength + 1) <= MAX_HEADLINE_LENGTH) {
				sb.append(nextWord).append(" ");
			}
		}
		
		if (sb.length() > 0) {
			return sb.toString();			
		}
		
		return description.substring(0, MAX_HEADLINE_LENGTH - 3)+"...";
	}

}
