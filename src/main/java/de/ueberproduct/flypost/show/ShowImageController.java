package de.ueberproduct.flypost.show;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mongodb.gridfs.GridFSDBFile;

import de.ueberproduct.flypost.domain.Flypost;
import de.ueberproduct.flypost.utils.StreamUtils;

@Controller
public class ShowImageController {
	
	@Resource
	private MongoOperations mongoOperations;
	
	@Resource
	private GridFsOperations gridFsOperations;
	
	@RequestMapping("/aushaenge/{id}/image")
	public void showImage(@PathVariable("id") String flypostId, HttpServletResponse response) throws IOException {
		Flypost flypost = mongoOperations.findById(flypostId, Flypost.class);
		
		GridFSDBFile file = gridFsOperations.findOne(new Query(Criteria.where("_id").is(new ObjectId(flypost.getImageId()))));
		
//		file.writeTo("/home/andre/Desktop/new-testing.png");
		response.setContentType(file.getContentType());
		InputStream in = file.getInputStream();
		OutputStream out = response.getOutputStream();
		StreamUtils.copy(in, out);
		
		in.close();
		out.flush();
		
	}
}
