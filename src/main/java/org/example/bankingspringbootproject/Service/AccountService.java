package org.example.bankingspringbootproject.Service;

import org.example.bankingspringbootproject.Model.Account;
import org.example.bankingspringbootproject.Dto.AccountDto;

import java.util.List;

public interface  AccountService {
    AccountDto CreateAccount(AccountDto accountDto);
    AccountDto getAccountById(Long id);
    AccountDto Deposit(Long id , double amount);
    AccountDto Withdraw(Long id , double amount);
    List<AccountDto> FindAllAccounts();
    List<AccountDto>CreateAccounts(List<AccountDto> accountDtos);
    AccountDto DeleteAccount(Long id);
}
