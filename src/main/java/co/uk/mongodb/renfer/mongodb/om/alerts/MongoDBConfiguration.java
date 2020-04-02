package co.uk.mongodb.renfer.mongodb.om.alerts;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoDBConfiguration {

    @Value("$spring.data.mongodb.uri")
    private String mongoURI;

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create();
    }
}
