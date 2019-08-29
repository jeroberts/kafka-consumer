package com.jonny.kafkaconsumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jonny.kafkaconsumer.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

// TODO : Write tests for this class
@Service
public class PersonConsumer {

  private static Logger LOG = LogManager.getLogger();

  @Autowired
  private ObjectMapper objectMapper;

  @KafkaListener(topics = "${kafka.topic.person}", groupId = "${kafka.groupId.person}", containerFactory = "kafkaListenerContainerFactory")
  public void consumePerson(Person person) {
    LOG.info(formatMessage(person));
  }

  private String formatMessage(Person person) {
    String message = null;
    try {
      message = objectMapper.writeValueAsString(person);
    } catch (JsonProcessingException e) {
      LOG.error(e);
    }

    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("===================================================================\n");
    stringBuilder.append("Reading new message\n");
    stringBuilder.append(message);
    stringBuilder.append("\n===================================================================");
    return stringBuilder.toString();
  }
}
