package com.gov.sys.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class VtaBaseDao extends DatatableHibernateDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
