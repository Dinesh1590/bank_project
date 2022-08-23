package com.bankproject.bankofAp.service;

import com.bankproject.bankofAp.entity.LoginEntity;
import com.bankproject.bankofAp.exceptions.InvalidEmailException;
import com.bankproject.bankofAp.exceptions.InvalidLoginException;
import com.bankproject.bankofAp.model.LoginModel;
import com.bankproject.bankofAp.repository.LoginRepo;
import com.bankproject.bankofAp.service.validations.EmailValidator;
import com.bankproject.bankofAp.service.validations.PasswordValidator;
import com.bankproject.bankofAp.service.validations.PhoneValidator;
import com.bankproject.bankofAp.service.validations.SecurityCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginServices {

    @Autowired
    private LoginRepo loginRepo;
    @Autowired
    private PasswordValidator passwordValidator;
    @Autowired
    private EmailValidator emailValidator;
    @Autowired
    private PhoneValidator phoneValidator;

    @Autowired
    private SecurityCheck securityCheck;

    @Transactional
    public String loginService(LoginModel signIn) {
        String status;
        String decrypt = null;
        String decrypt1 = null;

        if (signIn.getUsername().equals(null) || signIn.getPassword().equals((null))) {
            throw new InvalidLoginException("Username or Password cannot be null or empty");
        }

        LoginEntity user = getUserByUsername(signIn.getUsername());
        LoginEntity user1 = null;

        if (user == null) {
            user1 = getUserByEmail(signIn.getUsername());
            decrypt1 = securityCheck.decrypt(user1.getPassword());

        } else {
            decrypt = securityCheck.decrypt(user.getPassword());
        }

        if ((signIn.getPassword().equals(decrypt) && signIn.getUsername().equals(user.getUsername())) || (signIn.getPassword().equals(decrypt1) && signIn.getUsername().equals(user1.getEmail()))) {
            status = "successful login";
        } else {
            throw new InvalidLoginException("Invalid email or password");
        }
        return status;
    }

    public LoginEntity getUserByEmail(String email) {
        if (email == null) {
            throw new InvalidEmailException("email cannot be null");
        }
        return loginRepo.findByEmail(email);
    }

    public LoginEntity getUserByUsername(String username) {
        if (username == null) {
            throw new InvalidEmailException("username cannot be null");
        }
        return loginRepo.findByUsername(username);
    }
}
