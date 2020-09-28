package io.stahlferro.kusa;

import static org.assertj.core.api.Assertions.assertThat;

import io.stahlferro.kusa.controllers.api.APIKeycardController;
import io.stahlferro.kusa.controllers.api.APIUserController;
import io.stahlferro.kusa.mappers.UserBaseMapper;
import io.stahlferro.kusa.repositories.UserBaseRepository;
import io.stahlferro.kusa.services.JwtUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AutowiringTest {

	@Autowired
	private APIUserController userController;

	@Autowired
	private APIKeycardController keycardController;

	@Autowired
	private UserBaseRepository userBaseRepository;

	@Autowired
	private UserBaseMapper userMapper;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Test
	/* Ensures the application context always start */
	void controllerIsNotNull() throws Exception {
		assertThat(userController).isNotNull();
		assertThat(keycardController).isNotNull();
		assertThat(userBaseRepository).isNotNull();
		assertThat(userMapper).isNotNull();
		assertThat(jwtUserDetailsService).isNotNull();
	}
}
