package com.turalt.openmentor.resource;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import java.io.IOException;

import org.junit.After;
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
import com.turalt.openmentor.repository.impl.SimpleCourseInfoRepository;
import com.turalt.openmentor.request.RequestAttributes;
import com.turalt.openmentor.service.CurrentUserService;
import com.turalt.openmentor.test.AbstractShiroTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:**/testContextDatabase.xml" })
public class TutorListResourceTest extends AbstractShiroTest {
	
	private TutorListResource tutorListResource;

	@Autowired
    private SimpleCourseInfoRepository courseInfoRepository;

	@Before
	public void initialize() {
		tutorListResource = new TutorListResource();
		tutorListResource.setRepository(courseInfoRepository);
		Request request = new Request(Method.GET, "http://localhost:9998/services/studies");
		Reference rootReference = new Reference("http://localhost:9998/services");
		request.setRootRef(rootReference);
		tutorListResource.setRequest(request);
	}
	
	private void mockCurrentUserService(boolean administrator, String username) {
		CurrentUserService currentUserService = createMock(CurrentUserService.class);
		expect(currentUserService.isAdministrator()).andStubReturn(administrator);
		expect(currentUserService.getCurrentUserName()).andStubReturn(username);
		replay(currentUserService);
		courseInfoRepository.setCurrentUserService(currentUserService);
	}

	@After
	public void tearDownSubject() {
        clearSubject();
    }

	@Test
	public void testCourseList() throws IOException {
		mockCurrentUserService(false, "stuart");
		
		RequestAttributes.setRequestPager(tutorListResource.getRequest(), new Pager());
		
		Representation result = tutorListResource.getResource();
		Assert.assertEquals("application/json", result.getMediaType().toString());
		
		Gson gson = new Gson();
		JsonObject data = gson.fromJson(result.getText(), JsonObject.class);
		
		Assert.assertEquals( "http://localhost:9998/services", data.get("serviceUrl").getAsString());
		Assert.assertTrue(data.get("people").isJsonArray());
	}

}
