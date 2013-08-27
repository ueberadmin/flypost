package de.ueberproduct.flypost.show;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import de.ueberproduct.flypost.domain.Flypost;

@Controller
public class ShowFlypostController {
	
	@Resource
	private ShowFlypostApplication application;
	
	@RequestMapping("/aushaenge/{id}")
	public ModelAndView show(@PathVariable("id") String id) {
		
		ModelAndView mav = new ModelAndView("flypost");
		Flypost flypost = application.getFlypost(id);
		mav.addObject("flypost", flypost);
		
		String imageId = flypost.getImageId();
		if (imageId != null) {
			mav.addObject("imageUrl", flypost.getId()+"/image");
		}
		
		return mav;
	}

}
