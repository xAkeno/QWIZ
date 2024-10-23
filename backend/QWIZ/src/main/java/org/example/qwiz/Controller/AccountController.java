package org.example.qwiz.Controller;

import org.example.qwiz.Model.Account;
import org.example.qwiz.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AccountController {
    private AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping("/register")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        if(account == null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        if(accountService.create(account).isPresent()){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
