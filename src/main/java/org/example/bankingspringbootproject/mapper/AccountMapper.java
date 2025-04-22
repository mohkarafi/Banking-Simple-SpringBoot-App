package org.example.bankingspringbootproject.mapper;

import org.example.bankingspringbootproject.Dto.AccountDto;
import org.example.bankingspringbootproject.Model.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto) {
        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance());
        return account;
    }
    public static AccountDto mapToAccountDto(Account account) {
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountOwner(),
                account.getBalance()
        );
        return accountDto;
    }
}
