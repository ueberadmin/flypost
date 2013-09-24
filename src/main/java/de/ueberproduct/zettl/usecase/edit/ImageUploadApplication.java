package de.ueberproduct.zettl.usecase.edit;

import java.io.IOException;
import java.util.Set;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.gridfs.GridFSFile;

import de.ueberproduct.zettl.domain.Zettl;

@Component
public class ImageUploadApplication {
	
	@Resource
	private MongoOperations mongoOperations;
	
	@Resource
	private GridFsOperations gridFsOperations;
	
	@Resource
	private ZettlUpdater zettlUpdater;
	
	String storeImage(MultipartFile image) throws IOException {
		GridFSFile gridFSFile = gridFsOperations.store(image.getInputStream(), image.getName(), image.getContentType());
		return gridFSFile.getId().toString();
	}
	
	void setImage(String id, MultipartFile image, Set<String> tokens) throws IOException {
		Zettl zettl = mongoOperations.findById(id, Zettl.class);
		deleteOldImage(zettl, tokens);
		GridFSFile gridFSFile = gridFsOperations.store(image.getInputStream(), image.getName(), image.getContentType());
		zettl.setImageId(gridFSFile.getId().toString());
		zettlUpdater.save(zettl, tokens);
	}
	

	private void deleteOldImage(Zettl zettl, Set<String> tokens) {
		if (!tokens.contains(zettl.getEditToken())) {
			throw new IllegalArgumentException("User is not allowed to change this zettl.");
		}
		String imageId = zettl.getImageId();
		if (imageId != null) {
			gridFsOperations.delete(new Query(Criteria.where("_id").is(new ObjectId(zettl.getImageId()))));
			zettl.setImageId(null);
		}
	}

}
