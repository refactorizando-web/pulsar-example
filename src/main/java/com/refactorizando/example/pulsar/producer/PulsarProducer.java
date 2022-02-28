package com.refactorizando.example.pulsar.producer;

import static java.util.stream.Collectors.toList;

import com.refactorizando.example.pulsar.config.PulsarConfiguration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.CompressionType;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.MessageId;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PulsarProducer {

  private static final String TOPIC_NAME = "my-topic";

  private final PulsarClient client;


  @Bean(name = "producer")
  public void producer() throws PulsarClientException {

    Producer<byte[]> producer = client.newProducer()
        .topic(TOPIC_NAME)
        .compressionType(CompressionType.LZ4)
        .create();

    IntStream.range(1, 5).forEach(i -> {
      String content = String.format("message number: %d", i);

      // Send each message and log message content and ID when successfully received
      try {
        producer.send(content.getBytes());
      } catch (PulsarClientException e) {
        log.error("Error sending mesasage");
        e.printStackTrace();
      }

      System.out.println("Published message '"+content );

    });

    producer.close();

  }



}
