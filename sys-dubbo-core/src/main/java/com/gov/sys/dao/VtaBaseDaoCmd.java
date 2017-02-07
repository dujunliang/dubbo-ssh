package com.gov.sys.dao;

import java.util.HashMap;

public class VtaBaseDaoCmd extends DaoCmd {

    public VtaBaseDaoCmd(String queryKey) {
        this(queryKey, new HashMap<String, Object>());
    }

    public VtaBaseDaoCmd(String queryKey, Object params) {
        super(queryKey, params);
    }

}