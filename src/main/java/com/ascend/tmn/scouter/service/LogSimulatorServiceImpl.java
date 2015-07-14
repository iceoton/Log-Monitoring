package com.ascend.tmn.scouter.service;

import com.ascend.tmn.scouter.config.Configuration;
import com.ascend.tmn.scouter.model.KioskLog;
import com.ascend.tmn.scouter.model.KioskHibernateLog;
import com.ascend.tmn.scouter.model.PrepaidLog;
import org.apache.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
        logs = logService.getLog();
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
        int index = random.nextInt(logs.size());
        if ("prepaid".equals(config.getTableName())) {
            PrepaidLog prepaidLog = (PrepaidLog) logs.get(index);
            this.message = prepaidLog.getMessage();

        } else if ("kios".equals(config.getTableName())) {
            if (logs.get(index) instanceof KioskLog) {
                this.message = ((KioskLog) logs.get(index)).getMessage();
                this.isHibernate = false;
            } else {
                this.message = ((KioskHibernateLog) logs.get(index)).getMessage();
                this.isHibernate = true;
            }

        }

    }

    private synchronized void writeLogPrePaid() {
        final String separator = ", ";
        StringBuilder logLine = new StringBuilder();
//        logLine.append("Thread name: ");
//        logLine.append(Thread.currentThread().getName());
//        logLine.append(separator);
        logLine.append(this.getClass().getPackage().toString().replace("package ", ""));
        logLine.append("Message: ");
        logLine.append(this.message);
        logLine.append(separator);
        logLine.append("Sleep time: ");
        logLine.append(this.sleepTime);
        logLine.append(" ms");
        //logger.setLevel(Level.INFO);
        //logger.debug(logLine);

        double rand = Math.random();
        if (rand <= 0.05) {
            logger.setLevel(Level.ERROR);
            logger.error(logLine);
        } else if (rand <= 0.15) {
            logger.setLevel(Level.DEBUG);
            logger.debug(logLine);
        } else {
            logger.setLevel(Level.INFO);
            logger.info(logLine);
        }

    }

    private synchronized void writeLogKios() {
        final String separator = " : ";
        StringBuilder logLine = new StringBuilder();
        logLine.append(Thread.currentThread().getName());
        logLine.append(" SystemOut     O ");
        Level logLevel = this.randomLogLevel();
        if (this.isHibernate) {
            logLine.append("Hibernate: ");
            logLine.append(this.message);
        } else {
            logLine.append("WebContainer");
            logLine.append(separator);
            logLine.append("4");
            logLine.append(separator);
            logLine.append(this.getClass().getSimpleName());
            logLine.append(separator);
            //logLine.append(" INFO");
            logLine.append(" " + logLevel.toString());
            logLine.append(separator);
            logLine.append("85");
            logLine.append(separator);
            logLine.append(this.message);
        }
        logger.setLevel(logLevel);
        if(logLevel.equals(Level.ERROR)) {
            logger.error(logLine);
        } else if(logLevel.equals(Level.DEBUG)) {
            logger.debug(logLine);
        } else {
            logger.info(logLine);
        }


    }

    private void setUpLog4J() {
        try {
            if ("prepaid".equalsIgnoreCase(config.getTableName())) {
                layout = new PatternLayout("%-5p %d{yyyy-MM-dd HH:mm:ss,SSS} %m%n");
                fileAppender = new RollingFileAppender(layout, "/data/logs/LogSimulator/prepaidLog.log");
            } else if ("kios".equalsIgnoreCase(config.getTableName())) {
                layout = new PatternLayout("[%d{dd/MM/YY HH:mm:ss:SSS} ICT] %m%n");
                fileAppender = new RollingFileAppender(layout, "/data/logs/LogSimulator/kioskLog.log");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.addAppender(fileAppender);
    }

    private Level randomLogLevel(){
        double rand = Math.random();
        Level[] levels = {Level.INFO, Level.DEBUG, Level.ERROR};
        int index = 0;
        if (rand <= 0.05) {
            index = 2;
        } else if (rand <= 0.15) {
            index = 1;
        }

        return levels[index];
    }

}
