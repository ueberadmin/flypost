package de.ueberproduct.zettl.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	public void sentEditToken(String emailAddress, String editUrl) throws MessagingException {

	      // Sender's email ID needs to be mentioned
	      String from = "service@betabox.eu";

	      // Get system properties
	      Properties properties = System.getProperties();
	      Session session = Session.getDefaultInstance(properties);
	      
	      properties.setProperty("mail.smtp.host", "mail.betabox.eu");
	      properties.setProperty("mail.smtp.port", "587");
	      properties.setProperty("mail.smtp.user", "wp1185519-777");
	      properties.setProperty("mail.smtp.starttls.enable", "true");
	      properties.setProperty("mail.smtp.auth", "true");	      
	      session.setPasswordAuthentication(new URLName("smtp://wp1185519-777@mail.betabox.eu"), new PasswordAuthentication("wp1185519-777", "betabox2013"));

	      
		 // Create a default MimeMessage object.
		 MimeMessage message = new MimeMessage(session);
		
		 // Set From: header field of the header.
		 message.setFrom(new InternetAddress(from));
		
		 // Set To: header field of the header.
		 message.addRecipient(Message.RecipientType.TO,
		                          new InternetAddress(emailAddress));
		
		 // Set Subject: header field
		 message.setSubject("Dein Zettl");
		
		 // Now set the actual message
		 message.setText("Benutze " + editUrl + " um Deinen Zettl zu bearbeiten.");
		
		 // Send message
		 Transport.send(message);	      
	}
}
