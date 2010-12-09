package webmail;

import javax.mail.MessagingException;

import org.junit.Ignore;

/**
 * TODO still needed to unit test MailClient class.
 * TODO create a test subclass overriding createSession, createMime, and createMessage
 * @author raul
 *
 */
public class MailClientTest {

	@Ignore
	public void createMailCient() throws MessagingException {
		MailClient mailClient = new MailClient("username", "password", "host", "port");
		
		mailClient.send("destinaion@email.com", "subject of email", "text of email");
		
	}

}
