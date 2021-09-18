package ru.dankos.moneylover.service;

import ru.dankos.moneylover.domain.Operation;

import java.sql.Date;
import java.util.List;

public interface OperationService {
    Operation saveOperationAndThenUpdateWalletBalance(Operation operation);

    Operation getOperationById(Long id);

    List<Operation> getAllOperations();

    void deleteOperationById(Long id);

    List<Operation> saveAllOperations(List<Operation> operations);

    List<Operation> getOperationsByDate(Date first, Date second);

    Long getIncomeInOperations(List<Operation> operations);

    Long getExpenseInOperations(List<Operation> operations);
}
