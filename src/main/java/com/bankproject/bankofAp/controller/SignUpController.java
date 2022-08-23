package com.bankproject.bankofAp.controller;

import com.bankproject.bankofAp.model.SignUpModel;
import com.bankproject.bankofAp.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @PostMapping("/register")
    public ResponseEntity<String> registerNewUserAccount(@RequestBody SignUpModel sign) {

        String output = signUpService.register(sign);

        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}
