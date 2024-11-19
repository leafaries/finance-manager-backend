package com.leafaries.financemanagerbackend.category;

import com.leafaries.financemanagerbackend.transaction.Transaction;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a category of financial transactions.
 * Each category has a unique name and can be associated with multiple transactions.
 */
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

    // Getters and setters

    /**
     * Gets the unique identifier of the category.
     *
     * @return the unique identifier of the category
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the category.
     *
     * @param id the unique identifier to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the category.
     *
     * @return the name of the category
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the category.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the list of transactions associated with the category.
     *
     * @return the list of transactions
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * Sets the list of transactions associated with the category.
     *
     * @param transactions the list of transactions to set
     */
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
