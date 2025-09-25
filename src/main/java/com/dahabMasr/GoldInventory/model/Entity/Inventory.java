package com.dahabMasr.GoldInventory.model.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
 public class Inventory {

    public enum Type {
        GOLD,
        SILVER,
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String name;
    @Column(columnDefinition = "int default 0")
    private  Double amount;
    @Column(columnDefinition = "int default 0")
    private Integer reserved;

    private  Float weight;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "inventory")
    private List<TransactionDetail> details;

    @PrePersist
    protected void onCreate() {
        if (reserved == null) {
            reserved = 0;
        }
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }


    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
 }
