package com.dahabMasr.GoldInventory.model.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction_detailes")
public class TransactionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne
    @JoinColumn(
            name = "transaction_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "transaction_id") // gives your FK a nice name
    )
    private Transaction transaction;


    @ManyToOne
    @JoinColumn(name = "inventory_id",nullable = false,foreignKey = @ForeignKey(name = "inventory_id"))
    private  Inventory inventory;

    private double amount;
    private double count;
}
