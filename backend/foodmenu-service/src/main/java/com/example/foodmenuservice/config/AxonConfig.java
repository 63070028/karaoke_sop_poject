package com.example.foodmenuservice.config;


import com.example.foodmenuservice.controller.command.FoodMenuAggregate;
import com.mongodb.client.MongoClient;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.extensions.mongo.DefaultMongoTemplate;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.serialization.json.JacksonSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Bean
    EventSourcingRepository<FoodMenuAggregate> foodMenuAggregateEventSourcingRepository(EventStore eventStore){
        EventSourcingRepository<FoodMenuAggregate> repository = EventSourcingRepository.builder(FoodMenuAggregate.class).eventStore(eventStore).build();
        return repository;
    }

    @Bean
    public EventStorageEngine storageEngine(MongoClient client) {

        return MongoEventStorageEngine.builder()
                .mongoTemplate(DefaultMongoTemplate.builder().mongoDatabase(client, "FoodMenuEventSourcing").build())
                .eventSerializer(JacksonSerializer.defaultSerializer())
                .snapshotSerializer(JacksonSerializer.defaultSerializer())
                .build();
    }
}
