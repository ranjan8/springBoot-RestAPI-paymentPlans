package com.bottomline.exhandling;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -392184248522472685L;

	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}