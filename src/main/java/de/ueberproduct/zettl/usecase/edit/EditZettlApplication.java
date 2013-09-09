package de.ueberproduct.zettl.usecase.edit;

import java.io.IOException;
import java.util.Set;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.gridfs.GridFSFile;

import de.ueberproduct.zettl.domain.Geodata;
import de.ueberproduct.zettl.domain.Zettl;
import de.ueberproduct.zettl.osm.GeodataService;

@Component
public class EditZettlApplication {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private MongoOperations mongoOperations;
	
	@Resource
	private GridFsOperations gridFsOperations;
	
	@Resource
	private GeodataService geodataService;
	
	Zettl getZettl(String id) {
		Zettl zettl = loadZettl(id);
		return zettl;
	}
	
	void setDescriptionAndImage(String id, String description, MultipartFile image, Set<String> tokens) throws IOException {
		Zettl zettl = loadZettl(id);		
		
		zettl.setDescription(description);
		
		if (image != null && !image.isEmpty()) {
			deleteOldImage(zettl);
			GridFSFile gridFSFile = gridFsOperations.store(image.getInputStream(), image.getName(), image.getContentType());
			zettl.setImageId(gridFSFile.getId().toString());
		}
		
		save(zettl, tokens);
	}
	

	void setLocation(String id, EditZettlController.ViewModel viewModel, Set<String> tokens) throws IOException {
		String street = viewModel.getStreet();
		String postcode = viewModel.getPostCode();
		String city = viewModel.getCity();
		
		Zettl zettl = loadZettl(id);
		zettl.setEmailAddress(viewModel.getEmailAddress());
		zettl.setStreet(street);
		zettl.setPostCode(postcode);
		zettl.setCity(city);
		
		try {
			zettl.setRadius(viewModel.getRadius());
		} catch (NumberFormatException e) {
			logger.info("Invalid radius", e);
		}
		
		Geodata geodata = geodataService.find(street, postcode, city);
		zettl.setGeodata(geodata);
		
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


	private void deleteOldImage(Zettl flypost) {
		String imageId = flypost.getImageId();
		if (imageId != null) {
			gridFsOperations.delete(new Query(Criteria.where("_id").is(new ObjectId(flypost.getImageId()))));
			flypost.setImageId(null);
		}
	}
	
	
}
