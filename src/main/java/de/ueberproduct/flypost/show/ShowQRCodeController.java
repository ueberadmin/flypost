package de.ueberproduct.flypost.show;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class ShowQRCodeController {
	
	@RequestMapping(value = "/aushaenge/{id}/qr", method = RequestMethod.GET)
	public void showQRCode(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		StringBuffer urlBuffer = request.getRequestURL();
		String url = urlBuffer.substring(0, urlBuffer.length()-"/qr".length());
		response.setContentType("image/png");
		OutputStream out = response.getOutputStream();
		QRCode.from(url).to(ImageType.PNG).writeTo(out);
		out.flush();
	}

}
