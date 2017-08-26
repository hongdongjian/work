package Dto;

public class Mail {
    String toMails[];
    String subject;
    String content;

    public String[] getToMails() {
        return toMails;
    }

    public void setToMails(String[] toMails) {
        this.toMails = toMails;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
