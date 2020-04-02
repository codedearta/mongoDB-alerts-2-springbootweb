package co.uk.mongodb.renfer.mongodb.om.alerts;

public class JavaClientNotification {
    private Integer errorCode;

    public JavaClientNotification(){
    }

    public JavaClientNotification(Integer errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
