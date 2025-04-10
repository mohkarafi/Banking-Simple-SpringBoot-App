package org.example.bankingspringbootproject.Service.Implt;

import org.example.bankingspringbootproject.Dto.AccountDto;
import org.example.bankingspringbootproject.Model.Account;
import org.example.bankingspringbootproject.Repository.AccountRepo;
import org.example.bankingspringbootproject.Service.AccountService;
import org.example.bankingspringbootproject.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImplt implements AccountService {
    AccountRepo accountRepo;
   @Autowired
   public AccountServiceImplt(AccountRepo accountRepo) {
       this.accountRepo = accountRepo;
   }

    @Override
    public AccountDto CreateAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account SavedAccount = accountRepo.save(account);
        return AccountMapper.mapToAccountDto(SavedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepo.findById(id).orElseThrow(() -> new RuntimeException("Account not found "));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto Deposit(Long id, double amount) {
        Account account = accountRepo.findById(id).orElseThrow(() -> new RuntimeException("Account not found "));
       account.setBalance(account.getBalance() + amount);
       Account SavedAccount = accountRepo.save(account);
       return AccountMapper.mapToAccountDto(SavedAccount);
    }

    @Override
    public AccountDto Withdraw(Long id, double amount) {
       Account account =  accountRepo.findById(id).orElseThrow(()-> new RuntimeException("Account not found "));
        if(account.getBalance() < amount){
            throw new RuntimeException("Insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
        Account SavedAccount = accountRepo.save(account);
        return AccountMapper.mapToAccountDto(SavedAccount);
    }

    @Override
    public List<AccountDto> FindAllAccounts() {
        List<Account> AllAccounts = accountRepo.findAll();
        return AllAccounts.stream().map((account) -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
            }

    @Override
    public List<AccountDto> CreateAccounts(List<AccountDto> accountDtos) {
        List<AccountDto> accounts = new ArrayList<>();
        for (AccountDto accountDto : accountDtos) {
            Account account = AccountMapper.mapToAccount(accountDto);
            Account SavedAccount = accountRepo.save(account);
            accounts.add(AccountMapper.mapToAccountDto(SavedAccount));
        }
        return accounts;
    }

    @Override
    public AccountDto DeleteAccount(Long id) {
        Account account = accountRepo.findById(id).orElseThrow(() -> new RuntimeException("Account not found "));
        accountRepo.deleteById(id);
        return AccountMapper.mapToAccountDto(account);
    }


}
