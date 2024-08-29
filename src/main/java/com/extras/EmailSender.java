package com.extras;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
 
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

 
//import org.springframework.mail.MailException;
//import org.springframework.mail.MailSender;
//import org.springframework.mail.SimpleMailMessage;

 
import javax.mail.Message;
 
import javax.mail.Session;
 
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
	public static boolean sendOtp(String email, String otp) {
		
		//Variable for G-mail
		String host="smtp.gmail.com";
		
		//get the system properties
		Properties properties = System.getProperties();
		 
		//setting important information to properties object
		
		//host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port","465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");
		properties.put("authentication", "plain");
		properties.put("domain", "gmail.com");
		
		//Step 1: to get the session object..
		Session session=javax.mail.Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {	
				return new PasswordAuthentication("skshukla093@gmail.com","wnby mcic utxz qdmk");
			}});
		
		session.setDebug(true);
		
		//Step 2 : compose the message [text,multi media]
		MimeMessage m = new MimeMessage(session);
		
		try {
			
		//adding recipient to message
		m.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
		
		//adding subject to message
		//m.setSubject(data.getName()+" "+data.getLastName());
		m.setSubject("Verify your account");
		
		String content="<h2>Your OTP is "+otp+"</h2>\r\n";
		
		//adding text to message
		 m.setContent(content, "text/html");
		 m.setSentDate(new Date());
		
		//Step 3 : send the message using Transport class
		Transport.send(m);
		
		return true;
		
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
			
	}
	
	public static boolean sendPasswordResetLink(String email, String link) {
		
		//Variable for G-mail
		String host="smtp.gmail.com";
		
		//get the system properties
		Properties properties = System.getProperties();
		 
		//setting important information to properties object
		
		//host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port","465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");
		properties.put("authentication", "plain");
		properties.put("domain", "gmail.com");
		
		//Step 1: to get the session object..
		Session session=Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {	
				return new PasswordAuthentication("skshukla093@gmail.com","wnby mcic utxz qdmk");
			}});
		
		session.setDebug(true);
		
		//Step 2 : compose the message [text,multi media]
		MimeMessage m = new MimeMessage(session);
		
		try {
			
		//adding recipient to message
		m.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
		
		//adding subject to message
		//m.setSubject(data.getName()+" "+data.getLastName());
		m.setSubject("Forgotten your password");
		
		String content = "<h2>Click on the following link to reset your password: <a href=\"" + link + "\">Reset Password</a></h2>\r\n";
		
		//adding text to message
		 m.setContent(content, "text/html");
		 m.setSentDate(new Date());
		
		//Step 3 : send the message using Transport class
		Transport.send(m);
		
		return true;
		
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
			
	}
}
