package com.ivarrace.gringotts.domain.service;

import com.ivarrace.gringotts.application.dto.DtoUtils;
import com.ivarrace.gringotts.application.dto.mapper.GroupMapper;
import com.ivarrace.gringotts.application.dto.mapper.AccountingMapper;
import com.ivarrace.gringotts.application.dto.request.GroupRequest;
import com.ivarrace.gringotts.application.dto.response.GroupResponse;
import com.ivarrace.gringotts.application.dto.response.AccountingResponse;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.AccountingRepository;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.Accounting;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.Group;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.GroupType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {

    private final AccountingRepository accountingRepository;
    private final GroupMapper groupMapper;
    private final AccountingMapper accountingMapper;
    private final AccountingUtils accountingUtils;

    public GroupService(AccountingRepository accountingRepository,
                        GroupMapper groupMapper,
                        AccountingMapper accountingMapper,
                        AccountingUtils accountingUtils) {
        this.accountingRepository = accountingRepository;
        this.groupMapper = groupMapper;
        this.accountingMapper = accountingMapper;
        this.accountingUtils = accountingUtils;
    }

    public List<GroupResponse> findAllByType(String accountingId, GroupType type) {
        return groupMapper.toDtoList(accountingUtils.findAccountingEntityByKey(accountingId).getGroups().stream()
                .filter(group -> group.getType().equals(type)).collect(Collectors.toList()));
    }

    public GroupResponse findById(String accountingId, String groupId) {
        Accounting accounting = accountingUtils.findAccountingEntityByKey(accountingId);
        return groupMapper.toDto(accountingUtils.findAccountingGroup(accounting, groupId));
    }

    public AccountingResponse create(String accountingId, GroupType groupType,
                                     GroupRequest groupRequest) {
        Accounting actual = accountingUtils.findAccountingEntityByKey(accountingId);
        Group group = groupMapper.toNewEntity(groupType, groupRequest);
        actual.getGroups().add(group);
        return accountingMapper.toDto(accountingRepository.save(actual));
    }

    public AccountingResponse deleteById(String accountingId, String groupId) {
        Accounting accounting = accountingUtils.findAccountingEntityByKey(accountingId);
        accountingUtils.findAccountingGroup(accounting, groupId);
        accounting.getGroups().removeIf(group -> groupId.equals(group.getId()));
        return accountingMapper.toDto(accountingRepository.save(accounting));
    }

    public AccountingResponse modify(String accountingId, String groupId, GroupRequest groupRequest) {
        Accounting accounting = accountingUtils.findAccountingEntityByKey(accountingId);
        Group actualGroup = accountingUtils.findAccountingGroup(accounting, groupId);
        actualGroup.setId(DtoUtils.generateKey(groupRequest.getName()));
        actualGroup.setName(groupRequest.getName());
        return accountingMapper.toDto(accountingRepository.save(accounting));
    }

}
