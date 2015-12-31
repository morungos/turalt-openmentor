package com.turalt.openmentor.restlets;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.restlet.Component;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.security.Authenticator;
import org.restlet.security.User;

public class CustomSpringSessionAuthenticator extends Authenticator {

	public CustomSpringSessionAuthenticator(Component component) {
		super(component.getContext());
	}
	
    public CustomSpringSessionAuthenticator(Restlet parent) {
        super(parent.getContext());
    }

	@Override
	protected boolean authenticate(Request request, Response response) {
		
		Subject currentUser = SecurityUtils.getSubject();
		
		if (currentUser == null) {
			throw new IllegalStateException("Invalid subject: SecurityUtils.getSubject() returned null");
		}
		
		
		// If we have no principals, we're not authenticated
		PrincipalCollection principals = currentUser.getPrincipals();
		if (principals == null) {
			return false;
		}

		Object principal = principals.getPrimaryPrincipal(); 
		if (principal != null) {
			User user = new User(principal.toString());
			request.getClientInfo().setUser(user);
			
			ChallengeResponse challenge = new ChallengeResponse(ChallengeScheme.CUSTOM);
			challenge.setIdentifier(user.getIdentifier());
			request.setChallengeResponse(challenge);
			return true;
			
		} else {
			return false;
		}
	}
}