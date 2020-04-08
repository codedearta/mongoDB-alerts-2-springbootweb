package co.uk.mongodb.renfer.mongodb.om.alerts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private MongoDBAlertController controller;

	@Test
	public void contexLoads() throws Exception {
		assertThat(controller).isNotNull();
	}


//	@LocalServerPort
//	private int port;
//
//	@Autowired
//	private TestRestTemplate restTemplate;
//
//	@Test
//	public void greetingShouldReturnDefaultMessage() {
//
//		this.restTemplate.getForObject("http://localhost:" + port + "/",
//				String.class);
//		assertThat(
//				).contains("Hello, World");
//	}
}


