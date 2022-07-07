package com.ivarrace.gringotts.infrastructure.persistence;

import com.ivarrace.gringotts.domain.model.Accounting;

import java.util.List;
import java.util.Optional;

public interface AccountingPersistencePort {

    Optional<Accounting> findByUserAndKey(String userId, String accountingKey);

    List<Accounting> findAll(String userId);

    Accounting save(Accounting accounting);

    void delete(Accounting accounting);

}
