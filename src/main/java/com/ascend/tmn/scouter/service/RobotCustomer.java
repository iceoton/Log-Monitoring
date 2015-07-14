package com.ascend.tmn.scouter.service;

import com.ascend.tmn.scouter.service.LogSimulatorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class RobotCustomer extends Thread {

    @Autowired
    LogSimulatorService logSimulatorService;

    @Override
    public void run() {
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        System.out.println("Current thread name: " + Thread.currentThread().getName());
        for (Thread thread : threadSet) {
            System.out.println("Thread name: " + thread.getName());
            if ( thread.getName().equals(Thread.currentThread().getName()) ) {

                    thread.interrupt();
            }
        }
        logSimulatorService.generateLog();
    }
}
