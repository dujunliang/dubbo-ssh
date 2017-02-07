/**
 * @Project Name: dsat-common
 * @File Name: DatatableDTO.java
 * @Package Name: mo.gov.dsat.datatable
 * @Date: 14/04/20166:58:03 PM
 * @Copyright: Copyright (c) 2016 Atos Information Technology HK Limited. All Rights Reserved.
 */
package com.gov.sys.datatable;


import com.gov.sys.data.DTO;

import java.util.Collection;
import java.util.Collections;

/**
 * Title: <br/>
 * Description: <br/>
 * Date: 14/04/2016 6:58:03 PM<br/>
 *
 * @author Jim
 * @version 1.0
 * @since JDK1.8
 */
public class DatatableDTO implements DTO {
    private static final long serialVersionUID = -7099816727008531820L;

    private String error;
    private int draw;
    private int recordsTotal;
    private int recordsFiltered;

    private Collection<? extends DTO> dtos;

    public int getDraw() {
        return draw;
    }

    public DatatableDTO setDraw(int draw) {
        this.draw = draw;
        return this;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public DatatableDTO setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
        return this;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public DatatableDTO setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
        return this;
    }

    public Collection<? extends DTO> getDtos() {
        if(dtos==null){
            dtos = Collections.emptyList();
        }
        return dtos;
    }

    public DatatableDTO setDtos(Collection<? extends DTO> dtos) {
        this.dtos = dtos;
        return this;
    }

    public String getError() {
        return error;
    }

    public DatatableDTO setError(String error) {
        this.error = error;
        return this;
    }
}
