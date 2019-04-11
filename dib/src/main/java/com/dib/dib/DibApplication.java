package com.dib.dib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages= {"com.dib.***"})
//@ComponentScan(basePackages="com.dib.***")
@EnableJpaRepositories(basePackages="com.dib.***")
@EntityScan("com.dib.***")
public class DibApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(DibApplication.class, args);
	}
}
