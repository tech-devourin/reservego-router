package com.innobliss.reservego_router;

import javax.net.ssl.SSLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import reactor.netty.http.client.HttpClient;

@Configuration
public class ReserveGoRouterConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
       ObjectMapper mapper = new ObjectMapper();
       mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
       mapper.registerModule(new JavaTimeModule());
       mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
       mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
       return mapper;
    }

    @Bean
    public WebClient.Builder getWebClientBuilder(ObjectMapper objectMapper) throws SSLException {
       SslContext sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE)
             .build();
       HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));
       return WebClient.builder()
             .exchangeStrategies(ExchangeStrategies.builder()
                   .codecs(configurer -> {
                      configurer.defaultCodecs().jackson2JsonEncoder(
                            new Jackson2JsonEncoder(objectMapper, MediaType.APPLICATION_JSON));
                      configurer.defaultCodecs().jackson2JsonDecoder(
                            new Jackson2JsonDecoder(objectMapper, MediaType.APPLICATION_JSON));
                      configurer.defaultCodecs().maxInMemorySize(100000 * 1024);
                   })
                   .build())
             .clientConnector(new ReactorClientHttpConnector(httpClient));
    }
}