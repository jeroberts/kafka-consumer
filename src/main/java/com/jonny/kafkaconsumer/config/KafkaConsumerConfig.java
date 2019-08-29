package com.jonny.kafkaconsumer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jonny.kafkaconsumer.model.Person;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

  @Value("${kafka.autoOffsetResetConfig}")
  private String autoOffsetResetConfig;

  @Value("${kafka.groupId.person}")
  private String personGroupId;

  @Value("${kafka.servers}")
  private String kafkaServers;

  @Bean
  public ConsumerFactory<String, Person> consumerFactory() {
    Map<String, Object> config = new HashMap<>();
    config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServers);
    config.put(ConsumerConfig.GROUP_ID_CONFIG, personGroupId);
    config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

    // Set useHeadersIfPresent to false so that the inferred type Person can be deserialized
    return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(Person.class, false));
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Person> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, Person> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }
}
