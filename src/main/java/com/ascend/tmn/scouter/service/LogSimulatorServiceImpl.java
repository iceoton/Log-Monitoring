package com.ascend.tmn.scouter.service;

import com.ascend.tmn.scouter.config.Configuration;
import com.ascend.tmn.scouter.model.KiosLog;
import com.ascend.tmn.scouter.model.KiosHibernateLog;
import com.ascend.tmn.scouter.model.PrepaidLog;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * Created by keerati on 7/2/15 AD.
 */
@Service
public class LogSimulatorServiceImpl implements LogSimulatorService {


    /*
    * In millisecond
    */
    Logger logger = Logger.getLogger(LogSimulatorService.class);

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

    private static Random random = new Random();

    public LogSimulatorServiceImpl() {
        this(0L, 5000L);

    }

    public LogSimulatorServiceImpl(long lowerRandomRange, long upperRandomRange) {
        this.lowerRandomSleepTimeRange = lowerRandomRange;
        this.upperRandomSleepTimeRange = upperRandomRange;

    }


    @Override
    public void generateLog() {
        int i =2;
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
        int i = random.nextInt(logs.size()) ;
        if("prepaid".equals(config.getTableName())) {
            PrepaidLog prepaidLog = (PrepaidLog)logs.get(i);
            this.message = prepaidLog.getMessage();

        }
        else if("kios".equals(config.getTableName())) {
          if(logs.get(i) instanceof KiosLog ) {
              this.message = ((KiosLog) logs.get(i)).getMessage();
          }
            else {
              this.message = ((KiosHibernateLog) logs.get(i)).getMessage();

          }

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

        logger.info(logLine);

    }


}
