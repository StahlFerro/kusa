package com.stahlferro.kusa;

import static org.assertj.core.api.Assertions.assertThat;

import com.stahlferro.kusa.controllers.api.APIKeyCardController;
import com.stahlferro.kusa.controllers.api.APIUserController;
import com.stahlferro.kusa.mappers.UserMapper;
import com.stahlferro.kusa.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {

	@Autowired
	private APIUserController userController;

	@Autowired
	private APIKeyCardController keyCardController;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	@Test
	/* Ensures the application context always start */
	void controllerIsNotNull() throws Exception {
		assertThat(userController).isNotNull();
		assertThat(keyCardController).isNotNull();
		assertThat(userRepository).isNotNull();
		assertThat(userMapper).isNotNull();
	}
}
