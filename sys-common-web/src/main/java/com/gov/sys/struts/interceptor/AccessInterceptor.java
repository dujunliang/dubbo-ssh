package com.gov.sys.struts.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.Map;

/**
 * Created by dujunliang on 16/11/24.
 */
public class AccessInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = -4291195782860785705L;
    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {

        return actionInvocation.invoke();//go on
    }

}