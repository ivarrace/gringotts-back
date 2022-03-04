package com.ivarrace.gringotts.repository;

import com.ivarrace.gringotts.repository.model.Accounting;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountingRepository extends MongoRepository<Accounting, String> {

}