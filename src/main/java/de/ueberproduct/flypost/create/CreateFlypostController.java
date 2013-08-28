package de.ueberproduct.flypost.create;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CreateFlypostController {
	
	@Resource
	private CreateFlypostApplication application;
	
	@RequestMapping(value = "/aushaenge/neu", method = RequestMethod.GET) 
	public String showForm(HttpServletRequest request) {
		String id = application.create();
		return "redirect:/aushaenge/"+id;
	}
	


}
