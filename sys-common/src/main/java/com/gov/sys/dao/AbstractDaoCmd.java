package com.gov.sys.dao;

import java.util.Collections;
import java.util.Map;

public abstract class AbstractDaoCmd extends DaoCmd {

    protected Map<String, Object> params = Collections.emptyMap();

    public AbstractDaoCmd(String queryKey) {
        super(queryKey);
    }

    public AbstractDaoCmd(String queryKey, Map<String, Object> params) {
        super(queryKey, params);
    }

    @Override
    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

}