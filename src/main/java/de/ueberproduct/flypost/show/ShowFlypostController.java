package de.ueberproduct.flypost.show;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import de.ueberproduct.flypost.domain.Flypost;
import de.ueberproduct.flypost.edit.ViewModel;

@Controller
public class ShowFlypostController {
	
	@Resource
	private ShowFlypostApplication application;
	
	
	@RequestMapping(value = "/aushaenge/{id}", method = RequestMethod.GET) 
	public ModelAndView get(@PathVariable("id") String id, HttpServletRequest request) {
				
		Flypost flypost = application.getFlypost(id);
		ViewModel viewModel = new ViewModel();
		viewModel.setHeadline(flypost.getHeadline());
		viewModel.setDescription(flypost.getDescription());
		viewModel.setContactData(flypost.getContactData());
		
		ModelAndView mav = new ModelAndView("edit", "command", viewModel);
		mav.addObject("context", request.getContextPath());
		String imageId = flypost.getImageId();
		if (imageId != null) {
			mav.addObject("imageUrl", flypost.getId()+"/image");
		}
		mav.addObject("qrCodeUrl", flypost.getId()+"/qr");
		
		return mav;
	}

}
