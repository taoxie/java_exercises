package webmail;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * servlet that gets http requests (post or get) with email address, subject and text
 * and sends an email using the JavaMail API. Configuration are retrieved from
 * configuration file
 * @author raul
 *
 */
public class EmailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	final static private String USERNAME = "username";
	final static private String PASSWORD = "password";
	private static final String HOST = "host";
	private static final String PORT = "port";
	
	MailClient mailClient;


	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		mailClient = new MailClient(config.getInitParameter(USERNAME), config.getInitParameter(PASSWORD),
				                    config.getInitParameter(HOST), config.getInitParameter(PORT));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String email = req.getParameter("emailAddressParam");
		String subject = req.getParameter("emailSubjectParam");
		String text = req.getParameter("emailTextParam");
		
		boolean result;
		try {
			result = mailClient.send(email, subject, text);
		} catch (MessagingException e) {
			result = false;
		} 
		
		if (result) {
			resp.setStatus(resp.SC_OK);
		} else {
			// HTTP 503 => service unavailable
			resp.sendError(503);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

}
