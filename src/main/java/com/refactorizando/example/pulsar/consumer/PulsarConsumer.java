package com.refactorizando.example.pulsar.consumer;

import lombok.RequiredArgsConstructor;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.SubscriptionType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PulsarConsumer {

  private static final String SUBSCRIPTION_NAME = "my-subscription";

  private static final String TOPIC_NAME = "my-topic";

  private final PulsarClient client;


  @Bean
  @DependsOn("producer")
  public void conumser() throws PulsarClientException {
    Consumer<byte[]> consumer = client.newConsumer()
        .topic(TOPIC_NAME)
        .subscriptionType(SubscriptionType.Shared)
        .subscriptionName(SUBSCRIPTION_NAME)
        .subscribe();

    do {
      Message<byte[]> msg = consumer.receive();

      String content = new String(msg.getData());
      System.out.println("Received message '" + content);

      consumer.acknowledge(msg);
    } while (true);

  }

}
