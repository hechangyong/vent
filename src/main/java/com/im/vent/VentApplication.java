package com.im.vent;

import com.im.vent.netty.ChatServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.im.vent.bean")
public class VentApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(VentApplication.class, args);
		ChatServer.main();
	}
}



