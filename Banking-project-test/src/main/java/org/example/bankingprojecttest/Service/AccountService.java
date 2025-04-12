package org.example.bankingprojecttest.Service;

import org.example.bankingprojecttest.DTO.AccountDTO;

import java.util.List;

public interface AccountService {
    AccountDTO CreateAccount(AccountDTO accountDTO);
    AccountDTO FindAccountByID(Long id);
    AccountDTO Deposit(Long id , Double amount);
    AccountDTO Withdraw(Long id , Double amount);
    AccountDTO DeleteAccount(Long id);
    List<AccountDTO> getALLAccounts();

}
