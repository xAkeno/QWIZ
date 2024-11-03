package org.example.qwiz.AccountFeatures.Services;

import org.example.qwiz.AccountFeatures.Model.Account;
import org.example.qwiz.Security.Model.Role;
import org.example.qwiz.AccountFeatures.Repository.AccountRepository;
import org.example.qwiz.Security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String log(Account account) {
        //check if the json is empty
        if(account == null){
            return null;
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
            System.out.println(token);
            //creating a cookie that is httponly and send it along side token

            return token;
        }
        //return empty if the password and user is not found
        return null;
    }
}
