package com.ivarrace.gringotts.infrastructure.persistence;

import com.ivarrace.gringotts.domain.dto.AccountingDto;

import java.util.List;
import java.util.Optional;

public interface AccountingPersistencePort {

    Optional<AccountingDto> findByKey(String userId, String key);

    List<AccountingDto> findAll(String userId);
}
