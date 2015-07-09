package com.ascend.tmn.scouter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by keerati on 7/2/15 AD.
 */
@Component
@PropertySource("file:/data/database.properties")
public class Configuration {

    @Value("${mysql.tablename}")
    private  String tableName;

    private long lowerRandomSleepTimeRange = 0L;
    private long upperRandomSleepTimeRange = 1000L;

    public Configuration() {
    }

    public long getUpperRandomSleepTimeRange() {
        return upperRandomSleepTimeRange;
    }

    public void setUpperRandomSleepTimeRange(long upperRandomSleepTimeRange) {
        this.upperRandomSleepTimeRange = upperRandomSleepTimeRange;
    }

    public long getLowerRandomSleepTimeRange() {
        return lowerRandomSleepTimeRange;
    }

    public void setLowerRandomSleepTimeRange(long lowerRandomSleepTimeRange) {
        this.lowerRandomSleepTimeRange = lowerRandomSleepTimeRange;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
