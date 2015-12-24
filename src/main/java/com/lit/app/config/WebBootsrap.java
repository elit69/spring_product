package com.lit.app.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebBootsrap implements WebApplicationInitializer {
	
	@Override
	public void onStartup(ServletContext container) throws ServletException {
		
		/*AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(RootContextConfiguration.class);
		container.addListener(new ContextLoaderListener(rootContext));*/

		// 1.Create AnnotationConfigWebApplicationContext Object
		AnnotationConfigWebApplicationContext servletContext = new AnnotationConfigWebApplicationContext();
		// 2.Register the Configuration Class
		servletContext.register(WebConfiguration.class);

		// 3.Create DispatacherServelt, add it to container,
		// and assign it to the servletRegisteration
		ServletRegistration.Dynamic dispatcherServlet = container.addServlet("springDispatcher",
				new DispatcherServlet(servletContext));

		dispatcherServlet.addMapping("/");
		dispatcherServlet.setLoadOnStartup(1);
		
	}
	
}
