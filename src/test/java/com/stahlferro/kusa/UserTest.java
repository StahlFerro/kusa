package com.stahlferro.kusa;

import static org.assertj.core.api.Assertions.assertThat;

import com.stahlferro.kusa.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@DataJpaTest
/*  Below annotation is IMPORTANT because by default, @DataJpaTest uses in memory H2 database for repo tests, in which
    currently, Kusa does not have a H2 db as its dependency
*/
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void injectedComponentsAreNotNull() throws Exception {
        assertThat(userRepository).isNotNull();
    }

//    @Test
//    void testNum() throws Exception {
//        assertThat(4).isEqualTo(4);
//    }
}
