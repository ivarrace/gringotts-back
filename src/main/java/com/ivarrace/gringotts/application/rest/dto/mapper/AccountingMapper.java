package com.ivarrace.gringotts.application.rest.dto.mapper;

import com.ivarrace.gringotts.application.rest.dto.DtoUtils;
import com.ivarrace.gringotts.application.rest.dto.request.AccountingRequest;
import com.ivarrace.gringotts.application.rest.dto.response.AccountingResponse;
import com.ivarrace.gringotts.application.rest.dto.response.ReportResponse;
import com.ivarrace.gringotts.domain.model.Accounting;
import com.ivarrace.gringotts.domain.model.AccountingRole;
import com.ivarrace.gringotts.domain.model.GroupType;
import com.ivarrace.gringotts.domain.model.UserAccountingRole;
import com.ivarrace.gringotts.infrastructure.security.dto.User;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

public class AccountingMapper {

    private AccountingMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static AccountingResponse dtoToResponse(Accounting dto) {
        AccountingResponse response = new AccountingResponse();
        response.setId(dto.getId());
        response.setKey(dto.getKey());
        response.setCreatedDate(dto.getCreatedDate());
        response.setLastModified(dto.getLastModified());
        response.setName(dto.getName());
        response.setExpenses(Optional.of(new ReportResponse(dto.getGroups().stream().filter(group -> GroupType.EXPENSES.equals(group.getType())).map(GroupMapper::dtoToResponse).collect(Collectors.toList()))));
        response.setIncome(Optional.of(new ReportResponse(dto.getGroups().stream().filter(group -> GroupType.INCOME.equals(group.getType())).map(GroupMapper::dtoToResponse).collect(Collectors.toList()))));
        response.setSavings(AnnualSummaryGenerator.generateSavingsSummary(response));
        return response;
    }

    public static Accounting requestToDto(AccountingRequest request, User user) {
        Accounting dto = new Accounting();
        dto.setKey(DtoUtils.generateKey(request.getName()));
        dto.setName(request.getName());
        dto.setUsers(Collections.singletonList(new UserAccountingRole(user.getId(), AccountingRole.OWNER)));
        dto.setGroups(Collections.emptyList());
        return dto;
    }
}
