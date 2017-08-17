package io.fabioviana.microservices.config;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = DEFINED_PORT)
public class ConfigServerApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void contextLoads() {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = testRestTemplate.getForEntity("/service-registry/master", Map.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}
}