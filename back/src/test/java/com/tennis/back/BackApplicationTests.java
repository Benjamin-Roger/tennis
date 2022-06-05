package com.tennis.back;

import com.tennis.back.driver.repository.PlayerRepositoryProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackApplicationTests {

	@Autowired
	PlayerRepositoryProperties properties;

	@Test
	void contextLoads() {
	}

}
