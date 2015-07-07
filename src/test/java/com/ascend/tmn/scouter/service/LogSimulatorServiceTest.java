package com.ascend.tmn.scouter.service;

import com.ascend.tmn.scouter.config.Configuration;
import com.ascend.tmn.scouter.model.KiosLog;
import com.ascend.tmn.scouter.model.PrepaidLog;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LogSimulatorServiceTest extends Assert{
    @InjectMocks
    LogSimulatorServiceImpl logSimulatorServiceImplMock;
    @Mock
    LogServiceImpl logServiceImplMock;
    @Mock
    Configuration configurationMock;
    @Mock
    PrepaidLog prepaidLog;

    @Mock
    List<PrepaidLog> prepaidLogList;
    @Mock
    List<KiosLog> KiosLogListMock;
    @Mock
    KiosLog kiosLogMock;
    @Mock
    RobotCustomer customer1;
    @Test
    public void generateLog__prepaidLog__getMessageMethodOfPrepaidLogmustBeCalled(){

            List<PrepaidLog> prepaidLogList = new ArrayList<PrepaidLog>();

            prepaidLog.setId(1);
            prepaidLog.setMessage("PrepaidLog");
            prepaidLogList.add(prepaidLog);
            when(logServiceImplMock.getLog()).thenReturn(prepaidLogList);
            when(configurationMock.getTableName()).thenReturn("prepaid");
            when(this.prepaidLogList.get(anyInt())).thenReturn(prepaidLog);


        Thread t = new Thread(){
            public void run(){
                logSimulatorServiceImplMock.generateLog();
            }
        };
        t.start();
        try {
            Thread.sleep(5000);
            t.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            verify(prepaidLog,atLeastOnce()).getMessage();

    }

    @Test
    public void generatelog__kiosLog_getMessageOfKiosMustbecalled(){
        List<KiosLog> kiosLogList = new ArrayList<KiosLog>();

        kiosLogMock.setId(1);
        kiosLogMock.setMessage("kios");
        kiosLogList.add(kiosLogMock);
        when(logServiceImplMock.getLog()).thenReturn(kiosLogList);
        when(configurationMock.getTableName()).thenReturn("kios");
        when(this.KiosLogListMock.get(anyInt())).thenReturn(kiosLogMock);

        Thread t = new Thread(){
            public void run(){
                logSimulatorServiceImplMock.generateLog();
            }
        };
        t.start();
        try {
            Thread.sleep(5000);
            t.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        verify(kiosLogMock, atLeastOnce()).getMessage();

    }



}
