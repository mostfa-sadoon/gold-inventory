package com.dahabMasr.GoldInventory.model.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "transactions")
public class Transaction {

    public  enum Status{
        PENDDING,
        COMPLETED,
        CANCELD
    }

    public enum Type{
        buying,
        sale
    }
    @Column(columnDefinition = "float default 0.0")
    private Float pricing;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    private  Double amount;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if(status==null){
            status = Status.valueOf("PENDDING");
        }

    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "transaction")
    @JsonManagedReference
    private   List<TransactionDetail> detailes;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(
            name = "customer_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "customer_id") // gives your FK a nice name
    )
    private Customer customer;
}
