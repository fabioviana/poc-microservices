package io.fabioviana.microservices.api.svca;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

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
public class SocialMediaServiceApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void contextLoads() {
		ResponseEntity<String> entity = testRestTemplate.getForEntity("/info/name", String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}
}