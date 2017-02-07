package com.gov.sys.autolog;

import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.WeakHashMap;

public class AuditConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditConfiguration.class);

    private static Map<Configuration, AuditConfiguration> auditConfigurationMap = new WeakHashMap<Configuration, AuditConfiguration>();

    private final AuditProcessManager auditProcessManager;

    public AuditConfiguration() {
        auditProcessManager = new AuditProcessManager();
    }

    public AuditProcessManager getAuditProcessManager() {
        return auditProcessManager;
    }

    public synchronized static AuditConfiguration get(Configuration configuration) {
        AuditConfiguration auditConfiguration = auditConfigurationMap.get(configuration);
        if (auditConfiguration == null) {
            auditConfiguration = new AuditConfiguration();
            auditConfigurationMap.put(configuration, auditConfiguration);
            configuration.buildMappings();
        }
        return auditConfiguration;
    }

}
