/*
 * Created on Jan 14, 2016
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.gov.sys.ws;

import java.util.Date;

/**
 * Interface for tracking object creation and modification 
 */
public interface UpdateTrackable {

	public String getCreateUser();
	
	public Date getCreateDate();
	
	public String getLastUpdUser();
	
	public Date getLastUpdDate();
	
}
