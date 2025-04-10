package org.example.bankingspringbootproject.Repository;

import org.example.bankingspringbootproject.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Long> {
}
