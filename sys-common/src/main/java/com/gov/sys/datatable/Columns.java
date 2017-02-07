/**
 * @Project Name: dsat-common
 * @File Name: Columns.java
 * @Package Name: mo.gov.dsat.datatables
 * @Date: 13/04/201610:14:04 AM
 * @Copyright: Copyright (c) 2016 Atos Information Technology HK Limited. All Rights Reserved.
 */
package com.gov.sys.datatable;

/**
 * Title: <br/>
 * Description: <br/>
 * Date: 13/04/2016 10:14:04 AM<br/>
 *
 * @author Jim
 * @version 1.0
 * @since JDK1.8
 */
//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "columns", propOrder = { }, namespace = "http://namespace.thats.not.the.same.as.the.generated")
public class Columns {

    private String data;
    private String name;
    private boolean serachable = false;
    private boolean orderable = false;
    private Search search;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSerachable() {
        return serachable;
    }

    public void setSerachable(boolean serachable) {
        this.serachable = serachable;
    }

    public boolean isOrderable() {
        return orderable;
    }

    public void setOrderable(boolean orderable) {
        this.orderable = orderable;
    }

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

}
