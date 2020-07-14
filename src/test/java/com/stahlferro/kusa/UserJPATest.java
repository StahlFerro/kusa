package com.stahlferro.kusa;

import static org.assertj.core.api.Assertions.assertThat;

import com.stahlferro.kusa.models.User;
import com.stahlferro.kusa.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;


@DataJpaTest
/*  Below annotation is IMPORTANT because by default, @DataJpaTest uses in memory H2 database for repo tests, in which
    currently, Kusa does not have a H2 db as its dependency
*/
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserJPATest {

    private static final Logger log = LoggerFactory.getLogger(UserJPATest.class);

    @Autowired
    private UserRepository userRepository;

    @Test
    void injectedComponentsAreNotNull() throws Exception {
        assertThat(userRepository).isNotNull();
    }

    @Test
    void userRepositorySavesUserToDatabase() throws Exception{
        User user = new User();
        user.setName("Alder Liu");
        user.setEmail("aldliu12@email.com");
        userRepository.save(user);
        Optional<User> searchedUser = userRepository.findById(user.getId());
        assertThat(searchedUser).isNotNull();
        User savedUser = searchedUser.get();
        assertThat(savedUser.getId()).isEqualTo(user.getId());
    }

//    @Test
//    void userIdIncrements() throws Exception {
//        log.info("haha");
//        long firstId = userRepository.getNextId();
//        User user = new User();
//        user.setName("Rayleigh");
//        user.setEmail("rlgf@email.com");
//        userRepository.save(user);
//        long secondId = userRepository.getNextId();
//        assertThat(secondId).isEqualTo(firstId + 1);
//    }
//    @Test
//    void testNum() throws Exception {
//        assertThat(4).isEqualTo(4);
//    }
}
