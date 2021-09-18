package ru.dankos.moneylover.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dankos.moneylover.domain.Category;
import ru.dankos.moneylover.exceptions.EntityNotFoundException;
import ru.dankos.moneylover.repository.CategoryRepository;
import ru.dankos.moneylover.service.CategoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        boolean isCategoryPresent = categoryRepository.findById(id).isPresent();
        log.debug("Category is present: {}", isCategoryPresent);
        if (isCategoryPresent) {
            return categoryRepository.findById(id).get();
        }
        throw new EntityNotFoundException("Category with id: " + id + " is not found");
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteCategoryById(Long id) {
        boolean isCategoryPresent = categoryRepository.findById(id).isPresent();
        log.debug("Category is present: {}", isCategoryPresent);
        if (isCategoryPresent) {
            categoryRepository.deleteById(getCategoryById(id).getId());
        } else {
            throw new EntityNotFoundException("Category with id: " + id + " is not found");
        }
    }

    @Override
    public List<Category> saveAllCategories(List<Category> categories) {
        List<Category> result = new ArrayList<>();
        categories.forEach(category -> result.add(categoryRepository.save(category)));
        return result;
    }

    @Override
    public List<Category> getCategoriesWithNoParent() {
        List<Category> categories = categoryRepository.findAll();
        return categories
                .stream()
                .filter(category -> category.getParent() == null)
                .collect(Collectors.toList());
    }

    @Override
    public Category getCategoryByName(String name) {
        boolean isCategoryPresent = categoryRepository.findCategoryByName(name).isPresent();
        log.debug("Category is present: {}", isCategoryPresent);
        if (isCategoryPresent) {
            return categoryRepository.findCategoryByName(name).get();
        }
        throw new EntityNotFoundException("Category with name: " + name + " is not found");
    }
}
