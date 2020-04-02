package co.uk.mongodb.renfer.rbspayments;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MongoDBAlertController {

    @PostMapping("/javaClientAlert")
    public void javaClientAlert(@RequestBody JavaClientNotification notification) {
//        MongoDBClusterHealthIndicator.IS_HEALTHY = notification.getErrorCode();
    }

    @PostMapping("/omAlert")
    public void omAlert(@RequestBody Map<String, Object> alert) {
        MongoDBOpsManagerHealthIndicator.sendAlert(alert);
    }
}
