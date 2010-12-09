package webmail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailClient {

	private Session session;

	public MailClient(String username, String password,
			String host, String port) {
		session = createSession(username, password, host, port);
	}

	public boolean send(String emailDestination, String subject, String text) throws MessagingException {

	     MimeMessage message = createMime(session);

	     createMessage(emailDestination, subject, text, message);

	     Transport.send(message);
	      
	     return true;
	}

	protected void createMessage(String emailDestination, String subject,
			String text, MimeMessage message) throws MessagingException,
			AddressException {
		message.setSubject(subject);
	      message.setContent(text, "text/plain");
	      message.addRecipient(Message.RecipientType.TO,
	           new InternetAddress(emailDestination));
	      
	}

	
	protected Session createSession(final String username, final String password, final String host, final String port) {
		Properties properties = new Properties();
	    properties.setProperty("mail.transport.protocol", "smtps");
	    properties.setProperty("mail.host", host);
	    properties.setProperty("mail.user", port);

		return Session.getDefaultInstance(properties, new javax.mail.Authenticator() 
		{
			protected PasswordAuthentication getPasswordAuthentication()
			{ 
				return new PasswordAuthentication(username, password);	
			}
		});
	}

	protected MimeMessage createMime(Session localSession) {
		return new MimeMessage(localSession);
	}
}
