package com.assignment.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TradeStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeStoreApplication.class, args);
	}
}
