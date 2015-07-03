package com.ascend.tmn.scouter.service;

import com.ascend.tmn.scouter.service.LogSimulatorService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by keerati on 7/2/15 AD.
 */

public class RobotCustomer extends Thread {

    @Autowired
    LogSimulatorService logSimulatorService;

    @Override
    public void run() {

        logSimulatorService.generateLog();
    }
}
