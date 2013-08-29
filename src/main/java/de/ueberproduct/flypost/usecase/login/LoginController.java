package de.ueberproduct.flypost.usecase.login;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import de.ueberproduct.flypost.domain.User;
import de.ueberproduct.flypost.web.SessionData;

@Controller
public class LoginController {
	
	@Resource
	private SessionData sessionData;
	
	@Resource
	private LoginApplication application;
	
	@RequestMapping(value = "/einloggen", method = RequestMethod.GET) 
	public ModelAndView showForm(@RequestParam(value="url", required=false) String url) {
		ModelAndView mav = new ModelAndView("login");
		if (url != null) {
			mav.addObject("url", url);
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/ausloggen", method = RequestMethod.GET)
	public String logout(@RequestParam("url") String url) {
		sessionData.setUsername(null);
		if (url != null) {
			return "redirect:"+url;
		}
		return "redirect:start";
	}
	
	@RequestMapping(value = "/einloggen", method = RequestMethod.POST)
	public String login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam(value="url", required=false) String url) {
		User user = application.findUser(username, password);
		if (user == null) {
			return "login";
		}
		
		sessionData.setUsername(username);
		if (url != null) {
			return "redirect:"+url;
		}
		return "redirect:start";
	}
	
	@RequestMapping(value = "/registrieren", method = RequestMethod.POST)
	public String register(@RequestParam("username") String username, @RequestParam("password1") String password1, @RequestParam("password2") String password2, @RequestParam(value="url", required=false) String url) {
		if (username.isEmpty()) {
			throw new IllegalArgumentException("Username is empty.");
		}
		
		if (password1.isEmpty()) {
			throw new IllegalArgumentException("Password1 is empty.");
		}
		
		if (!password1.equals(password2)) {
			throw new IllegalArgumentException("Password1 not equal password2");
		}
		
		
		User user = application.register(username, password1);
		sessionData.setUsername(user.getUsername());
		
		if (url != null) {
			return "redirect:"+url;
		}
		return "redirect:start";
	}

}
