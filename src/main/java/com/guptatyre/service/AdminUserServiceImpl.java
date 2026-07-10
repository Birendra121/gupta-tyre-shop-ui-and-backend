package com.guptatyre.service;



import com.guptatyre.dto.RegisterAdminRequest;
import com.guptatyre.entity.AdminUser;
import com.guptatyre.repository.AdminUserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    private final AdminUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AdminUserServiceImpl(AdminUserRepository repository,
                                PasswordEncoder passwordEncoder) {

        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(RegisterAdminRequest request) {

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match.");
        }

        if (repository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists.");
        }

        AdminUser admin = new AdminUser();

        admin.setFullName(request.getFullName());

        admin.setUsername(request.getUsername());

        admin.setPassword(passwordEncoder.encode(request.getPassword()));

        admin.setActive(true);

        repository.save(admin);

    }

}
