package com.ivarrace.gringotts.infrastructure.persistence;

import com.ivarrace.gringotts.domain.model.Category;
import com.ivarrace.gringotts.domain.model.Group;

import java.util.List;
import java.util.Optional;

public interface CategoryPersistencePort {

    Optional<Category> findById(String accountingId, String groupId, String categoryId);

    List<Category> findAll(String accountingId, String groupId);

    Category save(String accountingId, String groupId, Category category);

    void delete(String accountingId, String groupId, String categoryId);
}
