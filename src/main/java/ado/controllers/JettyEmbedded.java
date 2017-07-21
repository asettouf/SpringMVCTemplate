package ado.controllers;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class JettyEmbedded {

	public static void startSimpleJetty(
			AnnotationConfigWebApplicationContext webAppContext) throws Exception {
		Server server = new Server(8070);
    ServletContextHandler contextHandler = new ServletContextHandler();
    server.setHandler(contextHandler);
    System.out.println(webAppContext.getBean(HelloController.class));
    DispatcherServlet dispatcherServlet = new DispatcherServlet(
            webAppContext);
    ServletHolder springServletHolder = new ServletHolder(
            dispatcherServlet);
    contextHandler.addServlet(springServletHolder, "/*");
    contextHandler
            .addEventListener(new ContextLoaderListener(webAppContext));
    server.start();
	}

}
