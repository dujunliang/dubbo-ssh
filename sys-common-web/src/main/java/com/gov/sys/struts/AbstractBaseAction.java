package com.gov.sys.struts;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gov.sys.data.ResultBean;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public abstract class AbstractBaseAction extends AbstractAction implements RequestAware, SessionAware, ApplicationAware {

    private static final long serialVersionUID = 20160216L;

    protected static final String JSON = "json";

    protected static final String REPORT_SOURCE = "reportSource";

    protected static final Gson gson = new GsonBuilder().serializeNulls().create();
    
    private ResultBean resultBean = new ResultBean();
    
    protected Map<String, Object> request;
    protected Map<String, Object> session;
    protected Map<String, Object> application;
    
    protected ResultBean setData(Object data) {
        resultBean.setData(data);
        return resultBean;
    }
    
    protected ResultBean setMsg(String msg) {
        resultBean.setMsg(msg);
        return resultBean;
    }
    
    protected ResultBean setErrorMsg(String errorMsg) {
        resultBean.setErrorMsg(errorMsg);
        return resultBean;
    }

    protected ResultBean setMsg(String msg, String msgType) {
        resultBean.setMsg(msg);
        resultBean.setMsgType(msgType);
        return resultBean;
    }
    
    protected ResultBean setSuccess(boolean success) {
        resultBean.setSuccess(success);
        return resultBean;
    }
    
    protected ResultBean setErrorCode(String errorCode) {
        resultBean.setErrorCode(errorCode);
        return resultBean;
    }

    protected String toJson(Object data) {
        return gson.toJson(data);
    }

    protected void writeJson(Object data) {
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("application/json; charset=utf-8");
            Writer writer = response.getWriter();
            writer.write(toJson(data));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /* ===== Generate Getters and Setters ===== */

    @Override
    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }
    
    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public void setApplication(Map<String, Object> application) {
        this.application = application;
    }
    
    public ResultBean getResultBean() {
        return resultBean;
    }

    public void setResultBean(ResultBean resultBean) {
        this.resultBean = resultBean;
    }

}