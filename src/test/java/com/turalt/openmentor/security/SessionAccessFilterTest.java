package com.turalt.openmentor.security;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.turalt.openmentor.test.AbstractShiroTest;

public class SessionAccessFilterTest extends AbstractShiroTest {

	private SessionAccessFilter filter;
	
	@Before
	public void initialize() {
		filter = new SessionAccessFilter();
		
		DefaultWebSessionManager sessions = new DefaultWebSessionManager();
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setSessionManager(sessions);
		setSecurityManager(manager);
	}
	
	@After
	public void tearDown() {
		setSecurityManager(null);
	}

	@Test
	public void testScheme() {
		Assert.assertEquals("session", filter.getAuthcScheme());
	}

	@Test
	public void testSetScheme() {
		filter.setAuthcScheme("awesome");
		Assert.assertEquals("awesome", filter.getAuthcScheme());
	}

	@Test
	public void testApplicationName() {
		Assert.assertEquals("application", filter.getApplicationName());
	}

	@Test 
	public void testOnAccessDeniedWithoutSession() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		
		request.setContextPath("http://localhost:8666/");
		request.setRequestURI("/api/studies");
				
		Assert.assertEquals(false, filter.onAccessDenied(request, response));
		Assert.assertEquals(HttpServletResponse.SC_UNAUTHORIZED, response.getStatus());
		Assert.assertFalse(response.getHeaderNames().contains(SessionAccessFilter.PROMPT_HEADER));
	}
	
	@Test
	public void testOnAccessDeniedPrompt() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		
		request.setContextPath("http://localhost:8666/");
		request.setRequestURI("/api/studies");
		
		filter.setPrompt("Please login using both front paws");
				
		Assert.assertEquals(false, filter.onAccessDenied(request, response));
		Assert.assertEquals("Please login using both front paws", response.getHeaderValue(SessionAccessFilter.PROMPT_HEADER));
	}
}