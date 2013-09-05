package de.ueberproduct.zettl.usecase.show;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;

import de.ueberproduct.zettl.domain.Zettl;

@Controller
public class ShowFlypostApplication {
	
	@Resource
	private MongoOperations mongoOperations;
	
	public Zettl getFlypost(String id) {
		return mongoOperations.findById(id, Zettl.class);
	}
}
