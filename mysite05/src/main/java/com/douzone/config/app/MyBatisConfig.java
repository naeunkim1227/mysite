package com.douzone.config.app;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {
	
	
	@Bean
	public SqlSessionFactory sessionFactory(DataSource dataSource, ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlsessionFactory  = new SqlSessionFactoryBean();
		
		//DBConfig에 있는 DataSource 주입이 필요하다. 
		sqlsessionFactory.setDataSource(dataSource);
		
		sqlsessionFactory.setConfigLocation(applicationContext.getResource("classpath:com/douzone/mysite/config/app/mybatis/configuration.xml"));
		
		return sqlsessionFactory.getObject();
	}
	
	@Bean
	public SqlSession sqlSession(SqlSessionFactory sessionFactory) {
		return new SqlSessionTemplate(sessionFactory);
	}
}
