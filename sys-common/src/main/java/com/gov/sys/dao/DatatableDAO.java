/**
 * @Project Name: dsat-common
 * @File Name: DatatableDAO.java
 * @Package Name: mo.gov.dsat.dao
 * @Date: 13/04/20166:04:24 PM
 * @Copyright: Copyright (c) 2016 Atos Information Technology HK Limited. All Rights Reserved.
 */
package com.gov.sys.dao;


import com.gov.sys.datatable.DatatableDaoCmd;
import com.gov.sys.datatable.Page;
import org.hibernate.transform.ResultTransformer;

import java.io.Serializable;


public interface DatatableDAO {

    public <T extends Serializable> Page<T> getPage(DaoCmd cmd, Class<?> clazz, Integer maxResult, Integer startFrom);

    public <T extends Serializable> Page<T> getPage(DatatableDaoCmd cmd, Class<?> clazz);

    public <T extends Serializable> Page<T> getPage(DatatableDaoCmd cmd, Class<?> clazz, ResultTransformer transformer);

    public int getCount(DaoCmd cmd);

    /**
     * 不使用hibernate 的分頁，使用 SQL自己進行分頁設置.
     * 
     * @param cmd
     * @param clazz
     * @param maxResult
     *            分頁大小
     * @param startFrom
     *            起始位置
     * @return
     */
    public <T extends Serializable> Page<T> getPageByNativeSQL(DatatableDaoCmd cmd, Class<?> clazz);

    public <T extends Serializable> Page<T> getPageByNativeSQL(DatatableDaoCmd cmd, Class<?> clazz, ResultTransformer transformer);
}
