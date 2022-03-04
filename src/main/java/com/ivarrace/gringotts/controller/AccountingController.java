package com.ivarrace.gringotts.controller;

import com.ivarrace.gringotts.repository.model.Accounting;
import com.ivarrace.gringotts.service.AccountingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/accounting")
public class AccountingController {

    @Autowired
    private AccountingService accountingService;

    @GetMapping("/")
    public List<Accounting> findAll(){
        return accountingService.findAll();
    }

    @GetMapping("/{accountingId}")
    public Accounting findById(@PathVariable String accountingId){
        return accountingService.findById(accountingId);
    }

    @PostMapping("/")
    public Accounting create(@RequestBody Accounting accounting){
        return accountingService.create(accounting);
    }

    @PutMapping("/{accountingId}")
    public Accounting put(@PathVariable String accountingId, @RequestBody Accounting accounting){
        return accountingService.modify(accountingId, accounting);
    }

    @DeleteMapping("/{accountingId}")
    public void delete(@PathVariable String accountingId){
        accountingService.deleteById(accountingId);
    }

}
