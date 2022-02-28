package com.refactorizando.example.pulsar.config;

import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PulsarConfiguration {

  private static final String SERVICE_URL =  "http://localhost:8080";

  @Bean
  public PulsarClient pulsarClient() throws PulsarClientException {

    return PulsarClient.builder()
        .serviceUrl(SERVICE_URL)
        .build();

  }
}
