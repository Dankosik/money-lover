package ru.dankos.moneylover.service;

import ru.dankos.moneylover.domain.Category;

import java.util.List;

public interface CategoryService {
    Category saveCategory(Category category);

    Category getCategoryById(Long id);

    List<Category> getAllCategories();

    void deleteCategoryById(Long id);

    List<Category> saveAllCategories(List<Category> categories);

    List<Category> getCategoriesWithNoParent();

    Category getCategoryByName(String name);
}
