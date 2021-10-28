package com.douzone.config.app;


import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {
	
	@Bean
	public DataSource dataSource() {
		 BasicDataSource dataSource = new BasicDataSource();
		 dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
		 dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/webdb?charset=utf8");
		 dataSource.setUsername("webdb");
		 dataSource.setPassword("webdb");
		 dataSource.setInitialSize(10);
		 dataSource.setMaxActive(20);
		 
		 return dataSource;
	}
	
}
