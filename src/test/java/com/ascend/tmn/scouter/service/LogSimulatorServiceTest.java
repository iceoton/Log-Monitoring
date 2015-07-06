package com.ascend.tmn.scouter.service;

import com.ascend.tmn.scouter.config.Configuration;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by keerati on 7/6/15 AD.
 */
@RunWith(MockitoJUnitRunner.class)
public class LogSimulatorServiceTest {
    @InjectMocks
    LogSimulatorService logSimulatorServiceMock;
    @Mock
    LogService logServiceMock;
    @Mock
    Configuration configurationMock;



}
