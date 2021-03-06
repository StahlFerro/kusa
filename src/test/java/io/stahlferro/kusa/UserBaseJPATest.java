package io.stahlferro.kusa;

import static org.assertj.core.api.Assertions.assertThat;

import io.stahlferro.kusa.models.UserBase;
import io.stahlferro.kusa.repositories.UserBaseRepository;
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
public class UserBaseJPATest {

    private static final Logger log = LoggerFactory.getLogger(UserBaseJPATest.class);

    @Autowired
    private UserBaseRepository userBaseRepository;

    @Test
    void injectedComponentsAreNotNull() throws Exception {
        assertThat(userBaseRepository).isNotNull();
    }

    @Test
    void userRepositorySavesUserToDatabase() throws Exception{
        UserBase userBase = new UserBase();
        userBase.setName("Alder Liu");
        userBase.setEmail("aldliu12@email.com");
        userBaseRepository.save(userBase);
        Optional<UserBase> searchedUser = userBaseRepository.findById(userBase.getId());
        assertThat(searchedUser).isNotNull();
        UserBase savedUserBase = searchedUser.get();
        assertThat(savedUserBase.getId()).isEqualTo(userBase.getId());
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
