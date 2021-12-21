package com.credit.card.controller;

import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.credit.card.model.CardDetails;
import com.credit.card.service.CreditCardService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/credit-card")
@RequiredArgsConstructor
@Api(value = "API to Credit Card Management",
		description = "This API provides the capability to Store and Retrieve credit card Details", produces = "application/json")
public class CreditCardController {

	private final CreditCardService creditCardService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Created Credit Card Details")
	@ApiResponses({ @ApiResponse(code = 400, message = "Unable to Create Credit Card Details"),
			@ApiResponse(code = 201, message = "Created Credit Card Details") })
	public CardDetails createCreditDetails(@RequestBody @Valid CardDetails cardDetails) {
		return creditCardService.createCreditDetails(cardDetails);

	}

	@GetMapping
	@ApiOperation(value = "Retrieve list of Credit Card details")
	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully retrieved list") })
	public List<CardDetails> getCreditDetails() {
		return creditCardService.retrieveCreditDetails();

	}
}
