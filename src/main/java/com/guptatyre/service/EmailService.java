package com.guptatyre.service;

import com.guptatyre.dto.ContactMessageDto;

public interface EmailService {
    void sendContactEmail(ContactMessageDto dto);
}
