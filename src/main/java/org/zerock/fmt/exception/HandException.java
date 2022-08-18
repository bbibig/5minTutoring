package org.zerock.fmt.exception;



// 영속성 계층의 모든 종류의 DAO 구현에서 발생하는 예외를 의미
public class HandException extends Exception {
	private static final long serialVersionUID = 1L;
	

	public HandException(String message) {
		super(message);
	} // constructor
	
	public HandException(Exception e) {
		super(e);
	} // constructor
	
} // end class
