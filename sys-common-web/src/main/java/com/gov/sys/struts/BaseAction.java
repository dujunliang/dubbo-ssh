package com.gov.sys.struts;

import com.opensymphony.xwork2.ModelDriven;

import java.lang.reflect.ParameterizedType;

public class BaseAction<T> extends AbstractBaseAction implements ModelDriven<T> {

    private static final long serialVersionUID = 1L;
    
    protected T model;
    
    @Override
    public T getModel() {
        return model;
    }

    @SuppressWarnings("unchecked")
    public BaseAction() {
        try {
            ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
            Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
            model = clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}