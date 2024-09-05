package hodau.backendapi.com.shopnoithat.payload.request;

import java.util.List;

public class EmailRequest {
    private List<String> toEmails; // Danh sách các địa chỉ email
    private String subject;
    private String body;

    // Getters and setters
    public List<String> getToEmails() {
        return toEmails;
    }

    public void setToEmails(List<String> toEmails) {
        this.toEmails = toEmails;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
