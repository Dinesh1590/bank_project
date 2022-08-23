package com.bankproject.bankofAp.service.validations;

import com.bankproject.bankofAp.model.PropertiesDTO;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class MailSenderApp {

    public void sendMail(PropertiesDTO propertiesDTO, String body,String email) {

        // Getting system properties
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", propertiesDTO.getHost());
        properties.put("mail.smtp.socketFactory.port", propertiesDTO.getPort());
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", propertiesDTO.getPort());

        // Setting up mail server from application properties

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(propertiesDTO.getSender(), propertiesDTO.getPassword());

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(propertiesDTO.getSender()));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

            // Set Subject: header field
            message.setSubject("BankOfAp");

            // Now set the actual message
            message.setContent(body, "text/html");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}














