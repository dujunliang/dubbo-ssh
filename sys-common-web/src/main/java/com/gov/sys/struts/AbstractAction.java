package com.gov.sys.struts;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Abstract struts action support getting/setting User
 */
public abstract class AbstractAction extends ActionSupport {


	private static final long serialVersionUID = 20130825L;

	public Object getFromSession(String key) {
		return ActionContext.getContext().getSession().get(key);
	}

	public void putToSession(String key, Object object) {
		ActionContext.getContext().getSession().put(key, object);
	}

	public void putToHttpSession(String key, Object object) {
		HttpServletRequest request =(HttpServletRequest)  ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		request.getSession().setAttribute(key, object);

	}
	public Object getToHttpSession(String key) {
		HttpServletRequest request =(HttpServletRequest)  ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		return request.getSession().getAttribute(key);

	}


}
