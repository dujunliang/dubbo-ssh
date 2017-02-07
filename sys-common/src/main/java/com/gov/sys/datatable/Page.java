/**
 * @Project Name: dsat-common
 * @File Name: Page.java
 * @Package Name: mo.gov.dsat.datatable
 * @Date: 13/04/201611:06:24 PM
 * @Copyright: Copyright (c) 2016 Atos Information Technology HK Limited. All Rights Reserved.
 */
package com.gov.sys.datatable;

import java.io.Serializable;
import java.util.List;


public class Page<T extends Serializable> {

    private List<T> entities;
    
    private int count;

    public List<T> getEntities() {
        return entities;
    }

    public void setEntities(List<T> entities) {
        this.entities = entities;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
}
