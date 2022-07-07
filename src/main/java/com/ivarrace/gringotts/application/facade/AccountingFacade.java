package com.ivarrace.gringotts.application.facade;

import com.ivarrace.gringotts.application.rest.dto.mapper.AccountingMapper;
import com.ivarrace.gringotts.application.rest.dto.request.AccountingRequest;
import com.ivarrace.gringotts.application.rest.dto.response.AccountingResponse;
import com.ivarrace.gringotts.domain.model.Accounting;
import com.ivarrace.gringotts.domain.service.AccountingService;
import com.ivarrace.gringotts.infrastructure.security.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountingFacade {

    private final AccountingService accountingService;

    public AccountingFacade(AccountingService accountingService){
        this.accountingService = accountingService;
    }

    public List<AccountingResponse> findAll() {
        return accountingService.findAll().stream().map(AccountingMapper::dtoToResponse).collect(Collectors.toList());
    }

    public AccountingResponse findByKey(String accountingKey) {
        return AccountingMapper.dtoToResponse(accountingService.findByKey(accountingKey));
    }

    public AccountingResponse create(AccountingRequest accountingRequest) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Accounting accounting = AccountingMapper.requestToDto(accountingRequest, user);
        return AccountingMapper.dtoToResponse(accountingService.create(accounting));
    }

    public ResponseEntity<?> modify(String accountingKey, AccountingRequest accountingRequest) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        accountingService.modify(accountingKey, AccountingMapper.requestToDto(accountingRequest, user));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> deleteById(String accountingKey) {
        accountingService.deleteById(accountingKey);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
