package com.peach.sellbuy.util;

import com.peach.sellbuy.business.*;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;


public class ContextListener
        implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent ctx) {
        //do stuff
    }

    @Override
    public void contextInitialized(ServletContextEvent ctx) {
        //do stuff before web application is started
    }
}