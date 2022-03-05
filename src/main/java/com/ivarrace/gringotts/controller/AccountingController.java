package com.ivarrace.gringotts.controller;

import com.ivarrace.gringotts.dto.request.AccountingRequest;
import com.ivarrace.gringotts.dto.response.AccountingResponse;
import com.ivarrace.gringotts.service.AccountingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/accounting")
public class AccountingController {

    private final AccountingService accountingService;

    public AccountingController(AccountingService accountingService){
        this.accountingService = accountingService;
    }

    @GetMapping("/")
    public List<AccountingResponse> findAll(){
        return accountingService.findAll();
    }

    @GetMapping("/{accountingId}")
    public AccountingResponse findById(@PathVariable String accountingId){
        return accountingService.findById(accountingId);
    }

    @PostMapping("/")
    public AccountingResponse create(@RequestBody AccountingRequest accounting){
        return accountingService.create(accounting);
    }

    @PutMapping("/{accountingId}")
    public AccountingResponse put(@PathVariable String accountingId, @RequestBody AccountingRequest accounting){
        return accountingService.modify(accountingId, accounting);
    }

    @DeleteMapping("/{accountingId}")
    public void delete(@PathVariable String accountingId){
        accountingService.deleteById(accountingId);
    }

}
