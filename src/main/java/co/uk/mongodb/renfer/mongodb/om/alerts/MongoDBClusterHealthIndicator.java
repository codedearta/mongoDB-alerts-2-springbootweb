package co.uk.mongodb.renfer.mongodb.om.alerts;

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
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class MongoDBClusterHealthIndicator extends AbstractHealthIndicator {
    @Autowired
    MongoClient mongoClient;

    @Override
    protected void doHealthCheck(Health.Builder builder) throws MongoException {
        final MongoDatabase db = this.mongoClient.getDatabase("rbs");
        final MongoCollection<Document> collection = this.mongoClient
                .getDatabase("rbs")
                .getCollection("health")
                .withReadPreference(ReadPreference.primary())
                .withReadConcern(ReadConcern.MAJORITY)
                .withWriteConcern(WriteConcern.MAJORITY);


        Document result = db.runCommand(new Document( "buildInfo", 1));
        builder.up().withDetail("version", result.getString("version")).build();
    }
}
