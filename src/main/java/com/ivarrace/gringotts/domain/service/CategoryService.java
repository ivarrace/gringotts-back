package com.ivarrace.gringotts.domain.service;

import com.ivarrace.gringotts.domain.model.Accounting;
import com.ivarrace.gringotts.domain.model.Category;
import com.ivarrace.gringotts.domain.model.Group;
import com.ivarrace.gringotts.domain.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final GroupService groupService;

    public CategoryService(GroupService groupService) {
        this.groupService = groupService;
    }

    public List<Category> findAllCategories(String accountingId, String groupId) {
        return groupService.findById(accountingId, groupId).getCategories();
    }

    public Category findCategoryById(String accountingId, String groupId, String categoryId) {
        return groupService.findById(accountingId, groupId)
                .getCategories().stream()
                .filter(categoryDto -> categoryId.equals(categoryDto.getId()))
                .findAny().orElseThrow(() -> new ObjectNotFoundException(categoryId));
    }

    public Category createCategory(String accountingId, String groupId, Category category) {
        Group group = groupService.findById(accountingId, groupId);
        group.getCategories().add(category);
        return category;
    }

    public void deleteCategoryById(String accountingId, String groupId, String categoryId) {
        Group group = groupService.findById(accountingId, groupId);
        Category toDelete = group.getCategories().stream()
                .filter(category -> categoryId.equals(category.getId()))
                .findAny().orElseThrow(() -> new ObjectNotFoundException(categoryId));
        group.getCategories().remove(toDelete);
        groupService.modify(accountingId, groupId, group);
    }

    public void modifyCategory(String accountingId, String groupId, String categoryId,
                                     Category category) {
        Group group = groupService.findById(accountingId, groupId);
        Category toModify = group.getCategories().stream()
                .filter(existingCategory -> categoryId.equals(existingCategory.getId()))
                .findAny().orElseThrow(() -> new ObjectNotFoundException(categoryId));
        group.getCategories().remove(toModify);
        group.getCategories().add(category);
        groupService.modify(accountingId, groupId, group);
    }

}
