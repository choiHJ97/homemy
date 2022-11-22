package com.acpla.homemy.controller;
import com.acpla.homemy.model.Customer;
import com.acpla.homemy.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/login")
    public String login(){
        return "account/login";
    }


    @GetMapping("/register")
    public String register(){

        return "account/register";
    }

    @PostMapping("/register")
    public String register(Customer customer){
        customerService.save(customer);
        return "redirect:/";
    }
}
