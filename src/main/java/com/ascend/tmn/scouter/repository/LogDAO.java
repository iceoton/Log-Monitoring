package com.ascend.tmn.scouter.repository;

import com.ascend.tmn.scouter.model.KioskLog;
import com.ascend.tmn.scouter.model.KioskHibernateLog;
import com.ascend.tmn.scouter.model.PrepaidLog;

import java.util.List;

/**
 * Created by keerati on 7/1/15 AD.
 */
public interface LogDAO {

    public List<KioskLog> getKiosLog();
    public List<PrepaidLog> getLogPrepaid();
    public List<KioskHibernateLog> getKiosHibernateLog();



}
