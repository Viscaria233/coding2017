package ood.srp.bean;

/**
 * Created by Haochen on 2017/6/17.
 * TODO:
 */
public class Email {
    private String destinationAddress;
    private String subject;
    private String message;

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
