package com.jonny.kafkaconsumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude = KafkaAutoConfiguration.class)
@SpringBootTest()
//@EmbeddedKafka
//@DirtiesContext
//@EmbeddedKafka(partitions = 1,
//		topics = {
//				KafkaStreamsTests.STREAMING_TOPIC1,
//				KafkaStreamsTests.STREAMING_TOPIC2 })
public class KafkaConsumerApplication_IT {
	// TODO : Fix this test

//	@Autowired
//	private EmbeddedKafkaBroker embeddedKafkaBroker;

//	@Autowired
//	private EmbeddedKafkaBroker embeddedKafka;
//
//	@Test
//	public void someTest() {
//		Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("testGroup", "true", this.embeddedKafka);
//		consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//		ConsumerFactory<Integer, String> cf = new DefaultKafkaConsumerFactory<>(consumerProps);
//		Consumer<Integer, String> consumer = cf.createConsumer();
//		this.embeddedKafka.consumeFromAnEmbeddedTopic(consumer, KafkaStreamsTests.STREAMING_TOPIC2);
//		ConsumerRecords<Integer, String> replies = KafkaTestUtils.getRecords(consumer);
//		assertThat(replies.count()).isGreaterThanOrEqualTo(1);
//	}
//
//	@Configuration
//	@EnableKafkaStreams
//	public static class KafkaStreamsConfiguration {
//
//		@Value("${" + EmbeddedKafkaBroker.SPRING_EMBEDDED_KAFKA_BROKERS + "}")
//		private String brokerAddresses;
//
//		@Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
//		public KafkaStreamsConfiguration kStreamsConfigs() {
//			Map<String, Object> props = new HashMap<>();
//			props.put(StreamsConfig.APPLICATION_ID_CONFIG, "testStreams");
//			props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, this.brokerAddresses);
//			return new KafkaStreamsConfiguration(props);
//		}
//
//	}

	@Test
	public void contextLoads() {}

}
