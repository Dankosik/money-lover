package ru.dankos.moneylover.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dankos.moneylover.domain.Operation;
import ru.dankos.moneylover.service.OperationService;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/operations")
@CrossOrigin
@AllArgsConstructor
public class OperationController {
    private final OperationService operationService;

    @GetMapping
    public ResponseEntity<List<Operation>> getAllOperations(@RequestParam(required = false) String firstDate,
                                                            @RequestParam(required = false) String lastDate) {
        if (firstDate != null && lastDate != null)
            return new ResponseEntity<>(operationService.getOperationsByDate(Date.valueOf(firstDate), Date.valueOf(lastDate)), HttpStatus.OK);
        return new ResponseEntity<>(operationService.getAllOperations(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Operation> getOperationById(@PathVariable Long id) {
        return new ResponseEntity<>(operationService.getOperationById(id), HttpStatus.OK);
    }

    @GetMapping("/income")
    public ResponseEntity<Long> getIncomeInOperations(@RequestParam(required = false) String firstDate,
                                                      @RequestParam(required = false) String lastDate) {
        if (firstDate != null && lastDate != null) {
            return new ResponseEntity<>(operationService.getIncomeInOperations(
                    operationService.getOperationsByDate(Date.valueOf(firstDate), Date.valueOf(lastDate))), HttpStatus.OK);
        }
        return new ResponseEntity<>(operationService.getIncomeInOperations(operationService.getAllOperations()), HttpStatus.OK);
    }

    @GetMapping("/expense")
    public ResponseEntity<Long> getExpenditureInOperations(@RequestParam(required = false) String firstDate,
                                                           @RequestParam(required = false) String lastDate) {
        if (firstDate != null && lastDate != null) {
            return new ResponseEntity<>(operationService.getExpenseInOperations(
                    operationService.getOperationsByDate(Date.valueOf(firstDate), Date.valueOf(lastDate))), HttpStatus.OK);
        }
        return new ResponseEntity<>(operationService.getExpenseInOperations(operationService.getAllOperations()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOperationById(@PathVariable("id") Long id) {
        operationService.deleteOperationById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Operation> addOperation(@Valid @RequestBody Operation operation) {
        return new ResponseEntity<>(operationService.saveOperationAndThenUpdateWalletBalance(operation), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Operation> updateOperation(@Valid @RequestBody Operation operation) {
        return ResponseEntity.ok(operationService.saveOperationAndThenUpdateWalletBalance(operation));
    }
}
