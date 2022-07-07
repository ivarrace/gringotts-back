package com.ivarrace.gringotts.infrastructure.persistence.mongo;

import com.ivarrace.gringotts.domain.exception.ObjectNotFoundException;
import com.ivarrace.gringotts.domain.model.Accounting;
import com.ivarrace.gringotts.domain.model.Group;
import com.ivarrace.gringotts.infrastructure.persistence.AccountingPersistencePort;
import com.ivarrace.gringotts.infrastructure.persistence.GroupPersistencePort;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.AccountingEntity;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.AccountingRepository;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.mapper.AccountingMapper;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.mapper.GroupMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GroupMongoPersistenceAdapter implements GroupPersistencePort {

    @Autowired
    private AccountingRepository accountingRepository;

    @Override
    public Optional<Group> findById(String accountingId, String groupId) {
        return accountingRepository.findById(accountingId).stream()
                .findFirst().orElseThrow(()->new ObjectNotFoundException(accountingId))
                .getGroups().stream()
                .filter(groupEntity -> groupId.equals(groupEntity.getId())).findFirst()
                .map(GroupMapper::entityToDomain);
    }

    @Override
    public List<Group> findAll(String accountingId) {
        return accountingRepository.findById(accountingId).stream()
                .findFirst().orElseThrow(()->new ObjectNotFoundException(accountingId))
                .getGroups().stream()
                .map(GroupMapper::entityToDomain).collect(Collectors.toList());
    }

    @Override
    public Group save(String accountingId, Group group) {
        AccountingEntity accountingEntity = accountingRepository.findById(accountingId).stream()
                .findFirst().orElseThrow(()->new ObjectNotFoundException(accountingId));
        accountingEntity.getGroups().add(GroupMapper.domainToEntity(group));
        accountingRepository.save(accountingEntity);
        return group;
    }

    @Override
    public void delete(String accountingId, String groupId) {
        AccountingEntity accountingEntity = accountingRepository.findById(accountingId).stream()
                .findFirst().orElseThrow(()->new ObjectNotFoundException(accountingId));
        accountingEntity.getGroups().stream().filter(groupEntity -> !groupId.equals(groupEntity.getId()));
        accountingRepository.save(accountingEntity);
    }

}
