package com.aegis.util.mail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDetail {
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;

    public EmailDetail() {

    }

    public EmailDetail(String recipient, String message) {
        this.msgBody = message;
        this.subject = "no-reply";
    }
}
