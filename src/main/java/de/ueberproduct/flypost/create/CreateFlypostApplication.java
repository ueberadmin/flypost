package de.ueberproduct.flypost.create;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import de.ueberproduct.flypost.domain.Flypost;

@Component
public class CreateFlypostApplication {
	
	@Resource
	private MongoOperations mongoOperations;

	public String create() {
		Flypost flypost = new Flypost.Builder().get();
		mongoOperations.save(flypost);
		
		return flypost.getId();
	}
	
	

}
