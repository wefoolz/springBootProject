package com.onlineExamSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SignupController {

    @PostMapping("/signup-request")
    public void OrgSignUp(@RequestBody Object obj){

    }
}
