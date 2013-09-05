package de.ueberproduct.zettl.usecase.show;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShowZettlController {
	
	@Resource
	private ShowZettlApplication application;
	
	@RequestMapping(value = "/anschauen/{id}", method = RequestMethod.GET) 
	public ModelAndView show(@PathVariable("id") String id, HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView("show/overview");
		mav.addObject("context", request.getContextPath());
		mav.addObject("id", id);
		return mav;
	}
	
	@RequestMapping(value = "/anschauen/{id}/abreisszettel", method = RequestMethod.GET)
	public ModelAndView printFlypost(@PathVariable("id") String id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("show/flypost");
		ViewModel vm = application.find(id);
		mav.addObject("vm", vm);
		mav.addObject("context", request.getContextPath());
		return mav;
	}
	

}
