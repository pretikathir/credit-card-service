package com.credit.card.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.credit.card.exception.DuplicateCardNoException;
import com.credit.card.exception.InvalidCheckLuhnException;
import com.credit.card.model.ApiErrorResponse;

@RestControllerAdvice
public class CreditCardExceptionHandlerEndpoint {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			WebRequest request) {

		return new ResponseEntity<>(ApiErrorResponse.builder().timestamp(LocalDateTime.now())
				.statusCode(BAD_REQUEST.value()).description(request.getDescription(false))
				.errors(ex.getBindingResult().getFieldErrors().stream()
						.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()))
				.build(), BAD_REQUEST);

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidCheckLuhnException.class)
	protected ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValid(InvalidCheckLuhnException ex,
			WebRequest request) {

		return new ResponseEntity<>(
				ApiErrorResponse.builder().timestamp(LocalDateTime.now()).statusCode(BAD_REQUEST.value())
						.description(request.getDescription(false)).message(ex.getMessage()).build(),
				BAD_REQUEST);

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DuplicateCardNoException.class)
	protected ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValid(DuplicateCardNoException ex,
			WebRequest request) {

		return new ResponseEntity<>(
				ApiErrorResponse.builder().timestamp(LocalDateTime.now()).statusCode(BAD_REQUEST.value())
						.description(request.getDescription(false)).message(ex.getMessage()).build(),
				BAD_REQUEST);

	}
}
