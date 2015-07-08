package com.ascend.tmn.scouter.service;

import com.ascend.tmn.scouter.config.Configuration;
import com.ascend.tmn.scouter.model.KiosLog;
import com.ascend.tmn.scouter.model.KiosHibernateLog;
import com.ascend.tmn.scouter.model.PrepaidLog;
import org.apache.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
import java.util.Random;

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

    private  Random random = new Random();
    PatternLayout layout = null;
    RollingFileAppender fileAppender = null;
    private boolean isHibernate;

    public LogSimulatorServiceImpl() {
        this(0L, 5000L);


    }

    public LogSimulatorServiceImpl(long lowerRandomRange, long upperRandomRange) {
        this.lowerRandomSleepTimeRange = lowerRandomRange;
        this.upperRandomSleepTimeRange = upperRandomRange;

    }


    @Override
    public void generateLog() {
        setUpLog4J();
        while (true) {
            this.randomSleep();
            try {
                Thread.sleep(this.sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.readLog();
            if ("prepaid".equalsIgnoreCase(config.getTableName())) {
                this.writeLogPrePaid();
            } else if ("kios".equalsIgnoreCase(config.getTableName())) {
                this.writeLogKios();
            }
        }

    }




    private void randomSleep() {
        this.sleepTime = (long) (Math.random() * (this.upperRandomSleepTimeRange - this.lowerRandomSleepTimeRange)) + this.lowerRandomSleepTimeRange;
    }

    private void readLog() {
        logs = logService.getLog();
        loghib = logService.getLoghib();
        int i = random.nextInt(logs.size());
        if ("prepaid".equals(config.getTableName())) {
            PrepaidLog prepaidLog = (PrepaidLog) logs.get(i);
            this.message = prepaidLog.getMessage();

        } else if ("kios".equals(config.getTableName())) {
            if (logs.get(i) instanceof KiosLog) {
                this.message = ((KiosLog) logs.get(i)).getMessage();
                this.isHibernate = false;
            } else {
                this.message = ((KiosHibernateLog) logs.get(i)).getMessage();
                this.isHibernate = true;
            }

        }

    }

    private synchronized void writeLogPrePaid() {
        final String separator = ", ";
        StringBuilder logLine = new StringBuilder();
        logLine.append("Thread name: ");
        logLine.append(Thread.currentThread().getName());
        logLine.append(separator);
        logLine.append(this.getClass().getPackage());
        logLine.append("Message: ");
        logLine.append(this.message);
        logLine.append(separator);
        logLine.append("Sleep time: ");
        logLine.append(this.sleepTime);
        logLine.append(" ms");
        logger.setLevel(Level.INFO);
        logger.info(logLine);

    }
    private synchronized void writeLogKios() {
        final String separator = " : ";
        StringBuilder logLine = new StringBuilder();
        logLine.append(Thread.currentThread().getName());
        logLine.append(" SystemOut     O ");
        if(this.isHibernate){
            logLine.append("Hibernate: ");
            logLine.append(this.message);
        }else{
            logLine.append("WebContainer");
            logLine.append(separator);
            logLine.append("4");
            logLine.append(separator);
            logLine.append(this.getClass().getName());
            logLine.append(separator);
            logLine.append("INFO");
            logLine.append(separator);
            logLine.append("85");
            logLine.append(separator);
            logLine.append(this.message);
        }
        logger.info(logLine);


    }

    private void setUpLog4J() {
        try {
            if(config.getTableName().equalsIgnoreCase("prepaid")) {
                layout = new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n");
                fileAppender = new RollingFileAppender(layout, "/data/logs/LogSimulator/prepaidLog.log");
            }else if(config.getTableName().equalsIgnoreCase("kios")){
                layout = new PatternLayout("[%d{dd/MM/YY HH:mm:ss} ICT] %m%n");
                fileAppender = new RollingFileAppender(layout, "/data/logs/LogSimulator/kiosLog.log");
            }

        }catch(IOException e) {
            e.printStackTrace();
        }
        logger.addAppender(fileAppender);
    }

}
