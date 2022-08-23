package com.bankproject.bankofAp.model;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesDTO {
    @Value("${host}")
    String host;
    /*@Value("${recipient}")
    String recipient;*/
    @Value("${sender}")
    String sender;
    @Value("${port}")
    String port;

    @Value("${password}")
    String password;
    @Value("${reset.linkMessage}")
    String resetMessage;
    @Value("${update.PasswordMessage}")
    String updatePasswordMessage;

    public String getPassword() {
        return password;
    }

    public String getResetMessage() {
        return resetMessage;
    }

    public String getUpdatePasswordMessage() {
        return updatePasswordMessage;
    }

    public String getHost() {
        return host;
    }

//    public String getRecipient() {
//        return recipient;
//    }

    public String getSender() {
        return sender;
    }

    public String getPort() {
        return port;
    }
}

