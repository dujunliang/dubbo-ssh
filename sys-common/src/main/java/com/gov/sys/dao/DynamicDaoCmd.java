package com.gov.sys.dao;

import java.util.Map;

/**
 * Abstract Command Object representing dynamic query
 * 
 * @author CPPPAA
 */
public abstract class DynamicDaoCmd extends AbstractDaoCmd {

	public DynamicDaoCmd(String query) {
		super(query);
	}

	public DynamicDaoCmd(String query, Map<String, Object> params) {
		super(query, params);
	}

	public abstract String getQueryType();
}
