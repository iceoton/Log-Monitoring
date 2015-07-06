package com.ascend.tmn.scouter.repository;

import com.ascend.tmn.scouter.model.KiosLog;
import com.ascend.tmn.scouter.model.KiosHibernateLog;
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
    final static Logger logger = Logger.getLogger(LogDAOHibernateImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<KiosLog> getKiosLog() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from KiosLog");
        List<KiosLog> kiosList = query.list();
        return kiosList;

    }

    @Override
    public List<PrepaidLog> getLogPrepaid() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from PrepaidLog");
        List<PrepaidLog> prepaidLogList = query.list();
        return prepaidLogList;
    }

    @Override
    public List<KiosHibernateLog> getKiosHibernateLog() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from KiosHibernateLog");
        List<KiosHibernateLog> kiosHibernateLog = query.list();
        return kiosHibernateLog;
    }


}
