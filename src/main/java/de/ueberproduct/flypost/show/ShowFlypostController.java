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
	public ModelAndView edit(@PathVariable("id") String id, HttpServletRequest request) {
		ModelAndView mav = getInternal(id, "edit", request);
		mav.addObject("printUrl", getBaseUrl(id, request.getContextPath())+"/druck");
		mav.addObject("borderStyle", "solid");
		mav.addObject("forEdit", true);
		
		return mav;
	}
	
	@RequestMapping(value = "/aushaenge/{id}/druck", method = RequestMethod.GET) 
	public ModelAndView print(@PathVariable("id") String id, HttpServletRequest request) {
		ModelAndView mav = getInternal(id, "print", request);
		mav.addObject("borderStyle", "none");
		mav.addObject("forEdit", false);
		return mav;
	}


	private ModelAndView getInternal(String id, String template, HttpServletRequest request) {
		String contextPath = request.getContextPath();
		
		Flypost flypost = application.getFlypost(id);
		ViewModel viewModel = new ViewModel();
		viewModel.setHeadline(flypost.getHeadline());
		viewModel.setDescription(flypost.getDescription());
		viewModel.setContactData(flypost.getContactData());
		
		ModelAndView mav = new ModelAndView(template, "command", viewModel);
		mav.addObject("context", request.getContextPath());
		String imageId = flypost.getImageId();
		if (imageId != null) {
			mav.addObject("imageUrl", getBaseUrl(id, contextPath)+"/image");
		}
		mav.addObject("qrCodeUrl", getBaseUrl(id, contextPath)+"/qr");
		
		return mav;
	}
	
	private String getBaseUrl(String id, String contextPath) {
		return contextPath+"/aushaenge/"+id;
	}

}
