package com.joaobertholino.serviceproducer.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaConfiguration {

	@Value(value = "${spring.kafka.bootstrap-servers}")
	private String kafkaServer;

	@Value(value = "${spring.kafka.payment.request.topic}")
	private String kafkaTopic;
	private final KafkaProperties kafkaProperties;

	@Bean
	ProducerFactory<String, String> producerFactory() {
		Map<String, Object> configProps = this.kafkaProperties.buildConsumerProperties();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.kafkaServer);
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		return new DefaultKafkaProducerFactory<>(configProps);
	}

	@Bean
	KafkaTemplate<String, String> kafkaTemplate() {
		return new KafkaTemplate<>(this.producerFactory());
	}

	@Bean
	NewTopic createNewTopic() {
		return TopicBuilder
				.name(this.kafkaTopic)
				.partitions(1)
				.replicas(1)
				.build();
	}
}
