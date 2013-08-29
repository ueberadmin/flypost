package de.ueberproduct.flypost.usecase.create;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import de.ueberproduct.flypost.domain.Flypost;

@Component
public class CreateFlypostApplication {
	
	@Resource
	private MongoOperations mongoOperations;

	public String create(String username) {
		Flypost flypost = new Flypost(username);
		mongoOperations.save(flypost);
		
		return flypost.getId();
	}
	
	

}
