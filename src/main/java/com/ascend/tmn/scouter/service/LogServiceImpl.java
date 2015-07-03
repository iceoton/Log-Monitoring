package com.ascend.tmn.scouter.service;

import com.ascend.tmn.scouter.config.Configuration;
import com.ascend.tmn.scouter.repository.LogDAO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by keerati on 7/1/15 AD.
 */
@PropertySource("classpath:database.properties")
@Service
public class LogServiceImpl implements LogService {
    final static Logger logger = Logger.getLogger(LogServiceImpl.class);
    @Autowired
    private LogDAO logDao;
    private List log;
    private List loghib;
    @Autowired
    private Configuration config;

    @Override
    public List getAllLog() {
            if ("kios".equalsIgnoreCase(config.getTableName())) {

                log = logDao.getKiosLog();
                loghib = logDao.getLogKioshib();

                return log;

            } else if ("prepaid".equalsIgnoreCase(config.getTableName())) {
                log = logDao.getLogPrepaid();
                return log;
            }

        return null;

    }

    public List getLog() {
        return log;
    }


    public List getLoghib() {
        return loghib;
    }
}
