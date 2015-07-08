package com.ascend.tmn.scouter.service;
import com.ascend.tmn.scouter.config.Configuration;
import com.ascend.tmn.scouter.model.KioskLog;
import com.ascend.tmn.scouter.model.PrepaidLog;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
    List<KioskLog> kioskLogListMock;
    @Mock
    KioskLog kioskLogMock;
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
        List<KioskLog> kioskLogList = new ArrayList<KioskLog>();

        kioskLogMock.setId(1);
        kioskLogMock.setMessage("kios");
        kioskLogList.add(kioskLogMock);
        when(logServiceImplMock.getLog()).thenReturn(kioskLogList);
        when(configurationMock.getTableName()).thenReturn("kios");
        when(this.kioskLogListMock.get(anyInt())).thenReturn(kioskLogMock);

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


        verify(kioskLogMock, atLeastOnce()).getMessage();

    }



}
