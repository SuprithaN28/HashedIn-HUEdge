package com.HUEdge.NetflixApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.HUEdge.NetflixApp.service.NetflixServiceImpl;

@SpringBootApplication
@EnableScheduling
public class NetflixAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixAppApplication.class, args);
		NetflixServiceImpl.Read();	
	}

}
