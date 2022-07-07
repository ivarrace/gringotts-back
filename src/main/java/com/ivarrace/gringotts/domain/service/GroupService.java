package com.ivarrace.gringotts.domain.service;

import com.ivarrace.gringotts.domain.model.Accounting;
import com.ivarrace.gringotts.domain.model.Group;
import com.ivarrace.gringotts.domain.model.GroupType;
import com.ivarrace.gringotts.domain.exception.ObjectNotFoundException;
import com.ivarrace.gringotts.infrastructure.persistence.GroupPersistencePort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {

    private final AccountingService accountingService;
    private final GroupPersistencePort groupPersistencePort;

    public GroupService(AccountingService accountingService, GroupPersistencePort groupPersistencePort) {
        this.accountingService = accountingService;
        this.groupPersistencePort = groupPersistencePort;
    }

    public List<Group> findAllByType(String accountingKey, GroupType type) {
        return accountingService.findByKey(accountingKey).getGroups().stream().filter(group -> group.getType().equals(type)).collect(Collectors.toList());
    }

    public Group findById(String accountingKey, String groupId) {
        return groupPersistencePort.findById(accountingKey, groupId).orElseThrow(() -> new ObjectNotFoundException(groupId));
    }

    public Group create(String accountingKey, GroupType groupType, Group group) {
        group.setType(groupType);
        return groupPersistencePort.save(accountingKey, group);
    }

    public void deleteById(String accountingKey, String groupId) {
        groupPersistencePort.delete(accountingKey, groupId);
    }

    public void modify(String accountingKey, String groupId, Group group) {
        Accounting accounting = accountingService.findByKey(accountingKey);
        accounting.getGroups().stream().map(groupDto -> !groupId.equals(groupDto.getId()));
        accounting.getGroups().add(group);

        accountingService.modify(accountingKey, accounting);
    }

}
