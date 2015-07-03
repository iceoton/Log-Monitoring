package com.ascend.tmn.scouter.service;

import com.ascend.tmn.scouter.config.Configuration;
import com.ascend.tmn.scouter.model.KiosLog;
import com.ascend.tmn.scouter.model.KiosHibernateLog;
import com.ascend.tmn.scouter.model.PrepaidLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by keerati on 7/2/15 AD.
 */
@Service
public class LogSimulatorServiceImpl implements LogSimulatorService {


    /*
    * In millisecond
    */

    @Autowired
    private Configuration config;

    @Autowired
    LogServiceImpl logService;

    private List logs;
    private List loghib;
    private long lowerRandomSleepTimeRange;
    private long upperRandomSleepTimeRange;
    private long sleepTime;
    private String message;
    private int logLineIndex;

    public LogSimulatorServiceImpl() {
        this(0L, 5000L);

    }

    public LogSimulatorServiceImpl(long lowerRandomRange, long upperRandomRange) {
        this.lowerRandomSleepTimeRange = lowerRandomRange;
        this.upperRandomSleepTimeRange = upperRandomRange;

    }


    @Override
    public void generateLog() {
        while (true) {
            this.randomSleep();
            try {
                Thread.sleep(this.sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.readLog();
            this.writeLog();
        }

    }
    private void randomSleep() {
        this.sleepTime =  this.lowerRandomSleepTimeRange + (long) ((this.upperRandomSleepTimeRange - this.lowerRandomSleepTimeRange) * Math.random());

    }

    private void readLog() {
        logs = logService.getLog();
        loghib = logService.getLoghib();
        if("prepaid".equals(config.getTableName())) {
            this.message = ((PrepaidLog) logs.get(this.logLineIndex++ % logs.size())).getMessage();
        }
        else if("kios".equals(config.getTableName())){

            this.message = Math.random() > 0.5 ? ((KiosLog) logs.get(this.logLineIndex++ % logs.size())).getMessage() : ((KiosHibernateLog) loghib.get(this.logLineIndex++ % loghib.size())).getMessage();
        }

    }

    private void writeLog() {
        final String separator = ", ";

        StringBuilder logLine = new StringBuilder();
        logLine.append("Thread name: ");
        logLine.append(Thread.currentThread().getName());
        logLine.append(separator);
        logLine.append("Message: ");
        logLine.append(this.message);
        logLine.append(separator);
        logLine.append("Sleep time: ");
        logLine.append(this.sleepTime);
        logLine.append(" ms");

        System.out.println(logLine);

    }


}
