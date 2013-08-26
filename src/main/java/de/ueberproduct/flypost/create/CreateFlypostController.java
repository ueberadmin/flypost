package de.ueberproduct.flypost.create;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import de.ueberproduct.flypost.domain.Flypost;

@Controller
public class CreateFlypostController {
	
	@Resource
	private CreateFlypostApplication application;
	
	@RequestMapping(value = "/aushaenge/neu", method = RequestMethod.GET) 
	public ModelAndView showForm() {
		
		return new ModelAndView("create", "command", new Flypost());
	}
	
	@RequestMapping(value = "/aushaenge", method = RequestMethod.POST) 
	public String post(@ModelAttribute(value="command") Flypost flyPost) {
		String id = application.post(flyPost);
		return "redirect:/aushaenge/"+id;
		
	}

}
