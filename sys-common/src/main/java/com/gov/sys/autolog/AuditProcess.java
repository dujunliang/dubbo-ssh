package com.gov.sys.autolog;

import com.gov.sys.util.JaxbMapper;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.action.BeforeTransactionCompletionProcess;
import org.hibernate.engine.SessionImplementor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.LinkedList;

public class AuditProcess implements BeforeTransactionCompletionProcess {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditProcess.class);

    private final SessionImplementor session;
    private final LinkedList<AuditEntry> auditEntries;

    public AuditProcess(SessionImplementor session) {
        this.session = session;
        auditEntries = new LinkedList<AuditEntry>();
    }

    public void add(AuditEntry auditEntry) {
        auditEntries.offer(auditEntry);
    }

    private void remove(AuditEntry auditEntry) {
        auditEntries.remove(auditEntry);
    }

    @Override
    public void doBeforeTransactionCompletion(SessionImplementor session) {
        if (FlushMode.isManualFlushMode(session.getFlushMode())) {
            Session temporarySession = null;
            try {
                temporarySession = session.getFactory().openTemporarySession();
                execute(temporarySession);
                temporarySession.flush();
            } finally {
                if (temporarySession != null) {
                    temporarySession.close();
                }
            }
        } else {
            execute((Session) session);
            session.flush();
        }
    }

    private void execute(Session session) {
        try {
            AuditContext auditContext = AuditContext.getInstance();
            AuditMasterEntity auditMaster = new AuditMasterEntity(
                    auditContext.getFuncCode(),
                    auditContext.getUserId(),
                    Calendar.getInstance().getTime(),
                    auditContext.getServiceId(),
                    auditContext.getProcessId(),
                    auditContext.getHostIP(),
                    auditContext.getHostName(),
                    auditContext.getRemark()
            );
            session.save(auditMaster);

            AuditEntry auditEntry;
            while ((auditEntry = auditEntries.poll()) != null) {
                AuditIndexEntity auditIndex = new AuditIndexEntity(
                        auditEntry.getEntityId(),
                        auditEntry.getAction(),
                        auditEntry.getTableName(),
                        JaxbMapper.toXML(new AuditFieldWrapper(auditEntry.getAuditFields())).getBytes(),
                        auditEntry.getVtaNo(),
                        auditEntry.getVtaYear(),
                        auditEntry.getVehId(),
                        auditEntry.getSpNo(),
                        auditEntry.getPlateNo(),
                        auditEntry.getVehType(),
                        auditMaster
                );
                session.save(auditIndex);
                for (AuditField auditField : auditEntry.getAuditFields()) {
                    session.save(new AuditDetailEntity(
                            auditField.getColumnName(),
                            auditField.getValueBefore(),
                            auditField.getValueAfter(),
                            auditIndex
                    ));
                }
            }
            auditContext.setLogId(auditMaster.getId());
        } catch (Exception e) {
            LOGGER.error("Error insert audit log.", e);
        }
    }

}
