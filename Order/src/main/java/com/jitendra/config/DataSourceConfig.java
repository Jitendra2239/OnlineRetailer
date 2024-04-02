package com.jitendra.config;

//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class DataSourceConfig {
//	  @Value("${spring.datasource.url}") 
//	    private String url;
//	  @Value("${spring.datasource.username}") 
//	    private String username;
//	  @Value("${spring.datasource.password}") 
//	    private String password;
//	  @Value("${spring.datasource.driverClassName}") 
//	    private String driverClassName;
//	  @Value("${spring.jpa.database-platform}") 
//	    private String platform;
//	  @Value("${server.port}") 
//	    private int port;
//	  @Value("${eureka.client.serviceUrl.defaultZone}") 
//	    private String eurekaclient;
//	  @Value("${spring.application.name}") 
//	    private String serviceName;
//	  
//    @Bean
//    public DataSource getDataSource() {
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.driverClassName(driverClassName);
//        dataSourceBuilder.url(url);
//        dataSourceBuilder.username(username);
//        dataSourceBuilder.password(password);
//      
//        return dataSourceBuilder.build();
//    }
//}
