package com.example.tutorrequirementsearchservice.configs;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticConfig {

    // Get the host and port from the application.properties file
    @Value("${app.elastic.host}")
    private String host;

    @Value("${app.elastic.port}")
    private int port;

    // Create an ElasticsearchClient bean
    @Bean
    public ElasticsearchClient elasticsearchClient(){
        // Create a RestClient using the host and port from the application.properties file
        RestClient httpClient = RestClient.builder(new HttpHost(host, port))
                .build();
        // Create an ElasticsearchTransport using the RestClient
        ElasticsearchTransport transport = new RestClientTransport(httpClient, new JacksonJsonpMapper());
        // Create an ElasticsearchClient using the ElasticsearchTransport
        ElasticsearchClient esClient = new ElasticsearchClient(transport);
        // Return the ElasticsearchClient
        return esClient;
    }

}