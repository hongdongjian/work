package Dto;

import java.io.File;

public class Mail {
    String toMails[];
    String subject;
    String content;
    File file;

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

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
