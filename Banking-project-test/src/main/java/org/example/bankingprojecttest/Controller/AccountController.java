package org.example.bankingprojecttest.Controller;

import org.example.bankingprojecttest.DTO.AccountDTO;
import org.example.bankingprojecttest.Model.Account;
import org.example.bankingprojecttest.Service.AccountService;
import org.example.bankingprojecttest.Service.Implt.AccountServiceImplt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/accountts")
public class AccountController {
    @Autowired
    AccountServiceImplt accountService;

     @PostMapping("/save")
    public ResponseEntity<AccountDTO> createAccount(AccountDTO accountDTO) {
        return new ResponseEntity<>(accountService.CreateAccount(accountDTO), HttpStatus.CREATED);
    }

    @PutMapping("{id}/deposit")
    public ResponseEntity<AccountDTO> DepositToAccount(@PathVariable Long id  , Map<String , Double> request) {
      AccountDTO accountDTO =   accountService.Deposit(id, request.get("amount") );
        return ResponseEntity.ok(accountDTO);
    }

    @PutMapping("{id}/withdraw")
    public ResponseEntity<AccountDTO> WithdrawFromAccount(@PathVariable Long id  , Map<String , Double> request) {
        AccountDTO accountDTO =   accountService.Withdraw(id, request.get("amount") );
        return ResponseEntity.ok(accountDTO);
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDTO>> GetAllAccounts(){
        List<AccountDTO> accounts = accountService.getALLAccounts();
        return  ResponseEntity.ok(accounts);
    }

    @GetMapping
    public ResponseEntity<AccountDTO> GetAccountById(@PathVariable Long id) {
      AccountDTO accountDTO =   accountService.FindAccountByID(id);
      return ResponseEntity.ok(accountDTO);
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<AccountDTO> DeleteAccount(  @PathVariable  Long id){
        AccountDTO accountDTO =   accountService.DeleteAccount(id);
        return ResponseEntity.ok(accountDTO);
    }
}
