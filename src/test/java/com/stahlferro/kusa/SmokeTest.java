package com.stahlferro.kusa;

import static org.assertj.core.api.Assertions.assertThat;

import com.stahlferro.kusa.controllers.api.APIUserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {

	@Autowired
	private APIUserController userController;

	@Test
	/* Ensures the application context always start */
	void controllerIsNotNull() throws Exception {
		assertThat(userController).isNotNull();
	}
}
