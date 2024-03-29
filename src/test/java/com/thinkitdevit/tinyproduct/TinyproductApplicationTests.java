package com.thinkitdevit.tinyproduct;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"eureka.client.enabled=false"})
class TinyproductApplicationTests {

	@Test
	void contextLoads() {
	}

}
