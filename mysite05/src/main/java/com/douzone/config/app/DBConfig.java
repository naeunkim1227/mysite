package com.douzone.config.app;


import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:com/douzone/mysite/config/app/jdbc.properties")
public class DBConfig {
	
	
	//jdbc.properties의 값을 가져올 수 있음
	@Autowired
	private Environment env;
	
	
	@Bean
	public DataSource dataSource() {
		 BasicDataSource dataSource = new BasicDataSource();
		 dataSource.setDriverClassName(env.getProperty("jdbc.driveClassName"));
		 dataSource.setUrl(env.getProperty("jdbc.url"));
		 dataSource.setUsername(env.getProperty("jdbc.username"));
		 dataSource.setPassword(env.getProperty("jdbc.password"));
		 // int값으로 변경해서 가져 와야함
		 dataSource.setInitialSize(env.getProperty("jdbc.initialSize", Integer.class));
		 dataSource.setMaxActive(env.getProperty("jdbc.maxActive", Integer.class));
		 
		 return dataSource;
	}
	
}
