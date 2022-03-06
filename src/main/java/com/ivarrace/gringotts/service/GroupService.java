package com.ivarrace.gringotts.service;

import com.ivarrace.gringotts.dto.mapper.GroupMapper;
import com.ivarrace.gringotts.dto.mapper.AccountingMapper;
import com.ivarrace.gringotts.dto.request.GroupRequest;
import com.ivarrace.gringotts.dto.response.GroupResponse;
import com.ivarrace.gringotts.dto.response.AccountingResponse;
import com.ivarrace.gringotts.repository.AccountingRepository;
import com.ivarrace.gringotts.repository.model.Accounting;
import com.ivarrace.gringotts.repository.model.Group;
import com.ivarrace.gringotts.repository.model.GroupType;
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
        return groupMapper.toDto(accountingUtils.findAccountingEntity(accountingId).getGroups().stream()
                .filter(group -> group.getType().equals(type)).collect(Collectors.toList()));
    }

    public GroupResponse findById(String accountingId, String groupId) {
        Accounting accounting = accountingUtils.findAccountingEntity(accountingId);
        return groupMapper.toDto(accountingUtils.findAccountingGroup(accounting, groupId));
    }

    public AccountingResponse create(String accountingId, GroupType groupType,
                                     GroupRequest groupRequest) {
        Accounting actual = accountingUtils.findAccountingEntity(accountingId);
        Group group = groupMapper.toNewEntity(groupType, groupRequest);
        actual.getGroups().add(group);
        return accountingMapper.toDto(accountingRepository.save(actual));
    }

    public AccountingResponse deleteById(String accountingId, String groupId) {
        Accounting accounting = accountingUtils.findAccountingEntity(accountingId);
        accountingUtils.findAccountingGroup(accounting, groupId);
        accounting.getGroups().removeIf(group -> groupId.equals(group.getId()));
        return accountingMapper.toDto(accountingRepository.save(accounting));
    }

    public AccountingResponse modify(String accountingId, String groupId, GroupRequest groupRequest) {
        Accounting accounting = accountingUtils.findAccountingEntity(accountingId);
        Group actualGroup = accountingUtils.findAccountingGroup(accounting, groupId);
        actualGroup.setName(groupRequest.getName());
        return accountingMapper.toDto(accountingRepository.save(accounting));
    }

}
