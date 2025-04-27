package io.raeliss.runnerz;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RunnerzApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void helloWorldTest() {
		String message = "Hello, World!";
		assertEquals("Hello, World!", message);
	}

}
