package com.joaobertholino.serviceconsumer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ObjectDto(
		@NotNull(message = "The object number cannot be null.")
		@Positive(message = "The object number must be greater than and non-zero.")
		Integer number,

		@NotNull(message = "The object amount cannot be null.")
		@Positive(message = "The object amount must be higher and different from zero.")
		BigDecimal value,

		@NotBlank(message = "The object description cannot be null or empty.")
		String description
) {
}
