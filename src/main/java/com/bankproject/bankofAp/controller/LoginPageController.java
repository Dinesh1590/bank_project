package com.bankproject.bankofAp.controller;

import com.bankproject.bankofAp.entity.SignUpEntity;
import com.bankproject.bankofAp.model.LoginModel;
import com.bankproject.bankofAp.model.SignUpModel;
import com.bankproject.bankofAp.service.LoginServices;
import com.bankproject.bankofAp.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoginPageController {

    @Autowired
    private LoginServices loginServices;

    @Autowired
    private SignUpService signUpService;

    @PostMapping("/login")
    public ResponseEntity<String> registerNewUserAccount(@RequestBody LoginModel signIn) {

        String output = loginServices.loginService(signIn);

        return new ResponseEntity<>(output, HttpStatus.OK);
    }

   @GetMapping("/getInformation/{username}")
    public ResponseEntity<List<SignUpEntity>> displayInfoById(@PathVariable String username){

        return new ResponseEntity<>(signUpService.getByUser(username),HttpStatus.OK);

    }
}
