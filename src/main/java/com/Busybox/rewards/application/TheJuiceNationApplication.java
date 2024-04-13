package com.Busybox.rewards.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//@ComponentScan(basePackages = "busybox.tjn")
//@ComponentScan(basePackages = "com.Busybox.rewards.application.impl.tjn_Login_Impl")
 public class TheJuiceNationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheJuiceNationApplication.class, args);
		}
	}
