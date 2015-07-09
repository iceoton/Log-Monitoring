package com.ascend.tmn.scouter.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class RobotCustomerTest {
    @InjectMocks
    RobotCustomer robotCustomerMock ;
    @Mock
    LogSimulatorService logSimulatorServiceMock;
    @Test
    public void run_runLogSim_LogSimIsRun() {

        robotCustomerMock.run();
        verify(logSimulatorServiceMock,atLeastOnce()).generateLog();
    }
}