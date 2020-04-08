package co.uk.mongodb.renfer.mongodb.om.alerts;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MongoDBAlertController {

    @PostMapping("/javaClientAlert")
    public void javaClientAlert(@RequestBody AlertsResponse notification) {
//        MongoDBClusterHealthIndicator.IS_HEALTHY = notification.getErrorCode();
    }

    @PostMapping("/omAlert")
    public void omAlert(@RequestBody Map<String, Object> alert) {
        MongoDBOpsManagerPushHealthIndicator.sendAlert(alert);
    }
}
