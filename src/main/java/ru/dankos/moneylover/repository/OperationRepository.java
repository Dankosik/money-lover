package ru.dankos.moneylover.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dankos.moneylover.domain.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
}
