package com.example.paymentservice.config;




import com.example.paymentservice.controller.command.PaymentAggregate;
import com.mongodb.client.MongoClient;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.extensions.mongo.DefaultMongoTemplate;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.serialization.json.JacksonSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class AxonConfig {

    @Bean
    EventSourcingRepository<PaymentAggregate> orderAggregateEventSourcingRepository(EventStore eventStore){
        EventSourcingRepository<PaymentAggregate> repository = EventSourcingRepository.builder(PaymentAggregate.class).eventStore(eventStore).build();
        return repository;
    }

    @Bean

    public EventStorageEngine storageEngine(MongoClient client) {

        return MongoEventStorageEngine.builder()
                .mongoTemplate(DefaultMongoTemplate.builder().mongoDatabase(client, "PaymentsEventSourcing").build())
                .eventSerializer(JacksonSerializer.defaultSerializer())
                .snapshotSerializer(JacksonSerializer.defaultSerializer())
                .build();
    }

}
