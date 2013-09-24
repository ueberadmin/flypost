package de.ueberproduct.zettl.usecase.edit;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import de.ueberproduct.zettl.domain.Zettl;

@Component
public class ZettlUpdater {
	
	@Resource
	private MongoOperations mongoOperations;
	
	public void save(Zettl zettl, Set<String> tokens) {
		if (!tokens.contains(zettl.getEditToken())) {
			throw new IllegalArgumentException("User is not allowed to change this zettl.");
		}
		mongoOperations.save(zettl);
	}
	

}
