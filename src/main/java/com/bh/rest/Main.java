package com.bh.rest;

import com.bh.users.UserService;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Slf4jRequestLog;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.RequestLogHandler;
import org.eclipse.jetty.server.handler.StatisticsHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;


/**
 * Created by bharatviswanadham on 6/17/17.
 */
public class Main {

    public static void main(String args[]) throws Exception {


        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.register(new RandomGen());
        resourceConfig.register(new MathOper());
        resourceConfig.register(new Start());
        resourceConfig.register(new UserService());

        ServletContainer servletContainer = new ServletContainer(resourceConfig);
        ServletHolder servletHolder = new ServletHolder(servletContainer);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.addServlet(servletHolder, "/*");

        RequestLogHandler requestLogHandler = new RequestLogHandler();

        Slf4jRequestLog requestLog = new Slf4jRequestLog();
        requestLog.setLoggerName(Main.class.getCanonicalName());
        requestLog.setLogLatency(true);
        requestLogHandler.setRequestLog(requestLog);


        HandlerCollection handlers = new HandlerCollection();
        handlers.setHandlers(new Handler[]{context, new DefaultHandler(), requestLogHandler});

        Server jettyServer = new Server(8080);
        jettyServer.setHandler(context);

        /* Needed for graceful shutdown as per `setStopTimeout` documentation */
        StatisticsHandler statsHandler = new StatisticsHandler();
        statsHandler.setHandler(handlers);
        jettyServer.setHandler(statsHandler);
        jettyServer.setStopTimeout(60000);
        jettyServer.setStopAtShutdown(true);

        try {
            jettyServer.start();
            jettyServer.join();
        } finally {
            jettyServer.destroy();
        }
    }
}
