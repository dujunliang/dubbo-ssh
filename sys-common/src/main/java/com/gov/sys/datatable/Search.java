/**
 * @Project Name: dsat-common
 * @File Name: Search.java
 * @Package Name: mo.gov.dsat.datatables
 * @Date: 13/04/201611:31:57 AM
 * @Copyright: Copyright (c) 2016 Atos Information Technology HK Limited. All Rights Reserved.
 */
package com.gov.sys.datatable;

public class Search {

    private String value;
    private boolean regex = false;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isRegex() {
        return regex;
    }

    public void setRegex(boolean regex) {
        this.regex = regex;
    }
}
