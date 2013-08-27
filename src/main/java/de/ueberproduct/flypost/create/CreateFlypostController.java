package de.ueberproduct.flypost.create;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CreateFlypostController {
	
	@Resource
	private CreateFlypostApplication application;
	
	@RequestMapping(value = "/aushaenge/neu", method = RequestMethod.GET) 
	public ModelAndView showForm(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView("create", "command", new ViewModel());
		mav.addObject("context", request.getContextPath());
		return mav;
	}
	
	@RequestMapping(value = "/aushaenge", method = RequestMethod.POST) 
	public String post(@ModelAttribute(value="command") ViewModel viewModel) throws IOException {
		String id = application.post(viewModel);
		return "redirect:/aushaenge/"+id;
		
	}

}
