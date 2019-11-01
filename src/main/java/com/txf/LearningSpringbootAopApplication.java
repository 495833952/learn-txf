package com.txf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.txf.*"})
@MapperScan(basePackages={"com.txf.dao", "com.txf.mapper"})
@EnableCaching
public class LearningSpringbootAopApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningSpringbootAopApplication.class, args);
	}

}
