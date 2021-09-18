package ru.dankos.moneylover.service;

import ru.dankos.moneylover.domain.Wallet;

import java.util.List;

public interface WalletService {
    Wallet saveWallet(Wallet wallet);

    Wallet getWalletById(Long id);

    List<Wallet> getAllWallets();

    void deleteWalletById(Long id);

    List<Wallet> saveAllWallets(List<Wallet> wallets);
}
