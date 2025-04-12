package org.example.bankingprojecttest.Service.Implt;

import org.example.bankingprojecttest.DTO.AccountDTO;
import org.example.bankingprojecttest.Mapper.AccountMapper;
import org.example.bankingprojecttest.Model.Account;
import org.example.bankingprojecttest.Repository.AccountRepository;
import org.example.bankingprojecttest.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImplt  implements AccountService {
    @Autowired
    private AccountRepository accountRepository;


    @Override
    public AccountDTO CreateAccount(AccountDTO accountDTO) {
        Account account = AccountMapper.mapToAccount(accountDTO);
        Account SavedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDTO(SavedAccount);
    }

    @Override
    public AccountDTO FindAccountByID(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        return AccountMapper.mapToAccountDTO(account);
    }


    @Override
    public AccountDTO Withdraw(Long id, Double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        if(account.getBalance() < amount){
            throw new RuntimeException("Insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
        return AccountMapper.mapToAccountDTO(account);
    }

    @Override
    public AccountDTO DeleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        accountRepository.deleteById(id);
        return AccountMapper.mapToAccountDTO(account);
    }

    @Override
    public List<AccountDTO> getALLAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(account -> AccountMapper.mapToAccountDTO(account)).collect(Collectors.toList());
    }

    @Override
    public AccountDTO Deposit(Long id, Double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance() + amount);
        return AccountMapper.mapToAccountDTO(account);
    }
}
