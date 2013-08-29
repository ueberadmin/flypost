package de.ueberproduct.flypost.usecase.show;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import de.ueberproduct.flypost.domain.Flypost;
import de.ueberproduct.flypost.usecase.edit.ViewModel;
import de.ueberproduct.flypost.web.SessionData;

@Controller
public class ShowFlypostController {
	
	@Resource
	private SessionData sessionData;
	
	@Resource
	private ShowFlypostApplication application;
	
	
	@RequestMapping(value = "/aushaenge/{id}", method = RequestMethod.GET) 
	public ModelAndView show(@PathVariable("id") String id, HttpServletRequest request) {
		String username = sessionData.getUsername();
		Flypost flypost = application.getFlypost(id);
		
		boolean isLoggedIn = username != null;
		boolean isEditable = isLoggedIn && username.equals(flypost.getOwner());
		
		
		String template = "edit"; //isEditable ? "edit" : "show";
		String borderStyle = isEditable ? "solid" : "none";
		
		ModelAndView mav = getInternal(flypost, template, request);
		mav.addObject("printUrl", getBaseUrl(id, request.getContextPath())+"/druck");
		mav.addObject("borderStyle", borderStyle);
		mav.addObject("forEdit", isEditable);
		mav.addObject("showSheets", true);
		mav.addObject("isLoggedIn", isLoggedIn);
		mav.addObject("currentUrl", request.getRequestURL().toString());
		
		return mav;
	}
	
	@RequestMapping(value = "/aushaenge/{id}/druck", method = RequestMethod.GET) 
	public ModelAndView print(@PathVariable("id") String id, HttpServletRequest request) {
		Flypost flypost = application.getFlypost(id);
		ModelAndView mav = getInternal(flypost, "print", request);
		mav.addObject("borderStyle", "none");
		mav.addObject("forEdit", false);
		mav.addObject("showSheets", true);
		return mav;
	}


	private ModelAndView getInternal(Flypost flypost, String template, HttpServletRequest request) {
		String contextPath = request.getContextPath();
		String id = flypost.getId();
		
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
