package de.ueberproduct.zettl.usecase.create;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import de.ueberproduct.zettl.domain.Zettl;

@Component
public class CreateFlypostApplication {
	
	@Resource
	private MongoOperations mongoOperations;

	public String create(String username) {
		
		Zettl flypost = new Zettl(username);
		mongoOperations.save(flypost);
		
		
		
		return flypost.getId();
	}
	
	

}
