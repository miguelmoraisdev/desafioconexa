package com.conexatest.miguel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MiguelApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiguelApplication.class, args);
	}

}
