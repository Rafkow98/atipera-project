package com.task.atipera;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AtiperaApplicationTests {
	@Autowired
	private TestRestTemplate template;

	@Test
	public void testExistingGitHubUser() {
		ResponseEntity<String> response = template.getForEntity("/Rafkow98", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void testNonExistingGitHubUser() {
		ResponseEntity<String> response = template.getForEntity("/NonExistingGitHubUser", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

}
