package com.ivarrace.gringotts.infrastructure.persistence;

import com.ivarrace.gringotts.domain.model.Group;

import java.util.List;
import java.util.Optional;

public interface GroupPersistencePort {

    Optional<Group> findById(String accountingId, String groupId);

    List<Group> findAll(String accountingId);

    Group save(String accountingId, Group group);

    void delete(String accountingId, String groupId);
}
