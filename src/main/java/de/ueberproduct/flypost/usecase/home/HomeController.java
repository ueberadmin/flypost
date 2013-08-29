package de.ueberproduct.flypost.usecase.home;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import de.ueberproduct.flypost.web.SessionData;

@SessionAttributes("user")
@Controller
public class HomeController {
	
	@Resource
	private SessionData sessionData;
	
	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public ModelAndView showHome() {
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("username", sessionData.getUsername());
		return mav;
	}

}
