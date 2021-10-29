package com.douzone.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.douzone.config.app.DBConfig;
import com.douzone.config.app.MyBatisConfig;

@Configuration
@EnableAspectJAutoProxy
@Import({DBConfig.class, MyBatisConfig.class})
@ComponentScan({"com.douzone.mysite.repository","com.douzone.mysite.service","com.douzone.mysite.aspect"})
public class AppConfig {
}
