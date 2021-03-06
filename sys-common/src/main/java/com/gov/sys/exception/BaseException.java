package com.gov.sys.exception;

import java.io.Serializable;

public abstract class BaseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 130629L;

	private String errorCode;

	private Serializable additionalContext;

	public BaseException(String errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public BaseException(String errorCode, Serializable additionalContext) {
		super();
		this.errorCode = errorCode;
		this.additionalContext = additionalContext;
	}

	public BaseException(String errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}

	public BaseException(String errorCode, Serializable additionalContext,
			Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
		this.additionalContext = additionalContext;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public Serializable getAdditionalContext() {
		return additionalContext;
	}

	public void setAdditionalContext(Serializable additionalContext) {
		this.additionalContext = additionalContext;
	}
	

}
