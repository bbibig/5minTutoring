package org.zerock.fmt.exception;

public class TutorException extends Exception {
	private static final long serialVersionUID = 1L;
	

	public TutorException(String message) {
		super(message);
	} // constructor 1
	
	public TutorException(Exception e) {
		super(e);
	} // constructor 2
	
} // end class
