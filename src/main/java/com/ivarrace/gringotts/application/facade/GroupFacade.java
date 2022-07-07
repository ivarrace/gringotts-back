package com.ivarrace.gringotts.application.facade;

import com.ivarrace.gringotts.application.rest.dto.mapper.AccountingMapper;
import com.ivarrace.gringotts.application.rest.dto.mapper.GroupMapper;
import com.ivarrace.gringotts.application.rest.dto.request.AccountingRequest;
import com.ivarrace.gringotts.application.rest.dto.request.GroupRequest;
import com.ivarrace.gringotts.application.rest.dto.response.AccountingResponse;
import com.ivarrace.gringotts.application.rest.dto.response.GroupResponse;
import com.ivarrace.gringotts.domain.model.Accounting;
import com.ivarrace.gringotts.domain.model.GroupType;
import com.ivarrace.gringotts.domain.service.AccountingService;
import com.ivarrace.gringotts.domain.service.GroupService;
import com.ivarrace.gringotts.infrastructure.security.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupFacade {

    private final AccountingService accountingService;
    private final GroupService groupService;

    public GroupFacade(AccountingService accountingService,
                       GroupService groupService){
        this.accountingService = accountingService;
        this.groupService = groupService;
    }

    public List<GroupResponse> findAll(String accountingId, GroupType groupType) {
        return groupService.findAllByType(accountingId, groupType).stream().map(GroupMapper::dtoToResponse).collect(Collectors.toList());
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

    public ResponseEntity<?> deleteById(String accountingKey, String groupId) {
        groupService.deleteById(accountingKey, groupId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public GroupResponse findById(String accountingId, String groupId) {
        return GroupMapper.dtoToResponse(groupService.findById(accountingId, groupId));
    }

    public AccountingResponse create(String accountingId, GroupType groupType, GroupRequest request) {
        groupService.create(accountingId, groupType, GroupMapper.requestToDto(request));
        return AccountingMapper.dtoToResponse(accountingService.findByKey(accountingId));
    }

    public AccountingResponse modify(String accountingId, String groupId, GroupRequest request) {
        groupService.modify(accountingId, groupId, GroupMapper.requestToDto(request));
        return AccountingMapper.dtoToResponse(accountingService.findByKey(accountingId));
    }
}
