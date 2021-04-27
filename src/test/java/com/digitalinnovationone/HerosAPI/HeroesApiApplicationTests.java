package com.digitalinnovationone.HerosAPI;

import com.digitalinnovationone.HerosAPI.repository.HeroesRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.digitalinnovationone.HerosAPI.constants.HeroesConstants.HEROES_ENDPOINT_LOCAL;

@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
@SpringBootTest
class HeroesApiApplicationTests {

	@Autowired
	WebTestClient webTestClient;

	@Autowired
	HeroesRepository heroesRepository;

	@Test
	public void getOneHeroById(){
		webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/id"), "1")
				.exchange()
				.expectStatus().isOk()
				.expectBody();
	}

	@Test
	public void getOneHeroNotFound(){
		webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/id"), "10")
				.exchange()
				.expectStatus().isNotFound();
	}

	@Test
	public void deleteHeroByID(){
		webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/id"), "1")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isNotFound()
				.expectBody(Void.class);
	}

}
