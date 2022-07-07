package com.ivarrace.gringotts.application.rest.controller;

import com.ivarrace.gringotts.application.facade.AccountingFacade;
import com.ivarrace.gringotts.application.rest.dto.request.AccountingRequest;
import com.ivarrace.gringotts.application.rest.dto.response.AccountingResponse;
import com.ivarrace.gringotts.infrastructure.persistence.GroupPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/accounting")
public class AccountingController {

    private final AccountingFacade accountingFacade;

    public AccountingController(AccountingFacade accountingFacade){
        this.accountingFacade = accountingFacade;
    }

    @GetMapping("/")
    public List<AccountingResponse> findAccounting(){
        return accountingFacade.findAll();
    }

    @GetMapping("/{accountingId}")
    public AccountingResponse findAccountingById(@PathVariable String accountingId){
        return accountingFacade.findByKey(accountingId);
    }

    @PostMapping("/")
    public AccountingResponse createAccounting(@RequestBody AccountingRequest accounting){
        return accountingFacade.create(accounting);
    }

    @PutMapping("/{accountingId}")
    public ResponseEntity<?> modifyAccounting(@PathVariable String accountingId, @RequestBody AccountingRequest accounting){
        return accountingFacade.modify(accountingId, accounting);
    }

    @DeleteMapping("/{accountingId}")
    public ResponseEntity<?> deleteAccounting(@PathVariable String accountingId){
        return accountingFacade.deleteById(accountingId);
    }

}
