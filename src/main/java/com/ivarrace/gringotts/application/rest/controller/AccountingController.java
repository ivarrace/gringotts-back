package com.ivarrace.gringotts.application.controller;

import com.ivarrace.gringotts.application.dto.request.AccountingRequest;
import com.ivarrace.gringotts.application.dto.response.AccountingResponse;
import com.ivarrace.gringotts.domain.service.AccountingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/accounting")
public class AccountingController {

    private final AccountingService accountingService;

    public AccountingController(AccountingService accountingService){
        this.accountingService = accountingService;
    }

    @GetMapping("/")
    public List<AccountingResponse> findAccounting(){
        return accountingService.findAll();
    }

    @GetMapping("/{accountingId}")
    public AccountingResponse findAccountingById(@PathVariable String accountingId,
                                                 @RequestParam Optional<Integer> year){
        return accountingService.findByKey(accountingId);
    }

    @PostMapping("/")
    public AccountingResponse createAccounting(@RequestBody AccountingRequest accounting){
        return accountingService.create(accounting);
    }

    @PutMapping("/{accountingId}")
    public ResponseEntity<String> modifyAccounting(@PathVariable String accountingId, @RequestBody AccountingRequest accounting){
        accountingService.modify(accountingId, accounting);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{accountingId}")
    public void deleteAccounting(@PathVariable String accountingId){
        accountingService.deleteById(accountingId);
    }

}
