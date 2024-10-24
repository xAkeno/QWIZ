package org.example.qwiz.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.example.qwiz.Model.Account;
import org.example.qwiz.Model.Role;
import org.example.qwiz.Repository.AccountRepository;
import org.example.qwiz.Security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    private AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;
    private JwtGenerator jwtGenerator;
    //Im so confuse why the Account is in reverse like wtf so what i did is used the passwword to username
    @Autowired
    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder, JwtGenerator jwtGenerator) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }
    public Optional<Account> create(Account account) {
        //return empty if json is empty
        if(account == null){
            return Optional.empty();
        }
        //creating a new object send set the parameter and create a new object and save it
        Account newAccount = new Account();
        newAccount.setId(account.getId());
        newAccount.setEmail(account.getEmail());
        newAccount.setUsername(account.getUsername());
        newAccount.setPassword(passwordEncoder.encode(account.getPassword()));
        newAccount.setRole(Role.USER);
        return Optional.of(accountRepository.save(newAccount));
    }
    public Optional<String> log(Account account, HttpServletResponse response) {
        //check if the json is empty
        if(account == null){
            return Optional.empty();
        }
        //finding the username on database using the response username
        Optional<Account> acc = accountRepository.findByUsername(account.getUsername());
        //matching the response
        if(passwordEncoder.matches(account.getPassword(), acc.get().getPassword())){
            //authenticating the username and password and make it into Authentication
            Authentication auth = new UsernamePasswordAuthenticationToken(account.getUsername(),account.getPassword());
            //Then put the auth into the securityContext Holder
            SecurityContextHolder.getContext().setAuthentication(auth);
            //passing the auth and generate the jwt using the auth and pass this to the cookie
            String token = jwtGenerator.generateToken(auth);

            //creating a cookie that is httponly and send it along side token
            Cookie cookie = new Cookie("Cookie", token);
             cookie.setPath("/");
             cookie.setHttpOnly(true);
             cookie.setMaxAge(3600);
             cookie.setSecure(true);
             response.addCookie(cookie);
            return Optional.of(token);
        }
        //return empty if the password and user is not found
        return Optional.empty();
    }
}
