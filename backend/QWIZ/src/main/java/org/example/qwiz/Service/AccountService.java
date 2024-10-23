package org.example.qwiz.Service;

import org.example.qwiz.Model.Account;
import org.example.qwiz.Model.Role;
import org.example.qwiz.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    private AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public Optional<Account> create(Account account) {
        if(account == null){
            return Optional.empty();
        }
        Account newAccount = new Account();
        newAccount.setId(account.getId());
        newAccount.setEmail(account.getEmail());
        newAccount.setUsername(account.getUsername());
        newAccount.setPassword(passwordEncoder.encode(account.getPassword()));
        newAccount.setRole(Role.USER);
        return Optional.of(accountRepository.save(newAccount));
    }
}
