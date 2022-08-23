package com.bankproject.bankofAp.service;

import com.bankproject.bankofAp.entity.SignUpEntity;
import com.bankproject.bankofAp.exceptions.InvalidEmailException;
import com.bankproject.bankofAp.exceptions.InvalidUserDataException;
import com.bankproject.bankofAp.exceptions.InvalidUsernameException;
import com.bankproject.bankofAp.model.PropertiesDTO;
import com.bankproject.bankofAp.model.SignUpModel;
import com.bankproject.bankofAp.repository.SignUpRepo;
import com.bankproject.bankofAp.service.validations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class SignUpService {
    @Autowired
    private PropertiesDTO properties;

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private SignUpRepo signUpRepo;
    @Autowired
    private PasswordValidator passwordValidator;
    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private PhoneValidator phoneValidator;
    @Autowired
    private SecurityCheck securityCheck;

    private Integer currentyear = 2022;

    @Transactional
    public String register(SignUpModel sign) {
        if (sign == null) {
            throw new InvalidUserDataException("User account data cannot be null");
        }
        checkIfUsernameNotUsed(sign.getUsername());
        passwordValidator.checkPassword(sign.getPassword());
        emailValidator.checkEmail(sign.getEmail());

        checkIfEmailNotUsed(sign.getEmail());

        String status = "successfully registered";
        SignUpEntity signUp = new SignUpEntity();
        mapModelToEntity(sign, signUp);
        signUp = signUpRepo.save(signUp);

        if (sign != null) {
            String body = "<h1>Hello</h1>" + "<h2>Your Account Created successfully.</h2>";
            signUpService.sendEmail(sign.getEmail(), body);
        } else {
            status = "user account not Registered";
        }
        return status;
    }

    private void checkIfEmailNotUsed(String email) {
        SignUpEntity userByEmail = getUserByEmail(email);
        if (userByEmail != null) {
            String msg = String.format("The email %s it's already in use from another user with ID = %s");

            throw new InvalidUserDataException(String.format("This email %s it's already in use."));
        }
    }

    private void checkIfUsernameNotUsed(String username) {

        SignUpEntity userByUsername = getUserByUsername(username);
        if (userByUsername != null) {
            String msg = String.format("The username %s it's already in use from another user with ID = %s", userByUsername.getUsername());

            throw new InvalidUserDataException(msg);
        }
    }

    private void mapModelToEntity(SignUpModel sign, SignUpEntity signUp) {

        signUp.setUsername(sign.getUsername());
        signUp.setPassword(securityCheck.encrypt(sign.getPassword()));
        signUp.setFirstName(sign.getFirstName());
        signUp.setLastName(sign.getLastName());
        signUp.setEmail(sign.getEmail());
        signUp.setPhoneNumber(sign.getPhoneNumber());
        signUp.setDateOfBirth(sign.getDateOB());
        signUp.setGender(sign.getGender());
    }

    public SignUpEntity getUserByUsername(String username) {
        if (username == null) {
            throw new InvalidUsernameException("username cannot be null");
        }
        return signUpRepo.findByUsername(username);
    }

    public SignUpEntity getUserByEmail(String email) {
        if (email == null) {
            throw new InvalidEmailException("email cannot be null");
        }
        return signUpRepo.findByEmail(email);
    }

    public List<SignUpEntity> getByUser(String name) {
        if (name == null) {
            throw new InvalidUsernameException("username cannot be null");
        }
        SignUpEntity user = signUpRepo.findByUsername(name);

        if (user == null) {
            throw new InvalidUserDataException("Invalid user details");
        }

        List list = new ArrayList<>();

        list.add(user.getUsername());
        list.add(user.getFirstName());
        list.add(user.getFirstName());
        list.add(user.getEmail());
        list.add(user.getGender());
        list.add(ageFinder(user.getDateOfBirth()));
        list.add(user.getPhoneNumber());

        return list;
    }

    private int ageFinder(String dateOfBirth) {
        String[] dob = dateOfBirth.split("-");

        return (currentyear - Integer.parseInt(dob[2]));
    }

    public void sendEmail(String email, String body) {
        MailSenderApp app = new MailSenderApp();
        app.sendMail(properties, body, email);
    }
}
