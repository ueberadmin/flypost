package de.ueberproduct.zettl.usecase.edit;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import de.ueberproduct.zettl.domain.Geodata;
import de.ueberproduct.zettl.domain.Zettl;
import de.ueberproduct.zettl.utils.RequestUtils;
import de.ueberproduct.zettl.web.SessionData;
import de.ueberproduct.zettl.web.Urls;

@Controller
public class EditZettlController {
	
	@Resource
	private SessionData sessionData;
	
	@Resource
	private EditZettlApplication application;
	
	
	@RequestMapping(value = Urls.FOR_EDIT, method = RequestMethod.GET) 
	public String edit(@PathVariable("id") String id) {
		return "redirect:"+id+"/beschreibung";
	}
	
	@RequestMapping(value = "/bearbeiten/{id}/beschreibung", method = RequestMethod.GET) 
	public ModelAndView showDescription(@PathVariable("id") String id, HttpServletRequest request) {
		Zettl zettl = application.getZettl(id);
		ViewModel viewModel = new ViewModel();
		viewModel.setDescription(zettl.getDescription());
		
		String context = request.getContextPath();
		ModelAndView mav = new ModelAndView("edit/description", "command", viewModel);
		String imageId = zettl.getImageId();
		if (imageId != null) {
			mav.addObject("imageUrl", context+"/aushaenge/"+id+"/image");
		}
		mav.addObject("context", context);
		return mav;
	}
	
	@RequestMapping(value = "/bearbeiten/{id}/beschreibung", method = RequestMethod.POST)
	public String changeDescription(@PathVariable("id") String id, @ModelAttribute("command") ViewModel viewModel, HttpServletRequest request) throws IOException {
		application.setDescription(id, viewModel.getDescription(), sessionData.getTokens());
		return "redirect:/bearbeiten/"+id+"/ort";
	}
	
	@RequestMapping(value = "/bearbeiten/{id}/ort", method = RequestMethod.GET)
	public ModelAndView showLocation(@PathVariable("id") String id, HttpServletRequest request) {
		ViewModel viewModel = new ViewModel();
		Zettl zettl = application.getZettl(id);
		viewModel.setEmailAddress(zettl.getEmailAddress());
		viewModel.setStreet(zettl.getStreet());
		viewModel.setPostCode(zettl.getPostCode());
		viewModel.setCity(zettl.getCity());
		viewModel.setRadius(zettl.getRadius());
		
		ModelAndView mav = new ModelAndView("edit/location", "command", viewModel);
		mav.addObject("context", request.getContextPath());
		return mav;
	}
	
	@RequestMapping(value = "/bearbeiten/{id}/ort", method = RequestMethod.POST)
	public ModelAndView changeLocation(@PathVariable("id") String id, @ModelAttribute("command") ViewModel viewModel, HttpServletRequest request) throws IOException, MessagingException {
		String context = request.getContextPath();
		List<Geodata> possibleGeodatas = application.setLocationAndEmailAddress(id, viewModel, sessionData.getTokens(), RequestUtils.getRequestContext(request));
		if (possibleGeodatas.size() <= 1) {
			return new ModelAndView(new RedirectView(context + "/anschauen/" + id));
		}
		ModelAndView mav = new ModelAndView("edit/geodatas");
		mav.addObject("geodatas", possibleGeodatas);
		mav.addObject("context", context);
		mav.addObject("postUrl", context + "/bearbeiten/"+id+"/osm_id");
		return mav;
	}
	
	@RequestMapping(value = "/bearbeiten/{id}/osm_id", method = RequestMethod.POST)
	public String setLocationByOsmId(@PathVariable("id") String id, @RequestParam("osm_id") String osmId) throws IOException {
		application.setLocationByOsmId(id, osmId, sessionData.getTokens());
		
		return "redirect:/anschauen/"+id;
	}
	
	
	
	
	
	public static class ViewModel {
		private String description;
		private String emailAddress;
		private String street;
		private String postCode;
		private String city;
		private String radius;
		
		public String getEmailAddress() {
			return emailAddress;
		}

		public void setEmailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
		}

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public String getPostCode() {
			return postCode;
		}

		public void setPostCode(String postCode) {
			this.postCode = postCode;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getRadius() {
			return radius;
		}

		public void setRadius(String radius) {
			this.radius = radius;
		}

		public String getDescription() {
			return description;
		}
		
		public void setDescription(String description) {
			this.description = description;
		}
	}

}
