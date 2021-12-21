package com.credit.card.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ApiErrorResponse {
    int statusCode;
    LocalDateTime timestamp;
    String message;
    String description;
    List<String> errors;
}
