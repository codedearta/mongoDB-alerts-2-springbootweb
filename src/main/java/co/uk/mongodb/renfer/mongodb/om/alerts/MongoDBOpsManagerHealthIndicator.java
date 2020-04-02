package co.uk.mongodb.renfer.mongodb.om.alerts;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MongoDBOpsManagerHealthIndicator implements HealthIndicator {
    private static Map<String, Object> latestAlert;

    public static void sendAlert(Map<String, Object> alert) {
        latestAlert = alert;
    }

    @Override
    public Health health() {
        final String status = (String) latestAlert.getOrDefault("status", "");

        if(status.equals("")){
            return Health.up().build();
        }else{
            AlertStatus alertStatus = AlertStatus.valueOf(status);
            switch(alertStatus) {
                case TRACKING:
                case OPEN:
                    return Health.down().withDetails(latestAlert).build();
                case CLOSED:
                case CANCELLED:
                default:
                    return Health.up().withDetails(latestAlert).build();
            }
        }
    }
}
