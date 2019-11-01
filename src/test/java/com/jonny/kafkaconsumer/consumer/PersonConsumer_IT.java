package com.jonny.kafkaconsumer.consumer;

import com.jonny.kafkaconsumer.model.Person;
import org.jeasy.random.EasyRandom;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class PersonConsumer_IT {

  private static String PERSON_TOPIC = "Person_Topic";

  @Autowired
  private PersonConsumer testObject;

  @Autowired
  private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

  private KafkaTemplate<String, Person> template;
  private EasyRandom easyRandom = new EasyRandom();

  @ClassRule
  public static EmbeddedKafkaRule embeddedKafka =
      new EmbeddedKafkaRule(1, true, PERSON_TOPIC);

  @Before
  public void setUp() throws Exception {
    // set up the Kafka producer properties
    Map<String, Object> senderProperties =
        KafkaTestUtils.senderProps(
            embeddedKafka.getEmbeddedKafka().getBrokersAsString());

    // create a Kafka producer factory
    ProducerFactory<String, Person> producerFactory =
        new DefaultKafkaProducerFactory<>(
            senderProperties);

    // create a Kafka template
    template = new KafkaTemplate<>(producerFactory);
    // set the default topic to send to
    template.setDefaultTopic(PERSON_TOPIC);

    // wait until the partitions are assigned
    for (MessageListenerContainer messageListenerContainer : kafkaListenerEndpointRegistry
        .getListenerContainers()) {
      ContainerTestUtils.waitForAssignment(messageListenerContainer,
          embeddedKafka.getEmbeddedKafka().getPartitionsPerTopic());
    }
  }

  @Test
  public void testReceive() throws Exception {
    // send the message
    Person person = easyRandom.nextObject(Person.class);
    template.sendDefault(PERSON_TOPIC, person);

//    testObject.getLatch().await(10000, TimeUnit.MILLISECONDS);
    testObject.consumePerson(person);
    // check that the message was received
//    assertThat(testObject.getLatch().getCount()).isEqualTo(0);
  }
}