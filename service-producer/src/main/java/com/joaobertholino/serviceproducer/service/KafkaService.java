package com.joaobertholino.serviceproducer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaobertholino.serviceproducer.dto.ObjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaService {

	@Value(value = "${spring.kafka.service.request.topic}")
	private String kafkaTopic;

	private final ObjectMapper objectMapper;
	private final KafkaTemplate<String, String> kafkaTemplate;

	public ObjectDto sendMessage(ObjectDto objectDto) throws JsonProcessingException {
		String objectJson = this.objectMapper.writeValueAsString(objectDto);
		this.kafkaTemplate.send(this.kafkaTopic, objectJson);
		return objectDto;
	}

}
