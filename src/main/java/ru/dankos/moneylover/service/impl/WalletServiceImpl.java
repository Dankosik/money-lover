package ru.dankos.moneylover.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dankos.moneylover.domain.Wallet;
import ru.dankos.moneylover.exceptions.EntityNotFoundException;
import ru.dankos.moneylover.repository.WalletRepository;
import ru.dankos.moneylover.service.WalletService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;

    @Override
    public Wallet saveWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet getWalletById(Long id) {
        boolean isWalletPresent = walletRepository.findById(id).isPresent();
        log.debug("Wallet is present: {}", isWalletPresent);
        if (isWalletPresent) {
            return walletRepository.findById(id).get();
        }
        throw new EntityNotFoundException("Wallet with id: " + id + " is not found");
    }

    @Override
    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }

    @Override
    public void deleteWalletById(Long id) {
        boolean isWalletPresent = walletRepository.findById(id).isPresent();
        log.debug("Wallet is present: {}", isWalletPresent);
        if (isWalletPresent) {
            walletRepository.deleteById(getWalletById(id).getId());
        } else {
            throw new EntityNotFoundException("Wallet with id: " + id + " is not found");
        }
    }

    @Override
    public List<Wallet> saveAllWallets(List<Wallet> wallets) {
        List<Wallet> result = new ArrayList<>();
        wallets.forEach(wallet -> result.add(walletRepository.save(wallet)));
        return result;
    }
}
