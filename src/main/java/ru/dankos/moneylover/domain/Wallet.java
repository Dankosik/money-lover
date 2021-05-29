package ru.dankos.moneylover.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Currency;

@Data
@Entity
@Table(name = "wallets")
public class Wallet {
    @Id
    private Long id;
    private String name;
    private Long balance;
    private Currency currency;
}
