package com.gov.sys.autolog;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * Author: Gavin
 * Date: Jul 27, 2016 9:00:11 AM
 * Version: 1.0
 */
@XmlRootElement
public class AuditField implements Serializable {

    private static final long serialVersionUID = 20160727L;

    private String propertyName;
    private String columnName;
    private String valueBefore;
    private String valueAfter;

    public AuditField() {
    }

    public AuditField(String propertyName, String columnName,
                      String valueBefore, String valueAfter) {
        this.propertyName = propertyName;
        this.columnName = columnName;
        this.valueBefore = valueBefore;
        this.valueAfter = valueAfter;
    }

    @XmlTransient
    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    @XmlElement
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @XmlElement(nillable = true)
    public String getValueBefore() {
        return valueBefore;
    }

    public void setValueBefore(String valueBefore) {
        this.valueBefore = valueBefore;
    }

    @XmlElement(nillable = true)
    public String getValueAfter() {
        return valueAfter;
    }

    public void setValueAfter(String valueAfter) {
        this.valueAfter = valueAfter;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
