package com.ivarrace.gringotts.dto.mapper;

import com.ivarrace.gringotts.repository.model.GroupType;
import com.ivarrace.gringotts.dto.request.AccountingRequest;
import com.ivarrace.gringotts.dto.response.AccountingResponse;
import com.ivarrace.gringotts.repository.model.Accounting;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountingMapper {

    private final GroupMapper groupMapper;

    public AccountingMapper(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }

    public AccountingResponse toDto(Accounting accounting) {
        AccountingResponse request = new AccountingResponse();
        request.setId(accounting.getId());
        request.setName(accounting.getName());
        request.setCreatedDate(accounting.getCreatedDate());
        request.setLastModified(accounting.getLastModified());
        request.setExpenses(accounting.getGroups().stream()
                .filter(item -> GroupType.EXPENSES.equals(item.getType()))
                .map(groupMapper::toDto)
                .collect(Collectors.toList()));
        request.setIncome(accounting.getGroups().stream()
                .filter(item -> GroupType.INCOME.equals(item.getType()))
                .map(groupMapper::toDto)
                .collect(Collectors.toList()));
        return request;
    }

    public List<AccountingResponse> toDto(List<Accounting> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Accounting toNewEntity(AccountingRequest accounting) {
        Accounting request = new Accounting();
        request.setName(accounting.getName());
        request.setGroups(Collections.emptyList());
        return request;
    }

}
