package com.guptatyre.service;

import com.guptatyre.dto.ContactMessageDto;
import com.guptatyre.entity.ContactMessage;
import com.guptatyre.repository.ContactMessageRepository;
import org.springframework.stereotype.Service;

@Service
public class ContactMessageServiceImpl implements ContactMessageService{

    private final ContactMessageRepository repository;
    private final EmailService emailService;

    public ContactMessageServiceImpl(ContactMessageRepository repository, EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    @Override
    public void save(ContactMessageDto dto) {
        ContactMessage message = new ContactMessage();
        message.setFullName(dto.getFullName());
        message.setMobile(dto.getMobile());
        message.setEmail(dto.getEmail());
        message.setSubject(dto.getSubject());
        message.setMessage(dto.getMessage());

        repository.save(message);

        /*try{
            emailService.sendContactEmail(dto);
        }catch (Exception e){
                e.printStackTrace();
        }*/

        try {

            emailService.sendContactEmail(dto);

            System.out.println("✅ Email sent successfully.");

        } catch (Exception e) {

            System.err.println("❌ Email sending failed");

            e.printStackTrace();

            throw new RuntimeException(e);

        }

    }
}
