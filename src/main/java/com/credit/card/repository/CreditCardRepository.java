package com.credit.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.credit.card.model.CardDetails;

@Repository
public interface CreditCardRepository extends JpaRepository<CardDetails, String> {
	CardDetails findByCardNo(String cardNumber);
}
