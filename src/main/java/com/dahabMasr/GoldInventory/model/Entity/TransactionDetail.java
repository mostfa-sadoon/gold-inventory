package com.dahabMasr.GoldInventory.model.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference
    @JoinColumn(
            name = "transaction_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "transaction_id") // gives your FK a nice name
    )
    private Transaction transaction;


    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "inventory_id",nullable = false,foreignKey = @ForeignKey(name = "inventory_id"))
    private  Inventory inventory;

    private Integer quantity;
    private double weight;
}
