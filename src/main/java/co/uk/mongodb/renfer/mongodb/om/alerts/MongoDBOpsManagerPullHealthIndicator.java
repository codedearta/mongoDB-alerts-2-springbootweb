package co.uk.mongodb.renfer.mongodb.om.alerts;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.tomcat.util.json.JSONParser;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.ReactiveHealthIndicator;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class MongoDBOpsManagerPullHealthIndicator implements HealthIndicator {
    @Autowired
    MongoClient mongoClient;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    String alertsUrl;

    @Override
    public Health health() {
        AlertsResponse alerts = restTemplate.getForObject(alertsUrl, AlertsResponse.class);
        final Optional<Map<String,Object>> too_few_healthy_member = alerts.results
                .stream()
                .filter(alert ->
                        (alert.get("eventTypeName").equals("TOO_FEW_HEALTHY_MEMBERS")) &&
                                alert.get("status").equals(AlertStatus.OPEN.name())
                                || alert.get("status").equals(AlertStatus.TRACKING.name())
                ).findAny();
        if(too_few_healthy_member.isPresent()) {
            return Health.down().withDetails(too_few_healthy_member.get()).build();
        } else {
            return Health.up().build();
        }
    }
}
