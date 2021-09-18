package ru.dankos.moneylover.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Date;

@Data
@Entity
@Table(name = "operations")
public class Operation {
    @Id
    private Long id;
    private Date date;
    @OneToOne
    private Wallet wallet;
    @OneToOne
    private Category category;
    private Long sum;
    private String note;
}
