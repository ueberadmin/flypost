package de.ueberproduct.zettl.usecase.edit;

import java.io.IOException;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import de.ueberproduct.zettl.web.Urls;

@Controller
public class ImageUploadController {
	
	@Resource
	private ImageUploadApplication application;

	@RequestMapping(value="/tinymce/plugins/doksoft_uploader/uploader.php", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody UploadResponse upload(HttpServletRequest req) throws IOException {
		MultipartHttpServletRequest request = (MultipartHttpServletRequest)req;
		String imageId = application.storeImage(getOnlyFile(request));
		String context = req.getContextPath();
		String url = Urls.forImage(imageId);
		String result = "<img class=\"doksoft_image_img\" src=\""+context+url+"\" />";
		return new UploadResponse(result);
		
	}
	
	private MultipartFile getOnlyFile(MultipartHttpServletRequest request) {
		for (Iterator<String> i=request.getFileNames(); i.hasNext();) {
			String filename = i.next();
			
			if (i.hasNext()) {
				throw new IllegalArgumentException("Only one file allowed.");
			}
			return request.getFile(filename);
		}
		
		throw new IllegalArgumentException("No file contained.");
	}
	
	static class UploadResponse {
		private String html;
		
		public UploadResponse(String html) {
			this.html = html;
		}
		
		public String getHtml() {
			return html;
		}
	}

}
