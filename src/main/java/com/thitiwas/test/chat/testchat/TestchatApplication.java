package com.thitiwas.test.chat.testchat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class TestchatApplication {

	public static void main(String[] args) {
		// log.debug("test");
		SpringApplication.run(TestchatApplication.class, args);
	}

}
