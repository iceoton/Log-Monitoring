package com.ascend.tmn.scouter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by keerati on 7/2/15 AD.
 */
@PropertySource("classpath:database.properties")
@Component
public class Configuration {



    private static String tableName;

    private long lowerRandomSleepTimeRange = 0L;
    private long upperRandomSleepTimeRange = 1000L;

    public Configuration() {
        tableName = System.getenv("TABLE_NAME");
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
