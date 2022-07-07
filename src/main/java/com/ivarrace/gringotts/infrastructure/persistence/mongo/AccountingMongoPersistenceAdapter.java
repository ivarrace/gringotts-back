package com.ivarrace.gringotts.infrastructure.persistence.mongo;

import com.ivarrace.gringotts.domain.exception.ObjectNotFoundException;
import com.ivarrace.gringotts.domain.model.Accounting;
import com.ivarrace.gringotts.infrastructure.persistence.AccountingPersistencePort;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.AccountingEntity;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.AccountingRepository;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.mapper.AccountingMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AccountingMongoPersistenceAdapter implements AccountingPersistencePort {

    //@Autowired
    private final AccountingRepository accountingRepository;

    public AccountingMongoPersistenceAdapter (AccountingRepository accountingRepository){
        this.accountingRepository = accountingRepository;
    }

    @Override
    public Optional<Accounting> findByUserAndKey(String userId, String accountingKey) {
        Optional<AccountingEntity> accounting = accountingRepository.findByKey(userId, accountingKey);
        if(accounting.isPresent()){
            return Optional.of(AccountingMapper.entityToDomain(accounting.get()));
        }
        return Optional.empty();
    }

    @Override
    public List<Accounting> findAll(String userId) {
        return accountingRepository.findAll(userId).stream()
                .map(AccountingMapper::entityToDomain).collect(Collectors.toList());
    }

    @Override
    public Accounting save(Accounting accounting) {
        AccountingEntity newAccountingEntity = accountingRepository.save(AccountingMapper.domainToEntity(accounting));
        return AccountingMapper.entityToDomain(newAccountingEntity);
    }

    @Override
    public void delete(Accounting accounting) {
        accountingRepository.deleteById(accounting.getId());
    }
}
