package ru.dankos.moneylover.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dankos.moneylover.domain.Wallet;
import ru.dankos.moneylover.service.WalletService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/wallets")
@CrossOrigin
@AllArgsConstructor
public class WallerController {
    private final WalletService walletService;

    @GetMapping
    public ResponseEntity<List<Wallet>> getAllWallets() {
        return new ResponseEntity<>(walletService.getAllWallets(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wallet> getWalletById(@PathVariable Long id) {
        return new ResponseEntity<>(walletService.getWalletById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWalletById(@PathVariable("id") Long id) {
        walletService.deleteWalletById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Wallet> addWallet(@Valid @RequestBody Wallet wallet) {
        return new ResponseEntity<>(walletService.saveWallet(wallet), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Wallet> updateWallet(@Valid @RequestBody Wallet wallet) {
        return ResponseEntity.ok(walletService.saveWallet(wallet));
    }
}
