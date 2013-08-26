package de.ueberproduct.flypost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet(
//        name = "MyServlet", 
//        urlPatterns = {"/hello"}
//    )
public class HelloWorldServlet extends HttpServlet {
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("index.html");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		ServletOutputStream out = resp.getOutputStream();
		String line = reader.readLine();
		while (line != null) {
			out.write(line.getBytes());
			line = reader.readLine();
		}
		
        out.flush();
        out.close();
        reader.close();
	}

}
