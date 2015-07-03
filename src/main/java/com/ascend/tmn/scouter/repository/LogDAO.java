package com.ascend.tmn.scouter.repository;

import com.ascend.tmn.scouter.model.KiosLog;
import com.ascend.tmn.scouter.model.KiosHibernateLog;
import com.ascend.tmn.scouter.model.PrepaidLog;

import java.util.List;

/**
 * Created by keerati on 7/1/15 AD.
 */
public interface LogDAO {

    public List<KiosLog> getKiosLog();
    public List<PrepaidLog> getLogPrepaid();
    public List<KiosHibernateLog> getLogKioshib();



}
