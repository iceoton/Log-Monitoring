package com.ascend.tmn.scouter.repository;

import com.ascend.tmn.scouter.model.KioskLog;
import com.ascend.tmn.scouter.model.KioskHibernateLog;
import com.ascend.tmn.scouter.model.PrepaidLog;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by keerati on 7/1/15 AD.
 */
@EnableTransactionManagement
@Transactional
@Repository
public class LogDAOHibernateImpl implements LogDAO {

    @Autowired
    private SessionFactory sessionFactory;



    @Override
    public List<PrepaidLog> getLogPrepaid() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from PrepaidLog");
        List<PrepaidLog> prepaidLogList = query.list();
        return prepaidLogList;
    }
    @Override
    public List<KioskLog> getKiosLog() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from KioskLog");
        List<KioskLog> kiosList = query.list();
        return kiosList;

    }
    @Override
    public List<KioskHibernateLog> getKiosHibernateLog() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from KioskHibernateLog");
        List<KioskHibernateLog> kioskHibernateLog = query.list();
        return kioskHibernateLog;
    }


}
