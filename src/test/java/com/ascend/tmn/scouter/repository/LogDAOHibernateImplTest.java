package com.ascend.tmn.scouter.repository;

import com.ascend.tmn.scouter.model.KioskHibernateLog;
import com.ascend.tmn.scouter.model.KioskLog;
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
        List<KioskLog> expect = new ArrayList<KioskLog>();
        KioskLog kioskLog = new KioskLog();
        kioskLog.setId(1);
        kioskLog.setMessage("kios message");
        when(sessionFactoryMock.getCurrentSession()).thenReturn(sessionMock);
        when(sessionMock.createQuery("from KiosLog")).thenReturn(queryMock);
        List<KioskLog> actual = logDAOHibernateMock.getKiosLog();
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
       List<KioskHibernateLog> expect = new ArrayList<KioskHibernateLog>();
       KioskHibernateLog kioskHibernateLog = new KioskHibernateLog();
       kioskHibernateLog.setId(1);
       kioskHibernateLog.setMessage("kiosHibernate message");when(sessionFactoryMock.getCurrentSession()).thenReturn(sessionMock);
       when(sessionMock.createQuery("from KiosHibernateLog")).thenReturn(queryMock);
       List<KioskHibernateLog> actual = logDAOHibernateMock.getKiosHibernateLog();
       assertEquals(expect,actual);

    }


}
