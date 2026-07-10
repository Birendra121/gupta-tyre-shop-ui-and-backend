package com.guptatyre.service;


import com.guptatyre.dto.ContactMessageDto;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendContactEmail(ContactMessageDto dto) {

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo("y121birendra@gmail.com");

        mail.setSubject("New Customer Enquiry - Gupta Tyre Shop");

        mail.setText(

                "New enquiry received.\n\n"

                        + "Name : "
                        + dto.getFullName()

                        + "\n\nMobile : "
                        + dto.getMobile()

                        + "\n\nEmail : "
                        + dto.getEmail()

                        + "\n\nSubject : "
                        + dto.getSubject()

                        + "\n\nMessage :\n"
                        + dto.getMessage()

        );

        mailSender.send(mail);

    }

}