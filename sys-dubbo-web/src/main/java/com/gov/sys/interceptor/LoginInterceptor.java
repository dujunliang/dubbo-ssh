package com.gov.sys.interceptor;

import com.gov.sys.action.LoginAction;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dujunliang on 16/11/24.
 */
public class LoginInterceptor  extends MethodFilterInterceptor {



    private static final long serialVersionUID = -4291195782860785705L;
    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        //except login action
        Object action = invocation.getAction();
        ActionContext actionContext = invocation.getInvocationContext();

        Map session = actionContext.getSession();

        if (action instanceof LoginAction) {
            return invocation.invoke();
        }
        //check session
        if(session.get("user") == null ){
            return "logout";
        }
        return invocation.invoke();//go on
    }


}
