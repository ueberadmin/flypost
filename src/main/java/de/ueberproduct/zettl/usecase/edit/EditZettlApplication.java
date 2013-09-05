package de.ueberproduct.zettl.usecase.edit;

import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import de.ueberproduct.zettl.domain.Zettl;

@Component
public class EditZettlApplication {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private MongoOperations mongoOperations;
	
	Zettl getZettl(String id) {
		Zettl zettl = loadZettl(id);
		return zettl;
	}
	
	void setDescription(String id, String description, Set<String> tokens) {
		Zettl zettl = loadZettl(id);		
		
		zettl.setDescription(description);
		
		save(zettl, tokens);
	}
	

	void setLocation(String id, EditZettlController.ViewModel viewModel, Set<String> tokens) {
		Zettl zettl = loadZettl(id);
		zettl.setEmailAddress(viewModel.getEmailAddress());
		zettl.setStreet(viewModel.getStreet());
		zettl.setPostCode(viewModel.getPostCode());
		zettl.setCity(viewModel.getCity());
		
		try {
			zettl.setRadius(viewModel.getRadius());
		} catch (NumberFormatException e) {
			logger.info("Invalid radius", e);
		}
		
		save(zettl, tokens);
	}


	private void save(Zettl zettl, Set<String> tokens) {
		if (!tokens.contains(zettl.getEditToken())) {
			throw new IllegalArgumentException("User is not allowed to change this zettl.");
		}
		mongoOperations.save(zettl);
	}
	
	

	private Zettl loadZettl(String id) {
		Zettl zettl = mongoOperations.findById(id, Zettl.class);
		if (zettl == null) {
			throw new IllegalArgumentException("No Zettl with id "+id+" found");
		}
		return zettl;
	}



	

}
