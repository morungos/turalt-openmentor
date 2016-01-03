package com.turalt.openmentor.resource;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.restlet.Request;
import org.restlet.data.Method;
import org.restlet.data.Reference;
import org.restlet.representation.Representation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.turalt.openmentor.dto.Pager;
import com.turalt.openmentor.repository.impl.SimpleUserRepository;
import com.turalt.openmentor.request.RequestAttributes;
import com.turalt.openmentor.test.AbstractShiroTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:**/testContextDatabase.xml" })
public class UserListResourceTest extends AbstractShiroTest {
	
	private UserListResource userListResource;

	@Autowired
    private SimpleUserRepository userRepository;

	@Before
	public void initialize() {
		userListResource = new UserListResource();
		userListResource.setRepository(userRepository);
		Request request = new Request(Method.GET, "http://localhost:9998/services/studies");
		Reference rootReference = new Reference("http://localhost:9998/services");
		request.setRootRef(rootReference);
		userListResource.setRequest(request);
	}
	
	@Test
	public void testUserList() throws IOException {
		RequestAttributes.setRequestPager(userListResource.getRequest(), new Pager());
		
		Representation result = userListResource.getResource();
		Assert.assertEquals("application/json", result.getMediaType().toString());
		
		Gson gson = new Gson();
		JsonObject data = gson.fromJson(result.getText(), JsonObject.class);
		
		Assert.assertEquals( "http://localhost:9998/services", data.get("serviceUrl").getAsString());
		Assert.assertTrue(data.get("users").isJsonArray());
	}

}
