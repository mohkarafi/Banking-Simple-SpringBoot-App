package org.example.bankingspringbootproject.Controller;
import org.example.bankingspringbootproject.Dto.AccountDto;
import org.example.bankingspringbootproject.Service.AccountService;
import org.example.bankingspringbootproject.mapper.AccountMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
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
@PostMapping
  public ResponseEntity<AccountDto> AddAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.CreateAccount(accountDto) , HttpStatus.CREATED);
  }

  @PutMapping("/{id}/with")
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
  @DeleteMapping("/{id}/delet")
  public ResponseEntity<String>DeleteAccount(@PathVariable Long id){

        accountService.DeleteAccount(id);
        return ResponseEntity.ok("Account deleting successfully ");
  }
    // cette est utiliser lorsque on veut handler un exception dans un seul class ou bien controller
    /**
     //   @ExceptionHandler(AccountNotFoundException.class)
     public ResponseEntity<String> AccountNotFoundHandler(AccountNotFoundException acc ){
     return new ResponseEntity<>(acc.getMessage() , HttpStatus.NOT_FOUND);
     */
        // ou bien
        // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(acc.getMessage());
    }
    /**
     ResponseEntity.ok()
     C’est une méthode pratique (statique) qui renvoie une réponse avec le statut HTTP 200 OK.
     Tu l’utilises quand tout s’est bien passé et tu veux retourner quelque chose simplement.



     ResponseEntity.status(...)
     Permet de spécifier un statut HTTP personnalisé (comme 201, 404, 500…).
     Tu peux ensuite ajouter le body() à la suite.
     Plus flexible que .ok().
       Exemple:
     ResponseEntity.status(HttpStatus.Created , "Account Added SuccesFuly") soit on ajoute . body("message") il devient =>
     ResponseEntity.status(httpStatus.Not_Found).body("User Introuvable");


     new ResponseEntity<>(...)
     C’est la façon manuelle complète de créer une ResponseEntity.
     Tu peux passer : le corps, les headers (optionnel), et le statut.
     Utile quand tu veux contrôler tout (headers, body, status).



     */

