package com.ascend.tmn.scouter.service;

import com.ascend.tmn.scouter.config.Configuration;
import com.ascend.tmn.scouter.model.KiosLog;
import com.ascend.tmn.scouter.model.PrepaidLog;
import com.ascend.tmn.scouter.repository.LogDAOHibernateImpl;
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
public class LogServiceImplTest extends Assert{
    @InjectMocks
    LogServiceImpl logService;
    @Mock
    LogDAOHibernateImpl logDAOHibernateMock;
    @Mock
    Configuration configurationMock;

   @Test
   public void getAllLog__getPrepaidLog__prepaidLogWasReturn(){
      when(configurationMock.getTableName()).thenReturn("prepaid");
       List expect= new ArrayList<PrepaidLog>();
       PrepaidLog temp = new PrepaidLog();
       temp.setId(1);
       temp.setMessage("prepaid message");
       expect.add(temp);
       when(logDAOHibernateMock.getLogPrepaid()).thenReturn(expect);
       List result =  logService.getAllLog();
       assertEquals(expect,result);

   }
    @Test
    public void getAllLog__getKiosLog__kiosLogWasReturn(){

        when(configurationMock.getTableName()).thenReturn("kios");
        List expect= new ArrayList<PrepaidLog>();
        KiosLog temp = new KiosLog();
        temp.setId(1);
        temp.setMessage("kios message");
        expect.add(temp);
        when(logDAOHibernateMock.getKiosLog()).thenReturn(expect);
        List result =  logService.getAllLog();
        assertEquals(expect,result);
    }
    @Test
    public void getAllLog__wrongTableName__nullWasReturn(){
        when(configurationMock.getTableName()).thenReturn("wrongTableName");
        List result =  logService.getAllLog();
        assertEquals(null,result);

    }


}
