package com.ivarrace.gringotts.infrastructure.persistence.mongo;

import com.ivarrace.gringotts.domain.exception.ObjectNotFoundException;
import com.ivarrace.gringotts.domain.model.Category;
import com.ivarrace.gringotts.domain.model.Group;
import com.ivarrace.gringotts.infrastructure.persistence.CategoryPersistencePort;
import com.ivarrace.gringotts.infrastructure.persistence.GroupPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CategoryMongoPersistenceAdapter implements CategoryPersistencePort {

    @Autowired
    private GroupPersistencePort groupPersistencePort;

    @Override
    public Optional<Category> findById(String accountingId, String groupId, String categoryId) {
        Optional<Group> group = groupPersistencePort.findById(accountingId, groupId);
        if(group.isPresent()){
            Category category = group.get().getCategories().stream()
                    .filter(categoryEntity -> categoryEntity.getId().equals(categoryId))
                    .findFirst().orElseThrow(() -> new ObjectNotFoundException(categoryId));
            return Optional.of(category);
        }
        return Optional.empty();
    }

    @Override
    public List<Category> findAll(String accountingId, String groupId) {
        Optional<Group> group = groupPersistencePort.findById(accountingId, groupId);
        if(group.isPresent()){
            return group.get().getCategories();
        }
        return Collections.emptyList();
    }

    @Override
    public Category save(String accountingId, String groupId, Category category) {
        Optional<Group> group = groupPersistencePort.findById(accountingId, groupId);
        if(group.isPresent()){
            Group modify = group.get();
            modify.getCategories().add(category);
            groupPersistencePort.save(accountingId, modify);
            return category;
        }
        return null;
    }

    @Override
    public void delete(String accountingId, String groupId, String categoryId) {
        Optional<Group> group = groupPersistencePort.findById(accountingId, groupId);
        if(group.isPresent()){
            Group modify = group.get();
            List<Category> newCategories =
                    modify.getCategories().stream().filter(category -> !category.getId().equals(categoryId)).collect(Collectors.toList());
            modify.setCategories(newCategories);
            groupPersistencePort.save(accountingId, modify);
        }
    }
}
