package com.turalt.openmentor.extractor;

import java.util.Map;

import org.restlet.Request;
import org.restlet.Response;
import org.restlet.routing.Extractor;

import com.turalt.openmentor.dto.Pager;
import com.turalt.openmentor.request.RequestAttributes;

/**
 * Extracts all the parameters for a limit and an offset, and any other case query 
 * values, and drops them into a new CasePager attribute that is added to the list
 * of attributes. 
 */
public class PagerExtractor extends Extractor {
	
	protected int beforeHandle(Request request, Response response) {
		
		extractFromQuery("offset", "offset", true);
		extractFromQuery("limit", "limit", true);
		super.beforeHandle(request, response);
		
		Pager pager = new Pager();
		
		Map<String, Object> attributes = request.getAttributes();
		if (attributes.containsKey("offset")) {
			pager.setOffset(new Integer((String) attributes.get("offset")));
		}
		if (attributes.containsKey("limit")) {
			pager.setLimit(new Integer((String) attributes.get("limit")));
		}

		RequestAttributes.setRequestPager(request, pager);

		return CONTINUE;
	}
}