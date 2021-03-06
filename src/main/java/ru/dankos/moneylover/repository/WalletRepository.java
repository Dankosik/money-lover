package ru.dankos.moneylover.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dankos.moneylover.domain.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
