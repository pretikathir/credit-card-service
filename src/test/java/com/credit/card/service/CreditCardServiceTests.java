package com.credit.card.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Collections;

import com.credit.card.exception.DuplicateCardNoException;
import com.credit.card.exception.InvalidCheckLuhnException;
import com.credit.card.model.Stringconstant;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.credit.card.model.CardDetails;
import com.credit.card.repository.CreditCardRepository;

@ContextConfiguration(classes = CreditCardService.class)
@TestPropertySource(locations = "classpath:application.properties")
@RunWith(SpringRunner.class)
public class CreditCardServiceTests {

	@Autowired
	CreditCardService creditCardService;
	@MockBean
	CreditCardRepository creditCardRepository;
	private CardDetails cardDetails;

	@Before
	public void setup() {
		cardDetails = CardDetails.builder().name("testName").cardNo("1358954993914435").limit("2311").build();
	}

	@Test
	public void testsRetrieveCreditDetails() {
		when(creditCardRepository.findAll()).thenReturn(Collections.singletonList(cardDetails));
		assertEquals(Collections.singletonList(cardDetails), creditCardService.retrieveCreditDetails());
		assertTrue(creditCardService.retrieveCreditDetails().size() > 0);
	}

	@Test
	public void testCreateCreditCardDetails() {
		when(creditCardRepository.save(cardDetails)).thenReturn(cardDetails);
		assertEquals(cardDetails, creditCardService.createCreditDetails(cardDetails));
	}

	@Test(expected = InvalidCheckLuhnException.class)
	public void testCreateCreditCardDetails_Throw_Luhn_Exception() {
		when(creditCardRepository.save(cardDetails)).thenThrow(new InvalidCheckLuhnException(Stringconstant.LUHN_EXCEPTION_MESSAGE));
		creditCardRepository.save(cardDetails);
	}

	@Test(expected = DuplicateCardNoException.class)
	public void testCreateCreditCardDetails_Throw_Duplicate_Exception() {
		when(creditCardRepository.save(cardDetails)).thenThrow(new DuplicateCardNoException(Stringconstant.DUPLICATE_EXCEPTION_MESSAGE));
		creditCardRepository.save(cardDetails);
	}
}
