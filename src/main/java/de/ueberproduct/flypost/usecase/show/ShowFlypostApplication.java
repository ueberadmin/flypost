package de.ueberproduct.flypost.usecase.show;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;

import de.ueberproduct.flypost.domain.Flypost;

@Controller
public class ShowFlypostApplication {
	
	@Resource
	private MongoOperations mongoOperations;
	
	public Flypost getFlypost(String id) {
		return mongoOperations.findById(id, Flypost.class);
	}
}
