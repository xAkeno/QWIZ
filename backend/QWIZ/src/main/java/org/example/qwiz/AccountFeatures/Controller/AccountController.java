package org.example.qwiz.AccountFeatures.Controller;

import jakarta.servlet.http.HttpServletResponse;
import org.example.qwiz.Security.DTO.AuthDTO;
import org.example.qwiz.AccountFeatures.Model.Account;
import org.example.qwiz.AccountFeatures.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/auth")

public class AccountController {

    private AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        //If The json is empty
        if(account == null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        //process if json is not empty and if logic will work then send a http created
        if(accountService.create(account).isPresent()){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    @PostMapping("/log")
    public ResponseEntity<?> log(@RequestBody Account account,HttpServletResponse response) {
        //if the json is empty
        if(account == null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        //if json is not empty it will procced to the log service and will check if user is authenticate and send a cookie with jwt
        String jwt = accountService.log(account);
        if(jwt !=null){
            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, "token=" + jwt + "; Path=/; HttpOnly; SameSite=None; Secure; Domain=localhost").body(new AuthDTO(Optional.of(jwt)));
        }
        
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
