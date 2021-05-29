package ru.dankos.moneylover.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    private Long id;
    private String name;
    @ManyToOne
    private Category parent;
    @OneToMany(mappedBy = "parent")
    private List<Category> subCategories;
}
