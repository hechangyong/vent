package com.im.vent;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.im.vent.bean")
public class VentApplication {

	public static void main(String[] args) {
		SpringApplication.run(VentApplication.class, args);
	}
}
