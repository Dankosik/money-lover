package ru.dankos.moneylover.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dankos.moneylover.domain.Operation;

import java.sql.Date;
import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findOperationByDateBetween(Date date, Date date2);
}
