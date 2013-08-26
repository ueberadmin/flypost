package de.ueberproduct.flypost.create;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import de.ueberproduct.flypost.domain.Flypost;

@Component
public class CreateFlypostApplication {
	
	@Resource
	private MongoOperations mongoOperations;
	
	public String post(Flypost flyPost) {
		
		mongoOperations.save(flyPost);
		return flyPost.getId();
	}
	
	

}
