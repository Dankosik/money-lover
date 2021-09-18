package ru.dankos.moneylover.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dankos.moneylover.domain.CategoryType;
import ru.dankos.moneylover.domain.Operation;
import ru.dankos.moneylover.domain.Wallet;
import ru.dankos.moneylover.exceptions.EntityNotFoundException;
import ru.dankos.moneylover.repository.OperationRepository;
import ru.dankos.moneylover.service.OperationService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class OperationServiceImpl implements OperationService {
    private final OperationRepository operationRepository;

    @Override
    public Operation saveOperationAndThenUpdateWalletBalance(Operation operation) {
        updateWalletBalance(operation);
        return operationRepository.save(operation);
    }

    @Override
    public Operation getOperationById(Long id) {
        boolean isOperationPresent = operationRepository.findById(id).isPresent();
        log.debug("Category is present: {}", isOperationPresent);
        if (isOperationPresent) {
            return operationRepository.findById(id).get();
        }
        throw new EntityNotFoundException("Category with id: " + id + " is not found");
    }

    @Override
    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

    @Override
    public void deleteOperationById(Long id) {
        boolean isOperationPresent = operationRepository.findById(id).isPresent();
        log.debug("Category is present: {}", isOperationPresent);
        if (isOperationPresent) {
            operationRepository.deleteById(getOperationById(id).getId());
        } else {
            throw new EntityNotFoundException("Category with id: " + id + " is not found");
        }
    }

    @Override
    public List<Operation> saveAllOperations(List<Operation> operations) {
        List<Operation> result = new ArrayList<>();
        operations.forEach(operation -> result.add(operationRepository.save(operation)));
        return result;
    }

    @Override
    public List<Operation> getOperationsByDate(Date first, Date second) {
        return operationRepository.findOperationByDateBetween(first, second);
    }

    @Override
    public Long getIncomeInOperations(List<Operation> operations) {
        return operations.stream()
                .filter(operation -> operation.getCategory().getType().equals(CategoryType.INCOME))
                .map(Operation::getSum)
                .mapToLong(Long::longValue)
                .sum();
    }

    @Override
    public Long getExpenseInOperations(List<Operation> operations) {
        return operations.stream()
                .filter(operation -> operation.getCategory().getType().equals(CategoryType.EXPENSE))
                .map(Operation::getSum)
                .mapToLong(Long::longValue)
                .sum();
    }

    private void updateWalletBalance(Operation operation) {
        Wallet wallet = operation.getWallet();
        Long walletBalance = wallet.getBalance();
        Long operationSum = operation.getSum();
        if (operation.getCategory().getType().equals(CategoryType.INCOME)) {
            wallet.setBalance(walletBalance + operationSum);
        } else {
            wallet.setBalance(walletBalance - operationSum);
        }
    }
}
