package co.uk.mongodb.renfer.mongodb.om.alerts;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MongoDBAlertController {

    @PostMapping("/javaClientAlert")
    public void javaClientAlert(@RequestBody Map<String, Object> alert) {
        MongoDBJavaClientHealthIndicator.sendAlert(alert);
    }

    @PostMapping("/omAlert")
    public void omAlert(@RequestBody Map<String, Object> alert, @RequestBody String salert) {
        MongoDBOpsManagerPushHealthIndicator.sendAlert(alert);
    }
}
