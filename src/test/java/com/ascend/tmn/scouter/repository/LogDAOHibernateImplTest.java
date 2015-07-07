package com.ascend.tmn.scouter.repository;

import com.ascend.tmn.scouter.model.KiosHibernateLog;
import com.ascend.tmn.scouter.model.KiosLog;
import com.ascend.tmn.scouter.model.PrepaidLog;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by keerati on 7/3/15 AD.
 */
@RunWith(MockitoJUnitRunner.class)
public class LogDAOHibernateImplTest extends Assert {

    @InjectMocks
    LogDAOHibernateImpl logDAOHibernateMock;

    @Mock
    SessionFactory sessionFactoryMock;
    @Mock
    Session sessionMock;
    @Mock
    Query queryMock;

    @Test
    public void getKiosLog__getKiosLog__kiosLogWasReturn(){
        List<KiosLog> expect = new ArrayList<KiosLog>();
        KiosLog kiosLog = new KiosLog();
        kiosLog.setId(1);
        kiosLog.setMessage("kios message");
        when(sessionFactoryMock.getCurrentSession()).thenReturn(sessionMock);
        when(sessionMock.createQuery("from KiosLog")).thenReturn(queryMock);
        List<KiosLog> actual = logDAOHibernateMock.getKiosLog();
        assertEquals(expect,actual);




    }
    @Test
    public void getKiosLog__getPrepaid__PrepaidWasReturn(){
        List<PrepaidLog> expect = new ArrayList<PrepaidLog>();
        PrepaidLog prepaid = new PrepaidLog();
        prepaid.setId(1);
        prepaid.setMessage("prepaid message");
        when(sessionFactoryMock.getCurrentSession()).thenReturn(sessionMock);
        when(sessionMock.createQuery("from PrepaidLog")).thenReturn(queryMock);
        List<PrepaidLog> actual = logDAOHibernateMock.getLogPrepaid();
        assertEquals(expect,actual);

    }
    @Test
   public void getKiosLog__getKiosHibernateLog__KiosHibernateLogWasReturn(){
       List<KiosHibernateLog> expect = new ArrayList<KiosHibernateLog>();
       KiosHibernateLog kiosHibernateLog = new KiosHibernateLog();
       kiosHibernateLog.setId(1);
       kiosHibernateLog.setMessage("kiosHibernate message");when(sessionFactoryMock.getCurrentSession()).thenReturn(sessionMock);
       when(sessionMock.createQuery("from KiosHibernateLog")).thenReturn(queryMock);
       List<KiosHibernateLog> actual = logDAOHibernateMock.getKiosHibernateLog();
       assertEquals(expect,actual);

    }


}
