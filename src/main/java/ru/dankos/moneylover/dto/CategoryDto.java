package ru.dankos.moneylover.dto;

import lombok.Data;
import ru.dankos.moneylover.domain.Category;
import ru.dankos.moneylover.domain.CategoryType;

import java.util.List;

@Data
public class CategoryDto {
    private Long id;
    private String name;
    private CategoryType type;
    private List<Category> subCategories;
}
