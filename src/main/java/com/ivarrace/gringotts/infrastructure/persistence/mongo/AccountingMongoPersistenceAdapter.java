package com.ivarrace.gringotts.infrastructure.persistence.mongo;

import com.ivarrace.gringotts.domain.dto.AccountingDto;
import com.ivarrace.gringotts.infrastructure.persistence.AccountingPersistencePort;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.Accounting;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.AccountingRepository;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.mapper.AccountingMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AccountingMongoPersistenceAdapter implements AccountingPersistencePort {

    @Autowired
    private AccountingRepository accountingRepository;

    @Override
    public Optional<AccountingDto> findByKey(String userId, String key) {
        Optional<Accounting> accounting = accountingRepository.findByKey(userId, key);
        if(accounting.isPresent()){
            return Optional.of(AccountingMapper.entityToDto(accounting.get()));
        }
        return Optional.empty();
    }

    @Override
    public List<AccountingDto> findAll(String userId) {
        return accountingRepository.findAll(userId).stream()
                .map(AccountingMapper::entityToDto).collect(Collectors.toList());
    }
}
