package com.nortal.assignment.tomcat;

import java.io.File;

import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class for configuring and starting the web application server
 */
public class StartTomcat {

  private static final Logger LOG = LoggerFactory.getLogger(StartTomcat.class);

  public static void main(String[] args) throws Exception {
    Integer port = Integer.valueOf(args[0]);
    String appBase = args[1];

    LOG.info("Starting Tomcat with application base: " + appBase);
    Tomcat tomcat = new Tomcat();
    tomcat.setPort(port);

    tomcat.setBaseDir(".");

    // Add AprLifecycleListener
    StandardServer server = (StandardServer) tomcat.getServer();
    AprLifecycleListener listener = new AprLifecycleListener();

    server.addLifecycleListener(listener);

    String contextPath = "/";
    String appBaseAbsolutePath = new File(appBase).getAbsolutePath();
    tomcat.addWebapp(contextPath, appBaseAbsolutePath);

    tomcat.start();
    tomcat.getServer().await();

  }

}
