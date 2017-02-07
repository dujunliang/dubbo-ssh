/**
 * @Project Name: dsat-common
 * @File Name: AbstractDataTablesDTO.java
 * @Package Name: mo.gov.dsat.datatables
 * @Date: 13/04/201610:19:54 AM
 * @Copyright: Copyright (c) 2016 Atos Information Technology HK Limited. All Rights Reserved.
 */
package com.gov.sys.datatable;



import com.gov.sys.data.DTO;
import com.gov.sys.util.JsonUtils;
import org.apache.commons.lang.StringUtils;

import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

/**
 * Title: <br/>
 * Description: <br/>
 * Date: 13/04/2016 10:19:54 AM<br/>
 *
 * @author Jim
 * @version 1.0
 * @since JDK1.8
 */
public abstract class AbstractDatatableDTO implements DTO {
    private static final long serialVersionUID = 7152194160690903992L;
    public static final int PAGE_SIZE = 10;

    @XmlTransient
    private String argsJson;
    @XmlTransient
    private int draw = 0;
    @XmlTransient
    private List<Columns> columns;
    @XmlTransient
    private List<Order> order;
    @XmlTransient
    private int start = 0;
    @XmlTransient
    private int length = PAGE_SIZE;
    @XmlTransient
    private Search search;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public List<Columns> getColumns() {
        return columns;
    }

    public void setColumns(List<Columns> columns) {
        this.columns = columns;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }
    

    public String getArgsJson() {
        return argsJson;
    }

    public void setArgsJson(String argsJson) {
        this.argsJson = argsJson;
        if (StringUtils.isNotBlank(argsJson)) {
            AbstractDatatableDTO dto = JsonUtils.fromJson(argsJson, getClass());
            if (dto != null) {
                setColumns(dto.getColumns());
                setDraw(dto.getDraw());
                setLength(dto.getLength());
                setOrder(dto.getOrder());
                setSearch(dto.getSearch());
                setStart(dto.getStart());
            }

        }
    }
    
    
}
