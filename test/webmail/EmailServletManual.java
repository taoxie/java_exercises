package webmail;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

public class EmailServletManual {

	private static final String EMAIL_PARAM = "emailAddressParam";
	private static final String SUBJECT_PARAM = "emailSubjectParam";
	private static final String TEXT_PARAM = "emailTextParam";

	
	private static final String EMAIL = "destination@emailadress.com";
	private static final String SUBJECT = "subject of email";
	private static final String TEXT = "text of email";
	
	HttpServletRequest request = mock(HttpServletRequest.class);
	HttpServletResponse response = mock(HttpServletResponse.class);
	MailClient mailClient = mock(MailClient.class);

	@Test
	public void sendCorrectRequest() throws Exception {
		
		when(mailClient.send(anyString(), anyString(), anyString())).thenReturn(true);
		
		EmailServlet servlet = new TestEmailServlet(mailClient);
		
		servlet.doPost(request, response);

		verify(request).getParameter(EMAIL_PARAM);
		verify(request).getParameter(SUBJECT_PARAM);
		verify(request).getParameter(TEXT_PARAM);
		
		verify(response).setStatus(200);
		
	}
	
	@Test
	public void sendRequestWithMissingOrWrongMailParameters() throws Exception {
		when(mailClient.send(anyString(), anyString(), anyString())).thenThrow(new MessagingException());
		
		EmailServlet servlet = new TestEmailServlet(mailClient);
		
		servlet.doPost(request, response);
		
		verify(request).getParameter(EMAIL_PARAM);
		verify(request).getParameter(SUBJECT_PARAM);
		verify(request).getParameter(TEXT_PARAM);
		
		verify(response).sendError(503);
		
	}
	
	class TestEmailServlet extends EmailServlet {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public TestEmailServlet(MailClient mailClient) {
			this.mailClient = mailClient;
		}
		
	}

}
