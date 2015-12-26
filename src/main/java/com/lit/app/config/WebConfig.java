package com.lit.app.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration 
/*
 * indicates that the class can be used by the Spring IoC
 * container as a source of bean definitions.
 */
@EnableWebMvc /*
 * activates annotation-driven controller request mapping and
 * replaces <mvc:annotation-driven>.
 */
@ComponentScan(basePackages = "com.lit.app")
public class WebConfig {
	
	/*
	 * @Bean annotation tells Spring that a method annotated with @Bean will
	 * return an object that should be registered as a bean in the Spring
	 * application context.
	 */
	@Bean
	public DataSource getDataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/productdb");
		dataSource.setUsername("postgres");
		dataSource.setPassword("12345");
		return dataSource;
	}
	
	@Bean
	public ViewResolver viewRsolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
}
