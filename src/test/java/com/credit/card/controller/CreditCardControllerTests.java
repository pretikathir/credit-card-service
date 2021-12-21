package com.credit.card.controller;

import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.credit.card.model.CardDetails;
import com.credit.card.service.CreditCardService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CreditCardController.class)
public class CreditCardControllerTests {

	@MockBean
	CreditCardService creditCardService;
	@Autowired
	MockMvc mockMvc;
	private CardDetails cardDetails;
	private static final String CREDITCARD_ENDPOINT = "/creditcard/";
	@Autowired
	ObjectMapper objectMapper;

	@Before
	public void setup() {
		cardDetails = CardDetails.builder().name("XYZ").cardNo("1358954993914435").limit("123").build();
	}

	@Test
	public void testCreateCreditDetails() throws Exception {
		when(creditCardService.createCreditDetails(cardDetails)).thenReturn(cardDetails);
		mockMvc.perform(post(CREDITCARD_ENDPOINT).content(CONTENT_TYPE)
				.content(objectMapper.writeValueAsString(cardDetails)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	public void testRetrieveCustomers() throws Exception {
		when(creditCardService.retrieveCreditDetails()).thenReturn(Collections.singletonList(cardDetails));
		mockMvc.perform(get(CREDITCARD_ENDPOINT).content(CONTENT_TYPE).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
