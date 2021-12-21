package com.credit.card.service;

import java.util.List;
import java.util.Objects;

import com.credit.card.model.Stringconstant;
import org.springframework.stereotype.Service;

import com.credit.card.exception.DuplicateCardNoException;
import com.credit.card.exception.InvalidCheckLuhnException;
import com.credit.card.model.CardDetails;
import com.credit.card.repository.CreditCardRepository;
import com.credit.card.validation.CreditCardValidation;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditCardService {

	private final CreditCardRepository creditCardRepository;

	public CardDetails createCreditDetails(CardDetails cardDetails) {
		if(!CreditCardValidation.checkLuhn(cardDetails.getCardNo())) 
		{
			throw new InvalidCheckLuhnException(Stringconstant.LUHN_EXCEPTION_MESSAGE);
		}
		if(Objects.nonNull(creditCardRepository.findByCardNo(cardDetails.getCardNo()))) {
			throw new DuplicateCardNoException(Stringconstant.DUPLICATE_EXCEPTION_MESSAGE);
		}
		cardDetails.setBalance(Double.valueOf("0.00"));
		return creditCardRepository.save(cardDetails);
	}

	public List<CardDetails> retrieveCreditDetails() {
		return creditCardRepository.findAll();
	}
}
