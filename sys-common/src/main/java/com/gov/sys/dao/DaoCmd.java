package com.gov.sys.dao;

import java.util.Collections;
import java.util.Map;


public abstract class DaoCmd {

	private String queryKey;
	
	private String orderString;
	
	private String paramString;
	
	private Map<String, Object> statements = Collections.emptyMap();

	protected Object params;

	public DaoCmd(String queryKey) {
		super();
		this.queryKey = queryKey;
	}

	public DaoCmd(String queryKey, Object params) {
		super();
		this.queryKey = queryKey;
		this.params = params;
	}

	public String getQueryKey() {
		return queryKey;
	}

	public String getOrderString() {
		return orderString;
	}

	public void setOrderString(String orderString) {
		this.orderString = orderString;
	}

	public Map<String, Object> getStatements() {
		return statements;
	}

	public void setStatements(Map<String, Object> statements) {
		this.statements = statements;
	}

	public String getParamString() {
		return paramString;
	}

	public void setParamString(String paramString) {
		this.paramString = paramString;
	}

	public void setParams(Object params) {
		this.params = params;
	}

	public Object getParams() {
		return params;
	}

}
