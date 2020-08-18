package com.killerwilmer.cards;

import com.killerwilmer.cards.controller.CardController;
import com.killerwilmer.cards.exception.CardNotFoundException;
import com.killerwilmer.cards.repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CardsApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private CardRepository repository;
	@Autowired
	private CardController cardController;

	@Test
	void contextLoads() {
	}

	@Test
	public void testNotFound() {
		String body = this.restTemplate.getForObject("/", String.class);
		assertThat(body).contains("Not Found");
	}

	@Test()
	public void testDeleteCardIsCardNotFoundException() {

		String idCard = "12345";

		Exception exception = assertThrows(CardNotFoundException.class, () -> {
			cardController.deleteCard(idCard);
		});

		String expectedMessage = "Could not find card " + idCard;
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test()
	public void testGetCardByIdIsCardNotFoundException() {

		String idCard = "12345678";

		Exception exception = assertThrows(CardNotFoundException.class, () -> {
			cardController.getOneCard(idCard);
		});

		String expectedMessage = "Could not find card " + idCard;
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

}
