package com.joaobertholino.serviceconsumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaobertholino.serviceconsumer.dto.ObjectDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerService {
	private final ObjectMapper objectMapper;
	private final static Logger LOGGER = LoggerFactory.getLogger(ConsumerService.class);

	@KafkaListener(topics = "${spring.kafka.service.request.topic}")
	public void serviceConsumer(String objectJson) throws JsonProcessingException {
		ObjectDto objectDto = this.objectMapper.readValue(objectJson, ObjectDto.class);
		ConsumerService.LOGGER.info("Comunicação realizada com sucesso => {}", objectDto.getClass().getName());
	}
}
