package com.turalt.openmentor.extractor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.Method;
import org.restlet.data.Reference;

import com.turalt.openmentor.dto.Pager;
import com.turalt.openmentor.request.RequestAttributes;

public class PagerExtractorTest {

	private class TraceRestlet extends Restlet {
		// Does snothing, but prevents warning shouts
	}

	private PagerExtractor extractor;

	@Before
	public void initialize() {
		
		Restlet mock = new TraceRestlet();
		extractor = new PagerExtractor();
		extractor.setNext(mock);
	}

	@Test
	public void testBasicExtraction() {
		
		Reference reference = new Reference();
		reference.addQueryParameter("offset", "5");
		reference.addQueryParameter("limit", "3");
		Request request = new Request(Method.GET, reference);
		Response response = new Response(request);
		extractor.handle(request, response);
		
		Pager pager = RequestAttributes.getRequestPager(request);
		assertNotNull(pager);
		
		assertNotNull(pager.getLimit());
		assertEquals(3, pager.getLimit().intValue());
		
		assertNotNull(pager.getOffset());
		assertEquals(5, pager.getOffset().intValue());
	}

	@Test
	public void testNullExtraction() {
		
		Reference reference = new Reference();
		Request request = new Request(Method.GET, reference);
		Response response = new Response(request);
		extractor.handle(request, response);
		
		Pager pager = RequestAttributes.getRequestPager(request);
		assertNotNull(pager);
		
		assertNull(pager.getLimit());
		assertNull(pager.getOffset());
	}

}
