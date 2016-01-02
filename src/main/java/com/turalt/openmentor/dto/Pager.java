package com.turalt.openmentor.dto;

/**
 * A simple class, which we can pass in to select a set of cases for data querying. 
 * 
 * @author stuartw
 */
public class Pager {
	
	/**
	 * Allows the ordre direction to be specified.
	 */
	public enum OrderDirection {
		ASC, DESC
	}
	
	/**
	 * When selecting a set of cases by order, for paging, specifies the start offset.
	 */
	private Integer offset = null;

	/**
	 * When selecting a set of cases by order, for paging, specifies the case limit.
	 */
	private Integer limit = null;
	
	/**
	 * @return the offset
	 */
	public Integer getOffset() {
		return offset;
	}

	/**
	 * @return the existence of the offset
	 */
	public boolean hasOffset() {
		return offset != null;
	}

	/**
	 * @param offset the offset to set
	 */
	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	/**
	 * @return the limit
	 */
	public Integer getLimit() {
		return limit;
	}

	/**
	 * @return the existence of the limit
	 */
	public boolean hasLimit() {
		return limit != null;
	}

	/**
	 * @param limit the limit to set
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}

}