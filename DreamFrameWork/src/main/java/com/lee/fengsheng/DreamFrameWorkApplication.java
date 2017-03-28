package com.lee.fengsheng;

import com.lee.fengsheng.jwt.Audience;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.lee.fengsheng.mappers")
@EnableConfigurationProperties(Audience.class)
public class DreamFrameWorkApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DreamFrameWorkApplication.class, args);
	}


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DreamFrameWorkApplication.class);
	}

}
