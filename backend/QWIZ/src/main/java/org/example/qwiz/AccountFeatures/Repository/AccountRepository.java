package org.example.qwiz.AccountFeatures.Repository;

import org.example.qwiz.AccountFeatures.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);
}
