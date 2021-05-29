package ru.dankos.moneylover.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dankos.moneylover.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
