package com.turalt.openmentor.request;

import org.restlet.Request;

import com.turalt.openmentor.dto.Pager;

public class RequestAttributes {

	private static final String PAGER_ATTRIBUTE = "pager";

	/**
	 * Helper method to read a pager from a request attribute
	 * @param request
	 * @return the request pager
	 */
	public static Pager getRequestCasePager(Request request) {
		return (Pager) request.getAttributes().get(RequestAttributes.PAGER_ATTRIBUTE);
	}

	/**
	 * Helper method to write a pager into a request attribute
	 * @param request
	 * @param q
	 */
	public static void setRequestCasePager(Request request, Pager q) {
		request.getAttributes().put(RequestAttributes.PAGER_ATTRIBUTE, q);
	}

}
