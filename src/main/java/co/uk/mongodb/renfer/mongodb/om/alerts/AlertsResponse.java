package co.uk.mongodb.renfer.mongodb.om.alerts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AlertsResponse {
    public AlertsResponse() {}
    public ArrayList<Map<String,Object>> results;
}

