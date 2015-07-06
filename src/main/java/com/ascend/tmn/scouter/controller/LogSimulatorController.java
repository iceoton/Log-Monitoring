package com.ascend.tmn.scouter.controller;

import com.ascend.tmn.scouter.service.LogService;
import com.ascend.tmn.scouter.service.RobotCustomer;
import org.apache.log4j.Logger;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

@Controller
@RequestMapping("/")
public class LogSimulatorController {
    @Autowired
    private LogService logService;

    @Autowired
    @Qualifier("robotCustomer1")
    private RobotCustomer robotCustomer1;

    @Autowired
    @Qualifier("robotCustomer2")
    private RobotCustomer robotCustomer2;
    @RequestMapping(method=RequestMethod.GET)
    public String getAllLog(ModelMap model){
        List log = null;
        try {
             log = logService.getAllLog();
        }
        catch(JDBCConnectionException e) {
            model.addAttribute("errorMessage","Database connection failed.");
            return "error";
        }
        if(log!=null) {
            robotCustomer1.start();
            robotCustomer2.start();
            model.addAttribute("logList", log);
            return "allLog";
        }
        else{
            model.addAttribute("errorMessage","Database failed.");
            return "error";

        }



    }
}