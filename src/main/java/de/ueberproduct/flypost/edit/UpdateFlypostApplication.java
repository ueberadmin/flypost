package de.ueberproduct.flypost.edit;

import java.io.IOException;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.gridfs.GridFSFile;

import de.ueberproduct.flypost.domain.Flypost;

@Component
public class UpdateFlypostApplication {
	
	@Resource
	private MongoOperations mongoOperations;
	
	@Resource
	private GridFsOperations gridFsOperations;

	
	public void update(String id, ViewModel viewModel) throws IOException {
		Flypost flypost = mongoOperations.findById(id, Flypost.class);
		flypost.setHeadline(viewModel.getHeadline());
		flypost.setDescription(viewModel.getDescription());
		flypost.setContactData(viewModel.getContactData());
		MultipartFile image = viewModel.getImage();
		if (image != null && !image.isEmpty()) {
			deleteOldImage(flypost);
			GridFSFile gridFSFile = gridFsOperations.store(image.getInputStream(), image.getName(), image.getContentType());
			flypost.setImageId(gridFSFile.getId().toString());
		}
		
		mongoOperations.save(flypost);
	}


	private void deleteOldImage(Flypost flypost) {
		String imageId = flypost.getImageId();
		if (imageId != null) {
			gridFsOperations.delete(new Query(Criteria.where("_id").is(new ObjectId(flypost.getImageId()))));
			flypost.setImageId(null);
		}
	}
	
	
//	
//	public String post(ViewModel viewModel) throws IOException {
//		Flypost.Builder flyPostBuilder = new Flypost.Builder().headline(viewModel.getHeadline())
//															  .description(viewModel.getDescription())
//															  .contactData(viewModel.getContactData());
//		
//		
//		MultipartFile image = viewModel.getImage();
//		if (image != null) {
//			GridFSFile gridFSFile = gridFsOperations.store(image.getInputStream(), image.getName(), image.getContentType());
//			flyPostBuilder.imageId(gridFSFile.getId().toString());
//		}
//		
//		Flypost flyPost = flyPostBuilder.get();
//		mongoOperations.save(flyPost);
//		return flyPost.getId();
//	}

}
