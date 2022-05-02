package com.ivarrace.gringotts.repository;

import com.ivarrace.gringotts.repository.model.Accounting;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountingRepository extends MongoRepository<Accounting, String> {

    @Query(value = "{ 'users.userId': ?0, 'key' : ?1 }")//, fields = "{ 'users.userId' : 1 }")
    Optional<Accounting> findByKey(String userId, String key);

    @Query(value ="{ 'users.userId': ?0 }")
    List<Accounting> findAll(String userId);
}