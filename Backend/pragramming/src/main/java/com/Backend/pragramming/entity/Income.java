package com.Backend.pragramming.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long incomeId;

    private String date;
    private String incomeSource;
    private Long incomeAmount;
    @ManyToOne
    @JoinColumn(name="finance_id")
    private Finance finance;


}
