package com.packt.learnjava.spring;

import com.packt.learnjava.spring.controller.WsController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationTests {
	@Autowired
	private WsController wsController;

	@Test
	void contextLoads() {
		assertThat(wsController).isNotNull();
	}

	@Test
	void contextLoads(ApplicationContext context) {
		assertThat(context).isNotNull();
	}
}
