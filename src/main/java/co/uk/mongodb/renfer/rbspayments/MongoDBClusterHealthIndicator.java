package co.uk.mongodb.renfer.rbspayments;

import com.mongodb.MongoException;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class MongoDBClusterHealthIndicator implements HealthIndicator {
    @Autowired
    MongoClient mongoClient;

    public static void SendMongoException(MongoException mongoException) {

    }

    @Override
    public Health health() {
        final MongoDatabase db = this.mongoClient.getDatabase("rbs");
        final MongoCollection<Document> collection = this.mongoClient
                .getDatabase("rbs")
                .getCollection("health")
                .withReadPreference(ReadPreference.primary())
                .withReadConcern(ReadConcern.MAJORITY)
                .withWriteConcern(WriteConcern.MAJORITY);

        final InsertOneResult healthCheck = collection.insertOne(new Document().append("healthCheck", new Date()));

        return Health.up().build();
//        Map<String,Object> details = Map.of("ErrorCode", IS_HEALTHY);
//        if(result != null) {
//            IS_HEALTHY = ErrorCodes.ALL_GOOD;
//            details = Map.of("version", result.getString("version"));
//        } else {
//            IS_HEALTHY = ErrorCodes.READ_FAILED;
//            details = Map.of("ErrorCode", IS_HEALTHY);
//        }
//
//        switch (IS_HEALTHY) {
//            case ErrorCodes.READ_FAILED : return Health.down().withDetails(details).build();
//            case ErrorCodes.WRITE_FAILED: return Health.down().withDetails(details).build();
//            default: return Health.up().withDetails(details).build();
//        }

    }
}
