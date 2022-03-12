package com.ivarrace.gringotts.repository;

import com.ivarrace.gringotts.repository.model.Accounting;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AccountingRepository extends MongoRepository<Accounting, String> {

    Optional<Accounting> findByKey(String key);
}