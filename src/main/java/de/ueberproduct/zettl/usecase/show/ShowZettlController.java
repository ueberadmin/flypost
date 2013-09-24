package de.ueberproduct.zettl.usecase.show;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import de.ueberproduct.zettl.web.SessionData;
import de.ueberproduct.zettl.web.Urls;

@Controller
public class ShowZettlController {
	
	@Resource
	private ShowZettlApplication application;
	
	@Resource
	private SessionData sessionData;
	
//	@RequestMapping(value = Urls.FOR_OVERVIEW, method = RequestMethod.GET) 
//	public ModelAndView show(@PathVariable("id") String id, HttpServletRequest request) {
//		ViewModel vm = application.find(id, sessionData.getTokens());
//		String context = request.getContextPath();
//		ModelAndView mav = new ModelAndView("show/overview");
//		mav.addObject("context", context);
//		mav.addObject("id", id);
//		if (vm.getEditToken() != null) {
//			String editUrl = context+"/bearbeiten/" + id + "?auth="+vm.getEditToken();
//			mav.addObject("editUrl", editUrl);
//		}
//		return mav;
//	}
	
	@RequestMapping(value = "/anschauen/{id}/abreisszettel", method = RequestMethod.GET)
	public ModelAndView printZettl(@PathVariable("id") String id, HttpServletRequest request) {
		return print(id, request, true);
	}
	
	@RequestMapping(value = "/anschauen/{id}/flyer", method = RequestMethod.GET)
	public ModelAndView printFlyer(@PathVariable("id") String id, HttpServletRequest request) {
		return print(id, request, false);
	}
	
	private ModelAndView print(String id, HttpServletRequest request, boolean withSheets) {
		String context = request.getContextPath();
		ModelAndView mav = new ModelAndView("show/flypost");
		ViewModel vm = application.find(id);
		mav.addObject("vm", vm);
		
		mav.addObject("qrCodeUrl", context+"/anschauen/"+id+"/qr");
		
		mav.addObject("context", context);
		mav.addObject("addSheets", withSheets);
		return mav;
	}
	
	@RequestMapping(value = Urls.FOR_OVERVIEW, method = RequestMethod.GET)
	public ModelAndView showMap(@PathVariable("id") String id, HttpServletRequest request) {
		ViewModel vm = application.find(id);
		
		String context = request.getContextPath();
		ModelAndView mav = new ModelAndView("show/map");
		mav.addObject("context", context);
		mav.addObject("vm", vm);

		return mav;
	}
	

}
