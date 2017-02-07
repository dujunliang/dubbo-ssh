/**
 * @Project Name: dsat-common
 * @File Name: Order.java
 * @Package Name: mo.gov.dsat.datatables
 * @Date: 13/04/201611:21:39 AM
 * @Copyright: Copyright (c) 2016 Atos Information Technology HK Limited. All Rights Reserved.
 */
package com.gov.sys.datatable;


public class Order {

    private Integer column;
    private String dir;

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
}
