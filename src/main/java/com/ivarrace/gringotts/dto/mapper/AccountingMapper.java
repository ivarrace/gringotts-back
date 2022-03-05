package com.ivarrace.gringotts.dto.mapper;

import com.ivarrace.gringotts.dto.request.AccountingRequest;
import com.ivarrace.gringotts.dto.response.AccountingResponse;
import com.ivarrace.gringotts.repository.model.Accounting;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountingMapper {

    public AccountingResponse toDto(Accounting accounting){
        AccountingResponse request = new AccountingResponse();
        request.setId(accounting.getId());
        request.setName(accounting.getName());
        request.setCreatedDate(accounting.getCreatedDate());
        request.setLastModified(accounting.getLastModified());
        request.setExpenses(accounting.getExpenses());
        request.setIncome(accounting.getIncome());
        return request;
    }

    public List<AccountingResponse> toDto(List<Accounting> list){
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Accounting toNewEntity(AccountingRequest accounting){
        Accounting request = new Accounting();
        request.setName(accounting.getName());
        request.setExpenses(Collections.emptyList());
        request.setIncome(Collections.emptyList());
        return request;
    }
}
