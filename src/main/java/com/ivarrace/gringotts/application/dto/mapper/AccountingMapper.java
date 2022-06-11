package com.ivarrace.gringotts.application.dto.mapper;

import com.ivarrace.gringotts.application.dto.request.AccountingRequest;
import com.ivarrace.gringotts.application.dto.response.AccountingResponse;
import com.ivarrace.gringotts.application.dto.response.GroupResponse;
import com.ivarrace.gringotts.application.dto.response.ReportResponse;
import com.ivarrace.gringotts.application.dto.DtoUtils;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.Accounting;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.AccountingRole;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.GroupType;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.UserAccountingRole;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountingMapper extends AnnualSummaryGenerator {

    private final GroupMapper groupMapper;

    public AccountingMapper(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }

    public AccountingResponse toDto(Accounting accounting) {
        AccountingResponse request = new AccountingResponse();
        request.setId(accounting.getKey());
        request.setName(accounting.getName());
        request.setCreatedDate(accounting.getCreatedDate());
        request.setLastModified(accounting.getLastModified());
        List<GroupResponse> expenses = new ArrayList<>();
        List<GroupResponse> income = new ArrayList<>();
        accounting.getGroups().forEach(group -> {
            if (GroupType.EXPENSES.equals(group.getType())) {
                expenses.add(groupMapper.toDto(group));
            } else if (GroupType.INCOME.equals(group.getType())) {
                income.add(groupMapper.toDto(group));
            }
        });
        request.setExpenses(new ReportResponse(expenses));
        request.setIncome(new ReportResponse(income));
        generateAnnualSummary(request);
        return request;
    }

    public List<AccountingResponse> toDtoList(List<Accounting> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Accounting toNewEntity(AccountingRequest accounting) {
        Accounting request = new Accounting();
        request.setKey(DtoUtils.generateKey(accounting.getName()));
        request.setName(accounting.getName());
        request.setGroups(Collections.emptyList());
        UserAccountingRole owner = new UserAccountingRole();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        owner.setUserId(user.getId());
        owner.setRole(AccountingRole.OWNER);
        request.setUsers(Collections.singletonList(owner));
        return request;
    }

}
