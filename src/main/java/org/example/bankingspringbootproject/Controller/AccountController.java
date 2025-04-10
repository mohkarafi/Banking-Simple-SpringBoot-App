package org.example.bankingspringbootproject.Controller;
import org.example.bankingspringbootproject.Dto.AccountDto;
import org.example.bankingspringbootproject.Service.AccountService;
import org.example.bankingspringbootproject.mapper.AccountMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
   AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    // a travers @RequestBody on converti le file json vers objet.java , et sans cette annotation je recois rien dans la class AccounrDto .

    // add Acount Rest API
@PostMapping("/save")
  public ResponseEntity<AccountDto> AddAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.CreateAccount(accountDto) , HttpStatus.CREATED);
  }

  @PutMapping("/{id}/koko")
  public ResponseEntity<AccountDto>WithdrawAccount(@PathVariable Long id , @RequestBody Map<String , Double> request){
        AccountDto accountDto = accountService.Withdraw(id ,request.get("amount"));
        return  ResponseEntity.ok(accountDto);
    }

  @GetMapping("/{id}")
    public ResponseEntity<AccountDto> GetAccountById(@PathVariable Long id) {
        AccountDto accountDto = accountService.getAccountById(id);
        return   ResponseEntity.ok(accountDto);
  }

  @PutMapping("/{id}/deposit")
  public ResponseEntity<AccountDto> depositAccount(@PathVariable Long id , @RequestBody Map<String , Double> request) {
      AccountDto accountDto =   accountService.Deposit(id , request.get("amount"));
      return ResponseEntity.ok(accountDto);
  }
@GetMapping
  public ResponseEntity <List<AccountDto>> getAllAccounts(){
   List<AccountDto> accounts =  accountService.FindAllAccounts();
   return ResponseEntity.ok(accounts);
  }
  @PostMapping("/saved")
  public ResponseEntity<List<AccountDto>>CreateAccounts( @RequestBody  List<AccountDto> accounts){
        List<AccountDto> accountDtos = new ArrayList<>();
        accountService.CreateAccounts(accountDtos);
        return ResponseEntity.ok(accountDtos);
  }
  @DeleteMapping("/{id}/delete")
  public ResponseEntity<String>DeleteAccount(@PathVariable Long id){
        accountService.DeleteAccount(id);
        return ResponseEntity.ok("Account deleting successfully ");
  }
}
