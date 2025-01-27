package com.joaobertholino.serviceproducer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.joaobertholino.serviceproducer.dto.ObjectDto;
import com.joaobertholino.serviceproducer.service.KafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/resource")
@RequiredArgsConstructor
public class PaymentController {
	private final KafkaService kafkaService;

	@PostMapping(value = "/access", produces = "application/json", consumes = "application/json")
	public ResponseEntity<ObjectDto> resourceAccess(@RequestBody ObjectDto objectDto) throws JsonProcessingException {
		return ResponseEntity.status(HttpStatus.OK).body(this.kafkaService.sendMessage(objectDto));
	}
}
