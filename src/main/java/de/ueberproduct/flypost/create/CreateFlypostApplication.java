package de.ueberproduct.flypost.create;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.gridfs.GridFSFile;

import de.ueberproduct.flypost.domain.Flypost;

@Component
public class CreateFlypostApplication {
	
	@Resource
	private MongoOperations mongoOperations;
	
	@Resource
	private GridFsOperations gridFsOperations;
	
	public String post(ViewModel viewModel) throws IOException {
		Flypost.Builder flyPostBuilder = new Flypost.Builder().headline(viewModel.getHeadline())
															  .description(viewModel.getDescription())
															  .contactData(viewModel.getContactData());
		
		
		MultipartFile image = viewModel.getImage();
		if (image != null) {
			GridFSFile gridFSFile = gridFsOperations.store(image.getInputStream(), image.getName(), image.getContentType());
			flyPostBuilder.imageId(gridFSFile.getId().toString());
		}
		
		Flypost flyPost = flyPostBuilder.get();
		mongoOperations.save(flyPost);
		return flyPost.getId();
	}
	
	

}
