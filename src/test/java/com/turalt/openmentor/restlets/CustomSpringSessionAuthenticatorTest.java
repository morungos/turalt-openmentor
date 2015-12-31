package com.turalt.openmentor.restlets;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.capture;

import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.easymock.Capture;
import org.easymock.CaptureType;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ClientInfo;

import com.turalt.openmentor.test.AbstractShiroTest;

public class CustomSpringSessionAuthenticatorTest extends AbstractShiroTest {

	private CustomSpringSessionAuthenticator authenticator;

	private Request request;
	private Response response;
	private Context context;
	private Subject subjectUnderTest;

	@Before
	public void initialize() {
		
		Restlet parent = createMock(Restlet.class);
		context = createMock(Context.class);
		expect(context.getDefaultEnroler()).andStubReturn(null);
		replay(context);
		
		expect(parent.getContext()).andStubReturn(context);
		replay(parent);
		
        request = createMock(Request.class);
        replay(request);
        response = createMock(Response.class);
        replay(response);
        
        subjectUnderTest = createMock(Subject.class);
        setSubject(subjectUnderTest);
        
		authenticator = new CustomSpringSessionAuthenticator(parent);
	}
	
	@Test
	public void testContext() {
		Assert.assertEquals(context, authenticator.getContext());
	}

	@Test
	public void testAuthenticateWithoutPrincipal() {
        expect(subjectUnderTest.getPrincipals()).andStubReturn(new SimplePrincipalCollection());
		replay(subjectUnderTest);
		
		Assert.assertFalse( authenticator.authenticate(request, response) );
		
		verify(subjectUnderTest);
	}

	@Test
	public void testAuthenticateWithPrincipal() {
		String principal = "admin";
        expect(subjectUnderTest.getPrincipals()).andStubReturn(new SimplePrincipalCollection(principal, "mock"));
		replay(subjectUnderTest);
		
		ClientInfo clientInfo = new ClientInfo();
		
		Capture<ChallengeResponse> capturedArgument = EasyMock.newCapture(CaptureType.FIRST);
		
		reset(request);
		expect(request.getClientInfo()).andStubReturn(clientInfo);
		request.setChallengeResponse(capture(capturedArgument));
		expectLastCall();		
		replay(request);
		
		Assert.assertTrue( authenticator.authenticate(request, response) );
		Assert.assertEquals("admin", capturedArgument.getValue().getIdentifier());
		
		verify(subjectUnderTest);
		verify(request);
	}
}