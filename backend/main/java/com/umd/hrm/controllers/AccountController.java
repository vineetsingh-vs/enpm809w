package com.umd.hrm.controllers;

import com.umd.hrm.models.Account;
import com.umd.hrm.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hrm")
public class AccountController {
    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody  Account account) {
        return accountService.login(account.getUsername(), account.getPassword());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Object> register(@RequestBody  Account account) {
        return accountService.registerAccount(account);
    }

}
