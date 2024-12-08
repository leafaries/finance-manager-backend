package com.leafaries.financemanagerbackend.category;

import com.leafaries.financemanagerbackend.transaction.Transaction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a category of financial transactions.
 * Each category has a unique name and can be associated with multiple transactions.
 */
@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {

    /**
     * The unique identifier for the category.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the category. This must be unique and cannot be null.
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * The list of transactions associated with this category.
     * The transactions are mapped by the 'category' field in the Transaction entity,
     * and any changes to them will cascade to the associated transactions.
     */
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();

}
