package co.uk.mongodb.renfer.mongodb.om.alerts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AlertResult {
    public AlertResult(){}
    public Date created;
    public String eventTypeName;
    public String id;
    public Date lastNotified;
    public String replicaSetName;
    public String status;
    public String typeName;
    public Date updated;

    public AlertStatus getAlertStatus(){
        return AlertStatus.valueOf(this.status);
    }
}
