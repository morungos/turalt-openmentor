package com.turalt.openmentor.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.turalt.openmentor.service.CurrentUserService;

public class ShiroCurrentUserService implements CurrentUserService {

	@Override
	public String getCurrentUserName() {
		Subject currentUser = SecurityUtils.getSubject();
		PrincipalCollection principals = currentUser.getPrincipals();
		return principals.getPrimaryPrincipal().toString();
	}

	@Override
	public Boolean isAdministrator() {
		return false;
	}

}
