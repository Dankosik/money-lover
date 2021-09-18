package ru.dankos.moneylover.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Enumerated(EnumType.STRING)
    private CategoryType type;
    @ManyToOne
    @JsonIgnore
    private Category parent;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Category> subCategories;
}
