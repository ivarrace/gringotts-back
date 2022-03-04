package com.ivarrace.gringotts.repository;

import com.ivarrace.gringotts.repository.model.AccountingGroup;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountingGroupRepository extends MongoRepository<AccountingGroup, String> {

}