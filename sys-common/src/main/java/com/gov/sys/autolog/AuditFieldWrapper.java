package com.gov.sys.autolog;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "auditFields")
public class AuditFieldWrapper implements Serializable {

    private List<AuditField> auditFields;

    public AuditFieldWrapper() {
    }

    public AuditFieldWrapper(List<AuditField> auditFields) {
        this.auditFields = auditFields;
    }

    @XmlElement(name = "auditField")
    public List<AuditField> getAuditFields() {
        return auditFields;
    }

    public void setAuditFields(List<AuditField> auditFields) {
        this.auditFields = auditFields;
    }
}
